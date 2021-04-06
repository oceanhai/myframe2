package com.wuhai.demo.lotteryticketkotlin.ui.base

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.wuhai.demo.lotteryticketkotlin.R
import com.wuhai.demo.lotteryticketkotlin.application.BaseApplication
import kotlinx.android.synthetic.main.ac_base_action_bar.*

abstract class BaseActionBarActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.setContentView(R.layout.ac_base_action_bar)

        base_action_left_txt.setOnClickListener{
            onLeftActionClicked()
        }

        base_action_right_txt.setOnClickListener {
            if (TextUtils.isEmpty(base_action_right_txt.text)
                    && base_action_right_txt.compoundDrawables[2] == null){
                return@setOnClickListener
            }

            onRightActionClicked()
        }

        base_loading_container_layout.setOnClickListener{
            if (base_loading_failed_text_view.visibility == View.VISIBLE) {
                reloadData()
            }
        }
    }

    /**
     * 根布局
     * @return
     */
    open fun getRootView(): ViewGroup? {
        return base_root_view
    }

    /**
     * 隐藏 titlebar
     * @param visible
     */
    open fun setActionBarVisibility(visible: Boolean) {
        base_action_bar_area!!.visibility = if (visible) View.VISIBLE else View.GONE
    }

    /**
     * 设置 titlebar 颜色
     * @param colorString
     */
    open fun setActionBarBackground(colorString: String?) {
        base_action_bar_area!!.setBackgroundColor(Color.parseColor(colorString))
    }

    /**
     * get title
     * @return
     */
    open fun getActionTitle(): String? {
        return base_action_bar_title_txt!!.text.toString()
    }

    override fun setTitle(title: CharSequence?) {
        base_action_bar_title_txt!!.text = title
    }

    override fun setTitle(titleResId: Int) {
        base_action_bar_title_txt!!.text = getString(titleResId)
    }

    /**
     * set left icon visibility
     * @param enable
     */
    open fun setActionLeftEnable(enable: Boolean) {
        if (!enable) {
            base_action_left_txt!!.visibility = View.GONE
        }
    }

    /**
     * set left text(string)
     * @param actionText
     */
    open fun setActionLeftText(actionText: CharSequence?) {
        base_action_left_txt!!.text = actionText
    }

    /**
     * set left text(id)
     * @param actionResId
     */
    open fun setActionLeftText(actionResId: Int) {
        base_action_left_txt!!.text = getString(actionResId)
    }

    /**
     * set left icon
     * @param iconResId
     */
    open fun setActionLeftIcon(iconResId: Int) {
        base_action_left_txt
                .setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(iconResId),
                        null, null, null)
    }

    /**
     * set right text(string)
     * @param actionText
     */
    open fun setActionRightText(actionText: CharSequence?) {
        base_action_right_txt!!.text = actionText
    }

    /**
     * set right text(id)
     * @param actionResId
     */
    open fun setActionRightText(actionResId: Int) {
        base_action_right_txt!!.text = getString(actionResId)
    }

    /**
     * set right icon
     * @param iconResId
     */
    open fun setActionRightIcon(iconResId: Int) {
        var drawable: Drawable? = null
        if (iconResId > 0) {
            drawable = resources.getDrawable(iconResId)
        }
        base_action_right_txt!!.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null)
    }

    /**
     * set 实质布局(id)
     * @param layoutResID
     */
    override fun setContentView(layoutResID: Int) {
        setContentView(View.inflate(this, layoutResID, null))
    }

    /**
     * set 实质布局(view)
     * @param view
     */
    override fun setContentView(view: View?) {
        base_content_container!!.addView(view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        base_content_container!!.addView(view, params)
    }

    /**
     * showLoading
     */
    open fun showLoading() {
        base_content_container!!.visibility = View.GONE
        base_loading_container_layout!!.visibility = View.VISIBLE
        base_loading_indicator_image_view!!.visibility = View.VISIBLE
        base_loading_failed_text_view!!.visibility = View.GONE
        base_loading_empty_text_view!!.visibility = View.GONE
        (base_loading_indicator_image_view.drawable as AnimationDrawable).start()
    }

    /**
     * dimssLoading
     */
    open fun dimssLoading() {
        val indicatorDrawable = base_loading_indicator_image_view
                .drawable as AnimationDrawable
        if (indicatorDrawable.isRunning) {
            indicatorDrawable.stop()
        }
        base_loading_indicator_image_view!!.visibility = View.GONE
    }

    /**
     * 显示 实际布局数据
     */
    open fun showSuccessLayout() {
        base_content_container!!.visibility = View.VISIBLE
        base_loading_container_layout!!.visibility = View.GONE
    }

    /**
     * failed msg
     * @param message
     */
    open fun showFailedMessage(message: String?) {
        base_content_container!!.visibility = View.GONE
        base_loading_container_layout!!.visibility = View.VISIBLE
        base_loading_indicator_image_view!!.visibility = View.GONE
        base_loading_failed_text_view!!.visibility = View.VISIBLE
        base_loading_empty_text_view!!.visibility = View.GONE
        if (!TextUtils.isEmpty(message)) {
            base_loading_failed_text_view.text = message
        }
    }

    /**
     * empty msg
     * @param message
     */
    open fun showEmptyMessage(message: String?) {
        base_content_container!!.visibility = View.GONE
        base_loading_container_layout!!.visibility = View.VISIBLE
        base_loading_indicator_image_view!!.visibility = View.GONE
        base_loading_failed_text_view!!.visibility = View.GONE
        base_loading_empty_text_view!!.visibility = View.VISIBLE
        if (!TextUtils.isEmpty(message)) {
            base_loading_empty_text_view.text = message
        }
    }

    /**
     * empty msg
     * @param emptyImage
     * @param message
     */
    open fun showEmptyMessage(emptyImage: Drawable, message: String?) {
        base_content_container!!.visibility = View.GONE
        base_loading_container_layout!!.visibility = View.VISIBLE
        base_loading_indicator_image_view!!.visibility = View.GONE
        base_loading_failed_text_view!!.visibility = View.GONE
        base_loading_empty_text_view!!.visibility = View.VISIBLE
        if (!TextUtils.isEmpty(message)) {
            emptyImage.setBounds(0, 0, emptyImage.minimumWidth, emptyImage.minimumHeight)
            base_loading_empty_text_view.setCompoundDrawables(null, emptyImage, null, null)
            base_loading_empty_text_view.text = message
        }
    }

    /**
     * empty msg
     * @param emptyImage
     * @param textColor
     * @param textSize
     * @param message
     */
    open fun showEmptyMessage(emptyImage: Drawable, textColor: Int, textSize: Float,
                              message: String?) {
        base_content_container!!.visibility = View.GONE
        base_loading_container_layout!!.visibility = View.VISIBLE
        base_loading_indicator_image_view!!.visibility = View.GONE
        base_loading_failed_text_view!!.visibility = View.GONE
        base_loading_empty_text_view!!.visibility = View.VISIBLE
        if (!TextUtils.isEmpty(message)) {
            emptyImage.setBounds(0, 0, emptyImage.minimumWidth, emptyImage.minimumHeight)
            base_loading_empty_text_view.setCompoundDrawables(null, emptyImage, null, null)
            base_loading_empty_text_view.setTextColor(textColor)
            base_loading_empty_text_view.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            base_loading_empty_text_view.text = message
        }
    }

    /**
     * Toast
     * @param msg
     */
    open fun showToast(msg: String?) {
        Toast.makeText(BaseApplication.context,
                msg, Toast.LENGTH_LONG).show()
    }

    /**
     * titlebar left 点击事件
     */
    protected open fun onLeftActionClicked() {
        finish()
    }

    /**
     * titlebar right 点击事件
     */
    protected open fun onRightActionClicked() {}

    /**
     * 重新加载
     */
    protected open fun reloadData() {}

}