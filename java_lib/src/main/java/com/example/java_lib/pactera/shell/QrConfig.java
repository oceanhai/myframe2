package com.example.java_lib.pactera.shell;

public class QrConfig {

    public static  int ding_path = 11;//默认声音
    public int loop_wait_time = 5000;//连续扫描间隔时间

    public static int getDing_path() {
        return ding_path;
    }

    public static void setDing_path(int ding_path) {
        QrConfig.ding_path = ding_path;
    }

    public int getLoop_wait_time() {
        return loop_wait_time;
    }

    public void setLoop_wait_time(int loop_wait_time) {
        this.loop_wait_time = loop_wait_time;
    }

}
