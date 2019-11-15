package com.wuhai.mvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StudentAdapter extends BaseDataAdapter<Student> {

    public StudentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder= null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.item_student,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.mNameTv = (TextView) convertView.findViewById(R.id.name);
            viewHolder.mAddrTv = (TextView)convertView.findViewById(R.id.addr);
            viewHolder.mPhotoIv = (ImageView) convertView.findViewById(R.id.iv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mNameTv.setText(mData.get(position).getName());
        viewHolder.mAddrTv.setText(mData.get(position).getAddr());

        Picasso.with(mContext)
                .load(mData.get(position).getPhoto())
                .into(viewHolder.mPhotoIv);

        return convertView;
    }

    public static class ViewHolder{
        private TextView mNameTv;
        private TextView mAddrTv;
        private ImageView mPhotoIv;
    }
}
