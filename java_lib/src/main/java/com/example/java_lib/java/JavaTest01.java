package com.example.java_lib.java;

public class JavaTest01 {

    public static void main(String[] args){
        C a = new A();
        a.methodC();

        D b = new B();
        b.methodD();

    }


}

class A extends C{

}

class B implements D{

    @Override
    public void methodD() {
        System.out.println("b实现methodD()方法");
    }
}

class C{
    void methodC(){
        System.out.println("c的methodC()方法");
    }
}

interface D{
    void methodD();
}
