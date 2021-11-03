package com.wuhai.myframe2.ui.recyclerview;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewUtils {
    /**
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     * @param spanCount    规定一行显示几列的参数常量
     */
    public static void initRecycle(RecyclerView recyclerView, Context context, boolean isHorizontal, int spanCount) {
        //布局管理器对象
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, spanCount);
        //这一句不能漏，否则adapter中看不见数据
        if (!isHorizontal) { //垂直滑动
            //设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        } else { //水平滑动
            //设置布局管理器
            /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);*/
            gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }

    /**
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     * @param spanCount    规定一行显示几列的参数常量
     */
    public static void initGridRecycleNoScroll(RecyclerView recyclerView, Context context, boolean isHorizontal, int spanCount) {
        //布局管理器对象
        GridLayoutManager gridLayoutManager;

        if (!isHorizontal) { //垂直滑动
            gridLayoutManager = new GridLayoutManager(context, spanCount) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);

        } else { //水平滑动
            gridLayoutManager = new GridLayoutManager(context, spanCount) {
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
            gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        }
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /**
     * 初始化不可滑动的recycleview
     *
     * @param recyclerView
     * @param context
     * @param isHorizontal 是否水平滑动
     */
    public static void initNoScrollRecycle(RecyclerView recyclerView, Context context, boolean isHorizontal) {
        //布局管理器对象
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        if (isHorizontal) { //水平滑动
            linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL, false) {
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
        } else {//垂直滑动
            linearLayoutManager = new LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
