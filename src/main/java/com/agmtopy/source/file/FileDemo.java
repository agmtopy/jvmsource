package com.agmtopy.source.file;

import java.io.File;

public class FileDemo {
    //查询指定文件夹下的文件顺序
    public static void main(String[] args) {
        File file = new File("D:\\project\\jvmsource\\temp");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
        }
    }
}
