package com.example.java_lib.java.codeblock;


public class Demo3 {

    public static String name= "zhangsan";
    static{
        System.out.println(name);
    }

    static{
//        System.out.println(name2); //程序会报错
    }
    public static String name2= "lisi";

    /**
     * 上述代码块会报错，原因:name2属性未被定义，因此可以理解为静态代码块和静态属性谁在前谁先被加载。
     */
}
