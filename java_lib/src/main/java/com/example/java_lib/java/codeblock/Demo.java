package com.example.java_lib.java.codeblock;

/**
 * 父类静态代码块执行！
 * 子类静态代码块执行！
 * 父类构造块执行!
 * 父类构造函数执行!
 * 子类构造块执行!
 * 子类构造函数执行!
 * 普通方法执行!
 */
public class Demo {

    {
        System.out.println("父类构造块执行!");
    }

    static{
        System.out.println("父类静态代码块执行！");
    }

    public Demo(){
        System.out.println("父类构造函数执行!");
    }

    public static void main(String[] args) {
        Demo1 d = new Demo1();
        d.Demo2();
    }
}

class Demo1 extends Demo{

    {
        System.out.println("子类构造块执行!");
    }

    static{
        System.out.println("子类静态代码块执行！");
    }

    public Demo1(){
        System.out.println("子类构造函数执行!");
    }

    public void Demo2(){
        System.out.println("普通方法执行!");
    }

}

