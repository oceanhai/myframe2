package com.example.java_lib.flutter;

import org.junit.Test;

public class Test01 {

    @Test
    public void method01(){//跟dart语法 一样
        int a = 10;
        int b = a++;
        System.out.println("a="+a);
        System.out.println("b="+b);
    }

    @Test
    public void method02(){//跟dart语法 一样
         int i=1;
         int sum=0;
         do{
            sum+=i;
            i++;
         }while(i<=100);
        System.out.println(sum);
    }
}
