package com.example.java_lib.pactera.yidongjr;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args){

        method01();
        System.out.println("-----------------------------------");
        method02();

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
