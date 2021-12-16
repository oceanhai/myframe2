package com.wuhai.myframe2.ui.dkplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.IPlayer;
import com.aliyun.player.bean.ErrorInfo;
import com.aliyun.player.bean.InfoBean;
import com.aliyun.player.bean.InfoCode;
import com.aliyun.player.source.UrlSource;

import java.util.Map;

import xyz.doikki.videoplayer.player.AbstractPlayer;
import xyz.doikki.videoplayer.util.L;

/**
 * 作者 wh
 *
 * 创建日期 2021/12/15 11:54
 *
 * 描述：v1.0 阿里播放器 作为核心
 */
public class DkAliPlayer extends AbstractPlayer implements
        IPlayer.OnErrorListener, IPlayer.OnCompletionListener, IPlayer.OnInfoListener,
        IPlayer.OnPreparedListener, IPlayer.OnVideoSizeChangedListener, IPlayer.OnStateChangedListener,
        IPlayer.OnLoadingStatusListener {

    protected AliPlayer mMediaPlayer;
    private final Context mAppContext;

    //当前播放器的状态
    private int mPlayerState = IPlayer.idle;
    //当前播放位置
    private long mCurrentPosition = 0;
    //缓冲位置
    private long mBufferedPosition = 0;
    //当前网络下载速度
    private long mCurrentDownloadSpeed = 0;

    public DkAliPlayer(Context context) {
        mAppContext = context;
    }

    @Override
    public void initPlayer() {
        mMediaPlayer = AliPlayerFactory.createAliPlayer(mAppContext);
        setOptions();
        mMediaPlayer.setAutoPlay(true);//TODO 自动播放，设置这个后，阿里播放器准备完后就自动开始播放了
        mMediaPlayer.setOnErrorListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnInfoListener(this);
//        mMediaPlayer.setOnBufferingUpdateListener(this);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
//        mMediaPlayer.setOnNativeInvokeListener(this);

        //※阿里播放器的一些值 需要监听获得并处理成自己想要的
        //状态改变监听
        mMediaPlayer.setOnStateChangedListener(this);
        //播放器加载状态监听
        mMediaPlayer.setOnLoadingStatusListener(this);
    }


    @Override
    public void setOptions() {
    }

    @Override
    public void setDataSource(String path, Map<String, String> headers) {
        try {
            Uri uri = Uri.parse(path);
            UrlSource urlSource = new UrlSource();
            if (ContentResolver.SCHEME_ANDROID_RESOURCE.equals(uri.getScheme())) {
                urlSource.setCacheFilePath(path);//本地文件路径
            } else {
                urlSource.setUri(path);//网络视频地址
            }
            mMediaPlayer.setDataSource(urlSource);
        } catch (Exception e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void setDataSource(AssetFileDescriptor fd) {
        //assets文件 TODO no support
//        try {
//            mMediaPlayer.setDataSource(new RawDataSourceProvider(fd));
//        } catch (Exception e) {
//            mPlayerEventListener.onError();
//        }
    }

    @Override
    public void pause() {
        try {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void start() {
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void stop() {
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void prepareAsync() {
        try {
            if(mMediaPlayer != null){
                mMediaPlayer.prepare();
            }
        } catch (Exception e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void reset() {
        mMediaPlayer.reset();
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        setOptions();
    }

    @Override
    public boolean isPlaying() {
        //我们通过监听播放状态mPlayerState 然后比较=IPlayer.started 时候是正在播放
        return mPlayerState == IPlayer.started;
    }

    @Override
    public void seekTo(long time) {
        try {
            mMediaPlayer.seekTo((int) time);
        } catch (IllegalStateException e) {
            mPlayerEventListener.onError();
        }
    }

    @Override
    public void release() {
        mMediaPlayer.setOnErrorListener(null);
        mMediaPlayer.setOnCompletionListener(null);
        mMediaPlayer.setOnInfoListener(null);
//        mMediaPlayer.setOnBufferingUpdateListener(null);
        mMediaPlayer.setOnPreparedListener(null);
        mMediaPlayer.setOnVideoSizeChangedListener(null);

        //※阿里播放器的一些值 需要监听获得并处理成自己想要的
        //状态改变监听
        mMediaPlayer.setOnStateChangedListener(null);
        //播放器加载状态监听
        mMediaPlayer.setOnLoadingStatusListener(null);

        new Thread() {
            @Override
            public void run() {
                try {
                    mMediaPlayer.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public long getCurrentPosition() {
        //TODO 获取到当前播放位置  这里我们通过onInfo(InfoBean infoBean) 判断code来获取值
        return mCurrentPosition;
    }

    @Override
    public long getDuration() {
        return mMediaPlayer.getDuration();
    }

    /**
     * 缓冲的百分比
     * ※注意，这个是 100最大，而在seekbar那边 max=1000，所以那里设置才需要*10
     * @return
     */
    @Override
    public int getBufferedPercentage() {
        //TODO 缓冲的百分比   这里我们通过onInfo(InfoBean infoBean) 判断code来获取值缓冲位置，并计算得到
        int percent = (int) ((float)mBufferedPosition/(float)getDuration()*100);
//        L.e("DkAliPlayer getBufferedPercentage 缓冲百分比="+percent+",mBufferedPosition="+
//                mBufferedPosition+",getDuration()="+getDuration());
        return percent;
    }

    @Override
    public void setSurface(Surface surface) {
        mMediaPlayer.setSurface(surface);
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        mMediaPlayer.setDisplay(holder);
    }

    @Override
    public void setVolume(float v1, float v2) {
        mMediaPlayer.setVolume(v1);
    }

    @Override
    public void setLooping(boolean isLooping) {
        mMediaPlayer.setLoop(isLooping);
    }

    @Override
    public void setSpeed(float speed) {
        mMediaPlayer.setSpeed(speed);
    }

    /**
     * 获取倍数播放值
     * @return      倍数播放值。范围[0.5,2]
     */
    @Override
    public float getSpeed() {
        return mMediaPlayer.getSpeed();
    }

    @Override
    public long getTcpSpeed() {
        //TODO 获得下载速度 这里我们通过onInfo(InfoBean infoBean) 判断code来获取值下载速度，感觉应该单位应该是字节
        return mCurrentDownloadSpeed;
    }


    private boolean isVideo() {
        //TODO 这个是IjkPlayer 判断是否是视频
//        IjkTrackInfo[] trackInfo = mMediaPlayer.getTrackInfo();
//        if (trackInfo == null) return false;
//        for (IjkTrackInfo info : trackInfo) {
//            if (info.getTrackType() == ITrackInfo.MEDIA_TRACK_TYPE_VIDEO) {
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public void onError(ErrorInfo errorInfo) {
        mPlayerEventListener.onError();
    }

    @Override
    public void onCompletion() {
        mPlayerEventListener.onCompletion();
    }

    @Override
    public void onInfo(InfoBean infoBean) {
        /*
        阿里播放器
        code
            CurrentDownloadSpeed        当前网络下载速度    TODO api中没有此解释，这是自己理解的
            CurrentPosition             当前播放位置。额外值为当前播放位置。单位：毫秒。
            BufferedPosition            缓冲位置。额外值为当前缓冲位置。单位：毫秒。
         */
        InfoCode code = infoBean.getCode(); //信息码
        String msg = infoBean.getExtraMsg();//信息内容
        long value = infoBean.getExtraValue(); //信息值

        L.e("DkAliPlayer onInfo code="+code+",msg="+msg+",value="+value);
        if(infoBean.getCode() == InfoCode.BufferedPosition){//缓冲位置
            mBufferedPosition = value;
        }else if(infoBean.getCode() == InfoCode.CurrentPosition){//当前播放位置
            mCurrentPosition = value;
        }else if(infoBean.getCode() == InfoCode.CurrentDownloadSpeed){//当前网络下载速度
            mCurrentDownloadSpeed = value;
        }
//        mPlayerEventListener.onInfo(what, extra);

//        if (infoBean.getCode() == InfoCode.AutoPlayStart) {
//            //自动播放开始,需要设置播放状态
//            if (mControlView != null) {
//                mControlView.setPlayState(ControlView.PlayState.Playing);
//            }
//            if (mOutAutoPlayListener != null) {
//                mOutAutoPlayListener.onAutoPlayStarted();
//            }
//        } else if (infoBean.getCode() == InfoCode.BufferedPosition) {
//            //更新bufferedPosition
//            mVideoBufferedPosition = infoBean.getExtraValue();
//            mControlView.setVideoBufferPosition((int) mVideoBufferedPosition);
//        } else if (infoBean.getCode() == InfoCode.CurrentPosition) {
//            //更新currentPosition
//            mCurrentPosition = infoBean.getExtraValue();
//            if (mDanmakuView != null) {
//                mDanmakuView.setCurrentPosition((int) mCurrentPosition);
//            }
//            if (mControlView != null) {
//                //如果是试看视频,并且试看已经结束了,要屏蔽其他按钮的操作
//                mControlView.setOtherEnable(true);
//            }
//            if (GlobalPlayerConfig.IS_VIDEO) {
//                //判断,是否需要暂停原视频,播放广告视频
//                if (mControlView != null && mControlView.isNeedToPause((int) infoBean.getExtraValue(), mAdvVideoCount)) {
//                    if (infoBean.getExtraValue() < TRAILER * 1000) {
//                        startAdvVideo();
//                    }
//                }
//                if (mControlView != null && !inSeek && mPlayerState == IPlayer.started) {
//                    /*
//                        由于关键帧的问题,seek到sourceDuration / 2时间点会导致进度条和广告时间对应不上,导致在播放原视频的时候进度条还在广告进度条范围内
//                     */
//                    if (mAdvVideoCount == 2 && ((mAdvTotalPosition + mCurrentPosition) < (mAdvTotalPosition + mSourceDuration / 2))) {
//                        mControlView.setAdvVideoPosition((int) (mAdvTotalPosition + mSourceDuration / 2), (int) mCurrentPosition);
//                    } else {
//                        mControlView.setAdvVideoPosition((int) (mAdvTotalPosition + mCurrentPosition), (int) mCurrentPosition);
//                    }
//                }
//            } else {
//                if (mControlView != null && !inSeek && mPlayerState == IPlayer.started) {
//                    mControlView.setVideoPosition((int) mCurrentPosition);
//                }
//            }
//        }
//        if (mOutInfoListener != null) {
//            mOutInfoListener.onInfo(infoBean);
//        }
    }

    @Override
    public void onPrepared() {
        mPlayerEventListener.onPrepared();
        // 修复播放纯音频时状态出错问题   TODO 这块逻辑需要注释掉，如果准备完毕后直接开始播放，这样也可能是可行方案
//        if (!isVideo()) {
//            mPlayerEventListener.onInfo(AbstractPlayer.MEDIA_INFO_RENDERING_START, 0);
//        }
    }

    @Override
    public void onVideoSizeChanged(int width, int height) {
        mPlayerEventListener.onVideoSizeChanged(width, height);
    }

    /**
     * 监听播放状态 阿里播放器
     * @param i
     */
    @Override
    public void onStateChanged(int i) {

        /**
         * 阿里播放器状态值 意思
         *     int unknow = -1;
         *     int idle = 0;//空状态
         *     int initalized = 1;
         *     int prepared = 2;//准备完毕
         *     int started = 3;//播放中
         *     int paused = 4;//暂停
         *     int stopped = 5;
         *     int completion = 6;//播放完毕
         *     int error = 7;
         */
        /**
         * 播放器框架的各种状态
         * STATE_ERROR = -1;
         * STATE_IDLE = 0;//空状态。
         * STATE_PREPARING = 1;//准备中
         * STATE_PREPARED = 2;//准备完毕
         * STATE_PLAYING = 3;//播放中
         * STATE_PAUSED = 4;//暂停
         * STATE_PLAYBACK_COMPLETED = 5;
         * STATE_BUFFERING = 6;//缓冲开始
         * STATE_BUFFERED = 7;//缓冲结束
         * STATE_START_ABORT = 8;//开始播放中止
         */

        mPlayerState = i;
        if(mPlayerState == IPlayer.started){//TODO 当阿里播放器开始播放，需要通知
            mPlayerEventListener.onInfo(AbstractPlayer.MEDIA_INFO_RENDERING_START, 0);
        }

        L.e("DkAliPlayer onStateChanged mPlayerState="+mPlayerState);
    }

    /**
     * 开始加载。
     */
    @Override
    public void onLoadingBegin() {
        mPlayerEventListener.onInfo(AbstractPlayer.MEDIA_INFO_BUFFERING_START, 0);
    }

    /**
     * 加载进度
     * @param percent               百分数
     * @param netSpeed              网络速度
     */
    @Override
    public void onLoadingProgress(int percent, float netSpeed) {

    }

    /**
     * 加载结束
     */
    @Override
    public void onLoadingEnd() {
        mPlayerEventListener.onInfo(AbstractPlayer.MEDIA_INFO_BUFFERING_END, 0);
    }
}
