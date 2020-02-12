package com.wuhai.lotteryticket.utils;

import java.util.Set;
import java.util.TreeSet;

public class MyLuck {

    /**
     * 排除特定红球和蓝球
     * @param redSetNum
     * @param blueNum
     * @return
     */
    public static String getLotteryRes(Set<String> redSetNum, String blueNum){
        if(redSetNum == null || blueNum == null){
            return getLotteryRes();
        }

        Set<String> set = new TreeSet<String>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            String suiStr = sui<10?"0"+sui:""+sui;
            if(!redSetNum.contains(suiStr)){
                set.add(suiStr);//将元素存入集合中
            }
            if (set.size() == 6) {//存满六个红色球，则结束循环
                break;
            }
        }

        Set<String> set2 = new TreeSet<String>();
        while(true){
            int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
            String sui2Str = sui2<10?"0"+sui2:""+sui2;
            if(!sui2Str.equals(blueNum)){
                set2.add(sui2Str);
                break;
            }
        }

        String result = "红球：" + set + " 蓝球：" + set2;
        System.out.println(result);

        return result;
    }

    /**
     * 随机双色球
     * @return
     */
    public static String getLotteryRes() {
        Set<String> set = new TreeSet<String>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            set.add(sui<10?"0"+sui:""+sui);//将元素存入集合中
            if (set.size() == 6) {//存满六个红色球，则结束循环
                break;
            }
        }
        Set<String> set2 = new TreeSet<String>();
        int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
        set2.add(sui2<10?"0"+sui2:""+sui2);

        String result = "红球：" + set + " 蓝球：" + set2;
        System.out.println(result);

        return result;
    }


    public static void main(String[] args){
        getLotteryRes();
        getLotteryRes();
        getLotteryRes();

        Set<String> set = new TreeSet<>();
        set.add("03");
        set.add("06");
        set.add("08");
        set.add("20");
        set.add("24");
        set.add("32");

        getLotteryRes(set, "07");
        getLotteryRes(set, "07");
        getLotteryRes(set, "07");
    }
}
