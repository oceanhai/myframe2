package com.wuhai.hhsc.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wuhai.hhsc.R;
import com.wuhai.hhsc.app.App;

import java.util.Locale;


/**
 * Description:工具类
 * Data: 2019-12-04
 *
 * @author: zhudi
 */
public class Utils {
    /**
     * convert dp to its equivalent px
     * 将dp转换为px
     */
    public static int dp2px(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, App.getInstance().getResources().getDisplayMetrics());
    }

    /**
     * convert sp to its equivalent px
     * 将sp转换为px
     */
    public static int sp2px(float spValue) {
        final float fontScale = App.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * toast弹出
     */
    private static Toast toast;

    public static void showToastShortTime(String content) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToastLongTime(String content) {
        if (toast == null) {
            toast = Toast.makeText(App.getInstance(), content, Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    /**
     * 图片显示
     *
     * @param imgUrl    图片的路径
     * @param imageView 控件
     */
    public static void imageViewDisplayByUrl(Context context, String imgUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().error(R.drawable.ic_error);
        Glide.with(context).asDrawable().load(imgUrl).apply(options).into(imageView);
    }

    /**
     * 图片显示
     *
     * @param imgUrl    图片的路径
     * @param imageView 控件
     */
    public static void imageViewDisplayByUrl(Context context, int imgUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().error(R.drawable.ic_error);
        Glide.with(context).asDrawable().load(imgUrl).apply(options).into(imageView);
    }

    /**
     * 图片显示
     *
     * @param imgUrl    图片的路径
     * @param imageView 控件
     */
    public static void imageViewDisplayByUrl(Context context, int imgUrl, ImageView imageView, int scaleType) {
        RequestOptions options = new RequestOptions().error(R.drawable.ic_error);
        Glide.with(context).asDrawable().load(imgUrl).apply(options).into(imageView);
    }

    /**
     * 圆角图片显示
     *
     * @param imgUrl         图片的路径
     * @param imageView      控件
     * @param roundTransform 圆角值
     */
    public static void cornerImageViewDisplayByUrl(Context context, String imgUrl, ImageView imageView, int roundTransform) {
        RequestOptions options = new RequestOptions().centerCrop().transform(new GlideRoundTransform(roundTransform)).error(R.drawable.ic_error);
        Glide.with(context).asDrawable().load(imgUrl).apply(options).into(imageView);
    }

    /**
     * 动态图片显示
     *
     * @param imgUrl    图片的路径
     * @param imageView 控件
     */
    public static void imageViewGifDisplayByUrl(Context context, String imgUrl, ImageView imageView) {
        RequestOptions options = new RequestOptions().centerCrop().error(R.drawable.ic_error);
        Glide.with(context).asGif().load(imgUrl).apply(options).into(imageView);
    }

    /**
     * 动态圆角图片显示
     *
     * @param imgUrl    图片的路径
     * @param imageView 控件
     */

//    static RequestOptions gifOptions = new RequestOptions()
//            .centerCrop()
//            .transform(new GlideRoundTransform(9))
//            .placeholder(R.drawable.ic_error)
//            .error(R.drawable.ic_error);
//
//    public static void cornerImageViewGifDisplayByUrl(Context context, String imgUrl, ImageView imageView) {
//        Glide.with(context).asGif().load(imgUrl).apply(gifOptions).into(imageView);
//    }

    /**
     * 判断否为登录状态
     *
     * @return
     */
//    public static boolean isLoginStatus() {
//        String tokenId = SafeSharePreferenceUtils.getString(Constants.Fields.TOKENID, "");
//        return !StringUtils.isEmpty(tokenId);
//    }

    /**
     * 弹出通知
     *
     * @param msgType    消息类型 用于扩展
     * @param contentStr 提示消息
     * @param context
     */
    public static void showNotification(int msgType, String contentStr, final Context context) {
        if (context == null) {
            return;
        }
        //默认系统声音震动
        int defaults = Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND;
        defaults = Notification.DEFAULT_VIBRATE;//关闭声音
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setContentText(contentStr)
                .setContentTitle("海汇优品")
                .setTicker("您有一条新消息")
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setDefaults(defaults)
                .setSmallIcon(R.mipmap.ic_launcher);


        Notification notify = mBuilder.build();
        notify.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(msgType, notify);
    }

    /**
     * 设置应用语言
     *
     * @param lang 要设置的语言，如：zh、en，目前只支持中文和英文，其它的请自行添加
     */
    public static void setLang(Context context, String lang) {
        Resources res = context.getResources();
        //应用当前语言
        String currLang = res.getConfiguration().locale.getLanguage();

        //当前语言和用户语言不一致时更改应用的当前语言
        if (!currLang.equals(lang)) {
            //设置应用语言类型
            Configuration config = res.getConfiguration();
            DisplayMetrics dm = res.getDisplayMetrics();
            config.locale = new Locale(lang);
//            一般中英法等常用文直接用Locale类列好的 有些是还没有列出来的 如印地语 需要自己手动new
            //config.locale = Locale.ENGLISH;
            res.updateConfiguration(config, dm);
        }
    }
}
