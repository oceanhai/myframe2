package com.example.java_lib.java.codeblock;


public class Demo5 {

    static {
        System.out.println("静态代码块");
    }

//    static {
//        System.out.println("静态代码块"+b);//b 不OK
//    }

    public static void method03(){
        System.out.println("静态方法method03 "+b);
    }

    static int b = 2;//静态属性

//    public static void method03(){
//        System.out.println("静态方法method03 "+b);
//    }

//    static {
//        System.out.println("静态代码块"+b);//b OK
//    }


    int a = 1;

    public Demo5() {
        System.out.println("构造方法 "+a);
    }

    {
        System.out.println("普通代码块 "+ a);
    }

    public void method01(){
        System.out.println("普通方法 " +a);
    }

    public static void method02(){
//        System.out.println("静态方法 " +a);//TODO 从静态方法里不能引用 非静态属性可以看出，静态方法加载要早
    }

    public static void main(String[] args){
//        Demo5 demo5 = new Demo5();
        Demo5.method03();
    }
}

