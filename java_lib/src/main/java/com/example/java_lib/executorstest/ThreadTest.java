package com.example.java_lib.executorstest;

public class ThreadTest {
    public static void main(String[] args){
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x=0;x<10;x++){
                    System.out.println(Thread.currentThread().getName()+",x="+x);
                }
            }
        });
        thread.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    System.gc();//gc 了后面还能getstate (lll￢ω￢)
                    Thread.sleep(5000);
                    /**
                     * Thread-1,2秒后 thread state=TERMINATED
                     * 终止线程的线程状态。
                     * 线程已完成执行。
                     * ※ TODO 感觉最终还是没得出thread执行完是否自动销毁，但从结果来看，并未销毁呢？不知道对不对
                     */
                    System.out.println(Thread.currentThread().getName()+",5秒后 thread state="+thread.getState());
                } catch (InterruptedException e) { }
            }
        }.start();
    }
}
