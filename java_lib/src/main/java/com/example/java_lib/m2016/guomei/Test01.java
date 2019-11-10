package com.example.java_lib.m2016.guomei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test01 {

    public static void main(String[] args){

        List<String> list = new ArrayList<>();
        list.add("123");

        List<List<String>> list1 = new ArrayList<>();
        list1.add(list);

        Map<String,String> map = new HashMap<>();
        map.put("1","123");
        Map<String,String> map2 = new HashMap<>();
        map.put("2","123");

        List<Map<String,String>> list2 = new ArrayList<>();
        list2.add(map);
        list2.add(map2);


    }
}
