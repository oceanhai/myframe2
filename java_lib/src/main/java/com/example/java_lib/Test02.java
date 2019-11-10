package com.example.java_lib;

public class Test02 {
    final static void func(){
        System.out.println("g");
    }

    public static void main(String[] args){
        for(int j=0; j<1000; j++)
            func();
    }
}
