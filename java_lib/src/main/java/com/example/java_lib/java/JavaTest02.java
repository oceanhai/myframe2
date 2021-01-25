package com.example.java_lib.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * new String()传入字节数组生成String后，通过getBytes（）得到的结果和原来不同
 * https://blog.csdn.net/qq_33543634/article/details/88740524
 *
 */
public class JavaTest02 {

    public static void main(String[] args) {

    }

    /**
     * 输出结果 [-17, -65, -67, -17, -65, -67, -22, -68, -99]
     */
    @Test
    public void method01(){
        byte[] bytes = new byte[]{-66,-98,-22,-68,-99};
        String str = new String(bytes);
        System.out.println(Arrays.toString(str.getBytes()));
    }

    /**
     * ISO-8859-1,这是一个单字节表示字符的字符集
     * 输出结果 [-66, -98, -22, -68, -99]
     */
    @Test
    public void method02(){
        byte[] bytes = new byte[]{-66,-98,-22,-68,-99};
        String str = null;
        try {
            str = new String(bytes,0,bytes.length,"ISO-8859-1");
            System.out.println(Arrays.toString(str.getBytes("ISO-8859-1")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
