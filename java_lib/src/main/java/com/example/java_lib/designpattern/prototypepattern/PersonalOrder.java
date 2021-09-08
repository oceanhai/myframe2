package com.example.java_lib.designpattern.prototypepattern;

public class PersonalOrder implements IOrder {
    private String orderName;
    private String orderUserName;
    private int orderNumber;

    @Override
    public int getOrderNumber() {
        return orderNumber;
    }

    @Override
    public void setOrderNumber(int number) {
        this.orderNumber = number;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }
    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }


    @Override
    public String toString() {
        return "PersonalOrder{" +
                "orderName='" + orderName + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", orderNumber=" + orderNumber +
                '}';
    }

    @Override
    public IOrderClonable orderClone() {
        PersonalOrder personalOrder = new PersonalOrder();
        personalOrder.setOrderName(orderName);
        personalOrder.setOrderUserName(orderUserName);
        return personalOrder;
    }
}

