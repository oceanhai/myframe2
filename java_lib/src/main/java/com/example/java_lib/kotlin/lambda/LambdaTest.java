package com.example.java_lib.kotlin.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * jdk1.8新特性：Lambda表达式
 * https://blog.csdn.net/weixin_43365369/article/details/91129356
 */
public class LambdaTest {

    public static void main(String[] args) {

        method03();
    }

    /**
     * Lambda的使用前提
     * 1、使用Lambda必须具有接口，且要求接口中有且仅有一个抽象方法。
     * 无论是JDK内置的Runnable、Comparator接口还是自定义的接口，只有当接口中的抽象方法存在且唯一时，才可以使用Lambda。
     * 2、使用Lambda必须具有上下文推断。
     * 也就是方法的参数类型必须为Lambda对应的接口类型，才能使用Lambda作为该接口的实例。
     */
    public static void method04(){

    }

    /**
     * 使用Lambda的标准格式调用invokeCalc方法，完成120和130的相加计算
     */
    public static void method03(){

        //Lambda
        invokeCalc(120, 130, (int a, int b)->{
            return a+b;
        });

        //Lambda省略格式
        /**
         * 省略规则：
         * 1小括号内参数的类型可以省略；
         * 2如果小括号内有且仅有一个参数，则小括号可以省略；
         * 3如果大括号内有且仅有一个语句，则无论是否有返回值，都可以省略大括号、return关键字及语句分号。
         * 注意：关于第三点如果省略一个，那么必须同时都得省略。
         */
        invokeCalc(120,130,(a,b)->a+b);
        invokeCook(()-> System.out.println("德玛西亚"));

        //正常匿名内部类
        invokeCalc(120, 130, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a+b;
            }
        });
    }

    private static void invokeCalc(int a, int b, Calculator calculator) {
        int result = calculator.calc(a, b);
        System.out.println("结果是：" + result);
    }

    public interface Calculator {
        int calc(int a, int b);
    }

    /**
     * Lambda的参数和返回值
     * 使用Lambda表达式对存在数组中的Person类型的对象数据按照年龄的降序输出
     */
    public static void method022(){
// 本来年龄乱序的对象数组
        Person[] array = {
                new Person("古力娜扎", 19),
                new Person("迪丽热巴", 18),
                new Person("马尔扎哈", 20) };

        Arrays.sort(array, (Person p1, Person p2)->{
            return p2.getAge() - p1.getAge();
        });

        for (Person person : array) {
            System.out.println(person);
        }
    }

    public static void method021(){
        // 本来年龄乱序的对象数组
        Person[] array = {
                new Person("古力娜扎", 19),
                new Person("迪丽热巴", 18),
                new Person("马尔扎哈", 20) };

        // 匿名内部类
        Comparator<Person> comp = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        };
        Arrays.sort(array, comp); // 第二个参数为排序规则，即Comparator接口实例

        for (Person person : array) {
            System.out.println(person);
        }
    }

    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    //使用Lambda标准格式（无参无返回）
    public static void method01(){

        invokeCook(() -> {
            System.out.println("吃饭啦！");
        });

        invokeCook(()-> System.out.println("吃喝嫖赌抽"));

        invokeCook(new Cooker() {
            @Override
            public void makeFood() {
                System.out.println("吃吃吃！");
            }
        });
    }


    private static void invokeCook(Cooker c){
        c.makeFood();
    }

    public interface Cooker {
        void makeFood();
    }
}
