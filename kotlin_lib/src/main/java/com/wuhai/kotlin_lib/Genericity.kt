package com.wuhai.kotlin_lib


/**
 * Android Kotlin(四) 泛型
 * https://blog.csdn.net/chennai1101/article/details/104274046
 */
fun main(){
    genericity05()
}

/**
 * 类型投射
 * 类A是类B的父类，但Array<A>和Array<B>不是继承关系，out和in关键字可以对类型投射。
 * 当我们不知道泛型参数的类型信息时，但仍需要安全的使用它时，可以使用星投影，用星号*表示。
 * 如果类型定义为interface Function<in T, out U>，那么可以出现以下几种信号投射。
    Function<*, String>，代表Function<in Nothing, String>
    Function<Int, *>，代表Function<Int, Out Any?>
    Function<*, *>，代表Function<in Nothing, Out Any?>
 */
fun genericity05(){
    var aArr: Array<A> = arrayOf(A(), A())
    var bArr: Array<B> = arrayOf(B(), B())

    copy(bArr, aArr)
    fill(aArr, B())
}
open class A{}
class B : A() {}
//Kotlin 中的out A类似于 Java 中的? extends A，即泛型参数类型必须是A或者A的子类，用来确定类型的上限
fun copy(from: Array<out A>, to: Array<A>) {
}
//Kotlin 中的in B类似于 Java 中的? super B，即泛型参数类型必须是B或者B的父类，用来确定类型的下限
fun fill(dest: Array<in B>, v: B) {
}

/**
 * 类型变异
 * kotlin泛型没有提供通配符，取而代之的是out和in关键字。
 *
 */
fun genericity04(){
    /**
     * 用out声明的泛型占位符只能在获取泛型类型值的地方，如函数的返回值。
     */
    var strData = OutData<String>("Hello World")
    println(strData.getValue())
//    var objData: OutData<Any> = OutData("Object")
    var objData: OutData<Any> = OutData(123)
    println(objData.getValue())
    objData = strData  // OutData<String>可以看做OutData<Any>的子类
    println(objData.getValue())

    /**
     * 用in声明的泛型占位符只能在设置泛型值的地方，如函数的参数
     * TODO 没搞懂
     */
    var intData = InData<Int>()
    println(intData)
    var anyData = InData<Any>()

    intData = anyData  // InData<Any>可以看做InData<Int>的子类
}
class InData<in T>() {
    fun setValue(t: T) {

    }
}
class OutData<out T>(val v: T) {

    fun getValue(): T {
        return v
    }
}

/**
 * 泛型约束
 * 泛型约束常见的是上界约束，类似于Java的extends关键字，在冒号(:)后面指定的类型就是泛型参数的上界。
 * 如果没有指定，则默认使用的上界类型是Any?
 */
fun genericity03(){
    println(equals(1, 1))  // true
    println(equals(1, 2))  // false
    println(equals("1", "2"))  // false
    println(equals("1", "1"))  // true
}
//在定义泛型的尖括号<>内，只允许定义唯一一个上界，如果需要指定多个上界，使用单独的where子句。
interface ICopy<T> {
    fun copy(): T
}
fun <T> copy(t: T) : T
        where T : Comparable<T>, T : ICopy<T> {
    return t.copy()
}
fun <T : Comparable<T>> equals(v1: T, v2: T): Boolean {
    return v1.compareTo(v2) == 0
}

/**
 * 泛型函数
 */
fun genericity02(){
    println(copy(Data(12)).value)
}

fun <T> copy(d: Data<T>): Data<T> {
    return Data(d.value)
}


/**
 * 泛型类型变量
 */
fun genericity01(){
    // 创建类的实例时指定类型参数
    var strData: Data<String> = Data("Hello World")
    // 编译器会进行类型推断
    var intData = Data(12)
    var intData2:Data<Int> = Data(12)

    println(strData.value)  // Hello World
    println(intData.value)  // 12
    println(intData2.value)  // 12
}

class Data<T>(v: T) {
    var value = v
}