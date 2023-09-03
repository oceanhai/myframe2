package com.wuhai.myframe2.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.wuhai.myframe2.R;


/**
 * Created by wuhai on 2017/3/10 0010 19:43.
 * 描述：含有两个的btn 的 选择btn dialog
 */
public class DialogTwoBtnSelect extends Dialog implements View.OnClickListener {

	private Context context;
	private TextView mTitle,mMessage,mCancel,mConfirm;
	private LinearLayout mBgLayout;
	private RelativeLayout mBgInnerLayout;

	private int titleId = -1, msgId = -1, okId = -1, cancelId = -1;

	public DialogTwoBtnSelect(@NonNull Context context) {
		super(context);
	}

	public DialogTwoBtnSelect(Context context, int msgId, int okId, int cancelId) {
		super(context, R.style.ShareDialog);
		this.context = context;
		this.msgId = msgId;
		this.okId = okId;
		this.cancelId = cancelId;
	}

	public DialogTwoBtnSelect(Context context, int title, int msgId, int okId, int cancelId) {
		super(context, R.style.ShareDialog);
		this.context = context;
		this.titleId = title;
		this.msgId = msgId;
		this.okId = okId;
		this.cancelId = cancelId;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_two_btn_select);

		mBgLayout = (LinearLayout) findViewById(R.id.bg_layout);
		mBgInnerLayout = (RelativeLayout) findViewById(R.id.bg_inner_layout);

		mTitle = (TextView) findViewById(R.id.dl_title_tv);
		mMessage = (TextView) findViewById(R.id.dl_message_tv);
		mCancel = (TextView) findViewById(R.id.dl_cancel_tv);
		mConfirm = (TextView) findViewById(R.id.dl_confirm_tv);

		if(titleId != -1){
			mTitle.setText(titleId);
			mTitle.setVisibility(View.VISIBLE);
		}
		if(msgId != -1){
			mMessage.setText(msgId);
		}
		if(cancelId != -1){
			mCancel.setText(cancelId);
		}
		if(okId != -1){
			mConfirm.setText(okId);
		}

		mBgLayout.setOnClickListener(this);
		mBgInnerLayout.setOnClickListener(this);
		mMessage.setOnClickListener(this);
		mCancel.setOnClickListener(this);
		mConfirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
				case R.id.dl_cancel_tv://取消
					if(mDialogNegativeListenerListener != null){
						mDialogNegativeListenerListener.onNegative();
						dismiss();
					}
					break;
				case R.id.dl_confirm_tv://确认
					if(mDialogPositiveListenerListener != null){
						mDialogPositiveListenerListener.onPositive();
						dismiss();
					}
					break;
				case R.id.bg_layout:
					dismiss();
					break;
				case R.id.bg_inner_layout:
					return;
			}
		}

	private DialogPositiveListener mDialogPositiveListenerListener;
	private DialogNegativeListener mDialogNegativeListenerListener;

	public void setOnDialogPositiveListener(DialogPositiveListener listener) {
		this.mDialogPositiveListenerListener = listener;
	}

	public void setOnDialogNegativeListener(DialogNegativeListener listener) {
		this.mDialogNegativeListenerListener = listener;
	}

	public interface DialogPositiveListener {
		void onPositive();//积极
	}

	public interface DialogNegativeListener{
		void onNegative();//消极
	}

}