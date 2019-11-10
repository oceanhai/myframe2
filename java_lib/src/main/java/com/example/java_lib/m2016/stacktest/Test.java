package com.example.java_lib.m2016.stacktest;

/**
 * 测试
 */
public class Test {
    public static void main(String[] arg){

        SequenceStack<Integer> stack1 = new SequenceStack<Integer>(3);
        SequenceStack<Integer> stack2 = new SequenceStack<Integer>();
        System.out.println(stack2.length());
        stack2.push(10);


        for(int x=0;x<16;x++){
            stack2.push(x);
        }
        System.out.println("栈顶"+stack2.peek());
        System.out.println(stack2.toString());
        for(int x=0;x<16;x++){
            System.out.print(stack2.pop()+",");
        }
        System.out.println();

        //测试下左移
        int x = 1;
        x <<=1;
        System.out.println(""+x);
        int y = 1;
        y <<=2;
        System.out.println(""+y);
        int z = 3;
        z <<=3;
        System.out.println(""+z);
    }
}
