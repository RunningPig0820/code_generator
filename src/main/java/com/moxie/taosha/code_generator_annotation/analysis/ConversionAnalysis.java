package com.moxie.taosha.code_generator_annotation.analysis;


import com.moxie.taosha.code_generator.util.FileUtils;
import com.moxie.taosha.code_generator_annotation.annotations.Conversion;
import com.moxie.taosha.code_generator_annotation.dto.bean.ConversionClass;
import com.moxie.taosha.code_generator_annotation.dto.bean.ConversionDefinition;
import com.moxie.taosha.code_generator_annotation.dto.bean.PropertyConversion;
import com.moxie.taosha.code_generator_annotation.dto.bean.SourceClass;
import com.moxie.taosha.code_generator_annotation.enums.ConversionType;
import com.moxie.taosha.code_generator_annotation.util.NameConversion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ConversionAnalysis
 * @Description 枚举解析器
 * @Author zhangMin
 * @Date 2019/1/11 15:56
 * @Version 1.0
 **/
@Service
public class ConversionAnalysis {

    @Value("sourceCode.bean.directoryPath")
    String directoryPath = "/Users/zhangmin/Documents/Workspace/study/code_generator/src/main/java/com/moxie/taosha/code_generator_source_code";

    String packageDirectory = "com.moxie.taosha.code_generator_source_code";

    /**
     * 解析自定义代码
     */
    public void conversionAnalysis(){
        //获取默认路径下的bean对象
        List<String> packageFileList = FileUtils.getPackageFileName(directoryPath,false,true);

        //注解转换成注解拼装类
        packageFileList.forEach(packageFile->{
            try {
                Class clazz = Class.forName(packageDirectory+FileUtils.SPOT+packageFile);

                Conversion clazzAnnotation = (Conversion) clazz.getAnnotation(Conversion.class);
                if(null == clazzAnnotation){
                    return;
                }

                List<PropertyConversion> propertyConversionList = new LinkedList<>();

                ConversionDefinition conversionDefinition = new ConversionDefinition();
                conversionDefinition.setSourceClassName(clazzAnnotation.sourceClass().getName());
                if(clazzAnnotation.conversionType().equals(ConversionType.CONVERSION_TO_OTHER)) {
                    conversionDefinition.setConversionClassName(clazzAnnotation.conversionClass().getName());
                }else{
                    conversionDefinition.setConversionClassName(clazz.getName());
                }

                Field[] fields = clazz.getDeclaredFields();
                for(Field field : fields){

                    String fieldName = field.getName();
                    String fieldType = field.getType().getSimpleName();

                    Conversion methodAnnotation = field.getAnnotation(Conversion.class);

                    PropertyConversion propertyConversion = new PropertyConversion();
                    propertyConversion.setProperty(methodAnnotation.conversionProperty());
                    propertyConversion.setConversionProperty("#"+fieldType+"."+fieldName);
                    propertyConversion.setConversionPattern(methodAnnotation.conversionPattern());
                    propertyConversion.setMethod(methodAnnotation.method());
                    propertyConversion.setMethodArgs(methodAnnotation.methodArgs());

                    if(clazzAnnotation.conversionType().equals(ConversionType.CONVERSION_TO_OTHER)) {
                        propertyConversion.setProperty("#"+fieldType+"."+fieldName);
                        propertyConversion.setConversionProperty(methodAnnotation.conversionProperty());
                    }else{
                        propertyConversion.setProperty(methodAnnotation.conversionProperty());
                        propertyConversion.setConversionProperty("#"+fieldType+"."+fieldName);
                    }

                    propertyConversionList.add(propertyConversion);

                }

                conversionDefinition.setPropertyConversionList(propertyConversionList);

                //数据类型转换
                generatorConverter(conversionDefinition);

            } catch (Exception e) {
                e.printStackTrace();
            }


        });


    }

    /**
     * 数据类型转换
     * @param conversionDefinition
     */
    private void generatorConverter(ConversionDefinition conversionDefinition) throws Exception {

        String sourceClassHumpName = conversionDefinition.getSourceClassName();
        SourceClass sourceClass = new SourceClass();
        sourceClass.setPackageName(sourceClassHumpName);
        sourceClassHumpName = getSimpleClass(sourceClassHumpName);
        sourceClass.setHumpName(sourceClassHumpName);
        sourceClass.setBigHumpName(NameConversion.humpNameToBigHumpName(sourceClassHumpName));

        String conversionClassHumpName = conversionDefinition.getConversionClassName();
        ConversionClass conversionClass = new ConversionClass();
        conversionClass.setPackageName(conversionClassHumpName);
        conversionClassHumpName = getSimpleClass(conversionClassHumpName);
        conversionClass.setHumpName(conversionClassHumpName);
        conversionClass.setBigHumpName(NameConversion.humpNameToBigHumpName(conversionClassHumpName));

        LinkedList<String> conversionPropertyList = new LinkedList<>();
        String newClass = String.format("%s %s = new %s()",sourceClass.getBigHumpName(),sourceClass.getHumpName(),sourceClass.getBigHumpName());
        conversionPropertyList.add(newClass);

        LinkedList<String> propertyList = new LinkedList<>();
        newClass = String.format("%s %s = new %s()",conversionClass.getBigHumpName(),conversionClass.getHumpName(),conversionClass.getBigHumpName());
        propertyList.add(newClass);

        List<PropertyConversion> propertyConversionList = conversionDefinition.getPropertyConversionList();
        for(PropertyConversion propertyConversion : propertyConversionList) {

            String property = getProperty(propertyConversion.getProperty());
            String propertyType = getType(propertyConversion.getProperty());

            String conversionProperty = getProperty(propertyConversion.getConversionProperty());
            String conversionPropertyType = getType(propertyConversion.getConversionProperty());

            //转换的属性

            conversionPropertyList.add("");

            //初始化转换对象
            String setProperty = String.format("%s set%s(%s)",conversionClass.getHumpName(),NameConversion.humpNameToBigHumpName(property),property);
            propertyList.add(setProperty);
        }

        conversionClass.setConversionPropertyList(conversionPropertyList);
        conversionClass.setPropertyList(propertyList);

    }

    private String getType(String property) throws Exception {
        int index = property.lastIndexOf("#");
        if(index == -1){
            throw new Exception("Conversion.conversionProperty 格式不正确 对应的类型需要 '#' '.' 分割");
        }
        property = property.substring(index+1,property.length());
        String[] conversion = property.split("\\.");
        return conversion[0];
    }

    private String getProperty(String property) throws Exception {
        int index = property.lastIndexOf("#");
        if(index == -1){
            throw new Exception("Conversion.conversionProperty 格式不正确 对应的类型需要 '#' '.' 分割");
        }
        property = property.substring(index+1,property.length());
        String[] conversion = property.split("\\.");
        return conversion[1];
    }

    private String getSimpleClass(String className){
        int index = className.lastIndexOf(".");
        className = className.substring(index+1,className.length());
        return className;
    }

    /**
     * 获取
     * @param args
     */

    public static void main(String[] args) {
        ConversionAnalysis conversionAnalysis = new ConversionAnalysis();
        conversionAnalysis.conversionAnalysis();
    }


}
