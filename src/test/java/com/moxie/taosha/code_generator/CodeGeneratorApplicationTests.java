package com.moxie.taosha.code_generator;

import com.moxie.taosha.code_generator.dto.bean.Attribute;
import com.moxie.taosha.code_generator.controller.CodeGeneratorController;
import com.moxie.taosha.code_generator.util.FileUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class CodeGeneratorApplicationTests {

    @Test
    public void contextLoads() {
        List<String> fileList = FileUtils.getAllFile("/Users/zhangmin/Documents/Workspace/code_generator/src/model",false,false);
        fileList = fileList.stream().filter((String fileName)->
            fileName.endsWith(".java"))
                .collect(Collectors.toList());

        System.out.println(fileList);


        Class<CodeGeneratorController> clazz = CodeGeneratorController.class;
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            System.out.println(method.getName());
        }

        System.out.println();

        Class<Attribute> attributeClass = Attribute.class;
        Field[] fields = attributeClass.getDeclaredFields();
        for(Field field : fields){
            System.out.println(field.getGenericType() + "-" + field.getName());
        }
    }

}
