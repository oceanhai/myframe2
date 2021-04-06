package com.wuhai.kotlin_lib

import java.util.*
import kotlin.collections.ArrayList

/**
 * Kotlin中 ?、!!、?:、:: 、->符号的简单说明
 * https://blog.csdn.net/qq_32534441/article/details/103876956
 */
fun main() {

    method04()

}

/**
 * “不安全的” 类型转换操作符
 * 如果类型转换不成功，类型转换操作符通常会抛出一个异常。因此，我们称之为 不安全的(unsafe)。在 Kotlin 中，
 * 不安全的类型转换使用中缀操作符 as
 *
 */
fun method04(){
//    val y = null
//    val x: String = y as String
// 输出

    /**
     * 注意 null 不能被转换为 String，因为这个类型不是 可为 null 的(nullable)，也就是说，如果 y 为 null，
     * 上例中的代码将抛出一个异常。为了实现与 Java 相同的类型转换，我们需要在类型转换操作符的右侧使用可为 null 的类型，
     * 比如：
     */
//    val y = null
//    val x: String? = y as String?
//    println("x = $x")   // x = null

    /**
     * 上述代码，表示允许 String 可空，这样当 y = null 时，不会抛异常；但是，当类型转换失败时，还是会崩溃，如下：
     */
//    val y = 66
//    val x: String? = y as String?

    /**
     * 二、“安全的” (nullable) 类型转换操作
     * 为了避免抛出异常，你可以使用 安全的 类型转换操作符 as?，当类型转换失败时，它会返回 null，但不会抛出异常崩溃：
     */
    val y = 66
    val x: String? = y as? String
    println("x = $x")   // x = null

    val a = null
    val b: String? = a as? String
    println("b = $b")   // b = null


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