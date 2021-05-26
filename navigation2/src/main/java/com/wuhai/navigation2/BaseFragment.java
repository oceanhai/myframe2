package com.wuhai.navigation2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * Created by yu on 2016/11/11.
 */

public class BaseFragment extends Fragment {
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get title
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.frag_base, null);

        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText(title);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("wh", "onResume() " + title);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("wh", "onPause() " + title);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("wh", "onHiddenChanged hidden="+hidden+", " + title);
    }
}
