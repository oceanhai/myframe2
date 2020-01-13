package com.wuhai.lotteryticket.model.bean;

import java.util.List;

/**
 * 作者 wh
 *
 * 创建日期 2020/1/10 18:08
 *
 * 描述：彩票历史数据entity
 */
public class LotteryHistoryEntity {

    /**
     *     "page":1,
     *     "pageSize":10,
     *     "totalPage":252
     *     lotteryResList   LotteryHistory集合
     */
    private int page;
    private int pageSize;
    private int totalPage;
    private List<LotteryHistory> lotteryResList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<LotteryHistory> getLotteryResList() {
        return lotteryResList;
    }

    public void setLotteryResList(List<LotteryHistory> lotteryResList) {
        this.lotteryResList = lotteryResList;
    }
}
