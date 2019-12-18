package com.wuhai.myframe2.ui.blutooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.databinding.AcBluetoothBinding;
import com.wuhai.myframe2.ui.base.BaseActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Set;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：蓝牙开发
 */
public class BluetoothActivity extends BaseActivity {

    public static final String TAG = "Bluetooth";

    private static final int REQUEST_ENBLE_BT = 1001;//启用蓝牙的请求
    private static final int REQUEST_DISCOVERABLE_BT = 1002;//启用可检测性

    //动态注册，把清单文件里的静态 注释掉了
    private BluetoothStateBroadcastReceive mReceive;

    private AcBluetoothBinding binding;

    private BluetoothAdapter mBluetoothAdapter;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BluetoothActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.ac_bluetooth);
        binding = DataBindingUtil.setContentView(this, R.layout.ac_bluetooth);
        parseIntent();
        init();
        setListener();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if(intent != null){

        }
    }

    @Override
    public void setStatistical() {

    }

    private void init() {
        registerBluetoothReceiver();

        // 注册这个 BroadcastReceiver  监听查找的蓝牙设备
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
        intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,intentFilter);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            Log.e(TAG,"此设备不支持蓝牙操作");
            return;
        }

        // 没有开始蓝牙
        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENBLE_BT);
        }

    }

    private void setListener() {

    }

    /**
     * 动态注册蓝牙状态监听
     * ①链接到的蓝牙设备监听
     * ②断开的蓝牙设备监听
     * ②蓝牙的开和关
     */
    private void registerBluetoothReceiver(){
        if(mReceive == null){
            mReceive = new BluetoothStateBroadcastReceive();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_OFF");
        intentFilter.addAction("android.bluetooth.BluetoothAdapter.STATE_ON");
        registerReceiver(mReceive, intentFilter);
    }

    /**
     * 查询配对的设备
     * @param view
     */
    public void searchBluetoothDevice(View view){
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0){
            for(BluetoothDevice device:pairedDevices){
                // 把名字和地址取出来添加到适配器中
                Log.e(TAG,device.getName()+","+ device.getAddress()+"\n");
                binding.bluetoothDevicesTv.append(device.getName()+","+ device.getAddress()+"\n");
            }
        }else{
            binding.bluetoothDevicesTv.setText("没有配对的设备");
        }
    }

    /**
     * 发现设备
     * @param view
     */
    public void foundBluetoothDevice(View view){
        boolean result = mBluetoothAdapter.startDiscovery();
        Log.e(TAG, "foundBluetoothDevice result="+result);
    }

    // 创建一个接受 ACTION_FOUND 的 BroadcastReceiver
    private final BroadcastReceiver mReceiver = new BroadcastReceiver(){

        public void onReceive(Context context,Intent intent){
            String action = intent.getAction();
            // 当 Discovery 发现了一个设备
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                // 从 Intent 中获取发现的 BluetoothDevice
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 将名字和地址放入要显示的适配器中
                Log.e(TAG,device.getName()+","+ device.getAddress()+"\n");
                binding.foundBluetoothDevicesTv.append(device.getName()+","+device.getAddress()+"\n");
            }else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.e(TAG,"搜索完毕\n");
                binding.foundBluetoothDevicesTv.append("搜索完毕\n");
            }
        }
    };

    /**
     * 启用可检测性
     * @param view
     */
    public void discoverable(View view){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        startActivityForResult(discoverableIntent,REQUEST_DISCOVERABLE_BT);
    }

    private BluetoothSocket socket;
    private InputStream mInputStream;
    private OutputStream mOutputStream;
    /**
     * 链接另一个手机的蓝牙
     * 链接
     * @param view
     */
    public void connect(View view){
        new Thread(){
            @Override
            public void run() {
                try {
                    // always return a remote device
                    BluetoothDevice device = mBluetoothAdapter.getRemoteDevice("68:A0:3E:EC:4D:09");

                    //获得一个socket，安卓4.2以前蓝牙使用此方法，获得socket，4.2后为下面的方法
                    //不能进行配对
                    // final BluetoothSocket socket = device.createRfcommSocketToServiceRecord
                    // (UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));

                    //安卓系统4.2以后的蓝牙通信端口为 1 ，但是默认为 -1，所以只能通过反射修改，才能成功
                    socket =(BluetoothSocket) device.getClass()
                            .getDeclaredMethod("createRfcommSocket",new Class[]{int.class})
                            .invoke(device,1);

                    Thread.sleep(500);

                    //这里建立蓝牙连接 socket.connect() 这句话必须单开一个子线程
                    //至于原因 暂时不知道为什么
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                socket.connect();
                                Log.e(TAG,"正在进行链接:"+socket.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e(TAG,"connect err:"+e.getMessage());
                            }
                        }
                    }.start();

                    //建立蓝牙连接

                    //获得一个输出流
                    mOutputStream = socket.getOutputStream();
                    mInputStream = socket.getInputStream();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(BluetoothActivity.this, "连接成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG,"err="+e.getMessage());
                }
            }
        }.start();
    }

    /**
     * 向另一个蓝牙设备发送信息
     * @param view
     */
    public void sendMessage(View view){
        Log.e(TAG,"sendMessage start");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(mOutputStream));
        try {
            writer.write("hello world!");
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            byte[] buffer = new byte[256];
            StringBuilder builder = new StringBuilder();
            while (mInputStream.available() == 0) ;
            while (true) {
                int num = mInputStream.read(buffer);
                String s = new String(buffer, 0, num);
                builder.append(s);
                if (mInputStream.available() == 0) break;
            }
            Log.e(TAG,"respose:"+builder.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 关闭链接
     * @param view
     */
    public void close(View view){
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case REQUEST_ENBLE_BT:
                    Log.e(TAG,"蓝牙启动成功");
                    break;
                case REQUEST_DISCOVERABLE_BT:
                    Log.e(TAG,"启用可检测性 成功："+data.toString());
                    break;
            }
        }else if(resultCode == RESULT_CANCELED){
            switch (requestCode){
                case REQUEST_ENBLE_BT:
                    Log.e(TAG,"启用可检测性 蓝牙启动");
                    break;
                case REQUEST_DISCOVERABLE_BT:
                    Log.e(TAG,"取消   启用可检测性");
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBluetoothReceiver();
    }

    /**
     * 注销 蓝牙状态监听
     */
    private void unregisterBluetoothReceiver(){
        if(mReceive != null){
            unregisterReceiver(mReceive);
            mReceive = null;
        }

        if(mReceiver != null){
            unregisterReceiver(mReceiver);
        }

        mBluetoothAdapter.cancelDiscovery();
    }
}
