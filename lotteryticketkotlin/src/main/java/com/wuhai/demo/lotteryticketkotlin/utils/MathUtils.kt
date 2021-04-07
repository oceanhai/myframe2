package com.wuhai.demo.lotteryticketkotlin.utils

object MathUtils {
    /**
     * C n m = n(n-1)(n-2)...(n-m+1)/m!
     * @param n
     * @param m
     * @return
     */
    fun getCombinationNum(n: Int, m: Int): Int {
        val num = -1
        if (n <= 0 || m < 0 || n < m) {
            return num
        }
        if (m == 0) {
            return 1
        }

        //m的阶乘
        val num2 = factorial(m)
        println("num2=$num2")
        //算被除数
        var num1 = n
        for (x in 2..m) {
            num1 = num1 * (n - x + 1)
            println("num1=$num1")
        }
        println("num1=$num1")
        return num1 / num2
    }

    /**
     * num的阶乘
     * @param num
     */
    fun factorial(num: Int): Int {
        return if (num == 1) {
            1
        } else {
            num * factorial(num - 1)
        }
    }
}