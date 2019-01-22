package com.moxie.taosha.code_generator_annotation.dto.bean;

import lombok.Data;

import java.util.List;

/**
 * @ClassName SourceClass
 * @Description 自动代码生成 -> 平台传入类基本类
 * @Author zhangmin
 * @Date 2019/1/16 17:09
 * @Version 1.0
 **/
@Data
public class SourceClass {
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

}
