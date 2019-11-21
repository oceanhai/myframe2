package com.wuhai.myframe2.ui.sound;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：Sound
 */
public class SoundActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn01)
    Button btn01;

    //播放器
    private MediaPlayer mMediaPlayer;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SoundActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_sound);
        ButterKnife.bind(this);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {

    }

    private void setListener() {
        btn01.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn01:
                //播放 金币进账铃声
//                SoundUtils soundUtils = new SoundUtils(this, SoundUtils.RING_SOUND);
//                soundUtils.putSound(0,R.raw.ring);
//                soundUtils.playSound(0, SoundUtils.SINGLE_PLAY);

                if(mMediaPlayer == null){
                    mMediaPlayer = MediaPlayer.create(this, R.raw.diaoluo_da);
                }
                mMediaPlayer.start();
                break;
        }
    }
}
