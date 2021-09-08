package com.example.java_lib.designpattern.chainmodel;

public class Client {
    public static void main(String[] args){
        Handler handler1 = new Handler1();
        Handler handler2 = new Handler2();
        Handler handler3 = new Handler3();
        Handler handler4 = new Handler4();

        //拼装成链子
        handler1.nextHandler = handler2;
        handler2.nextHandler = handler3;
        handler3.nextHandler = handler4;

        AbstractRequest request = new Request1();
        //一定要将请求对象，丢给第一个处理者
        handler1.handRequest(request);

        AbstractRequest request2 = new Request2();
        //handler1 不处理 交给handler2处理
        handler1.handRequest(request2);


    }
}

