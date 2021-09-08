package com.example.java_lib.designpattern.prototypepattern;

public interface IOrder extends IOrderClonable {
    /**
     * 设置订单数量
     * @param number
     * @return
     */
    public void setOrderNumber(int number);

    /**
     * 获取订单数量
     * @return
     */
    public int getOrderNumber();
}
