package com.example.java_lib.java;

import org.junit.Test;

public class JavaTest {

    @Test
    public void method01(){
        int a = 0x00000100;
        int b = 0x00000400;
        System.out.println("a="+a);//256
        System.out.println("b="+b);//1024

        int c = a | b;
        System.out.println("c="+c);//1280

    }

    @Test
    public void method02(){
        String str = "getinfo://equipmentID";
        System.out.println(str.substring(10));
    }

    @Test
    public void method03(){
        double a = 0.0;
        if(a==0){
            System.out.println("等于0");
        }else{
            System.out.println("不等于0");
        }
    }

    @Test
    public void method04(){
        String mDrUrl = "https://service.neuqsoft.com/ggfwH5/#/third-party?state=<state>&code=<code>&target=shbz/cx/yang-lao";
        String newUrl = mDrUrl
                .replace("<state>","123")
                .replace("<code>","456");
        System.out.println("newUrl="+newUrl);
    }

}
