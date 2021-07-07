package com.example.java_lib.pactera.gykj.shebao;

import com.example.java_lib.pactera.GsonUtils;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;

public class JavaTest {

    @Test
    public void method01(){
        String str ="{\n" +
                "                            \"pattern\": \"feedback\",\n" +
                "                            \"params\": {\n" +
                "                                \"isLogin\": \"0\",\n" +
                "                                \"isCertified\": \"1\",\n" +
                "                                \"parasId\": \"\",\n" +
                "                                \"title\": \"意见反馈\",\n" +
                "                                \"url\": \"\"\n" +
                "                            }\n" +
                "                        }";
        String str1 = "{\n" +
                "\n" +
                "                            \"params\": {\n" +
                "                                \"isLogin\": \"0\",\n" +
                "                                \"isCertified\": \"1\",\n" +
                "                                \"parasId\": \"\",\n" +
                "                                \"title\": \"意见反馈\",\n" +
                "                                \"url\": \"\"\n" +
                "                            }\n" +
                "                        }";
        RouterBean routerBean = GsonUtils.getInstance().fromJson(str1, RouterBean.class);
        switch (routerBean.pattern){
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "feedback":
                System.out.println("feedback");
                break;
        }
    }

    @Test
    public void method02(){
        HashMap<String, String> params = new HashMap<>();
        //开户时的交易单号
        params.put("corpOriginalSerno", "");
        //验证码，测试环境默认都是955888
        params.put("smsScode", "955888");
        //短信发送编号
        params.put("smsSendNo", "");
        //银行卡绑定的手机号
        params.put("phoneNo", "13661090741");
        //0-开户，1-绑卡
        params.put("type", "0");

        System.out.println("json："+new Gson().toJson(params));
    }
}
