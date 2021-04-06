package com.wuhai.kotlin_lib

/**
 * Kotlin常用高阶函数
 * https://blog.csdn.net/xingzhong128/article/details/79901218/
 *
 */
fun main(){
    HigherFunction02()
}

fun HigherFunction02(){
    val display: (MyInt) -> Unit = MyInt::show

    fun String?.isEmpty(): Boolean = this == null || this == ""
    val isEmpty: (String) -> Boolean = String::isEmpty
}
class MyInt(val value: Int) {
    fun show() {
        println(value)
    }
}

fun HigherFunction01(){
    // 这种引用适用于lambda表达式只有一个函数调用并且
    // 这个函数的参数也是这个lambda表达式的参数
    var args : ArrayList<String> = arrayListOf();
    args.add("123")
    args.add("123")
    args.add("123")
    args.add("123")
    args.forEach(::println)
}