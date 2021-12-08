package com.wuhai.myframe2.ui.pldroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;
import com.wuhai.myframe2.R;


/**
 * Created by wuhai on 2017/11/20 0020 15:06.
 * 描述：七牛云 播放器
 * 注意点
 * 1.混淆
 * 2.lib下放jar包、main/jinLibs下放.so文件（或者build.gradle从新指定路径）
 *
 * 本页面没通，查看官方demo，D:\work\third\pldroid\PLDroidPlayer
 * 但官方的播放 大视频也会出现问题，而且交互还没有dk播放器 好
 */
public class PldroidPlayerActivity extends AppCompatActivity implements PLMediaPlayer.OnPreparedListener, PLMediaPlayer.OnInfoListener, PLMediaPlayer.OnCompletionListener, PLMediaPlayer.OnVideoSizeChangedListener, PLMediaPlayer.OnErrorListener, PLMediaPlayer.OnBufferingUpdateListener, PLMediaPlayer.OnSeekCompleteListener {

    private static final String TAG = "PldroidPlayer";

    private PLVideoView mVideoView;

    private String downLoadPath = Environment.getExternalStorageDirectory() + "/posadvdownload/";

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PldroidPlayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pldroid_player);

        mVideoView = (PLVideoView) findViewById(R.id.PLVideoView);

        //4.3.3 关联播放控制器
//        MediaController mMediaController = new MediaController(this);
//        mVideoView.setMediaController((IMediaController) mMediaController);

        //4.3.4 设置加载动画
//        View loadingView = findViewById(R.id.LoadingView);
//        mVideoView.setBufferingIndicator(loadingView);

        //5.2 播放状态回调
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnVideoSizeChangedListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnSeekCompleteListener(this);

        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);
//        mVideoView.setVideoPath(downLoadPath + "video01.mp4");
//        mVideoView.setVideoPath("http://img.xiongzhangh.com/xuan.mp4");
        mVideoView.setVideoPath("http://img.hbszgxpt.com/video1.mp4");
        mVideoView.start();
    }

    /**
     * Called when the media file is ready for playback.
     *
     * @param plMediaPlayer the PLMediaPlayer that is ready for playback
     *
     * @param preparedTime prepared time: ms
     */
    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer, int preparedTime) {
        Log.e(TAG, "播放准备完毕:"+preparedTime);
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int extra) {
        //what 定义了消息类型，extra 是附加参数
        switch (what){
            case PLMediaPlayer.MEDIA_INFO_UNKNOWN:
                Log.e(TAG, "播放info:未知错误");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                Log.e(TAG, "播放info:第一帧视频已成功渲染");
                break;
            case PLMediaPlayer.MEDIA_INFO_CONNECTED:
                Log.e(TAG, "播放info:连接成功");
                break;
            case PLMediaPlayer.MEDIA_INFO_METADATA:
                Log.e(TAG, "播放info:读取到 metadata 信息");
                break;
            case PLMediaPlayer.MEDIA_INFO_BUFFERING_START:
                Log.e(TAG, "播放info:开始缓冲");
                break;
            case PLMediaPlayer.MEDIA_INFO_BUFFERING_END:
                Log.e(TAG, "播放info:停止缓冲");
                break;
            case PLMediaPlayer.MEDIA_INFO_SWITCHING_SW_DECODE:
                Log.e(TAG, "播放info:硬解失败，自动切换软解");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                Log.e(TAG, "播放info:获取到视频的播放角度");
                break;
            case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                Log.e(TAG, "播放info:第一帧音频已成功播放");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_GOP_TIME:
                Log.e(TAG, "播放info:获取视频的I帧间隔");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_BITRATE:
                Log.e(TAG, "播放info:视频的码率统计结果");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_FPS:
                Log.e(TAG, "播放info:视频的帧率统计结果");
                break;
            case PLMediaPlayer.MEDIA_INFO_AUDIO_BITRATE:
                Log.e(TAG, "播放info:音频的帧率统计结果");
                break;
            case PLMediaPlayer.MEDIA_INFO_AUDIO_FPS:
                Log.e(TAG, "播放info:音频的帧率统计结果");
                break;
        }
        return false;
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {
        Log.e(TAG, "播放完毕");
    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1) {
        //该回调用于监听当前播放的视频流的尺寸信息，在 SDK 解析出视频的尺寸信息后，
        // 会触发该回调，开发者可以在该回调中调整 UI 的视图尺寸。
    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int errorCode) {
        switch (errorCode){
            case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                Log.e(TAG, "播放err:未知错误");
                break;
            case PLMediaPlayer.ERROR_CODE_OPEN_FAILED:
                Log.e(TAG, "播放err:播放器打开失败");
                break;
            case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                Log.e(TAG, "播放err:网络异常");
                break;
            case PLMediaPlayer.ERROR_CODE_SEEK_FAILED:
                Log.e(TAG, "播放err:拖动失败");
                break;
            case PLMediaPlayer.ERROR_CODE_HW_DECODE_FAILURE:
                Log.e(TAG, "播放err:硬解失败");
                break;
        }

        return false;
    }

    @Override
    public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
        //该回调用于监听当前播放器已经缓冲的数据量占整个视频时长的百分比，
        // 在播放直播流中无效，仅在播放文件和回放时才有效。
    }

    @Override
    public void onSeekComplete(PLMediaPlayer plMediaPlayer) {
        //该回调用于监听 seek 完成的消息，当调用的播放器的 seekTo 方法后，
        // SDK 会在 seek 成功后触发该回调。
    }
}
