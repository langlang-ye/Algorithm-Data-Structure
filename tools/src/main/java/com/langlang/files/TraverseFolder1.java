package com.langlang.files;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.LinkedList;

public class TraverseFolder1 {
    public static void main(String[] args) {
        //Long start = System.currentTimeMillis();
        String path = "E:\\BaiduNetdiskDownload";
        traverseFolder1(path);
        //Long end = System.currentTimeMillis();
        //System.out.println(end-start);
    }

    private static void traverseFolder1(String path) {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = null;
            list.add(file);
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File tmpFile : files) {
                    if (tmpFile.isDirectory()) {
                        //System.out.println("文件夹:" + tmpFile.getAbsolutePath());
                        list.add(tmpFile);
                        folderNum++;
                    } else {
                        if (tmpFile.length() > 10485760) { // length() 返回文件的大小 字节byte
                            System.out.println("文件:" + tmpFile.getAbsolutePath());
                            fileNum++;
                        }
                        BasicFileAttributes attributes = null; // 获取文件的最后的修改时间 Linux 系统不记录文件的创建时间？
                        try {
                            attributes = Files.readAttributes(tmpFile.toPath(), BasicFileAttributes.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Instant s = attributes.lastModifiedTime().toInstant();
                        String s1 = s.atOffset(ZoneOffset.ofHours(8)).toString();
                        System.out.println(s1);

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
    }

}
