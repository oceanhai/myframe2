package com.wuhai.demo.lotteryticketkotlin.ui.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.wuhai.demo.lotteryticketkotlin.widget.tagflowlayout.FlowLayout
import com.wuhai.demo.lotteryticketkotlin.widget.tagflowlayout.TagAdapter

/**
 * 模拟一个kotlin类去继承一个存在的java类
 * 貌似还真的是可以这样操作的
 *
 * 但是 好像是用一个java类 去集成一个存在的 kotlin类
 * java类 貌似拿到不到父类的属性
 */
class TagAdapter2() : TagAdapter<String?>(arrayOf()) {

    override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
        TODO("Not yet implemented")
    }

}