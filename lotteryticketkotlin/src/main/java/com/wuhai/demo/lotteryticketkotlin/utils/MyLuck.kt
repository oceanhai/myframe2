package com.wuhai.demo.lotteryticketkotlin.utils

import java.util.*

object MyLuck {
    /**
     * 排除特定红球和蓝球  6+1 TODO 废弃
     * @param redSetNum
     * @param blueNum
     * @return
     */
    fun getLotteryRes(redSetNum: Set<String?>?, blueNum: String?): String {
        if (redSetNum == null || blueNum == null) {
            return getLotteryRes(6, 1)
        }
        val set: MutableSet<String> = TreeSet()
        while (true) {
            val sui = (Math.random() * 33).toInt() + 1 // 获取33以内的数字
            val suiStr = if (sui < 10) "0$sui" else "" + sui
            if (!redSetNum.contains(suiStr)) {
                set.add(suiStr) //将元素存入集合中
            }
            if (set.size == 6) { //存满六个红色球，则结束循环
                break
            }
        }
        val set2: MutableSet<String> = TreeSet()
        while (true) {
            val sui2 = (Math.random() * 16).toInt() + 1 // 获取16以内的数字
            val sui2Str = if (sui2 < 10) "0$sui2" else "" + sui2
            if (sui2Str != blueNum) {
                set2.add(sui2Str)
                break
            }
        }
        val result = "红球：$set 蓝球：$set2"
        println(result)
        return result
    }

    /**
     * 排除特定红球和蓝球
     * @param redSetNum
     * @param blueSetNum
     * @return
     */
    fun getLotteryRes(redSetNum: Set<String?>?, blueSetNum: Set<String?>?, redNum: Int, blueNum: Int): String? {
        if (redSetNum == null || blueSetNum == null) {
            return getLotteryRes(redNum, blueNum)
        }
        val excludeRedNum = redSetNum.size
        val excludeBlueNum = blueSetNum.size
        println("excludeRedNum=$excludeRedNum,excludeBlueNum=$excludeBlueNum")
        if (33 - excludeRedNum <= redNum || 16 - excludeBlueNum <= blueNum) {
            return null
        }
        val set: MutableSet<String> = TreeSet()
        while (true) {
            val sui = (Math.random() * 33).toInt() + 1 // 获取33以内的数字
            val suiStr = if (sui < 10) "0$sui" else "" + sui
            if (!redSetNum.contains(suiStr)) {
                set.add(suiStr) //将元素存入集合中
            }
            if (set.size == redNum) { //存满N个红色球，则结束循环
                break
            }
        }
        val set2: MutableSet<String> = TreeSet()
        while (true) {
            val sui2 = (Math.random() * 16).toInt() + 1 // 获取16以内的数字
            val sui2Str = if (sui2 < 10) "0$sui2" else "" + sui2
            if (!blueSetNum.contains(sui2Str)) {
                set2.add(sui2Str)
            }
            if (set2.size == blueNum) { //存满N个蓝色球，则结束循环
                break
            }
        }
        val result = "红球：$set 蓝球：$set2"
        println(result)
        return result
    }

    /**
     * 排除特定红球和蓝球 之红球
     * @param redSetNum
     * @return
     */
    fun getLotteryResRedBall(redSetNum: Set<String?>?, redNum: Int): String? {
        if (redSetNum == null) {
            return getLotteryResRedBall(redNum)
        }
        val excludeRedNum = redSetNum.size
        if (33 - excludeRedNum <= redNum) {
            return null
        }
        val set: MutableSet<String> = TreeSetNew()
        while (true) {
            val sui = (Math.random() * 33).toInt() + 1 // 获取33以内的数字
            val suiStr = if (sui < 10) "0$sui" else "" + sui
            if (!redSetNum.contains(suiStr)) {
                set.add(suiStr) //将元素存入集合中
            }
            if (set.size == redNum) { //存满N个红色球，则结束循环
                break
            }
        }
        println("红球：$set")
        return set.toString()
    }

    /**
     * 排除特定红球和蓝球 之蓝球
     * @param blueSetNum
     * @return
     */
    fun getLotteryResBlueBall(blueSetNum: Set<String?>?, blueNum: Int): String? {
        if (blueSetNum == null) {
            return getLotteryResBlueBall(blueNum)
        }
        val excludeBlueNum = blueSetNum.size
        if (16 - excludeBlueNum <= blueNum) {
            return null
        }
        val set2: MutableSet<String> = TreeSetNew()
        while (true) {
            val sui2 = (Math.random() * 16).toInt() + 1 // 获取16以内的数字
            val sui2Str = if (sui2 < 10) "0$sui2" else "" + sui2
            if (!blueSetNum.contains(sui2Str)) {
                set2.add(sui2Str)
            }
            if (set2.size == blueNum) { //存满N个蓝色球，则结束循环
                break
            }
        }
        println("蓝球：$set2")
        return set2.toString()
    }

    /**
     * 随机双色球
     * @return
     */
    fun getLotteryRes(redNum: Int, blueNum: Int): String {
        val set: MutableSet<String> = TreeSet()
        while (true) {
            val sui = (Math.random() * 33).toInt() + 1 // 获取33以内的数字
            set.add(if (sui < 10) "0$sui" else "" + sui) //将元素存入集合中
            if (set.size == redNum) { //存满N个红色球，则结束循环
                break
            }
        }
        val set2: MutableSet<String> = TreeSet()
        while (true) {
            val sui2 = (Math.random() * 16).toInt() + 1 // 获取16以内的数字
            set2.add(if (sui2 < 10) "0$sui2" else "" + sui2)
            if (set2.size == blueNum) { //存满N个蓝色球，则结束循环
                break
            }
        }
        val result = "红球：$set 蓝球：$set2"
        println(result)
        return result
    }

    /**
     * 随机双色球之 红球
     * @return
     */
    fun getLotteryResRedBall(redNum: Int): String {
        val set: MutableSet<String> = TreeSetNew()
        while (true) {
            val sui = (Math.random() * 33).toInt() + 1 // 获取33以内的数字
            set.add(if (sui < 10) "0$sui" else "" + sui) //将元素存入集合中
            if (set.size == redNum) { //存满N个红色球，则结束循环
                break
            }
        }
        println("红球：$set")
        return set.toString()
    }

    /**
     * 随机双色球之 蓝球
     * @return
     */
    fun getLotteryResBlueBall(blueNum: Int): String {
        val set2: MutableSet<String> = TreeSetNew()
        while (true) {
            val sui2 = (Math.random() * 16).toInt() + 1 // 获取16以内的数字
            set2.add(if (sui2 < 10) "0$sui2" else "" + sui2)
            if (set2.size == blueNum) { //存满N个蓝色球，则结束循环
                break
            }
        }
        println("蓝球：$set2")
        return set2.toString()
    }

    @JvmStatic
    fun main(args: Array<String>) {
        //非排除
//        getLotteryRes(6, 1);
//        getLotteryRes(12, 5);
//        getLotteryRes(20, 16);
//
        val set1: MutableSet<String?> = TreeSet()
        set1.add("03")
        set1.add("06")
        set1.add("08")
        set1.add("20")
        set1.add("24")
        set1.add("32")
        val set2: MutableSet<String> = TreeSet()
        set2.add("03")
        val set3: MutableSet<String> = TreeSet()
        set3.add("03")
        set3.add("06")
        set3.add("08")
        set3.add("20")
        set3.add("24")
        set3.add("32")
        set3.add("33")
        val set4: MutableSet<String?> = TreeSet()
        set4.add("03")
        set4.add("04")
        set4.add("05")
        set4.add("06")
        set4.add("07")
        set4.add("08")

        //排除
//        getLotteryRes(set1, "07");
//        getLotteryRes(set1, set2, 7, 1);
//        getLotteryRes(set3, set4, 7, 1);
//        getLotteryRes(set1, set2, 8, 2);
//        getLotteryRes(set3, set4, 8, 2);

        //红球
        getLotteryResRedBall(6)
        getLotteryResRedBall(8)
        getLotteryResRedBall(set1, 6)
        getLotteryResRedBall(set1, 12)
        //蓝球
        getLotteryResBlueBall(1)
        getLotteryResBlueBall(2)
        getLotteryResBlueBall(set4, 1)
        getLotteryResBlueBall(set4, 9)
    }
}