package com.wuhai.myframe2.ui.popwindow;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.wuhai.myframe2.R;


/**
* 非随点击位置 PopupWindow
* @author wuhai
* create at 2016/2/26 16:40
*/
public class FloatingWindow {

	private Context mContext;
	private PopupWindow mWindow;
	private View mParent;
	private View mContentView;
	
	private IFloatingOperation mOperCallback;

	private int[] location;

	public FloatingWindow(Context context, View parent){
		mContext = context;
		mParent = parent;

		location = new int[2];
		parent.getLocationOnScreen(location);
		
		mContentView = View.inflate(mContext, R.layout.popwindow_floating_window, null);
		mWindow = new PopupWindow(mContentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		mContentView.findViewById(R.id.read_fw_delete).setOnClickListener(mClickListener);
	}

	/**
	 *
	 * @param x
	 * @param y
	 * @param direction	方向 true：箭头向上；false:箭头向上
	 */
	public void show(int x, int y, boolean direction){
		setBackground(direction);

		/**
		 * location[0]=45,location[1]=372,mWindow.getHeight()=-2
		 * 这样定x,y不太科学
		 */
		Log.e(PopWindowActivity.TAG, "location[0]="+location[0]+
				",location[1]="+location[1]+",mWindow.getHeight()="+mWindow.getHeight());
		mWindow.showAtLocation(mParent, Gravity.NO_GRAVITY,
				location[0], location[1]-mWindow.getHeight());
	}
	
	private void setBackground(boolean direction) {
		View view = mContentView.findViewById(R.id.read_fw_bglayout);
		if (direction) {
//			view.setBackgroundResource(R.drawable.reader_note_arrow_up_empty);
		} else {
			view.setBackgroundResource(R.drawable.note_arrow_down_empty);
		}
	}
	
	public void hide(){
		if(mWindow != null){
			mWindow.dismiss();
		}
	}
	
	public boolean isShowing(){
		return mWindow != null && mWindow.isShowing();
	}
	
	/**
	 * 回调接口
	 * @param l
	 */
	public void setFloatingOperation(IFloatingOperation l){
		mOperCallback = l;
	}
	
	final OnClickListener mClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(mOperCallback == null){
				return;
			}
			switch (v.getId()) {
				case R.id.read_fw_delete:
					mOperCallback.onDelete();
					break;
				default:
					break;
			}
			hide();
		}
	};

	public interface IFloatingOperation {

		/**
		 * 删除
		 */
		void onDelete();

	}
}
