package com.moxie.taosha.code_generator_kotlin.bean

import com.moxie.taosha.code_generator_kotlin.util.getProcess
import com.moxie.taosha.code_generator_kotlin.util.underLineToBigHumpName
import com.moxie.taosha.code_generator_kotlin.util.underLineToHumpName
import lombok.Data

/**
 * @ClassName Process
 * @Description 流程节点对应响应数据
 * @Author zhangMin
 * @Date 2018/9/11 19:11
 * @Version 1.0
 **/

@Data
class ProcessType{

    constructor()

    constructor(processName: String, processDescribe: String?, req: String?, resp: String?) {
        this.processName = processName
        this.processDescribe = processDescribe
        this.req = req
        this.resp = resp
        this.underLineName = processName
        this.bigHumpName = underLineToBigHumpName(processName)
        this.humpName = underLineToHumpName(processName)
    }

    /**
     * 流程节点名称
     */
    var processName: String = ""
    /**
     * 流程节点描述
     */
    var processDescribe: String? = null
    /**
     * 流程节点对应入参 数据类型名称
     */
    var req: String? = null
    /**
     * 流程节点对应出参 数据类型名称
     */
    var resp: String? = null


    var bigHumpName: String? = null
    var underLineName: String? = null
    var humpName: String? = null

    fun init(){
        this.underLineName = processName
        this.bigHumpName = underLineToBigHumpName(processName)
        this.humpName = underLineToHumpName(processName)

        val process = getProcess(processName)
        this.req = process.req
        this.resp = process.resp
        this.processDescribe = process.processDescribe
    }

    override fun toString(): String {
        return "ProcessType(processName='$processName', processDescribe=$processDescribe, req=$req, resp=$resp, bigHumpName=$bigHumpName, underLineName=$underLineName, humpName=$humpName)"
    }


}