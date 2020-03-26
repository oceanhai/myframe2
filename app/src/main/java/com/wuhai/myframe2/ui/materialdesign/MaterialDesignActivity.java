package com.wuhai.myframe2.ui.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;
import com.wuhai.myframe2.ui.materialdesign.yxt.RetailSalerBrandPageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 材料设计
 */
public class MaterialDesignActivity extends BaseActivity2 implements View.OnClickListener {

    @BindView(R.id.navigation_view)
    TextView navigationView;
    @BindView(R.id.coordinator_layout)
    TextView coordinatorLayout;
    @BindView(R.id.collapsing_toolbar_layout)
    TextView collapsingToolbarLayout;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MaterialDesignActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_material_design);
        ButterKnife.bind(this);

        initListener();
    }

    private void initListener() {
        navigationView.setOnClickListener(this);
        coordinatorLayout.setOnClickListener(this);
        collapsingToolbarLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigation_view://抽屉
                NavigationViewActivity.startActivity(this);
                break;
            case R.id.coordinator_layout://材料设计
                RetailSalerBrandPageActivity.startActivity(this);
                break;
            case R.id.collapsing_toolbar_layout://折叠效果
                CollapsingtToolbarlayoutActivity.startActivity(this);
                break;
        }
    }
}
