package com.example.java_lib.designpattern.chainmodel;

/**
 * 处理对象Request
 */
public abstract class AbstractRequest {
    private Object object;
    public  Object getContent(){
        return object;
    }
    public abstract int getRequestLevel();
}

