package com.moxie.taosha.code_generator_annotation.annotations;

import com.moxie.taosha.code_generator_annotation.enums.ConversionPattern;
import com.moxie.taosha.code_generator_annotation.enums.ConversionType;

import java.lang.annotation.*;

/**
 * @ClassName Conversion
 * @Description
 * @Author zhangMin
 * @Date 2019/1/9 15:49
 * @Version 1.0
 **/
@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Conversion {

    /**
     * 平台传入节点数据对象,oct传给中间服务的数据对象
     */
    Class<?> sourceClass() default Object.class;

    /**
     * 数据转换方向
     */
    ConversionType conversionType() default ConversionType.SWITCH_TO_ME;

    /**
     * 转换成对应的数据对象
     * 只有在 conversionType = ConversionType.CONVERSION_TO_OTHER 有值
     */
    Class<?> conversionClass() default Object.class;

    /**
     *  对应的类型
     *  '#' 类中的对应的对象
     *  '.' 类中所对应的属性
     */
    String conversionProperty() default "";


    /**
     *  转换的方法 一些需要对转换类进行特殊处理的备注
     */
    ConversionPattern conversionPattern() default ConversionPattern.NORMAL;

    /**
     * 自定义处理方法
     * '#' 类中的对应的对象
     * '.' 类中所对应的属性
     */
    String method() default "";

    /**
     * 自定义处理方法的参数
     */
    String[] methodArgs() default {""} ;
}
