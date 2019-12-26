package com.wuhai.myframe2.business.getui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.application.BaseApplication;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Android notification 适配不同版本
 * https://blog.csdn.net/beyondforme/article/details/100123643
 * ※不知道管不管用
 */
public class NotificationUtil {
    public static List idNotification = new ArrayList();
    public static int iid;

    public static void showNotification(String uid, String title, String text) {
        iid++;
        NotificationManager notificationManager = (NotificationManager)
                BaseApplication.getApplication().getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        Intent intent = new Intent("main");
        intent.putExtra("notifForm", true);
        intent.putExtra("uid", uid);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                BaseApplication.getApplication(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(iid + "", iid + "", NotificationManager.IMPORTANCE_HIGH);
            Log.i("log", mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(BaseApplication.getApplication())
                    .setChannelId(iid + "")
                    .setContentTitle(title)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(BaseApplication.getApplication())
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(resultPendingIntent)
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setChannelId(iid + "");//无效
            notification = notificationBuilder.build();
        }
        notificationManager.notify(iid, notification);
    }
}
