package com.example.java_lib.java;

import org.junit.Test;

import java.util.HashMap;

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

    @Test
    public void method05(){
//        String str = "https://he.12333.gov.cn/ggfw-h5-funcs-heb.html#/third-party?state=TECH_ICBC_XASI&code=<code>&target=shbz/cx/gong-shang";
        String str = "https://he.12333.gov.cn/ggfw-h5-funcs-heb.html#/third-party?state=TECH_ICBC_XASI&code=<code>&target";
        String str1 = str.substring(str.indexOf("?")+1);
        System.out.println("str1:"+str1);
        String[] str2 = str1.split("&");
        HashMap<String,String> params = new HashMap<>();
        for (int x=0;x<str2.length;x++){
            System.out.println(x+":"+str2[x]);
            String[] str3 = str2[x].split("=");
            if(str3.length == 2){
                params.put(str3[0], str3[1]);
            }
        }
        System.out.println("params:"+params);
        System.out.println("state:"+params.get("state"));
    }

    @Test
    public void method06(){
        boolean isSafe = false;
        assert isSafe;
        System.out.println("断言通过!");
    }
}
