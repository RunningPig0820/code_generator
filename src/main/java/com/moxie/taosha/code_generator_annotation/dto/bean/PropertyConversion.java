package com.moxie.taosha.code_generator_annotation.dto.bean;

import com.moxie.taosha.code_generator_annotation.enums.ConversionPattern;
import lombok.Data;

/**
 * @ClassName Conversion
 * @Description TODO
 * @Author zhangMin
 * @Date 2019/1/15 14:20
 * @Version 1.0
 **/
@Data
public class PropertyConversion {
    /**
     * 需要转换的属性
     */
    String property;

    /**
     * 转换成该属性
     */
    String conversionProperty;


    /**
     * 自定义转换方式
     */
    ConversionPattern conversionPattern;

    /**
     * 自定义转换方式使用的方法名
     */
    String method;

    /**
     * 方法参数
     */
    String[] methodArgs;
}
