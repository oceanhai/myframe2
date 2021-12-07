package com.wuhai.myframe2.utils.cache;


import android.content.Context;

import com.wuhai.myframe2.utils.cache.bean.IndexAllBean;


/**
 * 作者 wh
 *
 * 创建日期 2021/11/25 16:47
 *
 * 描述：
 */
public final class CacheUtils {
    /**
     * 首页
     */
    public static final String HOME_PAGE_FILE = "home_page_file";

    private CacheUtils() {
    }

    /**
     * 缓存Home页面数据
     * @param indexAllBean
     * @return
     */
    public static boolean cacheHomePageData(Context context, IndexAllBean indexAllBean){
        return SerializeTools.cacheObj(context, HOME_PAGE_FILE, indexAllBean);
    }

    /**
     * 从缓存中获取 Home界面数据
     * @return
     */
    public static IndexAllBean getHomePageData(Context context){
        try {
            return SerializeTools.getObj(context, HOME_PAGE_FILE, IndexAllBean.class);
        }catch (Exception e){
            return null;
        }

    }


}
