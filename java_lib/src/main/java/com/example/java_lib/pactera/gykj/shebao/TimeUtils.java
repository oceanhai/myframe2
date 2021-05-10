package com.example.java_lib.pactera.gykj.shebao;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static void main(String[] args){
        System.out.println(isEffectiveDate(new Date()));

        Date nowTime = new Date();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(nowTime);
        cal1.set(Calendar.HOUR_OF_DAY, 19);
        System.out.println("cal1.getTime() = " +cal1.getTime());
        System.out.printf("%1$tF %1$tT\n", cal1.getTime());
        System.out.println(isEffectiveDate(cal1.getTime()));
    }

    public static boolean isEffectiveDate(Date nowTime ) {

        Date startTime = new Date();
        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);
        begin.set(Calendar.HOUR_OF_DAY, 8);
        begin.set(Calendar.MINUTE, 0);
        begin.set(Calendar.SECOND, 0);
        System.out.println("cal1.getTime() = " +begin.getTime());
        System.out.printf("%1$tF %1$tT\n", begin.getTime());

        Date endTime = new Date();
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        end.set(Calendar.HOUR_OF_DAY, 18);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        System.out.println("cal2.getTime() = " +end.getTime());
        System.out.printf("%1$tF %1$tT\n", end.getTime());

        if (nowTime.getTime() == begin.getTime().getTime()
                || nowTime.getTime() == end.getTime().getTime()) {
            System.out.println("=============== " +true);
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
