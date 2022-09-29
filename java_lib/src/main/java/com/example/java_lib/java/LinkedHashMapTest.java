package com.example.java_lib.java;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static final void main(String[] args) {
        /**
         * accessOrder = true    对于访问顺序
         * 输出结果 即最近访问的最后输出
         * 0:0
         * 3:3
         * 4:4
         * 5:5
         * 6:6
         * 1:1
         * 2:2
         *
         * accessOrder = false  对于插入顺序
         * 输出结果 即最近访问的最后输出
         * 0:0
         * 1:1
         * 2:2
         * 3:3
         * 4:4
         * 5:5
         * 6:6
         */
        LinkedHashMap<Integer, Integer> map =
                new LinkedHashMap<>(0, 0.75f, false);
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.put(4, 4);
        map.put(5, 5);
        Integer value1 = map.put(6, 6);
        System.out.println("value-1:"+value1);//插入的key是第一次插入 返回null
        Integer value2 = map.put(6, 6);
        System.out.println("value-2:"+value2);//插入的key非第一次插入 返回value
        map.get(1);
        map.get(2);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }
}
