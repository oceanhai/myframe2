package com.wuhai.myframe2.ui.dkplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.wuhai.myframe2.R;

import xyz.doikki.videocontroller.StandardVideoController;
import xyz.doikki.videocontroller.component.CompleteView;
import xyz.doikki.videocontroller.component.ErrorView;
import xyz.doikki.videocontroller.component.GestureView;
import xyz.doikki.videocontroller.component.PrepareView;
import xyz.doikki.videocontroller.component.TitleView;
import xyz.doikki.videocontroller.component.VodControlView;
import xyz.doikki.videoplayer.player.VideoView;
import xyz.doikki.videoplayer.util.L;

/**
 * 作者 wh
 * 
 * 创建日期 2021/11/3 16:43
 * 
 * 描述：
 *
 */
public class DkplayerActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    //封面图
    private static final String THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";

    private VideoView mVideoView;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DkplayerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_dkplayer);

        mVideoView = findViewById(R.id.player);

        StandardVideoController controller = new StandardVideoController(this);
        //根据屏幕方向自动进入/退出全屏
        controller.setEnableOrientation(false);

        //添加封面图
        PrepareView prepareView = new PrepareView(this);//准备播放界面
        prepareView.setClickStart();
        ImageView thumb = prepareView.findViewById(R.id.thumb);//封面图
        Glide.with(this).load(THUMB).into(thumb);
        controller.addControlComponent(prepareView);

        controller.addControlComponent(new CompleteView(this));//自动完成播放界面

        controller.addControlComponent(new ErrorView(this));//错误界面

        TitleView titleView = new TitleView(this);//标题栏
        //设置标题
        titleView.setTitle("");
        controller.addControlComponent(titleView);

        //根据是否为直播设置不同的底部控制条
//        controller.addControlComponent(new LiveControlView(this));//直播控制条
        VodControlView vodControlView = new VodControlView(this);//点播控制条
        //是否显示底部进度条。默认显示
//        vodControlView.showBottomProgress(false);
        controller.addControlComponent(vodControlView);

        GestureView gestureControlView = new GestureView(this);//滑动控制视图
        controller.addControlComponent(gestureControlView);
        //根据是否为直播决定是否需要滑动调节进度 TODO 点播true 直播false
        controller.setCanChangePosition(true);



        //注意：以上组件如果你想单独定制，我推荐你把源码复制一份出来，然后改成你想要的样子。
        //改完之后再通过addControlComponent添加上去
        //你也可以通过addControlComponent添加一些你自己的组件，具体实现方式参考现有组件的实现。
        //这个组件不一定是View，请发挥你的想象力😃

        //如果你不需要单独配置各个组件，可以直接调用此方法快速添加以上组件 TODO
//            controller.addDefaultControlComponent(title, isLive);

        //竖屏也开启手势操作，默认关闭
//            controller.setEnableInNormal(true);
        //滑动调节亮度，音量，进度，默认开启
//            controller.setGestureEnabled(false);
        //适配刘海屏，默认开启
//            controller.setAdaptCutout(false);
        //双击播放暂停，默认开启
//            controller.setDoubleTapTogglePlayEnabled(false);

        //在控制器上显示调试信息
//        controller.addControlComponent(new DebugInfoView(this));
        //在LogCat显示调试信息
//        controller.addControlComponent(new PlayerMonitor());

        //如果你不想要UI，不要设置控制器即可
        mVideoView.setVideoController(controller);

        String url = "http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4";

        mVideoView.setUrl(url);

        //保存播放进度
//            mVideoView.setProgressManager(new ProgressManagerImpl());
        //播放状态监听
        mVideoView.addOnStateChangeListener(mOnStateChangeListener);

        //临时切换播放核心，如需全局请通过VideoConfig配置，详见MyApplication
        //使用IjkPlayer解码
//            mVideoView.setPlayerFactory(IjkPlayerFactory.create());
        //使用ExoPlayer解码
//            mVideoView.setPlayerFactory(ExoMediaPlayerFactory.create());
        //使用MediaPlayer解码
//            mVideoView.setPlayerFactory(AndroidMediaPlayerFactory.create());

        //设置静音播放
//            mVideoView.setMute(true);

        //从设置的position开始播放
//            mVideoView.skipPositionWhenPlay(10000);

//        mVideoView.start();
    }

    private VideoView.OnStateChangeListener mOnStateChangeListener = new VideoView.SimpleOnStateChangeListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {
            switch (playerState) {
                case VideoView.PLAYER_NORMAL://小屏
                    L.d("onPlayerStateChanged 小屏");
                    break;
                case VideoView.PLAYER_FULL_SCREEN://全屏
                    L.d("onPlayerStateChanged 全屏");
                    break;
            }
        }

        @Override
        public void onPlayStateChanged(int playState) {
            switch (playState) {
                case VideoView.STATE_IDLE:
                    break;
                case VideoView.STATE_PREPARING:
                    break;
                case VideoView.STATE_PREPARED:
                    break;
                case VideoView.STATE_PLAYING:
                    //需在此时获取视频宽高
                    int[] videoSize = mVideoView.getVideoSize();
                    L.d("视频宽：" + videoSize[0]);
                    L.d("视频高：" + videoSize[1]);
                    break;
                case VideoView.STATE_PAUSED:
                    break;
                case VideoView.STATE_BUFFERING:
                    break;
                case VideoView.STATE_BUFFERED:
                    break;
                case VideoView.STATE_PLAYBACK_COMPLETED:
                    break;
                case VideoView.STATE_ERROR:
                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null) {
//            mVideoView.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        //如果视频还在准备就 activity 就进入了后台，建议直接将 VideoView release
        //防止进入后台后视频还在播放
        if (mVideoView.getCurrentPlayState() == VideoView.STATE_PREPARING) {
            mVideoView.release();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
