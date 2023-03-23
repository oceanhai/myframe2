package com.wuhai.myframe2.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;

import com.wuhai.myframe2.R;

import java.util.concurrent.Executor;

/**
 * 作者 wh
 * 
 * 创建日期 2023/3/23 17:35
 * 
 * 描述：Android生物识别
 * https://blog.csdn.net/liujun3512159/article/details/124792510
 */
public class BiometricActivity extends AppCompatActivity {

    public static final String TAG = BiometricActivity.class.getSimpleName();

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BiometricActivity.class);
        context.startActivity(intent);
    }

    private Handler handler = new Handler();

    private Executor executor = new Executor() {
        @Override
        public void execute(Runnable command) {
            handler.post(command);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_biometric);
        Button biometricLoginButton = findViewById(R.id.biometric_login);

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Toast.makeText(this, "应用可以进行生物识别技术进行身份验证", Toast.LENGTH_SHORT).show();

                biometricLoginButton.setOnClickListener(view -> showBiometricPrompt());


                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Toast.makeText(this, "该设备上没有搭载可用的生物特征功能", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "生物识别功能当前不可用", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this, "用户没有录入生物识别数据", Toast.LENGTH_SHORT).show();
                break;
        }

    }

    //生物认证的setting
    private void showBiometricPrompt() {
        BiometricPrompt.PromptInfo promptInfo =
                new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("Biometric login for my app") //设置大标题
                        .setSubtitle("Log in using your biometric credential") // 设置标题下的提示
                        .setNegativeButtonText("Cancel") //设置取消按钮
                        .build();

        //需要提供的参数callback
        BiometricPrompt biometricPrompt = new BiometricPrompt(BiometricActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            //各种异常的回调
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getApplicationContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();

                Log.e(TAG, "onAuthenticationError errorCode="+errorCode+",errString="+errString);
            }

            //认证成功的回调
            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject authenticatedCryptoObject =
                        result.getCryptoObject();
                // User has verified the signature, cipher, or message
                // authentication code (MAC) associated with the crypto object,
                // so you can use it in your app's crypto-driven workflows.

                Log.e(TAG, "onAuthenticationSucceeded result="+result.toString());
            }

            //认证失败的回调
            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.e(TAG, "onAuthenticationFailed");
            }
        });

        // 显示认证对话框
        biometricPrompt.authenticate(promptInfo);
    }
}