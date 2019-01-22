package com.moxie.taosha.code_generator.service;

import com.moxie.taosha.code_generator.dto.bean.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CodeGeneratorService
 * @Description bean 自动生成
 * @Author 淘沙
 * @Date 2018/8/23 21:38
 * @Version 1.0
 **/
@Service
public class CodeGeneratorService {

    public void generator(RouteInfo routeInfo, FileInfo fileInfo, List<Attribute> attrList) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(new File(routeInfo.getTemplateUrl()));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //获取对应模版
        Template temp = cfg.getTemplate(routeInfo.getTemplateName());

        //模版数据凭借
        Map<String, Object> root = new HashMap<>(16);

        //代码注释信息
        root.put("packageName",fileInfo.getPackageName());
        root.put("className", fileInfo.getClassName());
        root.put("author", fileInfo.getAuthor());
        root.put("description", fileInfo.getDescription());
        root.put("date", fileInfo.getDate());

        //bean 属性
        root.put("attrs", attrList);

        //新文件存储位置
        File dir = new File(routeInfo.getCreateFileUrl());
        if(!dir.exists()){
            dir.mkdirs();
        }

        //java文件的生成目录
        OutputStream fos = new FileOutputStream( new File(dir, routeInfo.getCreateFileName()));
        Writer out = new OutputStreamWriter(fos);

        //生成模版
        temp.process(root, out);

        fos.flush();
        fos.close();

        System.out.println("code generator success!");
    }

}
