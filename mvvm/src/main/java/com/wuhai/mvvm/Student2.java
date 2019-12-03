package com.wuhai.mvvm;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;


/**
 * Created by wuhai on 2017/08/14 15:47.
 * 描述：
 */

public class Student2 extends BaseObservable {

    private String name;
    private String addr;
    private String photo;

    public Student2() {
    }

    public Student2(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public Student2(String name, String addr, String photo) {
        this(name,addr);
        this.photo = photo;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.wuhai.mvvm.BR.name);
    }

    @Bindable
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
        notifyPropertyChanged(com.wuhai.mvvm.BR.addr);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @BindingAdapter("photo")
    public static void getInternetImage(ImageView iv, String url) {
        Picasso.with(iv.getContext()).
                load(url).
                error(R.mipmap.weixin).
                into(iv);
    }
}
