package com.wuhai.myframe2.ui.livedata;

import androidx.lifecycle.ViewModel;

public class DemoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private DemoData mDemoData = new DemoData();

    public DemoData getDemoData() {
        return mDemoData;
    }
}