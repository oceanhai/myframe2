package com.wuhai.myframe2.ui.xingneng.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.wuhai.myframe2.R;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {
    //图片与图片之间的间距
    private int mCardSpacing = 150;
    //图片与左侧距离的记录
    private int mCardLeft = 10;

    private List<DroidCard> mDroidCards = new ArrayList<DroidCard>();

    private Paint paint = new Paint();

    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }
    /**
     * 初始化卡片集合
     */
    protected void initCards(){
        Resources res = getResources();
        mDroidCards.add(new DroidCard(res, R.drawable.cat_1,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res,R.drawable.cat_3,mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res,R.drawable.cat_5,mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (DroidCard c : mDroidCards){
            drawDroidCard(canvas, c);
        }
        invalidate();
    }

    /**
     * 绘制DroidCard
     */
    private void drawDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap,c.x,0f,paint);
    }
}

