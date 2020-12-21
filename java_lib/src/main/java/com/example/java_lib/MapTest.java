package com.example.java_lib;

import com.example.java_lib.pactera.GsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {

    public static void main(String[] args){
        Map<String,String> map = new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");

        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("----------------------");
        map.put("1","4");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println("key="+entry.getKey()+",value="+entry.getValue());
        }
        System.out.println("----------------------");
        Map<String,Long> map2 = new HashMap<>();
        map2.put("1",123L);
        map2.put("2",345L);
        System.out.println("get 1 :"+map2.get("1"));
        System.out.println("get 3 :"+map2.get("3"));
        Long pageStartTime = map2.get("3");
        System.out.println("pageStartTime = " + pageStartTime);

        map2.remove("1");
        System.out.println("remove 后再次get 1 :"+map2.get("1"));

        System.out.println("----------------------gson解析map");
        //{"name":"wuhai","age":18}
        Map<String,Object> map3 = new HashMap<>();
        map3.put("age",18);
        map3.put("name","wuhai");
        map3.put("sex","男");
        System.out.println(GsonUtils.getInstance().mapToJson(map3));
        //[{aaa:"xxx"},{bbb:xxx}]
        //
        List<Student> list = new ArrayList<>();
        Student student = new Student();
        student.setName("吴海");
        student.setAge(18);
        Student student2 = new Student();
        student2.setName("冠华");
        student2.setAge(18);
        list.add(student);
        list.add(student2);
        System.out.println(GsonUtils.getInstance().toJson(list));

    }
}
