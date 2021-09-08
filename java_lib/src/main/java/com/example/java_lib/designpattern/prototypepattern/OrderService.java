package com.example.java_lib.designpattern.prototypepattern;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    /**
     * 订单拆分
     *
     * 需求：订单数量超过了100，我就拆分？
     *
     * @param order
     * @return
     */
    public static List<IOrder> getOrder(IOrder order){
        List<IOrder> orderList = new ArrayList<IOrder>();
        //订单数量大于100就要进行拆分
        IOrder newOrder = null;
        while (order.getOrderNumber() > 100)
        {
            newOrder = (IOrder)order.orderClone();
            //拆分
            newOrder.setOrderNumber(100);
            orderList.add(newOrder);
            //改变原来的订单数量
            order.setOrderNumber(order.getOrderNumber()-100);
        }
        orderList.add(order);

        return orderList;
    }

}

