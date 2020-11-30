package com.example.java_lib.pactera.shell;

/**
 * 作者 wh
 *
 * 创建日期 2020/11/25 11:07
 *
 * 描述：测试下静态属性和非静态属性
 * 难怪 我扫码那里，没有把config设置到qrmanager里，依然声音的引用变化了，直接引用raw id的时候
 */
public class QrConfigTest {

    public static void main(String[] args) {
        QrConfig config = new QrConfig();
        config.setLoop_wait_time(1000);

        System.out.println(QrConfig.ding_path +","+config.loop_wait_time);

        QrConfig config1 = new QrConfig();
        System.out.println(QrConfig.ding_path +","+config1.loop_wait_time);
    }

}
