package com.wuhai.lotteryticket.model.interfaces;


import com.wuhai.lotteryticket.model.bean.LotteryRecord;

import java.util.List;

/**
 * 作者 wh
 * 
 * 创建日期 2020/2/14 17:53
 * 
 * 描述：
 */
public interface ILotteryRecordModel extends IBaseModelInterFace   {

    /**
     * 彩票
     * @param lotteryRecord
     */
    void addLotteryRecord(LotteryRecord lotteryRecord);

    /**
     * 查询所有数据
     * @return
     */
    List<LotteryRecord> queryLotteryRecordAll();

    /**
     * 查询
     * @param page
     * @param maxInPage     每页最大 数量
     * @return
     */
    List<LotteryRecord> queryLotteryRecord(int page, int maxInPage);

}
