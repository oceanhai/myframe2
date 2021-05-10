package com.example.java_lib.executorstest.timer3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class TimerTask03 extends TimerTask {
    private String name;
    public TimerTask03(String name)
    {
        this.name=name;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
//		Calendar calendar=Calendar.getInstance();
        System.out.println("------------>");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
//		System.out.println("Timer最近会执行的Task的时间："+sdf.format(scheduledExecutionTime()));
        System.out.println("This TimerTask's time is "+sdf.format(new Date()));
        System.out.println("This TimerTask's name is "+name);
        System.out.println("<------------");
        System.out.println();

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
