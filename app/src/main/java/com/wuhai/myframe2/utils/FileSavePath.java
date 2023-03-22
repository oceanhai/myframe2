package com.wuhai.myframe2.utils;

import android.text.TextUtils;

import com.wuhai.myframe2.application.BaseApplication;

import java.io.File;

/**
 * Created by thornpear on 2019/11/6.
 * 文件保存路径
 */

public class FileSavePath {
    /**
     * 获取应用外部存储/file
     *
     * @return
     */
    public static String getStoragePath() {
        return BaseApplication.getApplication().getExternalFilesDir("").getAbsolutePath()+"/";
    }

    /**
     * 获取应用内部存储/file
     *
     * @return
     */
    public static String getInsidePath() {
        return BaseApplication.getApplication().getFilesDir().getAbsolutePath()+"/";
    }

    /**
     * 日志文件夹
     *
     * @return
     */
    public static String getLogFolder() {
        return getStoragePath() + "Log/";
    }

//    /**
//     * 获取用户的根文件夹
//     *
//     * @return
//     */
//    public static String getUserFolder() {
//        String userRole = "";
//
//        // TODO: 可以根据登陆用户获取不同路径，这里暂时预留
//
//        if (TextUtils.isEmpty(userRole)) {
//            userRole = "Vistor";
//        }
//        return getStoragePath() + userRole + "/";
//    }

    /**
     * 下载中的临时文件存放文件夹
     *
     * @return
     */
    public static String getTempFolder() {
        return getStoragePath() + "Temp/";
    }

    /**
     * 附件下载的文件夹
     *
     * @param type 类型
     * @return
     */
    public static String getAttachFolder(String type) {
        return getStoragePath() + (TextUtils.isEmpty(type) ? "" : type + "/") + "Attach/";
    }

    /**
     * 附件下载的文件夹
     *
     * @return
     */
    public static String getAttachFolder() {
        return getAttachFolder("");
    }

    /**
     * 升级安装包下载的文件夹
     *
     * @return
     */
    public static String getUpgradeFolder() {

        return getStoragePath() + "Upgrade/";
    }

    /**
     * H5离线包下载的文件夹
     *
     * @return
     */
    public static String getOfflinePackageFolder() {
        return getInsidePath() + "AirspaceDownload";
    }

    /**
     * EEUI下载目录
     * */
    public static String getEEUIOfflinePackageFloder(){
        return getInsidePath()+"EEUIDownload";
    }

    /**
     * EEUI安装目录
     * */
    public static String getEEUIOfflinePackageInstallFloder(){
        return getStoragePath()+"update";
    }

    /**
     * EEUI安装目录
     * */
    public static String getEEUIOfflinePackageInstallFloder(String appId){
        return getStoragePath()+"update"+ File.separator + appId;
    }

    /**
     * H5离线包安装目录
     *
     * @return
     */
    public static String getOfflinePackageInstallFolder() {
        return getInsidePath() + "AirspaceInstall";
    }

    /**
     * H5离线包安装目录
     *
     * @return
     */
    public static String getOfflinePackageInstallFolder(String appId) {
        return getInsidePath() + "AirspaceInstall" + File.separator + appId;
    }



    /**
     * 内容管理-json文件临时存放目录
     *
     * @return
     */
    public static String getContentManagerJsonFolder() {
        return getInsidePath() + "ContentManager" + File.separator;
    }

}
