package com.wuhai.demo.lotteryticketkotlin.config.network

import android.util.Log
import android.widget.Toast
import com.facebook.stetho.common.LogUtil
import com.google.gson.JsonParseException
import com.wuhai.demo.lotteryticketkotlin.application.BaseApplication
import org.json.JSONException
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 * 作者 wh
 *
 * 创建日期 2020/1/8 23:11
 *
 * 描述：
 */
abstract class RequestNetCallBack2<T : RootResponse<*>?> : Subscriber<T>() {
    open fun onFailure(responseError: ResponseError?) {
        //通用处理
        if (responseError != null) {
            if (responseError.type == ResponseError.ErrorCode.BUSINESS_ERROR) {
//                if (responseError.getCode() == LOGIN_EXPIRED_CODE) {
//                    AccountManager.getInstance().cleanUserInfo();
//                    //刷新所有数据
////                RxBus.getDefault().post(new RefreshEvent(RefreshEvent.REFRESH_RECEIVE));
//                    ToastUtil.showShort("用户信息已过期，请重新登录");
//                    Intent i = new Intent(BaseApplication.getApplication(), LoginAct.class);
//                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    i.putExtra(LoginAct.INTENT_KEY_LOGIN_EXPIRED, true);
//                    BaseApplication.getApplication().startActivity(i);
//                    return;
//                }
                Toast.makeText(BaseApplication.context, responseError.msg, Toast.LENGTH_SHORT).show()
            } else if (responseError.type == ResponseError.ErrorCode.NOT_NETWORD_ERROR) {
                Toast.makeText(BaseApplication.context, "请检查网络", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(BaseApplication.context, "数据出错啦...拼命抢修中...", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(BaseApplication.context, "数据出错啦...拼命抢修中...", Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun onSuccess(dataResponse: T)

    //    public abstract LogInfo getConfigLog();
    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart()")
        showLoadingDialog()
    }

    override fun onCompleted() {
        Log.e(TAG, "onCompleted()")
        disMissLoading()
    }

    override fun onError(throwable: Throwable) {
        Log.e(TAG, "onError:" + throwable.message)
        val responseError: ResponseError
        //        LogInfo configLog = getConfigLog();
        responseError = if (throwable is ResponseError) {
            throwable
            //            setConfigLog(configLog, "2", "2", "接口 code error");
        } else {
            if (throwable is UnknownHostException) {
                ResponseError(throwable, ResponseError.ErrorCode.NOT_NETWORD_ERROR)
                //                setConfigLog(configLog, "3", "3", "接口 UnknownHostException");
            } else if (throwable is JSONException
                    || throwable is JsonParseException) {
                ResponseError(throwable, ResponseError.ErrorCode.PARSE_ERROR)
                //                setConfigLog(configLog, "1", "4", "接口 PARSE_ERROR");
            } else if (throwable is HttpException) {
                ResponseError(throwable, ResponseError.ErrorCode.HTTP_ERROR)
                //                setConfigLog(configLog, "1", "5", "接口 HttpException");
            } else if (throwable is SSLHandshakeException) {
                ResponseError(throwable, ResponseError.ErrorCode.SSL_ERROR)
                //                setConfigLog(configLog, "1", "6", "接口 SSLHandshakeException");
            } else {
                ResponseError(throwable, ResponseError.ErrorCode.UNKNOWN_ERROR)
                //                setConfigLog(configLog, "1", "7", "接口 UNKNOWN_ERROR");
            }
        }
        //        if (configLog != null) {
//            configLog.setInfo(responseError.toString());
//            LogUtil.e(configLog.toString());
//            //上传log
//            BuglyLog.v(this.getClass().getSimpleName(), configLog.toString());
//            CrashReport.postCatchedException(throwable);
//        }
        disMissLoading()
        LogUtil.e("RequestNetCallBack  onFailure throwable:" +
                responseError.toString())
        onFailure(responseError)
    }

    //    private void setConfigLog(LogInfo configLog, String level,
    //                              String type_key, String type_name) {
    //        if (configLog != null) {
    //            configLog.setLevel(level);
    //            configLog.setType_key(type_key);
    //            configLog.setType_name(type_name);
    //            configLog.setTime(TimeUtils.getNowTimeString());
    //            configLog.setUser_id(AccountManager.getInstance().getUserId());
    //        }
    //    }
    override fun onNext(response: T) {
        Log.e(TAG, "onNext=" + response.toString())
        //返回结果的处理
        onSuccess(response)
    }

    /**
     * show loading
     */
    abstract fun showLoadingDialog()

    /**
     * dismiss loading
     */
    abstract fun disMissLoading()

    companion object {
        const val TAG = "RequestNetCallBack"

        /**
         * 成功状态码
         */
        const val SUCCESS_CODE = 0
        const val LOGIN_EXPIRED_CODE = 1001002
    }
}