package com.example.java_lib.designpattern.prototypepattern;

import java.util.List;

/**
 * Android 架构师之路9 设计模式之原型模式
 * https://juejin.cn/post/7002837054966988807?utm_source=gold_browser_extension
 */
public class OrderTest {
    public static void main(String[] args) {
        PersonalOrder personalOrder = new PersonalOrder();
        personalOrder.setOrderName("手机");
        personalOrder.setOrderUserName("kpioneer");
        personalOrder.setOrderNumber(999);


        EnterpriseOrder enterpriseOrder = new EnterpriseOrder();
        enterpriseOrder.setOrderName("电脑");
        enterpriseOrder.setOrderCompany("haocai");
        enterpriseOrder.setOrderNumber(666);
        //将来随着不断版本迭代，订单种类会不断增加

        // 获取拆分后的订单
        // 结果是拆分为10个订单
        List<IOrder> order = OrderService.getOrder(enterpriseOrder);
        for (IOrder iOrder : order) {
            System.out.println("订单信息:" + iOrder.toString());
        }

        System.out.println("==========================");

        List<IOrder> order2 = OrderService.getOrder(personalOrder);
        for (IOrder iOrder : order2) {
            System.out.println("订单信息:" + iOrder.toString());
        }
    }
}

