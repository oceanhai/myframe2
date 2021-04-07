package com.wuhai.demo.lotteryticketkotlin.widget.popupwindow

import android.app.Activity
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.ListNumAdapter
import com.wuhai.demo.lotteryticketkotlin.ui.adapter.base.BaseDataAdapter
import java.util.*

/**
 * 作者 wh
 *
 * 创建日期 2020/2/10 15:19
 *
 * 描述：
 */
class ListPopWindow(context: Activity, private val mType: Int) : PopupWindow(), BaseDataAdapter.OnItemClickLitener {
    private val mListNumAdapter: ListNumAdapter
    var mOnNumCallBackLitener: OnNumCallBackLitener? = null
    fun showPopupWindow(parent: View?) {
        if (!this.isShowing) {
            this.showAsDropDown(parent)

//            int width = parent.getLayoutParams().width;
//            Log.e(TAG, "width="+width);//TODO -2 什么鬼得出
//            int width = parent.getWidth();//264 这才是真正的view宽度
//            int popwindow_width = this.getWidth();//-2 坑
//            Log.e(TAG, "width="+width+",popwindow_width="+popwindow_width);
//
//            this.showAsDropDown(parent, 20, 0);
        } else {
            dismiss()
        }
    }

    /**
     * 设置点击监听
     * @param mOnNumCallBackLitener
     */
    fun setOnNumCallBackLitener(mOnNumCallBackLitener: OnNumCallBackLitener?) {
        this.mOnNumCallBackLitener = mOnNumCallBackLitener
    }

    override fun onItemClick(view: View?, position: Int) {
        if (mOnNumCallBackLitener != null) {
            dismiss()
            mOnNumCallBackLitener!!.onNumCallBack(mType, mListNumAdapter.getDataItem(position))
        }
    }

    /**
     * 回调
     */
    interface OnNumCallBackLitener {
        fun onNumCallBack(type: Int, num: String?)
    }

    companion object {
        private const val TAG = "ListPopWindow"
    }

    init {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val conentView = inflater.inflate(R.layout.popwindow_list, null)
        val mRecyclerView = conentView.findViewById<View>(R.id.list) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(context)
        mRecyclerView.layoutManager = linearLayoutManager
        mListNumAdapter = ListNumAdapter(context)
        mListNumAdapter.setOnItemClickLitener(this)
        mRecyclerView.adapter = mListNumAdapter
        val list: MutableList<String?> = ArrayList()
        var start = 0
        var end = 0
        if (mType == 0) {
            start = 6
            end = 20
        } else {
            start = 1
            end = 16
        }
        for (x in start..end) {
            list.add("" + x)
        }
        mListNumAdapter.setData(list)
        val h = context.windowManager.defaultDisplay.height
        val w = context.windowManager.defaultDisplay.width
        // 设置SelectPicPopupWindow的View
        this.contentView = conentView
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.width = ViewGroup.LayoutParams.WRAP_CONTENT
        // 设置SelectPicPopupWindow弹出窗体的高
        this.height = ViewGroup.LayoutParams.WRAP_CONTENT
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.isFocusable = true
        this.isOutsideTouchable = true
        // 刷新状态
        this.update()
        // 实例化一个ColorDrawable颜色为半透明
        val dw = ColorDrawable(0x00000000)
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        setBackgroundDrawable(dw)
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
//        this.setAnimationStyle(R.style.AnimationPreview);
    }
}