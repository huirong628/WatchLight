package com.qihoo360.antilostwatch.light.utils;

import java.util.Calendar;

/**
 * Created by HuirongZhang
 * on 2016/11/7.
 */

public class ApiUtil {
    public static String getTimestamp() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis() / 1000); // 时间戳（单位秒）
    }

    public static String getM2() {
        return "add72710293144865e2d8c053bf3b28a";
    }
}
