package com.qihoo360.antilostwatch.light.utils;

import java.security.MessageDigest;

/**
 * Created by HuirongZhang on 2016/10/25.
 */

public class MD5Utils {

    public static String getMD5code(String string) {
        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(string.getBytes());
            byte[] b = e.digest();
            StringBuffer buf = new StringBuffer("");

            for (int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (Exception var6) {
            return "";
        }
    }
}
