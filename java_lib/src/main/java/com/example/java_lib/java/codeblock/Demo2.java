package com.example.java_lib.java.codeblock;

public class Demo2 {

    public static void main(String[] args){
        ClassA classA = new ClassA();
        System.out.println("a="+classA.a);
        System.out.println("b="+classA.b);
    }
}

class ClassA{
    {
        a=2;
        //TODO err非法转发引用 说明普通属性的加载要在普通代码块前面，但最终输出结果a却等于1，说明复制操作是按普通属性和普通代码块的先后顺序
//        System.out.println("普通代码块"+a);
    }
    int a = 1 ;
    int b = 2;
    {
        b = 1;
    }
}
