package com.wuhai.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


/**
 * Created by wuhai on 2017/08/14 15:47.
 * 描述：
 */

public class Student extends BaseObservable {

    private String name;
    private String addr;
    private String photo;

    public Student() {
    }

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

    public Student(String name, String addr, String photo) {
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

}
