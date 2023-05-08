package com.example.java_lib.enum1;

/**
 * 作者 wh
 *
 * 创建日期 2023/5/8 15:18
 *
 * 描述：参考文章 https://juejin.cn/post/7148090723907141662
 */
public class EnumMain {

    public static void main(String[] args) {

        seasonTest();
        weekDayTest();

    }

    private static void weekDayTest() {
        Weekday day = Weekday.SUN;
        if (day.dayValue == 6 || day.dayValue == 0) {
            System.out.println("Today is " + day + ". Work at home!");
        } else {
            System.out.println("Today is " + day + ". Work at office!");
        }
//        使用 valueOf() 返回枚举常量，不存在的会报错 IllegalArgumentException
        //   System.out.println(Season.valueOf("summer"));
        //    System.out.println(Season.valueOf("summ"));
    }

    private static void seasonTest() {
        //        迭代季节
        for (Season season : Season.values()) {
            System.out.println(season);
        }

        //        使用 valueOf() 返回枚举常量，不存在的会报错 IllegalArgumentException
        System.out.println("valueOf():"+Season.valueOf("summer"));
        //    System.out.println(Season.valueOf("summ"));

        //        迭代季节
        for (Season season : Season.values()) {
//            ordinal可以找到每个枚举常量的索引，就像数组索引一样。
            System.out.println(season+"  索引为  "+season.ordinal());
        }

        /**
         * 当此Enum对象等于或等于给定的Enum对象时，它将返回0。
         * 当此Enum对象大于给定的Enum对象时，它将返回正值。
         * 当此Enum对象小于给定的Enum对象时，它将返回负值。
         */
        Season a;
        Season b;
        //单独用SeasonEnum做实参报错，但是加上后缀.class就没问题了
        a=Season.valueOf(Season.class,"spring");
        System.out.println("compareTo-1:"+a.compareTo(Season.spring));
        //上面调用主体是SeasonEnum，这里是Enum。需要说明一下：所有枚举类都继承了java.lang.Enum类。
        b=Enum.valueOf(Season.class,"summer");
        System.out.println("compareTo-2:"+b.compareTo(Season.summer));
        System.out.println("compareTo-3:"+a.compareTo(Season.summer));
        System.out.println("compareTo-4:"+b.compareTo(Season.spring));
    }
}
