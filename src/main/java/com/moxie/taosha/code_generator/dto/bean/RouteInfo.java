package com.moxie.taosha.code_generator.dto.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Routeinfo
 * @Description 文件路径信息
 * @Author zhangMin
 * @Date 2018/8/23 20:42
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteInfo {
    /**
     * 获取当前目录
     */
    String userDir ;
    /**
     * 模版文件目录
     */
    String templateUrl ;
    /**
     * 自动代码生成路径
     */
    String createFileUrl ;
    /**
     * 对应模版名称
     */
    String templateName ;
    /**
     * 生成文件名称
     */
    String createFileName ;
}
