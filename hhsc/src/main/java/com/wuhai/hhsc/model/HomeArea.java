package com.wuhai.hhsc.model;

/**
 * description:首页分区列表
 * data: 2019-12-17
 * author: zhudi
 */
public class HomeArea {

    /**
     * 图片地址
     */
    private String imgUri;
    /**
     * 名称
     */
    private String imgName;

    /**
     * 排序
     */
    private int sort;

    /**
     * 跳转
     */
    private String redirect;

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }
}
