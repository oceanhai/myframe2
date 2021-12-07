package com.wuhai.myframe2.utils.cache.bean;

import java.io.Serializable;

/**
 * 作者 wh
 *
 * 创建日期 2021/11/15 10:47
 *
 * 描述：站点
 */
public class Station implements Serializable {
    private String takepiecestation_id;//服务站ID
    private String headportrait;//图片
    private String takepieceName;//优先名
    private String villagename;//农村名

    public String getTakepiecestation_id() {
        return takepiecestation_id;
    }

    public void setTakepiecestation_id(String takepiecestation_id) {
        this.takepiecestation_id = takepiecestation_id;
    }

    public String getHeadportrait() {
        return headportrait;
    }

    public void setHeadportrait(String headportrait) {
        this.headportrait = headportrait;
    }

    public String getTakepieceName() {
        return takepieceName;
    }

    public void setTakepieceName(String takepieceName) {
        this.takepieceName = takepieceName;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }
}
