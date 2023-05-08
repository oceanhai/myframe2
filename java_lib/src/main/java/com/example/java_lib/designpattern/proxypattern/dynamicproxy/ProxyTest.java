package com.example.java_lib.designpattern.proxypattern.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

//import sun.misc.ProxyGenerator;

/**
 * https://www.cnblogs.com/gonjan-blog/p/6685611.html
 * 动态代理
 */
public class ProxyTest {

    /**
     * 我们执行这个ProxyTest类，先想一下，我们创建了一个需要被代理的学生张三，
     * 将zhangsan对象传给了stuHandler中，我们在创建代理对象stuProxy时，将stuHandler作为参数了的，
     * 上面也有说到所有执行代理对象的方法都会被替换成执行invoke方法，也就是说，
     * 最后执行的是StuInvocationHandler中的invoke方法。所以在看到下面的运行结果也就理所当然了。
     *
     * 上面说到，动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法。
     * 是因为所有被代理执行的方法，都是通过在InvocationHandler中的invoke方法调用的，
     * 所以我们只要在invoke方法中统一处理，就可以对所有被代理的方法进行相同的操作了。
     * 例如，这里的方法计时，所有的被代理对象执行的方法都会被计时，然而我只做了很少的代码量。
     */
    public static void main(String[] args) {
        proxy();

//        look();
    }

    private static void proxy() {
        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");

        //创建一个与代理对象相关联的InvocationHandler
        InvocationHandler stuHandler = new StuInvocationHandler<Person>(zhangsan);

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(),
                new Class<?>[]{Person.class}, stuHandler);

        //代理执行上交班费的方法
        stuProxy.giveMoney();

        //交作业  TODO 我自己追加的
        stuProxy.handInHomework();
    }

//    private static void look() {
//        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
//        String path = "D:/java_lib/designpattern/StuProxy.class";
//        try(FileOutputStream fos = new FileOutputStream(path)) {
//            fos.write(classFile);
//            fos.flush();
//            System.out.println("代理类class文件写入成功");
//        } catch (Exception e) {
//            System.out.println("写文件错误");
//        }
//    }


}
