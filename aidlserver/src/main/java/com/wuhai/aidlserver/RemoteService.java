package com.wuhai.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class RemoteService extends Service {

    private MyBinder myBinder;

    public RemoteService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(myBinder == null){
            myBinder = new MyBinder();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
//        return remoteInterface.asBinder();//方式二
    }

    class MyBinder extends RemoteInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String callRemote() throws RemoteException {
            return "成功调用远程服务！";
        }
    }

    /**
     * 方式二  onBind 方法里 要return remoteInterface.asBinder();
     */
    private RemoteInterface remoteInterface = new RemoteInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String callRemote() throws RemoteException {
            return "成功调用远程服务！";
        }
    };

}
