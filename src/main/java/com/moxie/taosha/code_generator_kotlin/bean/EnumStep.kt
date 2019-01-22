package com.moxie.taosha.code_generator_kotlin.bean

import com.moxie.taosha.code_generator_kotlin.util.underLineToBigHumpName
import com.moxie.taosha.code_generator_kotlin.util.underLineToHumpName

/**
 * @ClassName EnumStep
 * @Description 流程节点中的接口信息
 * @Author zhangMin
 * @Date 2018/9/17 19:18
 * @Version 1.0
 **/
class EnumStep {

    constructor()

    constructor(name: String,url: String?, describe: String?, UNDERLINE_NAME: String?, BigHumpName: String?, humpName: String?) {
        this.name = name
        this.url = url
        this.describe = describe
        this.underLineName = UNDERLINE_NAME
        this.bigHumpName = underLineToBigHumpName(name)
        this.humpName = underLineToHumpName(name)
    }

    /**
     * 名称
     */
    var name: String = ""


    /**
     *
     */
    var url: String? = null

    /**
     *
     */
    var describe: String? = null

    /**
     *
     */
    var underLineName: String? = null

    /**
     *
     */
    var bigHumpName: String? = null

    /**
     *
     */
    var humpName: String? = null

    fun init(){
        this.underLineName = name.toUpperCase();
        this.bigHumpName = underLineToBigHumpName(name)
        this.humpName = underLineToHumpName(name)
    }

    override fun toString(): String {
        return "EnumStep(name='$name', url=$url, describe=$describe, underLineName=$underLineName, bigHumpName=$bigHumpName, humpName=$humpName)"
    }


}