package com.wuhai.myframe2.network;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by flyeek on 11/16/15.
 */
public interface APICallBack<T> {
    /**
     * Successful response, and the respond result is ok.
     */
    void success(Call<T> call, Response<T> response);

    /**
     * Successful response, but the date within respond result is error.
     */
    void error(int code, String errorMsg);

    /**
     * Unsuccessful response due to network networkFailure, invalid respond data, or
     * unexpected exception.
     */
    void networkFailure(String networkFailureMsg);
}
