package com.example.java_lib.tencent.hengxue;

public class Test01 {

    public static void main(String[] args){
        Son son = new Son();
        System.out.println("son.a = " + son.a);//可继承
        son.method01();//可继承

        System.out.println("Father.a = " + Father.a);
        Father.method01();
    }

}
