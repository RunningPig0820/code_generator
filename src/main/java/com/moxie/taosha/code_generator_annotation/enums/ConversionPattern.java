package com.moxie.taosha.code_generator_annotation.enums;

/**
 * @ClassName ConversionPattern
 * @Description 特殊的转换逻辑 使用策略模式
 * @Author zhangMin
 * @Date 2019/1/9 17:06
 * @Version 1.0
 **/
public enum ConversionPattern {
    /**
     * 正常
     */
    NORMAL,
    /**
     * 转大写
     */
    TO_UPPER_CASE,
    /**
     * 转小写
     */
    TO_LOWER_CASE,
    /**
     * 自定义
     * 方法名 方法参数
     */
    CONVERSION_PATTERN;
}
