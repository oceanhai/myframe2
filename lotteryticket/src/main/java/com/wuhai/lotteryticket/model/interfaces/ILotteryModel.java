package com.wuhai.lotteryticket.model.interfaces;


import com.wuhai.lotteryticket.model.bean.Lottery;

/**
 * 作者 wh
 * 
 * 创建日期 2020/2/14 17:53
 * 
 * 描述：
 */
public interface ILotteryModel extends IBaseModelInterFace   {

    /**
     * 彩票
     * @param lottery
     */
    void addLottery(Lottery lottery);

}
