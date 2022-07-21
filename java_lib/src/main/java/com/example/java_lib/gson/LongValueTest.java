package com.example.java_lib.gson;

import com.example.java_lib.gson.fastjson.FastJsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;

import java.util.Map;

/**
 * @author sunRainAmazing
 */
public class LongValueTest {

    public static void main(String[] args){
        testLongValue();
    }

    /**
     * GSON处理JSON数据中Long型的数据变成 科学计数法的问题解决方案
     *
     * 1、先转成 JSONObject
     * 2、GsonBuilder 设置 Long数值型的转换策略为STRING策略
     * 3、gson.toJson(JSONObject)
     * 4、再转换成Map<String,Object>
     *
     * 结果
     * {username=tomcat, uuid=1.23456789012E11}
     * {username=tomcat, uuid=1.23456789012E11,
     *   mapObj={tname=jerry, tid=1.11523065825E11}, arg=abc}
     *
     * {"username":"tomcat","uuid":"123456789012",
     *   "mapObj":{"tname":"jerry","tid":"111523065825"},"arg":"abc"}
     *
     * {username=tomcat, uuid=123456789012,
     *   mapObj={tname=jerry, tid=111523065825}, arg=abc}
     */
//    @Test
    public static void testLongValue(){
        Gson gson = new Gson();
        String json = "{\"username\":\"tomcat\",\"uuid\":123456789012}";
        Map<String,Object> res = gson.fromJson(json,
                new TypeToken<Map<String,Object>>(){}.getType());
        System.out.println(res);

        // 原始json，带long类型的数值型
        String jsonStr = "{\n" +
                "\"username\": \"tomcat\",\n" +
                "\"uuid\": 123456789012,\n" +
                "\"mapObj\": {\n" +
                "\"tname\": \"jerry\",\n" +
                "\"tid\": 111523065825\n" +
                "},\n" +
                "\"arg\": \"abc\"\n" +
                "}";
        Map<String,Object> resStr = gson.fromJson(jsonStr,
                new TypeToken<Map<String,Object>>(){}.getType());
        System.out.println(resStr);

        String jsonStr1 = "{\n" +
                "  \"head\" : \"\",\n" +
                "  \"user\": {\n" +
                "    \"username\": \"tomcat\",\n" +
                "    \"uuid\": 123456789012\n" +
                "  }\n" +
                "}";

        User user = new Gson().fromJson(json, User.class);
        System.out.println(user);
        user = GsonUtils2.fromJson(json, User.class);
        System.out.println(user);

        //当数据体是 object的时候  gson 转换出问题
        Human human = GsonUtils2.fromJson(jsonStr1, Human.class);
        System.out.println(human);

        human = FastJsonUtils.jsonToObject(jsonStr1, Human.class);
        System.out.println(human);


//        // 先转成 JSONObject 对象
//        JSONObject jsonObject=JSONObject.fromObject(jsonStr);
//        // 设置 Long数值型的转换策略
//        Gson gsonBuilder = new GsonBuilder()
//                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
//                .create();
//        String jsonBu = gsonBuilder.toJson(jsonObject);
//        System.out.println(jsonBu);
//
//        //再转成Map对象
//        resStr = gson.fromJson(jsonBu,
//                new TypeToken<Map<String,Object>>(){}.getType());
//        System.out.println(resStr);

        // 若 嫌麻烦  -- 可换成其它JSON解析类，如 fastJson, Jackson
    }

    /**
     * json 中没有有嵌套属性 mapObj
     * 则可以通过 这种方式解决
     * Map<String,String> resStr = gson.fromJson(json,
     *   new TypeToken<Map<String,String>>(){}.getType());
     */
    @Test
    public void testStrNoObject(){
        // 原始json，带long类型的数值型
        String json = "{\"username\":\"tomcat\",\"uuid\":123456789012}";;
        Gson gson = new Gson();
        Map<String,String> resStr = gson.fromJson(json,
                new TypeToken<Map<String,String>>(){}.getType());
        System.out.println(resStr);
    }


    /**
     * json 中带有嵌套属性 mapObj
     * 使用上面的方式则会出错
     * Expected a string but was BEGIN_OBJECT at line 4 column 12 path $.
     * 因此， 我们需要转换json，换成我们需要的 格式
     *
     * @see LongValueTest#testLongValue
     *
     */
    @Test
    public void testStr(){
        // 原始json，带long类型的数值型
        String jsonStr = "{\n" +
                "\"username\": \"tomcat\",\n" +
                "\"uuid\": 123456789012,\n" +
                "\"mapObj\": {\n" +
                "\"tname\": \"jerry\",\n" +
                "\"tid\": 111523065825\n" +
                "},\n" +
                "\"arg\": \"abc\"\n" +
                "}";
        Gson gson = new Gson();
        Map<String,String> resStr = gson.fromJson(jsonStr,
                new TypeToken<Map<String,String>>(){}.getType());
        System.out.println(resStr);
    }
}