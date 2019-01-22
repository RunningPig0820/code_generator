package com.moxie.taosha.code_generator.dto.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Attribute
 * @Description Bean类型
 * @Author 淘沙
 * @Date 2018/8/23 19:35
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {
    /**
     * 是否不为空
     */
    String isNotNull;
    /**
     * bean 注释
     */
    String notes;
    /**
     * 字段类型
     */
    String type;
    /**
     * 字段名称
     */
    String name;
}
