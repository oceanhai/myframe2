package com.wuhai.lotteryticket.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 作者 wh
 *
 * 创建日期 2020/1/9 21:42
 *
 * 描述：匹配器
 */
public final class MatcherUtils {

    private MatcherUtils() {
    }

    public static boolean strSplitR(String str) {
        if (str != null) {
            String[] strs = str.split("\\.");
            return strs.length > 0 && "R".equals(strs[0]);
        }
        return false;
    }
    public static boolean isUrl(String str)throws PatternSyntaxException {
        // 只允许字母和数字
        String regEx  = "[a-zA-z]+://[^\\s]*" ;
        Pattern p   =   Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * 纯数字
     * @param str
     * @return
     */
    public static boolean isAllNumber(String str){
        String reg="^\\d+$";
        return str.matches(reg);
    }
}
