package com.example.java_lib.pactera;

import org.junit.Test;

public class Operator {

    @Test
    public void method01(){
        boolean a = true;
        boolean b = false;

        System.out.println(a ^ b);
        System.out.println(a ^ a);
        System.out.println(b ^ b);

        int c = 1;
        int d = 2;
        System.out.println(c ^ d);
    }
}
