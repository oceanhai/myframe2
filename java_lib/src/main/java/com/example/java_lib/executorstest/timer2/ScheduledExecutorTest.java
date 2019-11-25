package com.example.java_lib.executorstest.timer2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {
    private ScheduledExecutorService scheduExec;
    private ScheduleTask myTask;

    ScheduledExecutorTest(long num) {
        this.scheduExec = Executors.newScheduledThreadPool(1);
        myTask = new ScheduleTask();
        scheduExec.scheduleAtFixedRate(myTask, 0, num, TimeUnit.MINUTES);
    }

    public void startTask() {
        myTask.start();
    }

    public void endTask() {
        // 关闭具体任务线程
        myTask.interrupt();
        // 关闭周期计划
        scheduExec.shutdown();
    }
}

