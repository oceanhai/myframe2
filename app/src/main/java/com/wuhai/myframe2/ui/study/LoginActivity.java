package com.wuhai.myframe2.ui.study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.wuhai.myframe2.R;

/**
 * 作者 wuhai
 *
 * 创建日期 2019/4/3 11:45
 *
 * 描述：LoginActivity
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;

    /**
     * @param context
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_login);
        textInputLayout = (TextInputLayout)findViewById(R.id.textInputLayout);
        //检测长度应该低于6位数
        textInputLayout.getEditText().addTextChangedListener(new MinLengthTextWatcher(textInputLayout, "长度应低于6位数!"));

        //开启计数
        textInputLayout.setCounterEnabled(true);
        textInputLayout.setCounterMaxLength(10);//最大输入限制数

    }
    class MinLengthTextWatcher implements TextWatcher {
        private String errorStr;
        private TextInputLayout textInputLayout;
        public  MinLengthTextWatcher(TextInputLayout textInputLayout, String errorStr){
            this.textInputLayout = textInputLayout;
            this.errorStr = errorStr;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            // 文字改变后回调
            if(textInputLayout.getEditText().getText().toString().length()<=6){
                textInputLayout.setErrorEnabled(false);
            }else{
                textInputLayout.setErrorEnabled(true);
                textInputLayout.setError(errorStr);
            }
        }
    }
}
