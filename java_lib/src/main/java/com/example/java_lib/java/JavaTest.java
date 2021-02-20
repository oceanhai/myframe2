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

}
