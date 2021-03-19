package com.wuhai.hhsc.ui.viewholder;


import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.wuhai.hhsc.ui.adpater.ItemListener;
import com.wuhai.hhsc.ui.adpater.LongItemListener;


public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

	private ItemListener itemListener ;
	private LongItemListener longListener ;

	public ItemListener getItemListener() {
		return itemListener;
	}

	public void setItemListener(ItemListener itemListener) {
		this.itemListener = itemListener;
	}

	public LongItemListener getLongListener() {
		return longListener;
	}

	public void setLongListener(LongItemListener longListener) {
		this.longListener = longListener;
	}

	public BaseViewHolder(View itemView) {
		super(itemView);
		// TODO Auto-generated constructor stub
		if(itemView != null){
			itemView.setOnClickListener(this);
			itemView.setOnLongClickListener(this);
		}
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		if(getLongListener() != null){
			longListener.onItemLongClick(v, getLayoutPosition());
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(getItemListener() != null){
			itemListener.onItemClick(v, getLayoutPosition());
		}
	}



}
