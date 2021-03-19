package com.wuhai.hhsc.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * description:字符串工具类
 * data: 2019-12-13
 * author: zhudi
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str) || "null".equals(str) || "NULL".equals(str)) {
            return true;
        }
        //过滤空格
        return str.equals(" ") || str.trim().equals("") || str.equals("");
    }

    public static boolean isEmpty(CharSequence str) {
        if (TextUtils.isEmpty(str) || "null".equals(str) || "NULL".equals(str)) {
            return true;
        }
        //过滤空格
        return str.equals(" ");
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否是 url
     * @param emails
     * @return
     */
    public static boolean isUrls(String emails){
        String str = "^(http://|https://)?((?:[A-Za-z0-9]+-[A-Za-z0-9]+|[A-Za-z0-9]+)\\.)+([A-Za-z]+)[/\\?\\:]?.*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(emails);
        return m.matches();
    }

}
