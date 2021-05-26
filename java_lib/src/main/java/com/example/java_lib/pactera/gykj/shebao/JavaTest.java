package com.example.java_lib.pactera.gykj.shebao;

import com.example.java_lib.pactera.GsonUtils;

import org.junit.Test;

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
}
