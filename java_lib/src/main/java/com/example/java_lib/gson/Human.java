package com.example.java_lib.gson;

public class Human {
    private String head;
    private Object user;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Human{" +
                "head='" + head + '\'' +
                ", user=" + user +
                '}';
    }
}
