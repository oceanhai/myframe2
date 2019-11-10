package com.example.java_lib.m2016.xinmeichuanxin.hashset;

public class HashSetTest2 {

    public static void main(String[] args) {
        Demo2 d1=new Demo2();
        Demo2 d2=new Demo2();
        sop(d1);
        sop(d2);
    }
    public static void sop(Object obj){//定义此sop方法用来快捷输出，在输出较多的情况下尤为快捷。
        System.out.println(obj);
    }
}

class Demo2{
    @Override
    public int hashCode() {
        return 60;
    }
}
