package com.wuhai.share;

/**
 * Created by Zhenggy on 2017/2/21.
 */

public class QddShareModel {

    private String title;
    private String contentText;
    private String imageUrl;
    private String url;

    public QddShareModel() {
    }

    public QddShareModel(String title, String contentText, String imageUrl, String url) {
        this.title = title;
        this.contentText = contentText;
        this.imageUrl = imageUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "QddShareModel{" +
                "title='" + title + '\'' +
                ", contentText='" + contentText + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
