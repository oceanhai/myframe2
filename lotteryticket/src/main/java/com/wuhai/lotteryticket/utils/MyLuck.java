package com.wuhai.lotteryticket.utils;

import java.util.Set;
import java.util.TreeSet;

public class MyLuck {

    /**
     * 排除特定红球和蓝球  6+1 TODO 废弃
     * @param redSetNum
     * @param blueNum
     * @return
     */
    public static String getLotteryRes(Set<String> redSetNum, String blueNum){
        if(redSetNum == null || blueNum == null){
            return getLotteryRes(6, 1);
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
     * 排除特定红球和蓝球
     * @param redSetNum
     * @param blueSetNum
     * @return
     */
    public static String getLotteryRes(Set<String> redSetNum, Set<String> blueSetNum, int redNum, int blueNum){
        if(redSetNum == null || blueSetNum == null){
            return getLotteryRes(redNum, blueNum);
        }

        int excludeRedNum = redSetNum.size();
        int excludeBlueNum = blueSetNum.size();
        System.out.println("excludeRedNum="+excludeRedNum+",excludeBlueNum="+excludeBlueNum);
        if(33-excludeRedNum<=redNum || 16-excludeBlueNum<=blueNum){
            return null;
        }

        Set<String> set = new TreeSet<String>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            String suiStr = sui<10?"0"+sui:""+sui;
            if(!redSetNum.contains(suiStr)){
                set.add(suiStr);//将元素存入集合中
            }
            if (set.size() == redNum) {//存满N个红色球，则结束循环
                break;
            }
        }

        Set<String> set2 = new TreeSet<String>();
        while(true){
            int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
            String sui2Str = sui2<10?"0"+sui2:""+sui2;
            if(!blueSetNum.contains(sui2Str)){
                set2.add(sui2Str);
            }
            if (set2.size() == blueNum) {//存满N个蓝色球，则结束循环
                break;
            }
        }

        String result = "红球：" + set + " 蓝球：" + set2;
        System.out.println(result);

        return result;
    }

    /**
     * 排除特定红球和蓝球 之红球
     * @param redSetNum
     * @return
     */
    public static String getLotteryResRedBall(Set<String> redSetNum, int redNum){
        if(redSetNum == null){
            return getLotteryResRedBall(redNum);
        }

        int excludeRedNum = redSetNum.size();
        if(33-excludeRedNum<=redNum){
            return null;
        }

        Set<String> set = new TreeSetNew<>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            String suiStr = sui<10?"0"+sui:""+sui;
            if(!redSetNum.contains(suiStr)){
                set.add(suiStr);//将元素存入集合中
            }
            if (set.size() == redNum) {//存满N个红色球，则结束循环
                break;
            }
        }

        System.out.println("红球：" + set);

        return set.toString();
    }

    /**
     * 排除特定红球和蓝球 之蓝球
     * @param blueSetNum
     * @return
     */
    public static String getLotteryResBlueBall(Set<String> blueSetNum, int blueNum){
        if(blueSetNum == null){
            return getLotteryResBlueBall(blueNum);
        }

        int excludeBlueNum = blueSetNum.size();
        if(16-excludeBlueNum<=blueNum){
            return null;
        }

        Set<String> set2 = new TreeSetNew<>();
        while(true){
            int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
            String sui2Str = sui2<10?"0"+sui2:""+sui2;
            if(!blueSetNum.contains(sui2Str)){
                set2.add(sui2Str);
            }
            if (set2.size() == blueNum) {//存满N个蓝色球，则结束循环
                break;
            }
        }

        System.out.println("蓝球：" + set2);

        return set2.toString();
    }

    /**
     * 随机双色球
     * @return
     */
    public static String getLotteryRes(int redNum, int blueNum) {
        Set<String> set = new TreeSet<String>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            set.add(sui<10?"0"+sui:""+sui);//将元素存入集合中
            if (set.size() == redNum) {//存满N个红色球，则结束循环
                break;
            }
        }
        Set<String> set2 = new TreeSet<String>();
        while (true){
            int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
            set2.add(sui2<10?"0"+sui2:""+sui2);
            if (set2.size() == blueNum) {//存满N个蓝色球，则结束循环
                break;
            }
        }


        String result = "红球：" + set + " 蓝球：" + set2;
        System.out.println(result);

        return result;
    }

    /**
     * 随机双色球之 红球
     * @return
     */
    public static String getLotteryResRedBall(int redNum) {
        Set<String> set = new TreeSetNew<>();
        while(true){
            int sui = (int) (Math.random() * 33) + 1;// 获取33以内的数字
            set.add(sui<10?"0"+sui:""+sui);//将元素存入集合中
            if (set.size() == redNum) {//存满N个红色球，则结束循环
                break;
            }
        }

        System.out.println("红球："+set);

        return set.toString();
    }

    /**
     * 随机双色球之 蓝球
     * @return
     */
    public static String getLotteryResBlueBall(int blueNum) {
        Set<String> set2 = new TreeSetNew<>();
        while (true){
            int sui2 = (int) (Math.random() * 16) + 1;// 获取16以内的数字
            set2.add(sui2<10?"0"+sui2:""+sui2);
            if (set2.size() == blueNum) {//存满N个蓝色球，则结束循环
                break;
            }
        }

        System.out.println("蓝球："+set2);

        return set2.toString();
    }


    public static void main(String[] args){
        //非排除
//        getLotteryRes(6, 1);
//        getLotteryRes(12, 5);
//        getLotteryRes(20, 16);
//
        Set<String> set1 = new TreeSet<>();
        set1.add("03");
        set1.add("06");
        set1.add("08");
        set1.add("20");
        set1.add("24");
        set1.add("32");

        Set<String> set2 = new TreeSet<>();
        set2.add("03");

        Set<String> set3 = new TreeSet<>();
        set3.add("03");
        set3.add("06");
        set3.add("08");
        set3.add("20");
        set3.add("24");
        set3.add("32");
        set3.add("33");
        Set<String> set4 = new TreeSet<>();
        set4.add("03");
        set4.add("04");
        set4.add("05");
        set4.add("06");
        set4.add("07");
        set4.add("08");

        //排除
//        getLotteryRes(set1, "07");
//        getLotteryRes(set1, set2, 7, 1);
//        getLotteryRes(set3, set4, 7, 1);
//        getLotteryRes(set1, set2, 8, 2);
//        getLotteryRes(set3, set4, 8, 2);

        //红球
        getLotteryResRedBall(6);
        getLotteryResRedBall(8);
        getLotteryResRedBall(set1,6);
        getLotteryResRedBall(set1,12);
        //蓝球
        getLotteryResBlueBall(1);
        getLotteryResBlueBall(2);
        getLotteryResBlueBall(set4,1);
        getLotteryResBlueBall(set4,9);
    }
}
