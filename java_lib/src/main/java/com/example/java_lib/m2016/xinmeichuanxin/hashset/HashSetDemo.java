package com.example.java_lib.m2016.xinmeichuanxin.hashset;

import java.util.HashSet;
import java.util.Iterator;

class Person
{
    private String name;
    private int age;
    Person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    public int hashCode()
    {
        System.out.println(this.name+"......hashCode");
        return 60;
    }
    public boolean equals(Object obj)
    {

        if(!(obj instanceof Person))
            return false;
        Person p=(Person)obj;
        System.out.println(this.name+"...."+p.name);
        return this.name.equals(p.name) && this.age == p.age;
    }

}

public class HashSetDemo {

    public static void sop(Object obj)
    {
        System.out.println(obj);
    }
    public static void main(String[] args) {
        HashSet hs=new HashSet<>();

        hs.add(new Person("a1",11));
        hs.add(new Person("a2",12));
        hs.add(new Person("a2",12));
        hs.add(new Person("a3",13));

        Iterator it=hs.iterator();
        while(it.hasNext()){
            Person p=(Person)it.next();
            sop(p.getName()+"::"+p.getAge());
        }
    }

}
/*思路：
 * 1，对人描述，将数据封装进人对象
 * 2，定义容器，将人存入
 * 3，取出
 */
