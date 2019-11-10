package com.example.java_lib.designpattern.singletonpattern;

public class SingleTonTest {

    public static void main(String[] args){
      method02();
    }

    /**
     * 单例模式  只是获取的 instance 确保是同一个而已
     *
     */
    public static void method02(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int x=1;x<=30;x++){
                    System.out.println(Thread.currentThread().getName()+",num="+
                            SingleTon4.INSTANCE.getInstance().getNum());
                }
            }
        }).start();

        for(int x=1;x<=30;x++){
            System.out.println(Thread.currentThread().getName()+",num="+
                    SingleTon4.INSTANCE.getInstance().getNum());
        }
    }

    /**
     * 虽然单例，单如果对对象A的num操作 也是不安全的呢
     *
     */
    public static void method01(){
        SingleTon4.INSTANCE.getInstance().method01();
        System.out.println(SingleTon4.INSTANCE.getInstance());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(SingleTon4.INSTANCE.getInstance());
                for(int x=1;x<=30;x++){
                    System.out.println(Thread.currentThread().getName()+",num="+
                            SingleTon4.INSTANCE.getInstance().getNum());
                }
            }
        }).start();

        for(int x=1;x<=30;x++){
            SingleTon4.INSTANCE.getInstance().setNum(x);
            System.out.println(Thread.currentThread().getName()+",num="+
                    SingleTon4.INSTANCE.getInstance().getNum());
        }
    }
}
