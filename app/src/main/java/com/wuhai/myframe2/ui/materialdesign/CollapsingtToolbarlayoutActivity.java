package com.wuhai.myframe2.ui.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 折叠效果
 */
public class CollapsingtToolbarlayoutActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CollapsingtToolbarlayoutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_material_collapsingt_toolbarlayout);
        ButterKnife.bind(this);

        init();
        initListener();
    }

    private void init(){
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle("我是课程");
    }

    private void initListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floating_action_button:
                ToastUtils.showShort(this, "浮动button被点了");
                break;
        }
    }
}
