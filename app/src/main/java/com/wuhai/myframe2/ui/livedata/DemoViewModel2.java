package com.wuhai.myframe2.ui.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DemoViewModel2 extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> myString = new MutableLiveData<>();

    public MutableLiveData<String> getMyString(){
        return myString;
    }

    public void setMyString(String string) {
        this.myString.setValue(string);
    }
}