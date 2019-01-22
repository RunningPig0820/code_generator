package com.moxie.taosha.code_generator.controller;

import com.moxie.taosha.code_generator.dto.bean.*;
import com.moxie.taosha.code_generator.dto.req.*;
import com.moxie.taosha.code_generator.service.CodeGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName CodeGeneratorController
 * @Description 代码自动生成控制器
 * @Author 淘沙
 * @Date 2018/8/23 20:59
 * @Version 1.0
 **/
@RestController
public class CodeGeneratorController {

    @Autowired
    private CodeGeneratorService codeGeneratorService;

    @ApiOperation(value = "创建bean", position = 1)
    @RequestMapping(value = "/createBean", method = RequestMethod.POST)
    public void createBean(@RequestBody CreateBeanReq createBeanReq) {
        String userDir = System.getProperty("user.dir");

        RouteInfo routeInfo = createBeanReq.getRouteInfo();
        routeInfo.setUserDir(userDir);
        routeInfo.setTemplateUrl(userDir + "/src/main/resources");
        routeInfo.setTemplateName("bean.ftl");
        if(StringUtils.isEmpty(routeInfo.getCreateFileName())) {
            routeInfo.setCreateFileUrl(userDir + "/src/model");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        FileInfo fileInfo = createBeanReq.getFileInfo();
        fileInfo.setDate(date);

        List<Attribute> attrList = createBeanReq.getAttrList();


        try {
            codeGeneratorService.generator(routeInfo,fileInfo,attrList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
