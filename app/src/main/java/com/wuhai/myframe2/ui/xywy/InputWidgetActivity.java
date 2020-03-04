package com.wuhai.myframe2.ui.xywy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wuhai.myframe2.R;
import com.wuhai.myframe2.ui.base.BaseActivity2;
import com.wuhai.myframe2.ui.xywy.network.ApiParams;
import com.wuhai.myframe2.ui.xywy.network.Constants;
import com.wuhai.myframe2.ui.xywy.network.DataRequestTool;
import com.wuhai.myframe2.ui.xywy.network.ServiceProvider;
import com.wuhai.myframe2.ui.xywy.widget.CountDownTimeUtil;
import com.wuhai.myframe2.ui.xywy.widget.InputWidget;
import com.wuhai.myframe2.utils.ToastUtils;
import com.wuhai.myframe2.utils.VerificationPhoneUtil;
import com.xywy.component.datarequest.neworkWrapper.BaseData;
import com.xywy.component.datarequest.neworkWrapper.IDataResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 寻医问药 登录控件+网络请求
 * ※ 注意 现在是线上，而且线上 返回来的动态码，居然不能用来最新板本app登录用 囧
 * 1.※网络请求框架要初始化 在BaseApplication里RequestManager.init(this);
 * 2.logcat 关键搜索字 NETWORK_TAG
 * 3.
 * url request : http://test.api.wws.xywy.com/api.php/user/userSmsPhoneCode/index?os=android&sign=c29403c79b39be76a768abeb683eb921&source=xywyapp&api=784&pro=xywyf32l24WmcqquqqTdhXaEng&version=1.2
 * post params : {act=userSMS_send, code=, phone=13661090741, project=APP_XYWYCJAPP}
 * files : null
 * url response error: com.android.volley.TimeoutError
 * 4.
 * url request : http://api.wws.xywy.com/api.php/user/userSmsPhoneCode/index?os=android&sign=c29403c79b39be76a768abeb683eb921&source=xywyapp&api=784&pro=xywyf32l24WmcqquqqTdhXaEng&version=1.2
 * post params : {act=userSMS_send, code=, phone=13661090741, project=APP_XYWYCJAPP}
 * files : null
 * url response: {"code":10000,"msg":"\u53d1\u9001\u6210\u529f"}
 * response builder : code = 10000, msg = 发送成功
 */
public class InputWidgetActivity extends BaseActivity2 implements View.OnClickListener, InputWidget.InputWidgetActionHandler {

    @BindView(R.id.inputwidget1)
    InputWidget inputwidget1;
    @BindView(R.id.inputwidget2)
    InputWidget inputwidget2;
    @BindView(R.id.inputwidget3)
    InputWidget inputwidget3;
    @BindView(R.id.btn01)
    Button btn01;
    @BindView(R.id.inputwidget4)
    InputWidget inputwidget4;
    @BindView(R.id.inputwidget5)
    InputWidget inputwidget5;

    private GetCodeResponse getCodeResp;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, InputWidgetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_input_widget);
        ButterKnife.bind(this);

        btn01.setOnClickListener(this);
        btn01.setClickable(false);

        //方式1 都有值时候方可操作
//        inputwidget1.setInputWidgetEditTextChanged(new InputWidget.InputWidgetEditTextChanged() {
//            @Override
//            public void onEditTextChanged(CharSequence s) {
//                if (!TextUtils.isEmpty(s.toString().trim()) && !TextUtils.isEmpty(inputwidget2.getContent()) && !TextUtils.isEmpty(inputwidget3.getContent())) {
//                    btn01.setClickable(true);
//                }else{
//                    btn01.setClickable(false);
//                }
//            }
//        });
//        inputwidget2.setInputWidgetEditTextChanged(new InputWidget.InputWidgetEditTextChanged() {
//            @Override
//            public void onEditTextChanged(CharSequence s) {
//                if(!TextUtils.isEmpty(inputwidget1.getContent()) && !TextUtils.isEmpty(s.toString().trim()) && !TextUtils.isEmpty(inputwidget3.getContent())){
//                    btn01.setClickable(true);
//                }else{
//                    btn01.setClickable(false);
//                }
//            }
//        });
//        inputwidget3.setInputWidgetEditTextChanged(new InputWidget.InputWidgetEditTextChanged() {
//            @Override
//            public void onEditTextChanged(CharSequence s) {
//                if (!TextUtils.isEmpty(inputwidget1.getContent()) && !TextUtils.isEmpty(inputwidget2.getContent()) && !TextUtils.isEmpty(s.toString().trim())) {
//                    btn01.setClickable(true);
//                } else {
//                    btn01.setClickable(false);
//                }
//            }
//        });

        //方式2 都有值时候方可操作 懒得动寻医问药之前人写的代码
        setButtonClicked(inputwidget1.getEditText(), inputwidget2.getEditText(), null, null, btn01);

        inputwidget3.setInputWidgetActionHandler(this);

//        inputwidget4.setIconRightEnable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn01:
                Toast.makeText(this,"我被点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void setButtonClicked(final EditText mEdit, final EditText mEdit1, final EditText mEdit2, final TextView mTextView, final Button mLoginBtn) {
        mLoginBtn.setClickable(false);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                String phone = mEdit.getText().toString().trim();
                String code = mEdit1.getText().toString().trim();

                if (mEdit2 != null) {
                    String password = mEdit2.getText().toString().trim();

                    if (isMobileNO(phone) && !TextUtils.isEmpty(code) &&
                            !TextUtils.isEmpty(password) && (password.length() >= 6 && password.length() <= 15)) {
//                        mTextView.setAlpha(a);
                        mLoginBtn.setClickable(true);
                    } else {
//                        mTextView.setAlpha(b);
                        mLoginBtn.setClickable(false);
                    }
                } else {
                    if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(code)) {
//                        mTextView.setAlpha(a);
                        mLoginBtn.setClickable(true);
                    } else {
//                        mTextView.setAlpha(b);
                        mLoginBtn.setClickable(false);
                    }
                }
            }
        };
        mEdit.addTextChangedListener(textWatcher);
        mEdit1.addTextChangedListener(textWatcher);
        if (mEdit2 != null) mEdit2.addTextChangedListener(textWatcher);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
        //		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex
                = "(13[0-9]|14[57]|15[012356789]|17[0123456789]|18[0123456789])\\d{8}";
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else {
            return mobiles.matches(telRegex);
        }
    }


    private CountDownTimeUtil count;

    /**
     * 获取动态密码
     */
    @Override
    public void onGetIdentifyingCode() {
        isFormatRight(inputwidget1.getContent(), 0);
    }

    /**
     * 验证手机号
     */
    public void isFormatRight(String phone, int i) {
//        if (NetUtils.isConnected(this)) {
        if ("".equals(phone) || phone.length() == 0) {
            ToastUtils.showShort(this, "请输入手机号");
        } else if (VerificationPhoneUtil.isMobile(phone) ||
                phone.length() != 11) {
            ToastUtils.showShort(this, "请输入正确的手机号");
        } else {
            if (i == 0) {
                //TODO 获取动态密码接口
                getData(phone);
                if (count == null) {
                    count = new CountDownTimeUtil(60 * 1000, 1000,
                            inputwidget3.getButtonText());
                }
                count.start();
            } else if (i == 1) {
                //TODO 动态密码登录接口
                //requestData();
            }
        }
//        }
//        else {
//            ToastUtils.showShort(this, "亲，请检查您的手机是否联网");
//        }
    }

    private void getData(String phone) {
        ApiParams getApiParams = new ApiParams().with(Constants.version_value1);

        ApiParams postApiParams = new ApiParams().with(Constants.phone_key,
                phone);

        getCodeResp = new GetCodeResponse();

        ServiceProvider.getCode(phone, getCodeResp, "InputWidgetActivity");
    }

    public class GetCodeResponse implements IDataResponse {
        @Override
        public void onResponse(BaseData obj) {
            if (DataRequestTool.noError(InputWidgetActivity.this, obj, false) && obj != null) {
                ToastUtils.showShort(InputWidgetActivity.this, "获取动态密码成功");
            } else {
                if (count != null) {
                    count.reSend();
                }
                ToastUtils.showShort(InputWidgetActivity.this, "获取动态密码失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (count != null) {
            count.cancel();
        }
    }
}
