package com.example.java_lib.executorstest.timer3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

/**
 * https://blog.csdn.net/WatsonXh/article/details/78855727
 */
public class MyTimerTest02 {
    public static void main(String[] args) {
        Timer timer=new Timer();
//		Calendar calendar=Calendar.getInstance();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
        System.out.println(sdf.format(date));
//		calendar.add(Calendar.SECOND, 2);

//		myTimerTask02.setName("test TimerTask Cancel");
//
//		timer.schedule(myTimerTask02, 2000, 1000);

        TimerTask03 myTimerTask03=new TimerTask03("near time");
        timer.schedule(myTimerTask03,2000,1000);
        System.out.println("Timer最近会执行的Task的时间："+sdf.format(myTimerTask03.scheduledExecutionTime()));
    }
}
