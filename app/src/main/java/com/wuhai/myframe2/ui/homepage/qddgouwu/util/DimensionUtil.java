package com.wuhai.myframe2.ui.homepage.qddgouwu.util;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.DimenRes;

/**
 * Created by flyeek on 11/10/15.
 */
public class DimensionUtil {

    public static float convertDpToPx(Context context, @DimenRes int dimenResId) {
        return context.getResources().getDimension(dimenResId);
    }

    public static float convertDpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
