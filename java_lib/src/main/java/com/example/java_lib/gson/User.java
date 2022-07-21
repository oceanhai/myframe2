package com.example.java_lib.gson;

public class User {
    private String username;
    private long uuid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", uuid=" + uuid +
                '}';
    }
}
