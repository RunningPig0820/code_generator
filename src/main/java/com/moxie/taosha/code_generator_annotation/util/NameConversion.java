package com.moxie.taosha.code_generator_annotation.util;

/**
 * @ClassName NameConversion
 * @Description 名称转换
 * @Author zhangMin
 * @Date 2019/1/17 11:43
 * @Version 1.0
 **/
public class NameConversion {

    public static String humpNameToBigHumpName(String humpName){
        if(Character.isUpperCase(humpName.charAt(0))) {
            return humpName;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(humpName.charAt(0))).append(humpName.substring(1)).toString();
        }
    }

}
