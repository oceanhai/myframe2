package com.wuhai.navigation1;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Created by flyeek on 5/30/15.
 */
public class BaseSupportFragment extends Fragment {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();

        // UMeng page analytics.
//        AnalyticsService.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();

        // UMeng page analytics.
//        AnalyticsService.onPageEnd(getClass().getSimpleName());
    }
}
