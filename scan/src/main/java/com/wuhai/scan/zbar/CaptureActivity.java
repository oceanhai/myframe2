package com.wuhai.scan.zbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.wuhai.scan.BaseApplication;
import com.wuhai.scan.R;
import com.wuhai.scan.StringUtils;
import com.wuhai.scan.zbar.camera.CameraManager;
import com.wuhai.scan.zbar.decode.CaptureActivityHandler;
import com.wuhai.scan.zbar.decode.InactivityTimer;
import com.wuhai.scan.zbar.decode.RGBLuminanceSource;

import java.io.IOException;
import java.util.Hashtable;

/**
* 二维码 扫描
*/
public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private boolean hasSurface;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.50f;
	private boolean vibrate;//震动开关
	private int x = 0;
	private int y = 0;
	private int cropWidth = 0;
	private int cropHeight = 0;
	private RelativeLayout mContainer = null;
	private RelativeLayout mCropLayout = null;
	private boolean isNeedCapture = false;
	private static final int REQUEST_CODE = 92;
	private Dialog dialog;
	boolean flag = true;
	//震动时长
	private static final long VIBRATE_DURATION = 200L;

	public boolean isNeedCapture() {
		return isNeedCapture;
	
	}

	public void setNeedCapture(boolean isNeedCapture) {
		this.isNeedCapture = isNeedCapture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}

	/**
	 * 外部跳转调用
	 * @param activity
	 */
	public static void startActivity(Activity activity){
		Intent intent = new Intent();
		intent.setClass(activity, CaptureActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(intent);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qr_scan);
		ImageView button_back = (ImageView) findViewById(R.id.button_back);
		TextView button_function = (TextView) findViewById(R.id.button_function);
		button_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				CaptureActivity.this.finish();
			}
		});
		button_function.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(CaptureActivity.this, SelectPictureActivity.class);
//				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		
		
		// 初始化 CameraManager
//		CameraManager.init(getApplication());
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

		mContainer = (RelativeLayout) findViewById(R.id.capture_containter);
		mCropLayout = (RelativeLayout) findViewById(R.id.capture_crop_layout);

		ImageView mQrLineView = (ImageView) findViewById(R.id.capture_scan_line);
		TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.9f);
		mAnimation.setDuration(1500);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.REVERSE);
		mAnimation.setInterpolator(new LinearInterpolator());
		mQrLineView.setAnimation(mAnimation);
	}

	/**
	 * 闪关灯  暂定
	 */
	protected void light() {
		if (flag) {
			flag = false;
			// 开闪光灯
			CameraManager.get().openLight();
		} else {
			flag = true;
			// 关闪光灯
			CameraManager.get().offLight();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.capture_preview);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	/**
	 * 扫描结果后  跳转操作 TODO wuhai 逻辑待确定
	 * @param result
	 */
	public void handleDecode(final String result) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		AlertDialog.Builder builder=new AlertDialog.Builder(CaptureActivity.this);
		builder.setMessage(result);
		final String message=result;
		Toast.makeText(BaseApplication.getAppContext(),
				"扫描成功"+message,Toast.LENGTH_SHORT).show();
		if(StringUtils.isUrls(message)){
			if(message.contains(".xywy.com")){
				//TODO 根据业务 特殊处理
				finish();
			}else{

			}

		}else{
			Toast.makeText(BaseApplication.getAppContext(),
					"该二维码无法识别",Toast.LENGTH_SHORT).show();
		}

	}

	/**
	 * 初始化
	 * @param surfaceHolder
	 */
	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);

			Point point = CameraManager.get().getCameraResolution();
			int width = point.y;
			int height = point.x;

			int x = mCropLayout.getLeft() * width / mContainer.getWidth();
			int y = mCropLayout.getTop() * height / mContainer.getHeight();

			int cropWidth = mCropLayout.getWidth() * width / mContainer.getWidth();
			int cropHeight = mCropLayout.getHeight() * height / mContainer.getHeight();

			setX(x);
			setY(y);
			setCropWidth(cropWidth);
			setCropHeight(cropHeight);
			// 设置是否需要截图
			setNeedCapture(true);
			

		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(CaptureActivity.this);
		}
	}

	/**
	 * SurfaceHolder.Callback
	 * surfaceCreated方法
	 * surfaceChanged方法
	 * surfaceDestroyed方法
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public Handler getHandler() {
		return handler;
	}

	/**
	 * 初始化声音
	 */
	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	/**
	 * 声音和震动设置
	 */
	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * 声音播放结束监听
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){  
			case REQUEST_CODE://相册扫描 结果  TODO
				if(resultCode==RESULT_OK){
					String photo_path =  data.getExtras().getString("intent_selected_picture");
					Result result = scanningImage(photo_path);
					if (result != null) {
						//Toast.makeText(this, "result："+result, Toast.LENGTH_SHORT).show();
						handleDecode(result.getText());
	//	        		if (null != getHandler()) {
	//	    				Message msg = new Message();
	//	    				msg.obj = result.getText();
	//	    				msg.what = R.id.decode_succeeded;
	//	    				getHandler().sendMessage(msg);
	//	    			}
					} else {
						Toast.makeText(this, "该二维码无法识别", Toast.LENGTH_SHORT).show();
						if (null != getHandler()) {
							getHandler().sendEmptyMessage(R.id.decode_failed);
						}
					}
				}
				break;
			default:
				break;
        }  
	};
//	public String scanningImage(String path) {
//		if(TextUtils.isEmpty(path)){
//			return null; 
//		}
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inJustDecodeBounds = true;
//		Bitmap scanBitmap = BitmapFactory.decodeFile(path, options);
//		options.inJustDecodeBounds = false;
//		
//		int sampleSize = (int) (options.outHeight / (float) 200);
//		if (sampleSize <= 0)
//			sampleSize = 1;
//		options.inSampleSize = sampleSize;
//		scanBitmap = BitmapFactory.decodeFile(path, options);
//		
//		ByteArrayOutputStream stream = new ByteArrayOutputStream();
//		scanBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//		byte[] byteArray = stream.toByteArray();
//		
//		int width = options.outWidth;
//		int height = options.outHeight;
//		scanBitmap.recycle();
//		scanBitmap = null;
//		ZbarManager manager = new ZbarManager();
//		return manager.decode(byteArray, width, height, true, getX(), getY(), getCropWidth(), getCropHeight());
//		
//	}

	/**
	 * 相册二维码 扫描
	 * @param path
	 * @return
	 */
	@SuppressWarnings("PMD.ReplaceHashtableWithMap")
	public Result scanningImage(String path) {
		if(TextUtils.isEmpty(path)){
			return null;
		}
		Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF8"); 

		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true; 
		Bitmap scanBitmap = BitmapFactory.decodeFile(path, options);
		options.inJustDecodeBounds = false; 
		int sampleSize = (int) (options.outHeight / (float) 200);
		if (sampleSize <= 0){
			sampleSize = 1;
		}
		options.inSampleSize = sampleSize;
		scanBitmap = BitmapFactory.decodeFile(path, options);
		RGBLuminanceSource source = new RGBLuminanceSource(scanBitmap);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		try {
			return reader.decode(bitmap1, hints);

		}  catch (ChecksumException e) {
			e.printStackTrace();
		} catch (FormatException e) {
			e.printStackTrace();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}