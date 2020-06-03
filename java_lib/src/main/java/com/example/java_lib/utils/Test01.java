package com.example.java_lib.utils;

public class Test01 {

    public static void main(String[] args){
        method01();
    }

    /**
     * ThreadPoolUtils
     */
    private static void method01() {
        for (int x=0;x<5;x++){
            ThreadPoolUtils.getInstance().getCachedThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        int num = 0;
                        while (num<5){
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName()+", num = " + num);
                            num++;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
