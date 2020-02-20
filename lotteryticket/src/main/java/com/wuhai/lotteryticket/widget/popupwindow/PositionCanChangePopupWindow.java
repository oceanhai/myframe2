package com.wuhai.lotteryticket.widget.popupwindow;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.wuhai.lotteryticket.R;
import com.wuhai.lotteryticket.ui.activity.LotteryCreateActivity;
import com.wuhai.lotteryticket.utils.LogProxy;

import java.lang.reflect.Field;

/**
*
 * 跟随点击或者长按位置  弹PopupWindow
* @author wuhai
* create at 2016/2/26 15:55
*/
public class PositionCanChangePopupWindow {

	private Context mContext;
	private View mParent;
	private View mContentView;
	private PopupWindow mWindow;
	private View mDelete,mEditTv,mSaveTv;

	private IFloatingOperation mOperCallback;

	public PositionCanChangePopupWindow(Context context, View parent) {
		mContext = context;
		mContentView = View.inflate(mContext, R.layout.popwindow_position, null);
		mWindow = new PopupWindow(mContentView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		mWindow.setAnimationStyle(android.R.style.Animation_Toast);
		mParent = parent;
		mDelete = mContentView.findViewById(R.id.delete_tv);
		mEditTv = mContentView.findViewById(R.id.edit_tv);
		mSaveTv = mContentView.findViewById(R.id.save_tv);

		mDelete.setOnClickListener(mClickListener);
		mEditTv.setOnClickListener(mClickListener);
		mSaveTv.setOnClickListener(mClickListener);
	}

	private void setRelativeLayoutBackGround(boolean topOf) {
		if (topOf) {
			mContentView.setBackgroundResource(R.drawable.note_arrow_down_empty);
		} else {
			mContentView.setBackgroundResource(R.drawable.note_arrow_up_empty);
		}
	}

	/**
	 * ※ 值操作要把data或者postion 传过来 根据想法改动回传接口
	 *
	 * downX - 控件高度 < 状态栏高度  朝下；反之 朝上
	 * downX - 控件宽度/2 < 0  值为0;反之 正常差值
	 * @param downX
	 * @param downY
	 */
	public void showPopupWindow(float downX, float downY) {
		mContentView.measure(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		int top = (int) (downY - mContentView.getMeasuredHeight());
		LogProxy.e(LotteryCreateActivity.TAG,"FootprintsPopupWindow::top:"+top+"="+downY+"-"+mContentView.getMeasuredHeight());
		boolean topOf = true;
		LogProxy.e(LotteryCreateActivity.TAG,"FootprintsPopupWindow::getStatusHeight:"+getStatusHeight(mContext));
		if (top < getStatusHeight(mContext)) {
			topOf = false;
			top = (int) downY;
		}
		setRelativeLayoutBackGround(topOf);
		int left = (int) (downX - mContentView.getMeasuredWidth() / 2);
//		System.out.println("FootprintsPopupWindow::left:"+left);
		mWindow.showAtLocation(mParent, Gravity.NO_GRAVITY, left < 0 ? 0 : left, top);
	}

	public void hide() {
		mWindow.dismiss();
	}

	/**
	 * Status bar是launcher主界面上面提示当前状态（电池，网络，蓝牙等等）的一个栏
	 * 获取状态栏高度
	 * @param context
	 * @return
	 */
	private int getStatusHeight(Context context){
		int height = 0;
		try {
			@SuppressWarnings("rawtypes")
            Class c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			height = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return height;
	}

	/**
	 * PopupWindow 是否正在显示
	 * @return
	 */
	public boolean isShowing(){
		if(mWindow == null){
			return false;
		}
		return mWindow.isShowing();
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
				case R.id.edit_tv:
					mOperCallback.onEdit();
					break;
				case R.id.delete_tv:
					mOperCallback.onDelete();
					break;
				case R.id.save_tv:
					mOperCallback.onSave();
					break;
				default:
					break;
			}
			hide();
		}
	};

	public interface IFloatingOperation {
		void onEdit();
		void onDelete();
		void onSave();
	}
}
