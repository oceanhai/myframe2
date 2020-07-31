package com.example.java_lib.pactera;

/**
 * 作者 wh
 *
 * 创建日期 2020/7/29 15:47
 *
 * 描述： 请求   扩展参数对象列表
 */
public class ExtParm {

    /**
     * extKey : 输入内容
     * extOperator : 0
     * extValue : 值
     */
    private String extKey;//输入内容
    private int extOperator;//操作符号，0-包含于，1-不包含于，2-等于，3-小于，4-大于，5-小于等于，6-大于等于
    private String extValue;//值

    public void setExtKey(String extKey) {
        this.extKey = extKey;
    }

    public void setExtOperator(int extOperator) {
        this.extOperator = extOperator;
    }

    public void setExtValue(String extValue) {
        this.extValue = extValue;
    }

    public String getExtKey() {
        return extKey;
    }

    public int getExtOperator() {
        return extOperator;
    }

    public String getExtValue() {
        return extValue;
    }
}
