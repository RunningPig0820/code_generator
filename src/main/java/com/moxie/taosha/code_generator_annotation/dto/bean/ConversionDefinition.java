package com.moxie.taosha.code_generator_annotation.dto.bean;

import com.moxie.taosha.code_generator_annotation.enums.ConversionType;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ConversionDefinition
 * @Description 注解拼装类
 * @Author zhangMin
 * @Date 2019/1/15 10:01
 * @Version 1.0
 **/
@Data
public class ConversionDefinition {

    /**
     * 平台传入类
     */
    String sourceClassName;

    /**
     * 转换的类
     */
    String conversionClassName;
    
    /**
     * 平台传入属性
     */
    List<PropertyConversion> propertyConversionList;


}
