package com.wuhai.navigation1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyeek on 3/3/16.
 */
public class NavigationTabLayout extends LinearLayout implements View.OnClickListener {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private OnTabSelectedListener mOnTabSelectedListener;
    private OnTabItemClickListener mOnTabItemClickListener;

    private int mChildCount;
    private List<TabItem> mTabItems;

    private int mTextSize = 14;
    private int mTextColorSelect = Color.BLUE;
    private int mTextColorNormal = Color.BLACK;
    private int mTabPadding = 8;

    public NavigationTabLayout(Context context) {
        this(context, null);
    }

    public NavigationTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.NavigationTabLayout);
        final int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            switch (typedArray.getIndex(i)) {
                case R.styleable.NavigationTabLayout_tabTextSize:
                    mTextSize = (int) typedArray.getDimension(i, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                            mTextSize, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.NavigationTabLayout_tabTextNormalColor:
                    mTextColorNormal = typedArray.getColor(i, mTextColorNormal);
                    break;
                case R.styleable.NavigationTabLayout_tabTextSelectedColor:
                    mTextColorSelect = typedArray.getColor(i, mTextColorSelect);
                    break;
                case R.styleable.NavigationTabLayout_tabItemPadding:
                    mTabPadding = (int) typedArray.getDimension(i, TypedValue.applyDimension
                            (TypedValue.COMPLEX_UNIT_DIP, mTabPadding, getResources()
                                    .getDisplayMetrics()));
                    break;
            }
        }
        typedArray.recycle();
        setOrientation(HORIZONTAL);

        mTabItems = new ArrayList<>();
    }

    public void setViewPager(final ViewPager mViewPager) {
        if (mViewPager == null) {
            return;
        }

        this.mViewPager = mViewPager;
        this.mPagerAdapter = mViewPager.getAdapter();
        this.mChildCount = this.mPagerAdapter.getCount();
        if (this.mPagerAdapter == null) {
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
        }

        if (mPagerAdapter instanceof OnTabSelectedListener) {
            mOnTabSelectedListener = (OnTabSelectedListener) mPagerAdapter;
        } else {
            throw new RuntimeException("pageAdapter dose not implement OnTabSelectedListener interface.");
        }

        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                View leftView;
                View rightView;

                if (positionOffset > 0) {
                    leftView = mViewPager.getChildAt(position);
                    rightView = mViewPager.getChildAt(position + 1);
//                    leftView.setAlpha(1 - positionOffset);
//                    rightView.setAlpha(positionOffset);
                    mTabItems.get(position).setTabAlpha(1 - positionOffset);
                    mTabItems.get(position + 1).setTabAlpha(positionOffset);
                } else {
//                    mViewPager.getChildAt(position).setAlpha(1);
                    mTabItems.get(position).setTabAlpha(1 - positionOffset);
                }
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });

        initTabs();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener mOnPageChangeListener) {
        this.mOnPageChangeListener = mOnPageChangeListener;
    }

    public void setOnTabItemClickListener(OnTabItemClickListener l){
        mOnTabItemClickListener = l;
    }

    private void initTabs() {
        for (int i = 0; i < mChildCount; i++) {
            TabItem tabItem = new TabItem(getContext());
            LayoutParams params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1);
            params.gravity = Gravity.CENTER_VERTICAL;
            tabItem.setBackgroundColor(Color.argb(247,255,255,255));
            tabItem.setPadding(mTabPadding, mTabPadding, mTabPadding, mTabPadding);
            tabItem.setIconText(mOnTabSelectedListener.onIconSelected(i), mOnTabSelectedListener.onTextSelected(i));
            tabItem.setTextSize(mTextSize);
//            tabItem.setBackgroundIcon(R.drawable.home_tab_item_background);
            tabItem.setTextColorNormal(mTextColorNormal);
            tabItem.setTextColorSelect(mTextColorSelect);
            tabItem.setIconTextGap((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    2, this.getResources().getDisplayMetrics()));
            tabItem.setLayoutParams(params);
            tabItem.setTag(i);
            tabItem.setOnClickListener(this);
            addView(tabItem);

            mTabItems.add(tabItem);
        }
    }

    public void updateTabResource(){
        int position;
        for (TabItem item : mTabItems){
            position = (int) item.getTag();
            item.setIconText(mOnTabSelectedListener.onIconSelected(position), mOnTabSelectedListener.onTextSelected(position));
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if (mViewPager.getCurrentItem() == position) {
            return;
        }
        if(mOnTabItemClickListener != null && mOnTabItemClickListener.handleTabItemClick(position)){
            return;
        }

        selectItem(position);
        mViewPager.setCurrentItem(position, false);
    }

    public void selectItem(int position) {
        // TODO: optimize to avoid duplicated set alpha.
        for (TabItem tabItem : mTabItems) {
            tabItem.setTabAlpha(0);
        }
        mTabItems.get(position).setTabAlpha(1);
    }

    public interface OnTabItemClickListener{
        boolean handleTabItemClick(int position);
    }

    public interface OnTabSelectedListener {

        int[] onIconSelected(int position);

        String onTextSelected(int position);
    }

    /**
     * 隐藏 导航
     * @param position
     */
    public void hideItem(int position){
        if(mTabItems != null && mTabItems.size()>0){
            mTabItems.get(position).setVisibility(View.GONE);
        }
    }

    /**
     * 判断 导航位置 显隐状态
     * @param position
     * @return
     */
    public int getVisibility(int position){
        if(mTabItems != null && mTabItems.size()>0){
            return mTabItems.get(position).getVisibility();
        }
        return View.GONE;
    }
}
