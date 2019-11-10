package com.example.java_lib;

public class Test01 {

    public static void main(String[] args){
        method04();

    }

    public static void method04(){
        System.out.println(getStu().hashCode());
        System.out.println(getStu().hashCode());
    }

    public static Student getStu(){
        return new Student();
    }

    public static void method03(){
        FinalTest finalTest = new FinalTest();
        System.out.println("p="+finalTest.p);
        System.out.println("q="+finalTest.q);
    }

    public static void method02(){
        String s = null;
        System.out.println("s="+s);
    }

    public static void method01(){
        String s = new String("1");
        s.intern();
        String s1 = "1";

        System.out.println(s == s1);
        System.out.println(s.equals(s1));

        String s3 = new String("1")+new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
        System.out.println(s3.equals(s4));
    }
}

class FinalTest {
    final int p;
    final int q=3;

    FinalTest(){
        p=1;
    }

    FinalTest(int i){
        p=i;//可以赋值，相当于直接定义p
//        q=i;//不能为一个final变量赋值
    }
}

