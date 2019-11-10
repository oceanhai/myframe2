package com.example.java_lib;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

    public static void main(String[] args){
        method01();
    }

    /**
     * put 新<key,value>  返回null
     * put 旧<key,value>  返回value
     */
    public static void method02(){
        Map<String,String> map = new HashMap<>();
        String str1 = map.put("1","1");
        System.out.println("str1="+str1);
        String str2 = map.put("1","1");
        System.out.println("str2="+str2);
    }

    /**
     *
     * 构造方法
     * initialCapacity - 初始容量
     * loadFactor - 加载因子
     * accessOrder - 排序模式 - 对于访问顺序，为 true；对于插入顺序，则为 false
     *
     * 总结：get后的最后打印，符合lru算法
     */
    public static void method01(){
        LinkedHashMap<Integer, Integer> map =
                new LinkedHashMap<>(0, 0.75f, true);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        map.put(6, 6);
//        map.get(1);
//        map.get(2);
        map.remove(6);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());

        }
    }
}
