package com.example.java_lib.executorstest.timer2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeScheduleTask {
    Timer timerStart;//定时开始
    Timer timerEnd;//定时结束
    ScheduledExecutorTest myTask;//周期任务
    TimerTaskStart timerTaskStart;//开始任务

    public TimeScheduleTask(Date startTime, Date endTime) {
        System.out.println("开始时间" + startTime);
        System.out.println("结束时间" + endTime);

        timerStart = new Timer();
        timerEnd = new Timer();
        timerTaskStart = new TimerTaskStart();

        timerStart.schedule(timerTaskStart, startTime);
        timerEnd.schedule(new TimerTaskEnd(), endTime);

    }

    /**
     * 定时开始 任务
     */
    public class TimerTaskStart extends TimerTask {
        @Override
        public void run() {
            myTask = new ScheduledExecutorTest(1);//每一分钟的周期任务
            myTask.startTask();
        }
    }

    /**
     * 定时结束 任务
     */
    public class TimerTaskEnd extends TimerTask {
        @Override
        public void run() {
            if (myTask != null) {
                myTask.endTask();
                timerTaskStart.cancel();
                this.cancel();
            }

        }
    }

    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d H:m:s");
        // 指定一个日期
        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse("2019-11-25 23:03:00");
            endDate = dateFormat.parse("2019-11-25 23:8:00");
            new TimeScheduleTask(startDate, endDate);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


