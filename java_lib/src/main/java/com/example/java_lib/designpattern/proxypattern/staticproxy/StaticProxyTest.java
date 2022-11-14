package com.example.java_lib.designpattern.proxypattern.staticproxy;


/**
 * https://www.cnblogs.com/gonjan-blog/p/6685611.html
 * java动态代理实现与原理详细分析
 * 1.静态代理
 */
public class StaticProxyTest {


    /**
     * 根据上面代理模式的类图，来写一个简单的静态代理的例子。我这儿举一个比较粗糙的例子，
     * 假如一个班的同学要向老师交班费，但是都是通过班长把自己的钱转交给老师。这里，班长就是代理学生上交班费，
     *
     * 班长就是学生的代理。
     *
     *     首先，我们创建一个Person接口。这个接口就是学生（被代理类），和班长（代理类）的公共接口，
     *     他们都有上交班费的行为。这样，学生上交班费就可以让班长来代理执行。
     *
     * 这里并没有直接通过张三（被代理对象）来执行上交班费的行为，而是通过班长（代理对象）来代理执行了。这就是代理模式。
     */
    public static void main(String[] args) {

//        proxy1();
        proxy2();

    }

    private static void proxy1() {
        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Person zhangsan = new Student("张三");

        //生成代理对象，并将张三传给代理对象
        Person monitor = new StudentsProxy(zhangsan);

        //班长代理上交班费
        monitor.giveMoney();
    }

    private static void proxy2() {
        //被代理的学生张三，他的班费上交有代理对象monitor（班长）完成
        Person zhangsan = new Student("张三");

        //生成代理对象，并将张三传给代理对象
        Person monitor = new StudentsProxy2(zhangsan);

        //班长代理上交班费
        monitor.giveMoney();
    }
}
