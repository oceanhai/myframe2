package com.example.java_lib.java.codeblock;


public class Demo4 {

    public static String name= "zhangsan";

    public static void fun01(){
        name = "lisi";
    }

    static{
        System.out.println(name);
    }

    public static void main(String[] args) {
        System.out.println(name);
        fun01();
        System.out.println(name);
    }

    /**
     * 执行结果：
     *               zhangsan
     *               zhangsan
     *               lisi
     * 因此：静态函数在类被加载时就会被加载，但只有当其被调用时才会被执行。
     */

}

