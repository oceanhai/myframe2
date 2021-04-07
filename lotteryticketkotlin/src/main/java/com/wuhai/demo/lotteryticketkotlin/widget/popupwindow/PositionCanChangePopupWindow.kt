package com.wuhai.demo.lotteryticketkotlin.widget.popupwindow

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.utils.LogProxy.e

/**
 *
 * 跟随点击或者长按位置  弹PopupWindow
 * @author wuhai
 * create at 2016/2/26 15:55
 */
class PositionCanChangePopupWindow(private val mContext: Context, parent: View) {
    private val mParent: View
    private val mContentView: View
    private val mWindow: PopupWindow?
    private val mDelete: View
    private val mEditTv: View
    private val mSaveTv: View
    private var mOperCallback: IFloatingOperation? = null
    private fun setRelativeLayoutBackGround(topOf: Boolean) {
        if (topOf) {
            mContentView.setBackgroundResource(R.drawable.note_arrow_down_empty)
        } else {
            mContentView.setBackgroundResource(R.drawable.note_arrow_up_empty)
        }
    }

    /**
     * ※ 值操作要把data或者postion 传过来 根据想法改动回传接口
     *
     * downX - 控件高度 < 状态栏高度  朝下；反之 朝上
     * downX - 控件宽度/2 < 0  值为0;反之 正常差值
     * @param downX
     * @param downY
     */
    fun showPopupWindow(downX: Float, downY: Float) {
        mContentView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        var top = (downY - mContentView.measuredHeight).toInt()
//        e(LotteryCreateActivity.TAG, "FootprintsPopupWindow::top:" + top + "=" + downY + "-" + mContentView.measuredHeight)
        var topOf = true
//        e(LotteryCreateActivity.TAG, "FootprintsPopupWindow::getStatusHeight:" + getStatusHeight(mContext))
        if (top < getStatusHeight(mContext)) {
            topOf = false
            top = downY.toInt()
        }
        setRelativeLayoutBackGround(topOf)
        val left = (downX - mContentView.measuredWidth / 2).toInt()
        //		System.out.println("FootprintsPopupWindow::left:"+left);
        mWindow!!.showAtLocation(mParent, Gravity.NO_GRAVITY, if (left < 0) 0 else left, top)
    }

    fun hide() {
        mWindow!!.dismiss()
    }

    /**
     * Status bar是launcher主界面上面提示当前状态（电池，网络，蓝牙等等）的一个栏
     * 获取状态栏高度
     * @param context
     * @return
     */
    private fun getStatusHeight(context: Context): Int {
        var height = 0
        try {
            val c = Class.forName("com.android.internal.R\$dimen")
            val obj = c.newInstance()
            val field = c.getField("status_bar_height")
            val x = field[obj].toString().toInt()
            height = context.resources.getDimensionPixelSize(x)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return height
    }

    /**
     * PopupWindow 是否正在显示
     * @return
     */
    val isShowing: Boolean
        get() = mWindow?.isShowing ?: false

    /**
     * 回调接口
     * @param l
     */
    fun setFloatingOperation(l: IFloatingOperation?) {
        mOperCallback = l
    }

    val mClickListener = View.OnClickListener { v ->
        if (mOperCallback == null) {
            return@OnClickListener
        }
        when (v.id) {
            R.id.edit_tv -> mOperCallback!!.onEdit()
            R.id.delete_tv -> mOperCallback!!.onDelete()
            R.id.save_tv -> mOperCallback!!.onSave()
            else -> {
            }
        }
        hide()
    }

    interface IFloatingOperation {
        fun onEdit()
        fun onDelete()
        fun onSave()
    }

    init {
        mContentView = View.inflate(mContext, R.layout.popwindow_position, null)
        mWindow = PopupWindow(mContentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        mWindow.setAnimationStyle(android.R.style.Animation_Toast)
        mParent = parent
        mDelete = mContentView.findViewById(R.id.delete_tv)
        mEditTv = mContentView.findViewById(R.id.edit_tv)
        mSaveTv = mContentView.findViewById(R.id.save_tv)
        mDelete.setOnClickListener(mClickListener)
        mEditTv.setOnClickListener(mClickListener)
        mSaveTv.setOnClickListener(mClickListener)
    }
}