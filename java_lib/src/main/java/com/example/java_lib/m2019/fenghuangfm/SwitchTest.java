package com.example.java_lib.m2019.fenghuangfm;

import org.junit.Test;

public class SwitchTest {

    @Test
    public void method01(){
        System.out.println(getValue(2));
    }

    public static int getValue(int i){
        int result = 0;
        switch(i){
            case 1:
                result = result+i;
            case 2:
                result = result+i*2;
            case 3:
                result = result+i*2;
        }
        return result;
    }

}
