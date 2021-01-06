package com.example.java_lib.pactera;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class ReadFile {

    public static void main(String[] args){
        try {
            createFile("C:\\Users\\wpwh\\Desktop\\chajian", "readme.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建文件（读写字符串）
     * @param basePath 路径
     * @param fileName 文件名
     * @throws Exception 异常
     */
    private static void createFile(String basePath, String fileName) throws Exception
    {
        String content = "";
        if (!new File(basePath + fileName).exists())
        {
            content = ReadTemplateFile(fileName);
            System.out.println("content="+content);
            writeToFile(content, basePath, fileName);
        }
    }

    /**
     * 读取模板文件
     * @param fileName 文件名称
     * @return 文件内容（字符串）
     */
    private static String ReadTemplateFile(String fileName){
//        InputStream in = null;
//        in = getClass().getResourceAsStream("/template/" + fileName);

        InputStream in = null;
        try {
            in = new FileInputStream("D:\\work\\2020\\IdeaProjects\\K4MPaaS\\src\\template\\readme.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String content = "";
        try
        {
            content = new String(readStream(in),"GBK");
//            content = new String(inputToByte(in,1024));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 读取io流
     * @param inputStream 文件io流
     * @return byte数组
     * @throws IOException io异常
     */
    private static byte[] readStream(InputStream inputStream)
            throws IOException{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] buffer = new byte['?'];
        byte[] buffer = new byte[1024];
        int len = -1;
        try
        {
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            outputStream.close();
            inputStream.close();
        }
        return outputStream.toByteArray();
    }

    private static byte[] inputToByte(InputStream inStream, int fileSize) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[fileSize];
        int rc;
        while ((rc = inStream.read(buff, 0, fileSize)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        return swapStream.toByteArray();
    }

    /**
     * 写文件
     * @param content 内容
     * @param classPath 路径（文件）
     * @param className 名称（文件）
     */
    private static void writeToFile(String content, String classPath, String className)
    {
        try
        {
            File floder = new File(classPath);
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(classPath + "/" + className);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
