package com.wuhai.myframe2.ui.homepage.qddgouwu;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * createby yangzheng
 * date 2016/12/29
 * email yangzhenop@126.com
 */
public class HomeInfoV2 {

    public static final String BANNER = "banner";
    public static final String QUICKENTRY = "quickEntry";
    public static final String SUPERIMAGE = "superImage";
    public static final String SMALLIMAGE = "smallImage";
    public static final String BIGIMAGE = "bigImage";
    public static final String HOTITEM = "soldHot";
    public static final String NEWITEM = "newItem";
    public static final String NEWITEMTITLE = "newItemTitle";
    public static final String ADVERT = "advert";
    public static final String QUICKENTRYBACKGROUND = "quickEntryBackground";
    public static final String COMMONENTRANCE = "commonEntrance";

    public static final int TYPE_BANNER = 1;
    public static final int TYPE_QUICKENTRY = 2;
    public static final int TYPE_SUPERIMAGE = 3;
    public static final int TYPE_SMALLIMAGE = 4;
    public static final int TYPE_BIGIMAGE = 5;
    public static final int TYPE_HOTITEM = 6;
    public static final int TYPE_NEWITEM = 7;
    public static final int TYPE_NEWITEMTITLE = 8;
    public static final int TYPE_COMMONENTRANCE= 9;//TODO 虽然这是9，但解析数据体的时候，是根据json key顺序赋值的

    public ArrayList<LinkImage> banner;
    public ArrayList<LinkImage> quickEntry;
    public ArrayList<LinkImage> commonEntrance;
    public ArrayList<LinkImage> superImage;
    public ArrayList<LinkImage> smallImage;
    public ArrayList<LinkImage> bigImage;
    public ArrayList<LinkImage> quickEntryBackground;
    public ArrayList<SearchProInfo> hotItem;
    public ArrayList<SearchProInfo> newItem;

    public ArrayList<LinkImage> advert;

    public ArrayList<String> resource;//类型
    public ArrayList<Integer> indexs;//TODO 记录每一种类型的最后位置

    public HomeInfoV2() {
        banner = new ArrayList<>();
        quickEntry = new ArrayList<>();
        superImage = new ArrayList<>();
        commonEntrance = new ArrayList<>();
        smallImage = new ArrayList<>();
        bigImage = new ArrayList<>();
        hotItem = new ArrayList<>();
        newItem = new ArrayList<>();
        resource = new ArrayList<>();
        advert = new ArrayList<>();
        quickEntryBackground = new ArrayList<>();
        indexs = new ArrayList<>();
    }

    public int getItemType(int index) {
        for (int end : indexs) {
            Log.e("wuhai", "index="+index+",end="+end);//TODO 不太理解
            if (index <= end) {
                int pos = indexs.indexOf(end);
                return index(pos);
            }
        }
        return index(indexs.size() - 1);
    }

    private int index(int pos) {
        switch (resource.get(pos)) {
            case HomeInfoV2.BANNER:
                return TYPE_BANNER;
            case HomeInfoV2.QUICKENTRY:
                return TYPE_QUICKENTRY;
            case HomeInfoV2.SUPERIMAGE:
                return TYPE_SUPERIMAGE;
            case HomeInfoV2.SMALLIMAGE:
                return TYPE_SMALLIMAGE;
            case HomeInfoV2.BIGIMAGE:
                return TYPE_BIGIMAGE;
            case HomeInfoV2.HOTITEM:
                return TYPE_HOTITEM;
            case HomeInfoV2.NEWITEM:
                return TYPE_NEWITEM;
            case HomeInfoV2.NEWITEMTITLE:
                return TYPE_NEWITEMTITLE;
            case HomeInfoV2.COMMONENTRANCE:
                return TYPE_COMMONENTRANCE;
            default:
                throw new IllegalStateException("home type error ::: "+resource.get(pos));
        }
    }

    /**
     * 解析数据体
     * @param json
     */
    public void set(JsonObject json) {
        Gson gson = new Gson();
        for (Map.Entry<String, JsonElement> elementEntry : json.entrySet()) {
            Log.e("wuhai", "elementEntry.getKey()="+elementEntry.getKey());
            switch (elementEntry.getKey()) {
                case BANNER://首页-Banner
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        banner.add(gson.fromJson(item, LinkImage.class));
                    }
                    if (banner.size() > 0) {
                        resource.add(BANNER);
                        indexs.add(0);
                    }
                    break;
                case QUICKENTRY://首页-快捷入口
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        quickEntry.add(gson.fromJson(item, LinkImage.class));
                    }
                    if (quickEntry.size() > 0) {
                        resource.add(QUICKENTRY);
                        indexs.add(indexs.get(indexs.size() - 1) + 1);
                    }
                    break;
                case SUPERIMAGE://首页-大图推荐
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        superImage.add(gson.fromJson(item, LinkImage.class));
                    }
                    if (superImage.size() > 0) {
                        resource.add(SUPERIMAGE);
                        indexs.add(indexs.get(indexs.size() - 1) + superImage.size());//TODO 根据size计算出endSu,即最后的位置所在
                    }
                    break;
                case SMALLIMAGE://首页-小图推荐
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        smallImage.add(gson.fromJson(item, LinkImage.class));
                    }
                    if (smallImage.size() > 0) {
                        resource.add(SMALLIMAGE);
                        indexs.add(indexs.get(indexs.size() - 1) + smallImage.size());
                    }
                    break;
                case BIGIMAGE://首页-横图推荐
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        bigImage.add(gson.fromJson(item, LinkImage.class));
                    }
                    if (bigImage.size() > 0) {
                        resource.add(BIGIMAGE);
                        indexs.add(indexs.get(indexs.size() - 1) + bigImage.size());
                    }
                    break;
                case HOTITEM://首页-新品上架
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        hotItem.add(gson.fromJson(item, SearchProInfo.class));
                    }
                    if (hotItem.size() > 0) {
                        resource.add(HOTITEM);
                        indexs.add(indexs.get(indexs.size() - 1) + 1 /*hotItem.size()*/);
                    }
                    break;
                case NEWITEM://首页-热卖商品
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        newItem.add(gson.fromJson(item, SearchProInfo.class));
                    }
                    if (newItem.size() > 0) {
                        if(newItem.size() % 2 !=0){
                            newItem.remove(newItem.size()-1);//TODO wuhai 这里remove一个，假数据时候会咋样呢 嘿嘿
                        }
                        resource.add(NEWITEMTITLE);//首页-热卖商品 的头部作为一个单独type
                        indexs.add(indexs.get(indexs.size() - 1)+1);
                        resource.add(NEWITEM);
                        indexs.add(indexs.get(indexs.size() - 1) + newItem.size());
                    }
                case ADVERT:
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        advert.add(gson.fromJson(item, LinkImage.class));
                    }
                    break;
                case QUICKENTRYBACKGROUND:
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        quickEntryBackground.add(gson.fromJson(item, LinkImage.class));
                    }
                    break;
                case COMMONENTRANCE://首页-常用入口
                    for (JsonElement item : elementEntry.getValue().getAsJsonArray()) {
                        commonEntrance.add(gson.fromJson(item, LinkImage.class));
                    }
                    if(commonEntrance.size() > 0){
                        resource.add(COMMONENTRANCE);
                        indexs.add(indexs.get(indexs.size() - 1) + commonEntrance.size());
                    }
                    break;
                default:
                    break;
            }
        }

        Log.e("HomeAdapterV3",resource.toString()+"\n"+indexs.toString());
    }

    /**
     * 加载多页时候 更新 NEWITEM的indexs里的值
     * @param newData
     */
    public void addNew(ArrayList<SearchProInfo> newData) {
        newItem.addAll(newData);
        int old = indexs.get(resource.indexOf(HomeInfoV2.NEWITEM));
        indexs.set(resource.indexOf(HomeInfoV2.NEWITEM),old+newData.size());
    }
}