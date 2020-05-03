package com.wuhai.myframe2.ui.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcServiceBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

/**
 * 作者 wuhai
 * <p>
 * 创建日期 2019/4/3 11:45
 * <p>
 * 描述：Service
 */
public class ServiceActivity extends BaseActivity implements View.OnClickListener {

    private AcServiceBinding binding;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ServiceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_service);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        binding = DataBindingUtil.setContentView(this, R.layout.ac_service);
    }

    private void setListener() {
        binding.startService.setOnClickListener(this);
        binding.stopService.setOnClickListener(this);
        binding.startService2.setOnClickListener(this);
        binding.stopService2.setOnClickListener(this);

        binding.startService3.setOnClickListener(this);
        binding.stopService3.setOnClickListener(this);

        binding.bindService.setOnClickListener(this);
        binding.unBindService.setOnClickListener(this);
        binding.invokeMethod.setOnClickListener(this);

        binding.intentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.stop_service:
                Intent intent2 = new Intent(this, MyService.class);
                stopService(intent2);
                break;
            case R.id.start_service2:
                Intent intent3 = new Intent(this, MyService2.class);
                startService(intent3);
                break;
            case R.id.stop_service2:
                Toast.makeText(this, "不需要外部stop,内部线程执行完自己stopSelf()", Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_service3://起一个常驻服务，服务里进行广播监听
                Intent intent4 = new Intent(this, MyService3.class);
                startService(intent4);
                break;
            case R.id.stop_service3://结束常驻服务
                Intent intent5 = new Intent(this, MyService3.class);
                stopService(intent5);
                break;
            case R.id.bind_service://绑定服务
                Intent intent6 = new Intent(this, BindService.class);
                // 参数3:默认填写BIND_AUTO_CREATE:使得服务自动创建
                bindService(intent6, conn, Context.BIND_AUTO_CREATE);
                isBind = true;
                break;
            case R.id.un_bind_service://解除绑定服务
                if (isBind) {
                    isBind = false;
                    unbindService(conn);
                    binder = null;
                }
                break;
            case R.id.invoke_method://调用服务中的方法
                BindService bindService = binder.getMyService();
                int number = bindService.getNumber();
                Log.e(TAG, "number:" + number);
                break;
            case R.id.intent_service://IntentService
                num++;
                Intent intent7 = new Intent(this, MyIntentService.class);
                intent7.putExtra("start", "MyIntentService"+num);
                startService(intent7);
                break;
        }
    }

    public int num = 0;

    private boolean isBind;
    private BindService.MyBinder binder;
    // 与服务产生的连接接口
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (BindService.MyBinder) service;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当Activity销毁的时候，绑定服务会自动解除绑定
//        if (isBind) {
//            unbindService(conn);
//            isBind = false;
//            Log.e(TAG, "Activity 销毁 代码解除绑定服务");
//        }
    }
}
