package com.wuhai.myframe2.ui.retrofit.networknormalrx;

import android.app.Dialog;

import com.facebook.stetho.common.LogUtil;
import com.google.gson.JsonParseException;
import com.wuhai.myframe2.utils.ToastUtil;

import org.json.JSONException;

import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by fanchang on 2016/11/25.
 */
public abstract class RequestNetCallBack<T extends RootResponse> extends Subscriber<T> {
    /**
     * 成功状态码
     */
    public static final int SUCCESS_CODE = 0;
    public static final int LOGIN_EXPIRED_CODE = 1001002;

    //等待的窗口
    private Dialog mLoadingDialog = null;

    public void onFailure(ResponseError responseError) {
        //通用处理
        if (responseError != null) {
            if (responseError.getType() == ResponseError.ErrorCode.BUSINESS_ERROR) {
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
                ToastUtil.showShort(responseError.getMsg());
            } else if (responseError.getType() == ResponseError.ErrorCode.NOT_NETWORD_ERROR) {
                ToastUtil.showShort("请检查网络");
            } else {
                ToastUtil.showShort("数据出错啦...拼命抢修中...");
            }
        } else {
            ToastUtil.showShort("数据出错啦...拼命抢修中...");
        }
    }

    public abstract void onSuccess(T dataResponse);

//    public abstract LogInfo getConfigLog();

    @Override
    public void onStart() {
        super.onStart();
        showLoadingDialog();
    }

    @Override
    public void onCompleted() {
        disMissLoading();
    }

    @Override
    public void onError(Throwable throwable) {
        ResponseError responseError;
//        LogInfo configLog = getConfigLog();

        if (throwable instanceof ResponseError) {
            responseError = (ResponseError) throwable;
//            setConfigLog(configLog, "2", "2", "接口 code error");
        } else {
            if (throwable instanceof UnknownHostException) {
                responseError = new ResponseError(throwable, ResponseError.ErrorCode.NOT_NETWORD_ERROR);
//                setConfigLog(configLog, "3", "3", "接口 UnknownHostException");
            } else if (throwable instanceof JSONException
                    || throwable instanceof JsonParseException) {
                responseError = new ResponseError(throwable, ResponseError.ErrorCode.PARSE_ERROR);
//                setConfigLog(configLog, "1", "4", "接口 PARSE_ERROR");
            } else if (throwable instanceof HttpException) {
                responseError = new ResponseError(throwable, ResponseError.ErrorCode.HTTP_ERROR);
//                setConfigLog(configLog, "1", "5", "接口 HttpException");
            } else if (throwable instanceof SSLHandshakeException) {
                responseError = new ResponseError(throwable, ResponseError.ErrorCode.SSL_ERROR);
//                setConfigLog(configLog, "1", "6", "接口 SSLHandshakeException");
            } else {
                responseError = new ResponseError(throwable, ResponseError.ErrorCode.UNKNOWN_ERROR);
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

        disMissLoading();
        LogUtil.e("RequestNetCallBack  onFailure throwable:" +
                responseError.toString());
        onFailure(responseError);
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

    @Override
    public void onNext(T response) {
        //返回结果的处理
        onSuccess(response);
    }

    /**
     * 关闭等待的dialog
     *
     * @author wangchao
     * @time 2016/8/26 17:05
     */
    private void disMissLoading() {
        try {
            if ((this.mLoadingDialog != null) && this.mLoadingDialog.isShowing()) {
                this.mLoadingDialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
        }
    }

    /**
     * 显示加载进度条
     *
     * @return 要显示的dialog
     * @author wangchao
     * @time 2016/8/26 17:05
     */
    public abstract Dialog baseGetLoadingDialog();

    /**
     * 显示等待的dialog
     * *@author wangchao
     *
     * @time 2016/8/26 17:05
     */
    private void showLoadingDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = baseGetLoadingDialog();
            if (mLoadingDialog != null && !mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }
        }
    }
}
