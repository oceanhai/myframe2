package com.example.java_lib.pactera.yidongjr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HuarunMain {


    public static void main(String[] args) {
        String startTime1 = "2023-4-10";
        String endTime1 = "2023-4-15";
        System.out.println(judgeDate(startTime1, endTime1));
        String startTime2 = "2023-4-11";
        String endTime2 = "2023-4-15";
        System.out.println(judgeDate(startTime2, endTime2));
        String startTime3 = "2023-4-12";
        String endTime3 = "2023-4-15";
        System.out.println(judgeDate(startTime3, endTime3));
        String startTime4 = "2023-4-01";
        String endTime4 = "2023-4-10";
        System.out.println(judgeDate(startTime4, endTime4));
        String startTime5 = "2023-4-01";
        String endTime5 = "2023-4-11";
        System.out.println(judgeDate(startTime5, endTime5));
        String startTime6 = "2023-4-01";
        String endTime6 = "2023-4-12";
        System.out.println(judgeDate(startTime6, endTime6));
    }

    /**
     * 判断当前时间是否在指定时间段内
     * @param startTime
     * @param endTime
     * @return
     */
    private static boolean judgeDate(String startTime, String endTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(startTime);
            date2 = sdf.parse(endTime);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date currentDate = new Date();
        if (currentDate.after(date1) && currentDate.before(date2)) {
            return true;
        } else {
            return false;
        }
    }
}
