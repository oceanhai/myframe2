package com.example.java_lib.pactera.yidongjr;


import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainTest {

    public static void main(String[] args){

        //模拟vue离线包 按需下载
//        method01();
//        System.out.println("-----------------------------------");
//        method02();

//        method03();
//        method04();
//        method05();

        method06();
    }

    /**
     * 模拟 原生盒子  解析器 问题
     */
    private static void method06() {
        //模拟1
//        Person person = new Person("wuhai", 36);
//        changeAge(person);
//        System.out.println(person.toString());

        Map<String, Person> map = new HashMap();
        map.put("person", new Person("wh", 36));
        //模拟2 parse里不对list clear
//        List<String> result = new ArrayList<>();
//        result.addAll(map.get("person").parse("hello"));
//        result.addAll(map.get("person").parse("你好"));
//        System.out.println(result.toString());//[hello, hello, 你好]
        //模拟3 parse里对list 先clear
        List<String> result = new ArrayList<>();
        result.addAll(map.get("person").parse("hello"));
        result.addAll(map.get("person").parse("你好"));
        System.out.println(result.toString());//[hello, hello, 你好]
    }

    private static void method05() {
        int[] DEFAULT_MARGIN = new int[] {0, 0, 0, 0};
        System.out.println("int[] ="+DEFAULT_MARGIN.toString());

    }

    private static void method04() {
        List<Integer> list = new ArrayList<>();
        for(int x=0;x<4;x++){
            list.add(0);
        }
        System.out.println("list="+list.toString());
    }

    private static void method03() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("msg", "你好");
        jsonObject.put("result", new JSONObject().toString());

        System.out.println("jsonObject="+jsonObject.toString());
    }

    private static void method01() {
        List<PackageListEntity> packageList = new ArrayList<>();
        for(int x=0;x<5;x++){
            PackageListEntity entity = new PackageListEntity();
            entity.setAppId(""+x);
            if(x != 1 && x != 3){
                entity.setPreDownload(0);
                entity.setOfflineStatus(OfflineStatus.INIT);
            }else if(x == 1){
                entity.setPreDownload(1);
                entity.setOfflineStatus(OfflineStatus.INIT);
            }else if(x == 3){
                entity.setPreDownload(0);
                entity.setOfflineStatus(OfflineStatus.READY);
            }
            packageList.add(entity);
        }
        for(PackageListEntity packageItem : packageList) {
//            checkOfflinePackageUpdate(packageItem);

            if(packageItem.getOfflineStatus()==OfflineStatus.READY){
//                return;
                continue;
            }

            System.out.println("数据库插入 appId="+packageItem.getAppId());
            if(packageItem.getPreDownload() == 1){
                continue;
            }
            System.out.println("下载并解压 appId="+packageItem.getAppId());
        }
    }

    private static void method02() {
        List<PackageListEntity> packageList = new ArrayList<>();
        for(int x=0;x<5;x++){
            PackageListEntity entity = new PackageListEntity();
            entity.setAppId(""+x);
            if(x != 1 && x != 3){
                entity.setPreDownload(0);
                entity.setOfflineStatus(OfflineStatus.INIT);
            }else if(x == 1){
                entity.setPreDownload(1);
                entity.setOfflineStatus(OfflineStatus.INIT);
            }else if(x == 3){
                entity.setPreDownload(0);
                entity.setOfflineStatus(OfflineStatus.READY);
            }
            packageList.add(entity);
        }
        for(PackageListEntity packageItem : packageList) {
            checkOfflinePackageUpdate(packageItem);
        }
    }

    private static void checkOfflinePackageUpdate(PackageListEntity packageItem) {
        if(packageItem.getOfflineStatus()==OfflineStatus.READY){
                return;
        }

        System.out.println("数据库插入 appId="+packageItem.getAppId());
        if(packageItem.getPreDownload() == 1){
            return;
        }
        System.out.println("下载并解压 appId="+packageItem.getAppId());
    }


}
