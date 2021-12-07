package com.wuhai.myframe2.utils.cache.bean;


import java.io.Serializable;
import java.util.List;

/**
 * 首页数据实体类
 */
public class IndexAllBean implements Serializable {

    //广告栏
    public List<ServiceListBean> bannerList;
    //站点
    public StationBean station;
    //分享
    public ShareBean share;
    //惠农政策
    public BenefitFarmingPolicy hnzc;
    //推荐服务
    public List<ServiceListBean> hotServiceList;
    //视频
    public ServiceVideo video;
    //广告栏2
    public List<ServiceListBean> bannerList2;
    //乡村振兴
    public CountryCd xczx;
    //消费扶贫
    public ConsumptionPoverty xffp;
    //农技推广
    public TechnologyExtension njtg;

    /**
     * 站点
     */
    public static class StationBean implements Serializable{
        public String takepiecestation_id;//服务站ID
        public String headportrait;//图片
        public String takepiece_name;//优先名
        public String villagename;//农村名
        public RouterBean router;
    }

    /**
     * 分享
     */
    public static class ShareBean implements Serializable{
        public String webUrl;//分享h5链接地址
        public String title;//标题
        public String desc;//描述
        public String imageUrl;//图片
    }

    /**
     * 农技推广
     */
    public static class TechnologyExtension implements Serializable{
        public ServiceListBean askUrl;//去提问
        public List<ServiceListBean> iconList;//list
        public QAEntity qa;//问答
        public RouterBean router;
    }

    /**
     * 问答
     */
    public static class QAEntity implements Serializable{
        public String question;//问
        public List<String> labels;//标签集合
        public String answer;//答
        public String answerNumber;//问答交流 个数
        public RouterBean router;//路由
    }


    /**
     * 惠农政策
     */
    public static class BenefitFarmingPolicy implements Serializable{
        public String iconUrl;//icon
        public List<ServiceListBean> list;
    }

    /**
     * 消费扶贫
     */
    public static class ConsumptionPoverty implements Serializable{
        public ServiceListBean all;//查看全部
        public List<ServiceListBean> newsList;//news list
        public List<ServiceListBean> iconList;//icon list
    }

    /**
     * 乡村振兴
     */
    public static class CountryCd implements Serializable{
        public ServiceListBean all;//查看全部
        public List<ServiceListBean> list;//乡村振兴列表
    }

    /**
     * 视频
     */
    public static class ServiceVideo implements Serializable{
        public String coverImage;//封面图片
        public String path;//视频地址
    }

    /**
     * 特色服务 TODO 社保copy过来，后面删除优化
     */
    public static class SpecialServiceListBean implements Serializable {
        //特色服务封面名称
        public String coverName;
        //封面图标地址
        public String imgUrl;
        //特色服务列表
        public List<ServiceListBean> spList;


        //路由配置
        public RouterBean router;
    }

    /**
     * 推荐服务
     */
    public static class ServiceListBean implements Serializable {
        //图片路径
        public String imgUrl;
        //日期
        public String date;
        //关注人数
        public String personNumber;
        //title
        public String title;
        //subTitle
        public String subTitle;
        //color
        public String color;
        //新增字段1
        public String name1;
        //路由配置
        public RouterBean router;
    }

    @Override
    public String toString() {
        return "IndexAllBean{" +
                "bannerList=" + bannerList +
                ", station=" + station +
                ", share=" + share +
                ", hnzc=" + hnzc +
                ", hotServiceList=" + hotServiceList +
                ", video=" + video +
                ", bannerList2=" + bannerList2 +
                ", xczx=" + xczx +
                ", xffp=" + xffp +
                ", njtg=" + njtg +
                '}';
    }
}
