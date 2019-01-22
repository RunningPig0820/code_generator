package com.moxie.taosha.code_generator.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName FileUtilsTest
 * @Description TODO
 * @Author zhangMin
 * @Date 2019/1/14 11:06
 * @Version 1.0
 **/
public class FileUtilsTest {
    @Test
    public void test_getPackageFileName(){
        String directoryPath = "/Users/zhangmin/Documents/Workspace/study/code_generator/src/main/java/com/moxie/taosha/code_generator_source_code";
        String packageFileName = "com.moxie.origin.processor.CheckUserReq";
        List<String> fileList = FileUtils.getPackageFileName(directoryPath,false,true);
        Assert.assertTrue(fileList.contains(packageFileName));
    }
}
