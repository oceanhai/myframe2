package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lljjcoder.style.citythreelist.CityBean;
import com.lljjcoder.style.citythreelist.ProvinceActivity;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：地址选择
 * ※本ac 使用的自己电脑下  work/github/citypickerview 这个 详情看readme.txt
 * 此外   work/github/Android-PickerView 本地加载 符合我预期，准备用这个
 */
public class AddressChooseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.tv01)
    TextView tv01;
    @BindView(R.id.btn02)
    Button btn02;
    @BindView(R.id.tv02)
    TextView tv02;

    //申明对象
    CityPickerView mPicker=new CityPickerView();

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddressChooseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_address_choose);
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
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mPicker.init(this);
    }

    private void setListener() {
        btn01.setOnClickListener(this);
        btn02.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01://列表样式  初始化数据BaseApplication CityListLoader.getInstance().loadProData(this);
                //跳转到省份列表
                Intent intent = new Intent(this, ProvinceActivity.class);
                startActivityForResult(intent, ProvinceActivity.RESULT_DATA);
                break;
            case R.id.btn02://仿ios 初始化数据        mPicker.init(this);
                ios();
                break;
        }
    }

    private void ios() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);

        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {

            @Override
            public void onSelected(ProvinceBean province, com.lljjcoder.bean.CityBean city, DistrictBean district) {
                super.onSelected(province, city, district);
                //省份
                if (province != null) {
                    tv02.append("province=" + province.getId() + "," + province.getName() + "\n");
                }

                //城市
                if (city != null) {
                    tv02.append("city=" + city.getId() + "," + city.getName() + "\n");
                }

                //地区
                if (district != null) {
                    tv02.append("area=" + district.getId() + "," + district.getName() + "\n");
                }
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(AddressChooseActivity.this, "已取消");
            }
        });

        //显示
        mPicker.showCityPicker( );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }
                //省份结果
                CityBean province = data.getParcelableExtra("province");
                //城市结果
                CityBean city = data.getParcelableExtra("city");
                //区域结果
                CityBean area = data.getParcelableExtra("area");

                tv01.append("province=" + province.getId() + "," + province.getName() + "\n");
                tv01.append("city=" + city.getId() + "," + city.getName() + "\n");
                tv01.append("area=" + area.getId() + "," + area.getName() + "\n");
            }
        }
    }

}
