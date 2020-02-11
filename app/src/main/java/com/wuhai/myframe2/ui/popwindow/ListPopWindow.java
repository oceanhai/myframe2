package com.wuhai.myframe2.ui.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.myframe2.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者 wh
 *
 * 创建日期 2020/2/10 15:19
 *
 * 描述：
 */
public class ListPopWindow extends PopupWindow {

    private static final String TAG = "ListPopWindow";

    public ListPopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View conentView = inflater.inflate(R.layout.popwindow_list, null);

        RecyclerView mRecyclerView = (RecyclerView) conentView.findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        ListNumAdapter mListNumAdapter = new ListNumAdapter(context);
        mRecyclerView.setAdapter(mListNumAdapter);

        List<String> list = new ArrayList<>();
        for (int x=6;x<=20;x++){
            list.add(""+x);
        }
        mListNumAdapter.setData(list);


        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimationPreview);
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
//            this.showAsDropDown(parent);

//            int width = parent.getLayoutParams().width;
//            Log.e(TAG, "width="+width);//TODO -2 什么鬼得出
            int width = parent.getWidth();//264 这才是真正的view宽度
            int popwindow_width = this.getWidth();//-2 坑
            Log.e(TAG, "width="+width+",popwindow_width="+popwindow_width);

            this.showAsDropDown(parent, 20, 0);
        } else {
            this.dismiss();
        }
    }
}
