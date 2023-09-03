package com.wuhai.myframe2.ui.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;
import com.wuhai.myframe2.utils.ToastUtil;
import com.wuhai.myframe2.widget.dialog.DialogChooseDate;
import com.wuhai.myframe2.widget.dialog.DialogTwoBtnSelect;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：Dialog
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn03)
    Button btn03;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, DialogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_dialog);
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
        btn02.setOnClickListener(this);
        btn03.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                initDateDialog();
                break;
            case R.id.btn02:
                showtime();
                break;
            case R.id.btn03:
                showTwoBtnDialog();
                break;
        }
    }

    private void showTwoBtnDialog() {
        DialogTwoBtnSelect mDialog = new DialogTwoBtnSelect(this);
        mDialog.setOnDialogPositiveListener(new DialogTwoBtnSelect.DialogPositiveListener() {
            @Override
            public void onPositive() {
                ToastUtil.showDebug("onPositive");
            }
        });
        mDialog.setOnDialogNegativeListener(new DialogTwoBtnSelect.DialogNegativeListener() {
            @Override
            public void onNegative() {
                ToastUtil.showDebug("onNegative");
            }
        });
        mDialog.setCancelable(false);
        mDialog.show();
    }

    /**
     * 时间的dialog 初始化
     */
    public void initDateDialog() {
        DialogChooseDate dateDialog = new DialogChooseDate(this);
        dateDialog.setBirthdayListener(new DialogChooseDate.OnBirthListener() {
            @Override
            public void onClick(String year, String month, String day) {
                StringBuffer sb = new StringBuffer();
                String monthStr = "";
                String dayStr = "";
                int monthInt = Integer.valueOf(month);
                int dayInt = Integer.valueOf(day);
                if (monthInt < 10) {
                    monthStr = "0" + monthInt;
                } else {
                    monthStr = month;
                }
                if (dayInt < 10) {
                    dayStr = "0" + dayInt;
                } else {
                    dayStr = day;
                }
                String time = sb.append(year).append("-").append(monthStr).append("-") + dayStr;

                //设置日期
                tv01.setText(time);
            }
        });

        String arr[];
        int year = 0, month = 0, day = 0;
        String dateStr = tv01.getText().toString();//获取日期

        if (!TextUtils.isEmpty(dateStr)) {
            arr = dateStr.split("-");
            year = Integer.parseInt(arr[0]);
            month = Integer.parseInt(arr[1]);
            day = Integer.parseInt(arr[2]);
        } else {//未获取到
            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DATE);
        }
        dateDialog.setDate(year, month, day);
        dateDialog.show();
    }

    /**
     * DatePickerDialog
     */
    private void showtime() {
        Calendar calendar = Calendar.getInstance();//调用Calendar类获取年月日
        int  mYear = calendar.get(Calendar.YEAR);//年
        int  mMonth = calendar.get(Calendar.MONTH);//月份要加一个一，这个值的初始值是0。不加会日期会少一月。
        int  mDay = calendar.get(Calendar.DAY_OF_MONTH);//日
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                tv01.setText(i + "-" + (i1+1) + "-" + i2 );//当选择完后将时间显示,记得月份i1加一
            }
        }, mYear,mMonth, mDay);//将年月日放入DatePickerDialog中，并将值传给参数
        datePickerDialog.setTitle("选择日期");//TODO 没啥卵用
        datePickerDialog.show();//显示dialog

    }

}
