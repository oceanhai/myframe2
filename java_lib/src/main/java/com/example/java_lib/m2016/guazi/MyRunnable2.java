package com.example.java_lib.m2016.guazi;



public class MyRunnable2  {

    public static void main(String[] args) {
        MyRunnable2 run = new MyRunnable2();
        Foo2 foo2=new Foo2();

        MyThread t1 = run.new MyThread("线程A", foo2, 10);
        MyThread t2 = run.new MyThread("线程B", foo2, -2);
        MyThread t3 = run.new MyThread("线程C", foo2, -3);
        MyThread t4 = run.new MyThread("线程D", foo2, 5);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    class MyThread extends Thread {
        private Foo2 foo2;
        /**当前值*/
        private int y = 0;

        MyThread(String name, Foo2 foo2, int y) {
            super(name);
            this.foo2 = foo2;
            this.y = y;
        }

        @Override
        public void run() {
            foo2.fix(y);
        }

    }

}
