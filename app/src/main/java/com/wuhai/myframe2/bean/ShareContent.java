package com.wuhai.myframe2.bean;

/**
 * Created by wuhai on 2019/5/20.
 */

public class ShareContent {

    /**
     * shareContent : 免费出价，真有人一块钱买走了iPhone X，这羊毛赶紧薅！
     * shareImageUrl : http://static.qujingjia.com/upload/lanhai_qjj/image/qjj.png
     * shareTitle : 老板傻了吧，谁出价低，就卖给谁
     */
    private String shareContent;
    private String shareImageUrl;//icon
    private String shareTitle;

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    @Override
    public String toString() {
        return "ShareContent{" +
                "shareContent='" + shareContent + '\'' +
                ", shareImageUrl='" + shareImageUrl + '\'' +
                ", shareTitle='" + shareTitle + '\'' +
                '}';
    }
}
