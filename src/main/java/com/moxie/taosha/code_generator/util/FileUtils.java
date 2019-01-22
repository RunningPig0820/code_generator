package com.moxie.taosha.code_generator.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName FileUtils
 * @Description 文件处理接口
 * @Author 淘沙
 * @Date 2018/8/28 16:16
 * @Version 1.0
 **/
public class FileUtils {

    public final static CharSequence SPOT = ".";

    /**
     * 获取路径下的所有文件/文件夹
     *
     * @param directoryPath  需要遍历的文件夹路径
     * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
     * @return
     */
    public static List<String> getAllFile(String directoryPath, boolean isAddDirectory, boolean isAbsolutePath) throws NullPointerException {
        List<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }

        File[] files = files = baseFile.listFiles();
        ;
        if (null == files) {
            return list;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory, isAbsolutePath));
            } else {
                if (isAbsolutePath) {
                    list.add(file.getAbsolutePath());
                } else {
                    list.add(file.getName());
                }
            }
        }
        return list;
    }

    /**
     * 获取对应路劲下的包名
     */
    public static List<String> getPackageFileName(String directoryPath, boolean isAddDirectory, boolean isAbsolutePath) throws NullPointerException {
        List<String> fileList = getAllFile(directoryPath,isAddDirectory,isAbsolutePath);

        return fileList.stream().map(fileName->{
            String file =  fileName.replace(directoryPath,"").replace(File.separator,FileUtils.SPOT);
            file = file.substring(0,file.lastIndexOf(FileUtils.SPOT.toString()));
            while(file.startsWith(FileUtils.SPOT.toString())){
                file = file.substring(1, file.length());
            }
            return file;
        }).collect(Collectors.toList());
    }
}

