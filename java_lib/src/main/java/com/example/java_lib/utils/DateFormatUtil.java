package com.example.java_lib.utils;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {

    @Test
    public void run4(){
        /**
         * Date转化成   November 1, 2017 2:14:16 PM
         */
        DateFormat format = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM ,Locale.ENGLISH);
        String string = format.format(new Date());
        System.out.println(string);
        /**
         * Date转化成   2017年11月1日 14:16:01
         */
        DateFormat format1 = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM ,Locale.CHINESE);
        String string1 = format1.format(new Date());
        System.out.println(string1);

        /**
         * Date转化成   November 1, 2017
         */
        DateFormat format2 = DateFormat.getDateInstance(DateFormat.LONG, Locale.ENGLISH);
        String string2 = format2.format(new Date());
        System.out.println(string2);


        /**
         * Date转化成   Wednesday, November 1, 2017
         */
        DateFormat format3 = DateFormat.getDateInstance(DateFormat.FULL, Locale.ENGLISH);
        String string3 = format3.format(new Date());
        System.out.println(string3);


        /**
         * Date转化成  Nov 1, 2017
         */
        DateFormat format4 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.ENGLISH);
        String string4 = format4.format(new Date());
        System.out.println(string4);


        /**
         * Date转化成  2017-11-1
         */
        DateFormat format5 = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CHINESE);
        String string5 = format5.format(new Date());
        System.out.println(string5);


        /**
         * Date转化成  2017年11月1日
         */
        DateFormat format6 = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINESE);
        String string6 = format6.format(new Date());
        System.out.println(string6);

    }


}
