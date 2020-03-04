package com.example.java_lib.m2016.xinlang;

/**
 * 参考https://blog.csdn.net/qq_40940540/article/details/86739869
 * 同时 参考 com.example.java_lib.designpattern.builderpattern BuildMan
 */
public class A {
    private int x;
    public int y;
    public static int z;

    public void methodA(){
        D d = new D();
//        System.out.println(d.d1);//错误 private修饰 不可引用
        System.out.println(d.d2);//ok public修饰 可以引用

        B b = new B();
        System.out.println(b.b1);
        System.out.println(b.b2);

        C c = new C();
        System.out.println(c.c1);
        System.out.println(c.c2);
        System.out.println(c.c3);
    }

    public static void main(String[] args){
//        B b1 = new B();//错误 不能从静态上下文引用   我可以理解为static里不能有this
        B b2 = new A().new B();//OK

        C c = new C();//OK 静态方法里 只能引用静态的

    }

    /**
     * 内部类
     */
    public class B{
        private int b1;
        public int b2;
//        public static int b3;//错 非静态内部类不能有静态申明

        void methodB(){
            System.out.println("methodB");
        }
    }

    /**
     * 静态内部类
     */
    public static class C{
        private int c1;
        public int c2;
        public static int c3;

        void methodC(){
//            System.out.println(x);//错
//            System.out.println(y);//错
            System.out.println(z);//对 只能引用外部静态类
        }
    }
}
