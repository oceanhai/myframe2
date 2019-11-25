package com.example.java_lib.executorstest.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 * 实现1秒执行一次，总共执行三次
 */
public class TimerTest03 {
    Timer timer;
    public TimerTest03(){
        timer = new Timer();
        timer.schedule(new TimerTaskTest03(), 1000, 2000);
    }
    public static void main(String[] args) {
        new TimerTest03();
    }
    public class TimerTaskTest03 extends TimerTask {
        int count=0;
        @Override
        public void run() {
            Date date = new Date(this.scheduledExecutionTime());
            System.out.println("本次执行该线程的时间为：" + date);
            count++;
            if(count==3) {
                this.cancel();
            }
        }
    }
}

