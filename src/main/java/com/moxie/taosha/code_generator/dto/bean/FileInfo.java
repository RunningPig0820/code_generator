package com.moxie.taosha.code_generator.dto.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName FileInfo
 * @Description 文件基础信息
 * @Author 淘沙
 * @Date 2018/8/23 20:33
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    /**
     * 包名
     */
    String packageName ;
    /**
     * 类名
     */
    String className ;
    /**
     * 作者
     */
    String author ;
    /**
     * 类名简介
     */
    String description ;
    /**
     * 创建日期
     */
    String date;
    /**
     * 产品名称
     */
    String product;
}
