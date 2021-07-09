package com.wuhai.kotlin_lib.designpattern.dynamicproxy

import java.lang.reflect.Proxy

/**
 * 买家委托中介购房。
 */
interface ToDo {
    fun buyHouse()
}

/**
 * 动态代理
 * https://juejin.cn/post/6982315510592045064?utm_source=gold_browser_extension
 *
 * 可以比较java_lib里的动态代理实现方式，两者内涵是一样的，代码写法不同
 */
fun main(args: Array<String>) {
    val toDo: ToDo = Buyer()
    val dynamicMid = Proxy.newProxyInstance(
            toDo.javaClass.classLoader, toDo.javaClass.interfaces,
            Middlemen(toDo)
    ) as ToDo
    dynamicMid.buyHouse()
}
