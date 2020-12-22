package com.wuhai.aop;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @BehaviorTrace("摇一摇")
    public void play1(View view) {

        Log.i("MainActivity", "摇一摇被使用了");
        SystemClock.sleep(30);

    }
    @BehaviorTrace("语音")
    public void play2(View view) {

//        int beginTime = TimeUtils.begin();
        SystemClock.sleep(30);
        /*TimeUtils.end(beginTime, "play2");*/

    }

    @BehaviorTrace("视频通话")
    public void play3(View view) {

//        int beginTime = TimeUtils.begin();
        SystemClock.sleep(30);
        /*TimeUtils.end(beginTime, "play3");*/

    }

    @BehaviorTrace("商城")
    public void play4(View view) {

//        int beginTime = TimeUtils.begin();
        SystemClock.sleep(30);
        /*TimeUtils.end(beginTime, "play4");*/

    }

    public void jumpToFriend(View view) {
//        Intent i = new Intent(this,FriendActivity.class);
//        startActivity(i);

    }
}
