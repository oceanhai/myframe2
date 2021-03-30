package com.wuhai.kotlin_lib

import java.util.*
import kotlin.collections.ArrayList

/**
 * Kotlin中 ?、!!、?:、:: 、->符号的简单说明
 * https://blog.csdn.net/qq_32534441/article/details/103876956
 */
fun main() {

    method03()

}

/**
 * 参数 combine 具有函数类型 (R, T) -> R，因此 fold 接受一个函数作为参数，
 * 该函数接受类型分别为 R 与 T 的两个参数并返回一个 R 类型的值。
 * 在 for-循环内部调用该函数，然后将其返回值赋值给 accumulator。
 *
 * (((φ(◎ロ◎;)φ))) 看的，感觉还是java看的舒服
 */
fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}

/**
 * ::
 * Kotlin 中 双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法。
 */
fun method03(){
    //Kotlin 中 双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法。先来看一下例子
    println(lock("param1", "param2", ::getResult))

    //如果我们需要调用其他 Class 中的某一个方法是：
    val d = d();
    println(lock("param1", "param2", d::getResult))

    d.test()
}

class d{

    fun test(){
        println(lock("param1", "param2", ::getResult))
        /**
         * 一般情况，我们调用当前类的方法 this 都是可省略的，这里之所以不可省略的原因是
         * 为了防止作用域混淆 ， :: 调用的函数如果是类的成员函数或者是扩展函数，必须使用限定符,比如this
         */
        println(lock("param1", "param2", ::getResult2))
        println(lock("param1", "param2", this::getResult))
    }

    fun getResult(str1: String, str2: String): String = "引用D的getResult {$str1 , $str2}"
//    fun getResult2(str1: String, str2: String): String = "引用D的getResult2 {$str1 , $str2}"
}

/**
 * @param str1 参数1
 * @param str2 参数2
 */
fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}"
fun getResult2(str1: String, str2: String): String = "result2 is {$str1 , $str2}"

/**
 * @param p1 参数1
 * @param p2 参数2
 * @param method 方法名称
 */
fun lock(p1: String, p2: String, method: (str1: String, str2: String) -> String): String {
    return method(p1, p2)
}


/**
 * ?：
 * 对象A ?: 对象B 表达式，意思为，当对象 A值为 null 时，那么它就会返回后面的对象 B。
 */
fun method02(){
    /*
    报错
     */
//    val roomList: ArrayList<String>? = null
//    if (roomList?.size > 0) {
//        println("-->> 列表数不是0")
//    }

    val roomList: ArrayList<String>? = null
    val mySize= roomList?.size ?: 0
    println("mySize = $mySize")

    /*
    解决 上面报错的 正确方式
     */
    val roomList2: ArrayList<String>? = null
    if (roomList2?.size?:0 > 0) {
        println("-->> 列表数不是0")
    }else{
        println("-->> 列表数是0")
    }
}

/**
 * ? 和 !!
 * "?"加在变量名后，系统在任何情况不会报它的空指针异常。
 * "!!"加在变量名后，如果对象为null，那么系统一定会报异常！
 */
fun method01(){
    val myList: ArrayList<String> ? = null //  创建一个null的队列
//    val name1 : String  = null;//报错
    val name2 : String  = "wuhai";
    val name3 : String ? = null;
//    println(" List Size = " + myList!!.size)
    println(" List Size = " + myList?.size)
}