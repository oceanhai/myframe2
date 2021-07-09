package com.wuhai.kotlin_lib.designpattern.dynamicproxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

/**
 * 中介
 *
 * 动态代理最主要的部分在于代理对象实现InvocationHandler，并重写invoke方法。
 * 当代理对象代理了委托者的要求，不管要求有多少，当代理执行时，都会走进invoke()方法中。这是重点，圈起来后面要考。
 */
class Middlemen(private val any: Any) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        return method?.invoke(any, *(args ?: arrayOfNulls<Any>(0)))
    }
}