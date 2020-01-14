package com.wuhai.myframe2.ui.homepage.qddgouwu.model;

/**
 * createby yangzheng
 * date 2016/5/18
 * email yangzhenop@126.com
 */
public class Pager {
    public int page;// : 当前页
    public int pageCount;//: 总页数
    public boolean hasNextPage;// 是否有下一页
    public int count;//: 总数

    public Pager() {
    }

    public Pager(int page, int pageCount, boolean hasNexPage, int count) {
        this.page = page;
        this.pageCount = pageCount;
        hasNextPage = hasNexPage;
        this.count = count;
    }
}