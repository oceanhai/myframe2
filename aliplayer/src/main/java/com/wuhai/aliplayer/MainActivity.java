package com.wuhai.aliplayer;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.aliyun.player.alivcplayerexpand.constants.GlobalPlayerConfig;
import com.aliyun.player.alivcplayerexpand.theme.Theme;
import com.aliyun.player.alivcplayerexpand.widget.AliyunVodPlayerView;
import com.aliyun.player.nativeclass.CacheConfig;
import com.aliyun.player.nativeclass.PlayerConfig;
import com.aliyun.player.source.UrlSource;
import com.aliyun.svideo.common.utils.FileUtils;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "aliplayer";

    private AliyunVodPlayerView mAliyunVodPlayerView = null;
    /**
     * 当前系统屏幕亮度
     */
    private int mCurrentBrightValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAliyunVodPlayerView =  findViewById(R.id.video_view);

        //阿里播放器
        mCurrentBrightValue = getCurrentBrightValue();
        initAliyunPlayerView();
        initPlayerConfig();
        initDataSource("http://img.xiongzhangh.com/xuan.mp4");
    }


    /**
     * 仅当系统的亮度模式是非自动模式的情况下，获取当前屏幕亮度值[0, 255].
     * 如果是自动模式，那么该方法获得的值不正确。
     */
    private int getCurrentBrightValue() {
        int nowBrightnessValue = 0;
        ContentResolver resolver = getContentResolver();
        try {
            nowBrightnessValue = android.provider.Settings.System.getInt(resolver,
                    Settings.System.SCREEN_BRIGHTNESS, 255);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowBrightnessValue;
    }

    private void initAliyunPlayerView() {
        //保持屏幕敞亮
        mAliyunVodPlayerView.setKeepScreenOn(true);
        mAliyunVodPlayerView.setTheme(Theme.Blue);
        // 设置自动播放
        mAliyunVodPlayerView.setAutoPlay(false);
        mAliyunVodPlayerView.needOnlyFullScreenPlay(false);

//        mAliyunVodPlayerView.setOnPreparedListener(new MyPrepareListener(this));
//        mAliyunVodPlayerView.setNetConnectedListener(new MyNetConnectedListener(this));
//        mAliyunVodPlayerView.setOnCompletionListener(new MyCompletionListener(this));
//        mAliyunVodPlayerView.setOnFirstFrameStartListener(new MyFrameInfoListener(this));
//        mAliyunVodPlayerView.setOnTrackChangedListener(new MyOnTrackChangedListener(this));
//        mAliyunVodPlayerView.setOnStoppedListener(new MyStoppedListener(this));
//        mAliyunVodPlayerView.setOrientationChangeListener(new MyOrientationChangeListener(this));
//        mAliyunVodPlayerView.setOnTimeExpiredErrorListener(new MyOnTimeExpiredErrorListener(this));
//        mAliyunVodPlayerView.setOnShowMoreClickListener(new MyShowMoreClickLisener(this));
//        mAliyunVodPlayerView.setOnPlayStateBtnClickListener(new MyPlayStateBtnClickListener(this));
//        mAliyunVodPlayerView.setOnSeekCompleteListener(new MySeekCompleteListener(this));
//        mAliyunVodPlayerView.setOnSeekStartListener(new MySeekStartListener(this));
//        mAliyunVodPlayerView.setOnFinishListener(new MyOnFinishListener(this));
//        mAliyunVodPlayerView.setOnScreenCostingSingleTagListener(new MyOnScreenCostingSingleTagListener(this));
//        mAliyunVodPlayerView.setOnScreenBrightness(new MyOnScreenBrightnessListener(this));
//        mAliyunVodPlayerView.setSoftKeyHideListener(new MyOnSoftKeyHideListener(this));
//        mAliyunVodPlayerView.setOnErrorListener(new MyOnErrorListener(this));
//        mAliyunVodPlayerView.setScreenBrightness(BrightnessDialog.getActivityBrightness(AliyunPlayerSkinActivity.this));
//        mAliyunVodPlayerView.setOnTrackInfoClickListener(new MyOnTrackInfoClickListener(this));
//        mAliyunVodPlayerView.setOnInfoListener(new MyOnInfoListener(this));
//        mAliyunVodPlayerView.setOutOnSeiDataListener(new MyOnSeiDataListener(this));
//        mAliyunVodPlayerView.setOnTipClickListener(new MyOnTipClickListener(this));
//        mAliyunVodPlayerView.setOnTipsViewBackClickListener(new MyOnTipsViewBackClickListener(this));
//        mAliyunVodPlayerView.setOutOnVerifyTimeExpireCallback(new MyOnVerifyStsCallback(this));
        mAliyunVodPlayerView.enableNativeLog();
        mAliyunVodPlayerView.setScreenBrightness(mCurrentBrightValue);
        mAliyunVodPlayerView.startNetWatch();

    }

    /**
     * 初始化播放配置
     */
    private void initPlayerConfig() {
        if (mAliyunVodPlayerView != null) {
            //界面设置
            mAliyunVodPlayerView.setEnableHardwareDecoder(GlobalPlayerConfig.mEnableHardDecodeType);
            mAliyunVodPlayerView.setRenderMirrorMode(GlobalPlayerConfig.mMirrorMode);
            mAliyunVodPlayerView.setRenderRotate(GlobalPlayerConfig.mRotateMode);
            //播放配置设置
            PlayerConfig playerConfig = mAliyunVodPlayerView.getPlayerConfig();
            playerConfig.mStartBufferDuration = GlobalPlayerConfig.PlayConfig.mStartBufferDuration;
            playerConfig.mHighBufferDuration = GlobalPlayerConfig.PlayConfig.mHighBufferDuration;
            playerConfig.mMaxBufferDuration = GlobalPlayerConfig.PlayConfig.mMaxBufferDuration;
            playerConfig.mMaxDelayTime = GlobalPlayerConfig.PlayConfig.mMaxDelayTime;
            playerConfig.mNetworkTimeout = GlobalPlayerConfig.PlayConfig.mNetworkTimeout;
            playerConfig.mMaxProbeSize = GlobalPlayerConfig.PlayConfig.mMaxProbeSize;
            playerConfig.mReferrer = GlobalPlayerConfig.PlayConfig.mReferrer;
            playerConfig.mHttpProxy = GlobalPlayerConfig.PlayConfig.mHttpProxy;
            playerConfig.mNetworkRetryCount = GlobalPlayerConfig.PlayConfig.mNetworkRetryCount;
            playerConfig.mEnableSEI = GlobalPlayerConfig.PlayConfig.mEnableSei;
            playerConfig.mClearFrameWhenStop = GlobalPlayerConfig.PlayConfig.mEnableClearWhenStop;
            mAliyunVodPlayerView.setPlayerConfig(playerConfig);
            //缓存设置
            initCacheConfig();
            Log.e(TAG, "cache dir : " + GlobalPlayerConfig.PlayCacheConfig.mDir
                    + " startBufferDuration = " + GlobalPlayerConfig.PlayConfig.mStartBufferDuration
                    + " highBufferDuration = " + GlobalPlayerConfig.PlayConfig.mHighBufferDuration
                    + " maxBufferDuration = " + GlobalPlayerConfig.PlayConfig.mMaxBufferDuration
                    + " maxDelayTime = " + GlobalPlayerConfig.PlayConfig.mMaxDelayTime
                    + " enableCache = " + GlobalPlayerConfig.PlayCacheConfig.mEnableCache
                    + " --- mMaxDurationS = " + GlobalPlayerConfig.PlayCacheConfig.mMaxDurationS
                    + " --- mMaxSizeMB = " + GlobalPlayerConfig.PlayCacheConfig.mMaxSizeMB);
        }
    }

    /**
     * 缓存设置
     */
    private void initCacheConfig(){
        CacheConfig cacheConfig = new CacheConfig();
        GlobalPlayerConfig.PlayCacheConfig.mDir = FileUtils.getDir(this) + GlobalPlayerConfig.CACHE_DIR_PATH;
        cacheConfig.mEnable = GlobalPlayerConfig.PlayCacheConfig.mEnableCache;
        cacheConfig.mDir = GlobalPlayerConfig.PlayCacheConfig.mDir;
        cacheConfig.mMaxDurationS = GlobalPlayerConfig.PlayCacheConfig.mMaxDurationS;
        cacheConfig.mMaxSizeMB = GlobalPlayerConfig.PlayCacheConfig.mMaxSizeMB;

        mAliyunVodPlayerView.setCacheConfig(cacheConfig);
    }

    /**
     * 设置播放地址
     */
    private void initDataSource(String videoPath) {
        UrlSource urlSource = new UrlSource();
        urlSource.setUri(videoPath);
        mAliyunVodPlayerView.setLocalSource(urlSource);
    }
}