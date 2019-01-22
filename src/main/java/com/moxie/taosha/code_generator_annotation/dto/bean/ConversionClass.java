package com.moxie.taosha.code_generator_annotation.dto.bean;

import lombok.Data;

import java.util.LinkedList;

/**
 * @ClassName ConversionClass
 * @Description 自动代码生成 -> 转换类基本类
 * @Author zhangMin
 * @Date 2019/1/16 17:13
 * @Version 1.0
 **/
@Data
public class ConversionClass{
    /**
     * 全类名
     */
    String packageName ;

    /**
     * 驼峰规范
     */
    String humpName;

    /**
     * 驼峰规范-首字母大写
     */
    String bigHumpName;

    /**
     * 蛇型规格
     */
    String underLineName;

    /**
     * 数据转换属性使用
     */
    LinkedList<String> conversionPropertyList;

    /**
     * 初始化属性使用
     */
    LinkedList<String> propertyList;
}
