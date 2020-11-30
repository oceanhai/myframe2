package com.wuhai.myframe2.ui.refelct;

/**
 * 作者 wh
 *
 * 创建日期 2020/11/20 15:31
 *
 * 描述：JAVA中getComponentType()的用法
 */
public class GetComponentType {

    public static void main(String[] args) {
        // 正常方式创建 一个String数组
        String[] strArr = new String[10];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = i + " ";
        }
        System.out.println("正常方式创建数组的值为：");
        for (String s : strArr) {
            System.out.print(s);
        }

        // 以反射的方式创建一个String数组
        String[] strArr2 = (String[]) java.lang.reflect.Array.newInstance(String[].class.getComponentType(), 10);
        for (int i = 0; i < strArr2.length; i++) {
            strArr2[i] = i + 10 + " ";
        }
        System.out.println("\n以反射的方式创建数组的值为：");
        for (String s : strArr2) {
            System.out.print(s);
        }

        System.out.println();

        /**
         * 当不是数组的Class对象调用此方法时
         * 结论：非数组类型不能通过**getComponentType()**方法获得元素的Class对象类型
         */
        try {
            String str = (String) java.lang.reflect.Array.newInstance(String.class.getComponentType(), 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
