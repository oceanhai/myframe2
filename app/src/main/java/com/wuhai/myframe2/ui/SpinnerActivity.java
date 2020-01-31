package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：Spinner
 * xml 解析文章 https://www.cnblogs.com/dasusu/p/5450919.html
 * TODO 特么的，parser.getAttributeValue  怎么把前面的0 给去掉了（以前用eclipse没问题）
 * 我擦了 xml数据都有问题，只改了北京对应的三级的数据
 */
public class SpinnerActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sp_pro, sp_city, sp_county;
    private List<String> proNameList, proIdList, cityIdList, cityNameList,countyNameList;
    private ArrayAdapter<String> adapter_city;
    private ArrayAdapter<String> adapter_county;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SpinnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_spinner);
        initView();
        fillProList();
        setAdapter();
        setListener();
    }

    @Override
    public void setStatistical() {

    }

    // 设置Spinner条目选择的监听
    private void setListener() {
        sp_pro.setOnItemSelectedListener(this);
        sp_city.setOnItemSelectedListener(this);

    }

    // 填充地级市列表
    private void fillCityList(String proId) {
        XmlResourceParser parser = getResources().getXml(R.xml.citys_weather);
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        cityIdList = new ArrayList<String>();
                        cityNameList = new ArrayList<String>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("c".equals(nodeName)) {
                            String cityId = parser.getAttributeValue(0);
                            if(cityId.length() == 3){// TODO 追加处理
                                cityId = "0"+cityId;
                            }
                            if (cityId.substring(0, 2).equals(proId)) {
                                cityIdList.add(cityId);
                                parser.next();
                                cityNameList.add(parser.nextText());
                            }
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 设置适配器
    private void setAdapter() {
        ArrayAdapter<String> adapter_pro = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, proNameList);
        adapter_city = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter_county = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        sp_pro.setAdapter(adapter_pro);

        sp_city.setAdapter(adapter_city);

        sp_county.setAdapter(adapter_county);
    }

    // 填充省份集合数据
    private void fillProList() {
        XmlResourceParser parser = getResources().getXml(R.xml.citys_weather);
        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        proIdList = new ArrayList<String>();
                        proNameList = new ArrayList<String>();
                        break;
                    case XmlPullParser.START_TAG:
                        if ("p".equals(nodeName)) {
                            String id = parser.getAttributeValue(0);
                            if(id.length() == 1){//TODO 追加处理
                                id = "0"+id;
                            }
                            proIdList.add(id);
                            parser.next();
                            proNameList.add(parser.nextText());
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 初始化view
    private void initView() {
        sp_pro = (Spinner) findViewById(R.id.sp_pro);
        sp_city = (Spinner) findViewById(R.id.sp_city);
        sp_county = (Spinner) findViewById(R.id.sp_county);
    }

    // 默认执行一次，选择第0个条目
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        switch (parent.getId()) {
            case R.id.sp_pro:

                String proId = proIdList.get(position);
                fillCityList(proId);
                adapter_city.clear();
                adapter_city.addAll(cityNameList);
                //默认选择省份下面的第一个地级市的所有县
                fillCountyList(cityIdList.get(0));
                adapter_county.clear();
                adapter_county.addAll(countyNameList);

                //设置选择位置
                sp_city.setSelection(0);
                sp_county.setSelection(0);
                break;
            case R.id.sp_city:
//			System.out.println("sp——city的条目选择监听执行了");
                String cityId = cityIdList.get(position);
                fillCountyList(cityId);
                adapter_county.clear();
                adapter_county.addAll(countyNameList);
                break;
            default:
                break;
        }


    }

    private void fillCountyList(String cityId) {
        XmlResourceParser parser = getResources().getXml(R.xml.citys_weather);
        try {
            int eventType = parser.getEventType();
            while(eventType!=XmlPullParser.END_DOCUMENT){
                String nodeName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        countyNameList=new ArrayList<String>();
                        break;
                    case XmlPullParser.START_TAG:
                        if("d".equals(nodeName)){
                            String countyId = parser.getAttributeValue(0);
                            if(countyId.substring(3,7).equals(cityId)){
                                countyNameList.add(parser.nextText());
                            }
                        }
                        break;
                    default:
                        break;
                }
                eventType=parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
