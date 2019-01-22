package com.moxie.taosha.code_generator_kotlin.DSL

import com.moxie.taosha.code_generator_kotlin.bean.EnumStep
import com.moxie.taosha.code_generator_kotlin.bean.FileInfo
import com.moxie.taosha.code_generator_kotlin.bean.ProcessType
import com.moxie.taosha.code_generator_kotlin.bean.RouteInfo
import com.moxie.taosha.code_generator_kotlin.service.generator
import java.util.*


/**
 * @ClassName ProcessorDsl
 * @Description ProcessorDsl
 * @Author zhangMin
 * @Date 2018/9/17 16:29
 * @Version 1.0
 **/
fun main(args: Array<String>) {
    //使用样例
    ProcessorReq().codeAbstractCommonProcessor{
        routeInfo{
            templateUrl = "/Users/zhangmin/Documents/Workspace/study/code_generator/src/main/resources"
            templateName = "Process.ftl"
            createFileUrl = "/Users/zhangmin/Documents/Workspace/moxie/orion-processor/qiqifenqi/src/main/java/com/moxie/origin/processor/qiqifenqi/processor/"
            createFileName = "QiQiFenQiThirdRepaymentExecuteProcessor.java"
        }
        fileInfo{
            packageName = "com.moxie.origin.processor.qiqifenqi.processor"
            author = "zhangmin"
            description = "拉取审批结果"
            date = Date().toString()
            product = "QiQiFenQi"
        }
        processorType{
            this.processName = "THIRD_REPAYMENT_EXECUTE"
        }
        enumStepList{
            enumStep{
                this.name = "orderStatus"
                this.url = "/orderStatus"
                this.describe = "拉取审批结果"
            }
//            enumStep{
//                this.name = "APPROVE_RESULT_PULL"
//                this.url = "www.4399.com1"
//                this.describe = "USER_CHECK_A"
//            }
        }
    }
}

class ProcessorReq() {
    internal var routeInfo: RouteInfo? = null
    internal var fileInfo: FileInfo? = null
    internal var processType: ProcessType? = null
    internal var enumStepList :List<EnumStep>? = null
}

fun ProcessorReq.routeInfo(action: RouteInfo.()->Unit){
    val routeInfo = RouteInfo()
    routeInfo.action()
    this.routeInfo = routeInfo;
}

fun ProcessorReq.fileInfo(action: FileInfo.()->Unit){
    val fileInfo = FileInfo()
    fileInfo.action()
    this.fileInfo = fileInfo
}

fun ProcessorReq.processorType(action: ProcessType.()->Unit){
    val processType = ProcessType()
    processType.action()
    processType.init()
    this.processType = processType
}

fun ProcessorReq.enumStepList(action: ArrayList<EnumStep>.()->Unit){
    val enumStepList = ArrayList<EnumStep>()
    enumStepList.action()
    this.enumStepList = enumStepList
}

fun ArrayList<EnumStep>.enumStep(action: EnumStep.()->Unit){
    val enumStep = EnumStep()
    enumStep.action()
    enumStep.init()
    add(enumStep)
}

fun ProcessorReq.codeAbstractCommonProcessor(action: ProcessorReq.() -> Unit){
    action()

    val root = HashMap<String?, Any?>(16)

    //代码注释信息
    root["packageName"] = fileInfo?.packageName
    root["author"] = fileInfo?.author
    root["description"] = fileInfo?.description
    root["date"] = fileInfo?.date
    root["product"] = fileInfo?.product

    root["processorType"] = processType

    root["steps"] = enumStepList

    println("root : $root");

    generator(routeInfo,root)
}
