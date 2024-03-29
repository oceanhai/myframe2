package com.wuhai.myframe2.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.customview.view.LotteryView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义控件 集合
 *
 * @author wuhai
 * create at 2016/7/28 9:52
 */
public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.lottery_view)
    LotteryView lotteryView;

    @BindView(R.id.notice_container_tv)
    TextView noticeContaineTv;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CustomViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_custom_view);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);

        init();
    }

    private void init() {
        lotteryView.setResource("09,17,26,29,30,32,03");

        //TODO 200dp 宽度如果够宽，textview的跑马灯就不在跑马了
        // notice_container_tv match_parent 或 权重剩余部分宽度空间，都不再跑马了
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                HeaderTextGridViewActivity.startActivity(this);
                break;
            case R.id.ac_matrix:
                MatrixActivity.startActivity(this);
                break;
            case R.id.ClipImageView:
                ClipImageActivity.startActivity(this);
                break;
        }
    }
}
