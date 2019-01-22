package com.moxie.taosha.code_generator_kotlin.bean

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

/**
 * @ClassName RouteInfo
 * @Description 文件存放路径和对应数据模版路径
 * @Author zhangMin
 * @Date 2018/9/11 17:06
 * @Version 1.0
 **/

@Data
class RouteInfo{

        constructor()

        constructor(userDir: String?, templateUrl: String?, createFileUrl: String?, templateName: String?, createFileName: String?) {
                this.userDir = userDir
                this.templateUrl = templateUrl
                this.createFileUrl = createFileUrl
                this.templateName = templateName
                this.createFileName = createFileName
        }

        /**
         * 获取当前目录
         */
        internal var userDir: String? = null
        /**
         * 模版文件目录
         */
        internal var templateUrl: String? = null
        /**
         * 自动代码生成路径
         */
        internal var createFileUrl: String? = null
        /**
         * 对应模版名称
         */
        internal var templateName: String? = null
        /**
         * 生成文件名称
         */
        internal var createFileName: String? = null
}