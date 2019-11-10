package com.example;

public class FinalTest {
    public static void getJava() {
        String str1 = "Java ";
        String str2 = "final ";
        for (int i = 0; i < 10000; i++) {
            str1 += str2;
        }
    }

    public static final void getJava_Final() {
        String str1 = "Java ";
        String str2 = "final ";
        for (int i = 0; i < 10000; i++) {
            str1 += str2;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        getJava();
        System.out.println("调用不带final修饰的方法执行时间为:" + (System.currentTimeMillis() - start) + "毫秒时间");

        start = System.currentTimeMillis();
        String str1 = "Java ";
        String str2 = "final ";
        for (int i = 0; i < 10000; i++) {
            str1 += str2;
        }
        System.out.println("正常的执行时间为:" + (System.currentTimeMillis() - start) + "毫秒时间");

        start = System.currentTimeMillis();
        getJava_Final();
        System.out.println("调用final修饰的方法执行时间为:" + (System.currentTimeMillis() - start) + "毫秒时间");
    }
}
