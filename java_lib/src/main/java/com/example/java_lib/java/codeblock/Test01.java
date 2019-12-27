package com.example.java_lib.java.codeblock;

public class Test01 {
    //代码内 作为一种开关控制
    public static boolean DEBUG = true;
    static {
        if("debug".equals("hehe")){
            DEBUG = true;
        }else{
            DEBUG = false;
        }
    }

    public static void main(String[] arg){
        System.out.println(DEBUG);
    }

}
