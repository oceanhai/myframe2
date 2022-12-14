package com.example.java_lib.pactera.yidongjr;

public class PackageListEntity {
    /**
     * packageUrl : /mfs/1/1/eeui/1/10000001.zip
     * appId : 10006
     * type : vue
     * version : 1.0.0
     */
    private String packageUrl;//整包下载地址
    private String appId;//离线包id
    private String type;//离线包类型
    private String version;//离线包版本
    private String diffUrl;//差量包下载地址
    private String fallbackBaseUrl;//在线回调地址
    private int delete=-1;//是否删除 0 不删除 1 删除
    private String packageMD5;//全量包md5 备注：该值会用于上送，并且也会用于下载校验
    private String diffMD5;//差量包md5 备注：该值不会用于上送，只会用于下载校验  毕竟差量包合并后会被删除掉  上送无意义
    private int preDownload;//0:预下载  1:不预下载

    //TODO 懒的在弄一个类 ，就新增一个属性 模拟下吧
    private int offlineStatus;

    public int getOfflineStatus() {
        return offlineStatus;
    }

    public void setOfflineStatus(int offlineStatus) {
        this.offlineStatus = offlineStatus;
    }

    public int getPreDownload() {
        return preDownload;
    }

    public void setPreDownload(int preDownload) {
        this.preDownload = preDownload;
    }

    public void setPackageUrl(String packageUrl) {
        this.packageUrl = packageUrl;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackageUrl() {
        return packageUrl;
    }

    public String getAppId() {
        return appId;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public String getFallbackBaseUrl() {
        return fallbackBaseUrl;
    }

    public void setFallbackBaseUrl(String fallbackBaseUrl) {
        this.fallbackBaseUrl = fallbackBaseUrl;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getPackageMD5() {
        return packageMD5;
    }

    public void setPackageMD5(String packageMD5) {
        this.packageMD5 = packageMD5;
    }

    public String getDiffMD5() {
        return diffMD5;
    }

    public void setDiffMD5(String diffMD5) {
        this.diffMD5 = diffMD5;
    }

    @Override
    public String toString() {
        return "PackageListEntity{" +
                "appId='" + appId + '\'' +
                ", preDownload=" + preDownload +
                '}';
    }
}
