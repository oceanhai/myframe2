package com.example.java_lib.m2019.fenghuangfm;

import org.junit.Test;

public class VoluationTest {

    @Test
    public void method01(){
//        float f = 11.1;//11.1默认是double
        double d = 5.3E12;//科学计数法，5.3E12 == 5.3乘以10的12次方
        Boolean b = true;
//        byte bb = 433;//byte 的取值范围  -128到+127

        System.out.println(d);//5.3E12
        System.out.println(d==5300000000000d);//true
        System.out.println(b);//true
    }
}
