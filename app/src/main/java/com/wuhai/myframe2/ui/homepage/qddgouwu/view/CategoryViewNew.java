package com.wuhai.myframe2.ui.homepage.qddgouwu.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.homepage.qddgouwu.util.DimensionUtil;

/**
 * createby yangzheng
 * date 2016/12/23
 * email yangzhenop@126.com
 * TODO wuhai
 */
public class CategoryViewNew extends RelativeLayout {

    private Context mContext;
    public CategoryCutTag mFlag;
    public SimpleDraweeView mImg;
    public TextView mName;
    public TextView mPrice;
    public TextView mMonthly;
    public TextView mExtra;
    public String mNameExtra;

    public CategoryViewNew(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CategoryViewNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        inflate(mContext, R.layout.category_layout_new,this);
        setPadding((int) DimensionUtil.convertDpToPx(mContext, 5f), (int) DimensionUtil.convertDpToPx(mContext, 5f)
                , (int) DimensionUtil.convertDpToPx(mContext, 5f), (int) DimensionUtil.convertDpToPx(mContext, 5f));
        mFlag = (CategoryCutTag) findViewById(R.id.pro_flag);
        mImg = (SimpleDraweeView) findViewById(R.id.pro_img);
        mImg.setAspectRatio(1.0f);
        mName = (TextView) findViewById(R.id.pro_name);
        mPrice = (TextView) findViewById(R.id.pro_price);
        mMonthly = (TextView) findViewById(R.id.pro_monthly);
        mExtra = (TextView) findViewById(R.id.pro_name_extra);
        mExtra.setTypeface(Typeface.DEFAULT_BOLD);
        setBackgroundColor(Color.parseColor("#ffffff"));
    }

    public void setFlag(int cut){
        mFlag.setCut(cut);
    }

    public void setImg(String url){
        mImg.setImageURI(Uri.parse(url));
    }

    public void setName(String name){
        if(TextUtils.isEmpty(mNameExtra)){
            mName.setText(name);
        }else{
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.TRANSPARENT);
            SpannableString ss = new SpannableString(mNameExtra+"辅"+name);
            ss.setSpan(foregroundColorSpan,0,mNameExtra.length()+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mName.setText(ss);
        }
    }

    public void setNameExtra(String extra){
        mNameExtra = extra;
        mExtra.setText(extra);
        mExtra.setVisibility(TextUtils.isEmpty(extra)?GONE:VISIBLE);
        setName(mName.getText().toString());
    }

    public void setPrice(String price){
        mPrice.setText("¥ "+price);
    }

    public void setMonthly(String amount){
        mMonthly.setText("月供 ¥ "+amount);
    }

    public void setCoupon(boolean enable){
        int drawableRight = enable ? R.drawable.coupon_icon : 0;
        mPrice.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawableRight, 0);
    }

    private String buildExtra(float len){
        StringBuilder stringBuilder = new StringBuilder();
        String c = Character.SPACE_SEPARATOR+"";
        float raw = mName.getPaint().measureText(c);
        for (int i=0; i<len; i++){
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

}