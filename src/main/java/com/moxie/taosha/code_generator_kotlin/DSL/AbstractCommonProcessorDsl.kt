package com.moxie.taosha.code_generator_kotlin.DSL


import com.moxie.taosha.code_generator_kotlin.bean.FileInfo
import com.moxie.taosha.code_generator_kotlin.bean.RouteInfo
import com.moxie.taosha.code_generator_kotlin.service.generator
import java.util.*

/**
 * @ClassName AbstractCommonProcessorDsl
 * @Description AbstractCommonProcessor 类的 DSL
 * @Author zhangMin
 * @Date 2018/9/10 20:47
 * @Version 1.0
 **/
fun main(args: Array<String>) {
    //使用样例
    AbstractCommonProcessorReq().codeAbstractCommonProcessor{
        routeInfo{
            templateUrl = "/Users/zhangmin/Documents/Workspace/study/code_generator/src/main/resources"
            templateName = "AbstractCommonProcessor.ftl"
            createFileUrl = "/Users/zhangmin/Documents/Workspace/moxie/orion-processor/qiqifenqi/src/main/java/com/moxie/origin/processor/qiqifenqi/processor/common"
            createFileName = "AbstractCommonProcessor.java"
        }
        fileInfo{
            packageName = "com.moxie.origin.processor.wanguanjie.processor.common"
            author = "zhangmin"
            description = "抽象类"
            date = Date().toString()
            product = "Qiqifenqi"
        }
    }
}

class AbstractCommonProcessorReq() {
    internal var routeInfo: RouteInfo? = null
    internal var fileInfo: FileInfo? = null
}

fun AbstractCommonProcessorReq.routeInfo(action: RouteInfo.()->Unit){
    val routeInfo = RouteInfo()
    routeInfo.action()
    this.routeInfo = RouteInfo().apply{action()};
}

fun AbstractCommonProcessorReq.fileInfo(action: FileInfo.()->Unit){
    val fileInfo = FileInfo()

    fileInfo.action()
    this.fileInfo = fileInfo
}

fun AbstractCommonProcessorReq.codeAbstractCommonProcessor(action: AbstractCommonProcessorReq.() -> Unit){
    action()

    val root = HashMap<String?, Any?>(16)

    //代码注释信息
    root["packageName"] = fileInfo?.packageName
    root["author"] = fileInfo?.author
    root["description"] = fileInfo?.description
    root["date"] = fileInfo?.date
    root["product"] = fileInfo?.product

    generator(routeInfo,root)

}


