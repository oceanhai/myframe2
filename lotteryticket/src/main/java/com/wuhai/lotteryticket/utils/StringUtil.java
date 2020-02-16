package com.wuhai.lotteryticket.utils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 字符串工具类
 * 
 */
public class StringUtil {

	/**
	 * 格式化数据，保留指定的位数
	 * 
	 * @param flt
	 * @param count
	 *            小数点后位数
	 * @return
	 */
	public static String formatFloatStr(String flt, int count) {
		if (TextUtils.isEmpty(flt)) {
			if (count <= 0) {
				return "0";
			}
			StringBuffer sb = new StringBuffer("0.");
			for (int i = 0; i < count; i++) {
				sb.append("0");
			}
			return sb.toString();
		}
		StringBuffer sb = new StringBuffer("0.00000");
		DecimalFormat df = new DecimalFormat(sb.toString()); // " "内写格式的模式
																// 如保留2位就用"0.00"即可
		String format = df.format(Float.parseFloat(flt));

		return format.substring(0, format.indexOf(".") + count + 1);
	}

	/**
	 * 格式化钱币，每三位加一逗号
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney(String money, int count) {
		// setMaximumFractionDigits(int) 设置数值的小数部分允许的最大位数。
		// setMaximumIntegerDigits(int) 设置数值的整数部分允许的最大位数。
		// setMinimumFractionDigits(int) 设置数值的小数部分允许的最小位数。
		// setMinimumIntegerDigits(int) 设置数值的整数部分允许的最小位数.
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(count);
		format.setMaximumFractionDigits(count);
		format.setMaximumIntegerDigits(20);
		format.setMinimumIntegerDigits(1);
		return format.format(new BigDecimal(money));
		// return format.format(Float.parseFloat(money));
	}

	public static void addColorAndSize(final TextView textView, final int start, final int end, int color, int fontSize){
		String text = textView.getText().toString().trim();
		if(TextUtils.isEmpty(text) || start > end){
			return;
		}
		SpannableStringBuilder builder = new SpannableStringBuilder(text);
		if(color != 0) {
			builder.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		if(fontSize > 0) {
			builder.setSpan(new AbsoluteSizeSpan(fontSize, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		}
		textView.setText(builder);
	}

	/**
	 * 单位
	 * @param money
	 * @param unit
     * @return
     */
	public static String formatMoneyUnit(int money, int unit){
		String result ="";
		if(unit == 10000){
			result = money/unit+"W";
		}
		return result;
	}

	/**
	 * double 类型 截取小数点后位数
	 * @param format
	 * @param num
	 * @return
	 */
	public static String getDecimalFormat(String format, double num){
		DecimalFormat df = null;
		if (TextUtils.isEmpty(format)){
			df = new DecimalFormat("######0.00");
		}else{
			df = new DecimalFormat(format);
		}
		df.setRoundingMode(RoundingMode.HALF_UP);
		String result = df.format(num);
		return result;
	}

	/**
	 * 处理后的电话号码
	 * @param phone
	 * @return	eg:177****9269
     */
	public static String getPhoneStr(String phone){

		if(TextUtils.isEmpty(phone)){
			return null;
		}

		if(phone.length() != 11){
			return null;
		}

		return phone.substring(0,3)+"****"+phone.substring(8);
	}

	/**
	 * 返回长度为【strLength】的随机数
	 * 最大16位
	 */
	public static String getFixLenthString(int strLength) {

		Random rm = new Random();

		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);

		// 返回固定的长度的随机数
		return fixLenthString.substring(2, strLength + 2);
	}

	/**
	 * 获取uuid
	 *
	 */
	public static String getUUid() {

		UUID uuid=UUID.randomUUID();
		return   uuid.toString().replace("-","").toLowerCase();
	}



	/**
	 *
	 * @return
	 */
	public static String get17RandomString( ){

		String dateAllMsecString = DateUtils.getDateAllMsecString().replace("-","").replace(" ","").replaceAll(":","");
		return dateAllMsecString;
	}

	/**
	 * 获得一个随机 id
	 * 订单id、退货单id、订货单id
	 * @return
	 */
	public static String get17RandomNo(){
		return DateUtils.getDateString("yyMMddHHmm",
				System.currentTimeMillis())+ getFixLenthString(7);
	}

	/**
	 * 判断 str 是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric1(String str){
		for (int i = 0; i < str.length(); i++){
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断 str 是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric2(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	/**
	 * x位，前面不足自动补零
	 * @param num       位数
	 * @param str       入参
	 * @return
	 */
	public static String addZeroBefore(int num, String str){
		return String.format("%0"+num+"d",Integer.valueOf(str));
	}

	/**
	 * x位，前面不足自动补零
	 * @param num       位数
	 * @param str       入参
	 * @return
	 */
	public static String addZeroBefore2(int num, int str){
		return String.format("%0"+num+"d",str);
	}

	/**
	 * 判断是否是会员卡
	 * 99、00开头的6位、11位数字
	 * @param str
	 * @return
	 */
	public static boolean isMemberCard(String str){
		Pattern pattern = Pattern.compile("^(00|99)(\\d{4}|\\d{9})$");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是 订单no
	 * 17位数字
	 * @param str
	 * @return
	 */
	public static boolean isOrderNo(String str){
		if(str.length() != 17){
			return false;
		}

		return isNumeric2(str);
	}
}
