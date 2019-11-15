package com.wuhai.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.wuhai.mvvm.databinding.ItemStudent2Binding;


/**
 * Created by wuhai on 2017/08/15 17:40.
 * 描述：
 */

public class StudentAdapter2 extends BaseDataAdapter<Student2>{

    private LayoutInflater inflater;

    public StudentAdapter2(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemStudent2Binding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_student2, parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setStu(mData.get(position));

        return  dataBinding.getRoot();
    }


}
