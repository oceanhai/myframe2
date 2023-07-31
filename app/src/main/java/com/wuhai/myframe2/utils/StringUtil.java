package com.wuhai.myframe2.utils;

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

/**
 * 
 * 字符串工具类，用于MD5加密解密
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
//		LogProxy.v("format", sb.toString() + "----" + flt);
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
//		LogProxy.e("StringUtil","入参 format="+format+",num="+num+",结果 result="+result);
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
}
