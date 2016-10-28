package com.qihoo360.antilostwatch.light.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class DesUtil {
    private static final String TAG = "ACCOUNT.DesUtil";
    private static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    public DesUtil() {
    }

    public static String encryptDES(String encryptStr, String sKey) {
        String ret = "";
        IvParameterSpec zeroIv = null;
        SecretKeySpec key = null;
        Cipher cipher = null;
        Object encryptedData = null;

        try {
            if(encryptStr != null && !encryptStr.equals("")) {
                zeroIv = new IvParameterSpec(sKey.getBytes());
                key = new SecretKeySpec(sKey.getBytes(), "DES");
                cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(1, key, zeroIv);
                byte[] encryptedData1 = cipher.doFinal(encryptStr.getBytes());
                ret = Base64.encodeToString(encryptedData1, 3);
            }
        } catch (Exception var8) {
            ;
        }

        return ret;
    }

    public static String decryptDES(String decryptStr, String sKey) {
        String ret = "";
        Object byteMi = null;
        Object decryptData = null;
        IvParameterSpec zeroIv = null;
        SecretKeySpec key = null;
        Cipher cipher = null;

        try {
            if(decryptStr != null && !decryptStr.equals("")) {
                byte[] byteMi1 = Base64.decode(decryptStr, 3);
                zeroIv = new IvParameterSpec(sKey.getBytes());
                key = new SecretKeySpec(sKey.getBytes(), "DES");
                cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                cipher.init(2, key, zeroIv);
                byte[] decryptData1 = cipher.doFinal(byteMi1);
                ret = new String(decryptData1);
            }
        } catch (Exception var9) {
            ;
        }

        return ret;
    }

    public static String qucDesEncryptStr(String source, String key) {
        String ret = "";

        try {
            byte[] data = crypt(source.getBytes(), key, 1);
            ret = Base64.encodeToString(data, 3);
        } catch (Exception var4) {
            ;
        }

        return ret;
    }

    public static String qucDesDecryptStr(String source, String key) {
        String ret = "";

        try {
            byte[] data = Base64.decode(source, 3);
            data = crypt(data, key, 2);
            if(data != null) {
                ret = new String(data);
            }
        } catch (Exception var4) {
            ;
        }

        return ret;
    }

    private static byte[] crypt(byte[] source, String key, int mode) throws Exception {
        Object ret = null;
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(mode, secretKey, iv);
        byte[] ret1 = cipher.doFinal(source);
        return ret1;
    }
}
