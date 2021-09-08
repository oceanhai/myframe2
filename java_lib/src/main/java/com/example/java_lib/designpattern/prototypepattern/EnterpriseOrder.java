package com.example.java_lib.designpattern.prototypepattern;

/**
 * 企业订单
 * Created by Xionghu on 2017/7/5.
 * Desc:
 */

public class EnterpriseOrder implements IOrder {
    private String orderName;
    private String orderCompany;
    private int orderNumber;

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderCompany() {
        return orderCompany;
    }

    public void setOrderCompany(String orderCompany) {
        this.orderCompany = orderCompany;
    }

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

    @Override
    public String toString() {
        return "EnterpriseOrder{" +
                "orderName='" + orderName + '\'' +
                ", orderCompany='" + orderCompany + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }

    @Override
    public IOrder orderClone() {
        EnterpriseOrder enterpriceOrder = new EnterpriseOrder();
        enterpriceOrder.setOrderCompany(orderCompany);
        enterpriceOrder.setOrderName(orderName);
        return enterpriceOrder;
    }
}

