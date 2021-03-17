package com.wuhai.hhsc.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.wuhai.hhsc.constans.Constants;


/**
 * Description:
 * Data: 2019-11-26
 *
 * @author: zhudi
 */
public abstract class BaseFragment extends Fragment {
    /**
     * 是否初始化
     */
    private boolean isOnInit = true;
    /**
     * 是否对用户可见
     */
    private boolean isVisibleToUser = false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null && isSaveState()) {
            isOnInit = savedInstanceState.getBoolean(Constants.Key.KEY_TAB_INIT);
        }

        if (isVisibleToUser) {
            onVisible(isOnInit);
            isOnInit = false;
        }
        initFontScale();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        if (isVisibleToUser && getView() != null) {
            onVisible(isOnInit);
            isOnInit = false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (isSaveState()) {
        }
    }

    /**
     * 是否保存初始化状态
     *
     * @return
     */
    public boolean isSaveState() {
        return true;
    }

    /**
     * 监听Fragment是否显示，isInit是否初为首次初始化，当把Fragment加入TabFragment时使用
     * <p>
     * 由于ViewPager是预加载，所以联网获取数据需要判断当前Fragment是否显示，然后在获取数据
     */
    protected abstract void onVisible(boolean isInit);


    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //非默认值
        if (newConfig.fontScale != 1) {
//            getResources();
            initFontScale();
        }
        super.onConfigurationChanged(newConfig);
    }

    private void initFontScale() {
        Configuration configuration = getResources().getConfiguration();
        configuration.fontScale = (float) 1;
        //0.85 小, 1 标准大小, 1.15 大，1.3 超大 ，1.45 特大
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        getActivity().getResources().updateConfiguration(configuration, metrics);
    }
}
