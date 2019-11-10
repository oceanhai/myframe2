package com.example.java_lib.m2016.ihandy;

/**
 * 多叉树
 */
public class Node {
    private int data;  //数据域
    private Node next;    //指针域

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
