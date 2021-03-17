package com.wuhai.hhsc.model;

/**
 * description:首页banner
 * data: 2019-12-17
 * author: zhudi
 */
public class HomeBanner {

    /**
     * 首页Banner图片地址
     */
    private String imgUri;

    /**
     * 首页Banner排序
     */
    private int sort;

    /**
     * 首页Banner跳转
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
}
