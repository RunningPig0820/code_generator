package com.moxie.taosha.code_generator_kotlin.service

import com.moxie.taosha.code_generator_kotlin.bean.RouteInfo
import freemarker.template.Configuration
import freemarker.template.TemplateException
import freemarker.template.TemplateExceptionHandler
import java.io.*
import java.util.*

/**
 * @ClassName CodeGeneratorService
 * @Description 自动生成类
 * @Author taosha
 * @Date 2018/9/10 20:06
 * @Version 1.0
 **/
@Throws(IOException::class, TemplateException::class)
fun generator(routeInfo: RouteInfo?, root: HashMap<String?, Any?>) {
    val cfg = Configuration(Configuration.VERSION_2_3_22)
    cfg.setDirectoryForTemplateLoading(File(routeInfo?.templateUrl))
    cfg.defaultEncoding = "UTF-8"
    cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER

    //获取对应模版
    val temp = cfg.getTemplate(routeInfo?.templateName)

    //新文件存储位置
    val dir = File(routeInfo?.createFileUrl)
    if (!dir.exists()) {
        dir.mkdirs()
    }

    //java文件的生成目录
    val fos = FileOutputStream(File(dir, routeInfo?.createFileName))
    val out = OutputStreamWriter(fos as OutputStream?)

    //生成模版
    temp.process(root, out)

    fos.flush()
    fos.close()

    println("code generator success!")
}