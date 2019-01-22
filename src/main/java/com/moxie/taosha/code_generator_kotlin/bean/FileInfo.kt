package com.moxie.taosha.code_generator_kotlin.bean

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

/**
 * @ClassName FileInfo
 * @Description 文件相关信息
 * @Author zhangMin
 * @Date 2018/9/11 17:05
 * @Version 1.0
 **/

@Data
class FileInfo{

    constructor()

    constructor(packageName: String?, className: String?, author: String?, description: String?, date: String?, product: String?) {
        this.packageName = packageName
        this.className = className
        this.author = author
        this.description = description
        this.date = date
        this.product = product
    }

    /**
     * 包名
     */
    internal var packageName: String? = null
    /**
     * 类名
     */
    internal var className: String? = null
    /**
     * 作者
     */
    internal var author: String? = null
    /**
     * 类名简介
     */
    internal var description: String? = null
    /**
     * 创建日期
     */
    internal var date: String? = null
    /**
     * 产品名称
     */
    internal var product: String? = null
}