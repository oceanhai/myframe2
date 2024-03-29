package com.wuhai.hhsc.widget.banner;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wuhai.hhsc.ui.fragment.BaseFragment;

import java.lang.ref.WeakReference;



/**
 * description:广告handler
 * data: 2019-12-18
 * author: zhudi
 */
public class AdvertisementHandler extends Handler {
    /**
     * 请求更新显示的View。
     */
    public static final int MSG_UPDATE_IMAGE = 1;
    /**
     * 请求暂停轮播。
     */
    public static final int MSG_KEEP_SILENT = 2;
    /**
     * 请求恢复轮播。
     */
    public static final int MSG_BREAK_SILENT = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    public static final int MSG_PAGE_CHANGED = 4;

    //轮播间隔时间  
    public static final long MSG_DELAY = 5000;

    //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等  
    private WeakReference<BaseFragment> weakReference;
    private int SquareCurrentItem = 0;
    private int homeBannerCurrentItem = 0;
    HomeBannerFragment homeBannerFragment = null;

    public AdvertisementHandler(WeakReference<BaseFragment> wk) {
        weakReference = wk;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d("advertisementHandler", "receive message " + msg.what);
        BaseFragment baseFragment = weakReference.get();
        if (baseFragment == null) {
            //Activity已经回收，无需再处理UI了  
            return;
        }

        homeBannerFragment = (HomeBannerFragment) baseFragment;
        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
        if (homeBannerFragment.getHandler().hasMessages(MSG_UPDATE_IMAGE)) {
            homeBannerFragment.getHandler().removeMessages(MSG_UPDATE_IMAGE);
        }
        switch (msg.what) {
            case MSG_UPDATE_IMAGE:
                homeBannerCurrentItem++;
                // Log.d("advertisementHandler", "homeBannerCurrentItem=" + homeBannerCurrentItem);
                homeBannerFragment.viewPager.setCurrentItem(homeBannerCurrentItem, true);
                //   Log.d("advertisementHandler", "准备下次播放");
//                    //准备下次播放
//                    homeBannerFragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_KEEP_SILENT:
                //只要不发送消息就暂停了
                break;
            case MSG_BREAK_SILENT:
                homeBannerFragment.getHandler().sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
                //记录当前的页号，避免播放的时候页面显示不正确。
                homeBannerCurrentItem = msg.arg1;
                //准备下次播放
                homeBannerFragment.getHandler().sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            default:
                break;
        }
    }


    public void clearData() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        if (homeBannerFragment != null) {
            homeBannerFragment = null;
        }

    }

}
