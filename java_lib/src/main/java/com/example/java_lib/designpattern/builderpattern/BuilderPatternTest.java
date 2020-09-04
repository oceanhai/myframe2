package com.example.java_lib.designpattern.builderpattern;


/**
 * Created by wuhai on 2017/11/13 14:39.
 * 描述：建造者模式
 * 参考文章 https://blog.csdn.net/sinat_35821285/article/details/80004060
 */

public class BuilderPatternTest {

    public static void main(String[] args){
//        PersonBuilder builder = new ManBuilder();
//        Person person =  builder.buildHead().buildBody().buildFoot().buildPerson();
//        System.out.println(person.toString());

        //方式1
        PersonDirector director = new PersonDirector();
        Person man = director.constructPerson(new ManBuilder());
        Person woman = director.constructPerson(new WomanBuilder());
        System.out.println(man.toString());
        System.out.println(woman.toString());

        //方式2
        BuildMan buildMan =new BuildMan.Builder()
                .buildHead("建造头部")
                .buildBody("建造身体")
                .buildFoot("建造脚")
                .build();
        System.out.println(buildMan.toString());
        BuildMan buildMan1 = new BuildMan.Builder()
                .buildHead("龙头")
                .buildBody("龙身")
                .buildFoot("龙尾")
                .build();
    }

}
