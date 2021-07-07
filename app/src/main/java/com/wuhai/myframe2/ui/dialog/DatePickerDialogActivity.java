package com.wuhai.myframe2.ui.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcDatePickerDialogBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.util.Calendar;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：DatePickerDialog
 */
public class DatePickerDialogActivity extends BaseActivity {

    private AcDatePickerDialogBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DatePickerDialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_null);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_date_picker_dialog);
    }

    private void setListener() {
        binding.time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtime();
            }
        });
    }

    private void showtime() {
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                binding.time.setText(i + "年" + (i1+1) + "月" + i2 + "日");//当选择完后将时间显示,记得月份i1加一
            }
        }, mYear,mMonth, mDay);//将年月日放入DatePickerDialog中，并将值传给参数
        datePickerDialog.setTitle("选择日期");//TODO 没啥卵用
        datePickerDialog.show();//显示dialog

    }
}
