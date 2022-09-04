package com.langlang.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TraverseFolder2 {


    private static PrintStream out;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String path = "E:\\BaiduNetdiskDownload";
        out = new PrintStream("E:\\1.txt");
        GetData(path);
    }

    /*
     * 递归调用查找指定文件加下所有文件
     */
    public static String GetData(String path) throws FileNotFoundException {
        File rootDir = new File(path);
        if (!rootDir.isDirectory()) {
            System.setOut(out);
            System.out.println("文件名" + rootDir.getAbsolutePath());
            /*String absolutePath = rootDir.getAbsolutePath();
            if(absolutePath.endsWith("代码.zip")){
                	System.out.println(absolutePath);
                if(absolutePath.endsWith("源代码.zip")){
                } else{
                    System.err.println(absolutePath);
                }
            }*/
            //System.out.println();
        } else {
            String[] fileList = rootDir.list();
            for (int i = 0; i < fileList.length; i++) {
                path = rootDir.getAbsolutePath() + "\\" + fileList[i];
                GetData(path);
            }
        }
        return null;
    }
}
