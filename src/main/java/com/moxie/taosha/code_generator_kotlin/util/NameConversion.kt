package com.moxie.taosha.code_generator_kotlin.util

/**
 * @ClassName NameConversion
 * @Description 名称改成驼峰
 * @Author zhangMin
 * @Date 2018/9/17 16:56
 * @Version 1.0
 **/

//大写下滑线转大驼峰
fun underLineToBigHumpName(underLineStr:String):String{
    val bigHumpName: StringBuffer = StringBuffer();

    if(underLineStr.length <= 1){
        return underLineStr
    }


    underLineStr.split("_").forEach(){
        bigHumpName.append( it.substring(0, 1).toUpperCase())
        bigHumpName.append(it.substring(1, it.length).toLowerCase())
    }

    return bigHumpName.toString();
}

//大写下滑线转驼峰
fun underLineToHumpName(underLineStr:String):String{
    val bigHumpName: StringBuffer = StringBuffer();

    if(underLineStr.length <= 1){
        return underLineStr
    }

    underLineStr.split("_").forEachIndexed(){index, it ->
        if(index == 0){
            bigHumpName.append( it.substring(0, 1).toLowerCase())
        }else{
            bigHumpName.append( it.substring(0, 1).toUpperCase())
        }

        bigHumpName.append(it.substring(1, it.length).toLowerCase())
    }

    return bigHumpName.toString();
}