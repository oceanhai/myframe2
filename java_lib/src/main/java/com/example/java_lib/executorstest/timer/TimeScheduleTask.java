package com.example.java_lib.executorstest.timer;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeScheduleTask {
    Timer timer;

    public TimeScheduleTask(Date scheduledTime) {
        System.out.println("指定时间time=" + scheduledTime);
        timer = new Timer();
        timer.schedule(new TimerTaskTest(), scheduledTime);
    }

    public class TimerTaskTest extends TimerTask {
        @Override
        public void run() {
            System.out.println("指定时间执行线程任务...");
        }
    }

    public static void main(String[] args) {
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        // 指定一个日期
        Date date;
        try {
            date = dateFormat.parse("2019-11-25 22:32:00");
            // 对 calendar 设置为 date 所定的日期
            calendar.setTime(date);
            new TimeScheduleTask(calendar.getTime());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

