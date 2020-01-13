package com.wuhai.myframe2.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.customview.view.HeaderTextGridView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderTextGridViewActivity extends AppCompatActivity {


    @BindView(R.id.pay_amount_detail)
    HeaderTextGridView payAmountDetail;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HeaderTextGridViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_header_text_grid_view);
        ButterKnife.bind(this);

        payAmountDetail.setResource(new String[][]{{"", "还款前", "还款后"},
                {"借款本金", 100 + "元", 101 + "元"},
                {"借款费用", 200 + "元", 201 + "元"},
                {"逾期费用", 300 + "元", 301 + "元"}});
        payAmountDetail.startLight();
    }
}
