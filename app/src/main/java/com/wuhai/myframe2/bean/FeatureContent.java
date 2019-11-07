package com.wuhai.myframe2.bean;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/8 18:13
 *
 * 描述：推荐位
 */
public class FeatureContent {

    /**
     * summary :
     * featureImage : http://m.qiandaodao.com/ecshop/webview/item/detail.do?itemId=2VM4MKV8ULS12_11_22_31
     * featureUrl : http://m.qiandaodao.com/ecshop/webview/item/detail.do?itemId=2VM4MKV8ULS12_11_22_31
     */
    private String summary;//推荐位内容
    private String featureImage;//推荐位图片
    private String featureUrl;//推荐位跳转链接

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setFeatureImage(String featureImage) {
        this.featureImage = featureImage;
    }

    public void setFeatureUrl(String featureUrl) {
        this.featureUrl = featureUrl;
    }

    public String getSummary() {
        return summary;
    }

    public String getFeatureImage() {
        return featureImage;
    }

    public String getFeatureUrl() {
        return featureUrl;
    }
}
