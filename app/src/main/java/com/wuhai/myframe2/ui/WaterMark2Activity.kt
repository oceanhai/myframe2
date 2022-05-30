package com.wuhai.myframe2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wuhai.myframe2.R
import com.wuhai.myframe2.utils.WaterMarkUtil2

/**
 * 水印照片
 *
 * https://www.jianshu.com/p/e9f4f54f8069
 *1.根据要显示的文字内容，绘制图片，然后将画布旋转-45°，生成bitmap。
 *2.获取要显示水印的Activty的RootView，在RootView上添加一个view用于显示水印图片。
 *3.根据需求给view布局文件设置透明度，android:alpha="0.8"
 *
 *Android自定义View：仿企业微信，钉钉实现倾斜水印效果
 *
 */
class WaterMark2Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivity(context: Context) {
            val intent = Intent()
            intent.setClass(context, WaterMark2Activity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_watermark2)
        //为当前Activity添加水印
        WaterMarkUtil2.showWatermarkView(this, "Hello Word!")
    }
}