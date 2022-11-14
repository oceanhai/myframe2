package com.wuhai.myframe2.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewSwitcher;

import com.wuhai.myframe2.R;

/**
 * ViewSwitcher
 *
 * Android ViewSwitcher主要应用场景之一：比如在一个布局文件中，根据业务需求，需要在两个View间切换，在任意一个时刻，只能显示一个View。
 */
public class ViewSwitcherActivity extends Activity {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ViewSwitcherActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_switcher);

        Button buttonPrev = (Button) findViewById(R.id.prev);
        Button buttonNext = (Button) findViewById(R.id.next);

        final ViewSwitcher viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);

        Animation slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        viewSwitcher.setInAnimation(slide_in_left);
        viewSwitcher.setOutAnimation(slide_out_right);

        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(viewSwitcher.getDisplayedChild() != 0){
                    // viewSwitcher.showPrevious();切换效果类似
                    viewSwitcher.setDisplayedChild(0);
                }

            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(viewSwitcher.getDisplayedChild() != 1){
                    // viewSwitcher.showNext();切换效果类似
                    viewSwitcher.setDisplayedChild(1);
                }
            }
        });
    }
}