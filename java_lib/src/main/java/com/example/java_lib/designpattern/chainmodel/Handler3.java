package com.example.java_lib.designpattern.chainmodel;

public class Handler3 extends Handler {
    @Override
    public void handle(AbstractRequest request) {
        System.out.println("handle3---->处理了对象" + request.getRequestLevel());
    }

    @Override
    public int getHandlerLevel() {
        return 3;
    }
}