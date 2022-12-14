package com.example.java_lib.pactera.yidongjr;

/**
 * date： 2020/6/17 15:04
 * author： rwz
 * description：
 **/
public interface OfflineStatus {

    //初始化
    int INIT = 0;
    //网络请求失败(该状态不会记录到数据库)
    int NET_ERROR = 1;
    //网络请求完成(该状态不会记录到数据库，真实应该是离线包检测更新接口响应成功返回)
    int NET_CHECK_VALIDATE_COMPLETED = 2 <<6;//128
    //就绪，安装完成
    int READY = 2;
    //下载中
    int DOWNLOADING = 2 << 1;//4
    //下载完成
    int DOWNLOAD_COMPLETED = 2 << 2;//8
    //下载失败
    int DOWNLOAD_FAIL = 2 << 3;//16
    //安装中
    int INSTALLING = 2 << 4;//32
    //安装失败
    int INSTALL_FAIL = 2 << 5;//64
    //下载中（差量包）
    int PATCH_DOWNLOADING = 2 << 7;//256
    //下载完成（差量包）
    int PATCH_DOWNLOAD_COMPLETED = 2 << 8;//512
    //下载失败（差量包）
    int PATCH_DOWNLOAD_FAIL = 2 << 9;//1024
    //合并(差量包）
    int PATCH_MERGING = 2 << 10;//2048
    //合并失败(差量包）
    int PATCH_MERGING_FAIL = 2 << 11;//4096

}


