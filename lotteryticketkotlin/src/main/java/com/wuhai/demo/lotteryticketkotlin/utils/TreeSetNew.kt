package com.wuhai.demo.lotteryticketkotlin.utils

import java.util.*

class TreeSetNew<E> : TreeSet<E>() {
    override fun toString(): String {
        val it: Iterator<E?> = iterator()
        if (!it.hasNext()) return ""
        val sb = StringBuilder()
        while (true) {
            val e = it.next()
            sb.append(if (e === this) "(this Collection)" else e)
            if (!it.hasNext()) return sb.toString()
            sb.append(',')
        }
    }
}