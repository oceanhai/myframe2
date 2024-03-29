package com.wuhai.scan;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**String 字符判断处理的类
 * Created by xywy on 2016/5/9.
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * 保留前keepStart位，保留后keepEnd位，中间替换成*
     * @param keepStart
     * @param keepEnd
     * @param str
     * @return
     */
    public static String replaceAsterisk(int keepStart, int keepEnd, String str){
        if(TextUtils.isEmpty(str)){
            return null;
        }

        if(keepStart + keepEnd >= str.length()){
            return null;
        }

        int len = str.length();
        char[] chars = str.toCharArray();
        for(int x=keepStart; x<len-keepEnd; x++){
            chars[x] = '*';
        }

        return new String(chars);
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

    /**
     * 判断是否是中英文
     * @param str
     *   String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$"; 包含数字
     * @return
     */
    public static boolean isLetterDigitOrChinese(String str){
        String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    /**
     * 判断密码是否为6-15位的字母或数字
     * @param password
     * @return
     */
    public static boolean passwordIsOk(String password){
        String str = "^[a-zA-Z0-9]{6,15}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);
        return m.matches();
    }

    /**
     * String型转化 Calendar
     * @param str 形式 2016-07-01 12:12:12
     * @return
     */
    public static Calendar parseStrToCld(String str){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return  calendar;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 向下取0.5
     * @param str
     * @return
     */
    public static double toLowPointFive(Double str){
       double max= Math.ceil(str);
        if(Math.abs(max-str)>0.5){
            return  max-1;
        }else if(Math.abs(max-str)==0){
            return  str;
        }else{
            return  max-0.5;
        }
    }

}
