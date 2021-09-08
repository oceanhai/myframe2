package com.example.java_lib.designpattern.chainmodel;

/**
 * 抽象的处理类
 * Created by Administrator on 2018/1/25.
 */
public abstract  class Handler {

    public Handler nextHandler;

    /**
     *
     */
    public void handRequest(AbstractRequest request){
        if(request.getRequestLevel()==getHandlerLevel()){
            handle(request);
        }else {
            if(nextHandler!=null){
                nextHandler.handRequest(request);
            }else {
                System.out.println("----> 所有处理对象 都不能处理 " );
            }
        }
    }
    /**
     * 具体的处理方法，给子类实现
     * @param request
     */
    public abstract void handle(AbstractRequest request);

    /**
     * 能够处理请求的级别
     * @return
     */
    public abstract int  getHandlerLevel();
}

