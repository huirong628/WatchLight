package com.qihoo360.antilostwatch.light.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;

/**
 * Created by HuirongZhang on 2016/10/31.
 */

public class EncryptRC4 {
    public static byte[] make(String keyStr, byte[] byteData) throws Exception {
        byte[] byteKey = initKey(keyStr);
        byte[] data = make(byteKey, byteData);
        return data;
    }

    public static void makeStream(String keyStr, InputStream is, OutputStream os) throws Exception {
        byte[] byteKey = initKey(keyStr);
        makeStream(byteKey, is, os);
    }


    /**
     * 初始化密钥
     *
     * @param srcKey 原字符串密钥
     * @return 格式化的密钥
     * @throws NoSuchAlgorithmException
     */
    private static byte[] initKey(String srcKey) throws NoSuchAlgorithmException {
        byte[] byteKey = srcKey.getBytes();

        byte[] hashKey = new byte[256];

        for (int i = 0; i < 256; i++) {
            hashKey[i] = (byte) i;
        }

        if (byteKey == null || byteKey.length == 0) {
            return null;
        }

        int index1 = 0;
        int index2 = 0;
        for (int i = 0; i < 256; i++) {
            index2 = ((byteKey[index1] & 0xff) + (hashKey[i] & 0xff) + index2) & 0xff;
            byte tmp = hashKey[i];
            hashKey[i] = hashKey[index2];
            hashKey[index2] = tmp;
            index1 = (index1 + 1) % byteKey.length;
        }

        return hashKey;
    }

    /**
     * RC4加解密处理
     *
     * @param key       格式化的密钥
     * @param inputData 待处理的数据
     * @return 加解密处理的结果
     */
    private static byte[] make(byte[] key, byte[] inputData) {
        if (key == null || key.length == 0) {
            return inputData;
        }

        if (inputData == null || inputData.length == 0) {
            return inputData;
        }

        int x = 0;
        int y = 0;
        int xorIndex;
        byte[] result = new byte[inputData.length];

        for (int i = 0; i < inputData.length; i++) {
            x = (x + 1) & 0xff;
            y = ((key[x] & 0xff) + y) & 0xff;
            byte tmp = key[x];
            key[x] = key[y];
            key[y] = tmp;
            xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
            result[i] = (byte) (inputData[i] ^ key[xorIndex]);
        }
        return result;
    }

    /**
     * RC4加解密处理
     *
     * @param key 格式化的密钥
     * @param is  输入流
     * @param os  输出流
     * @throws 异常
     */
    private static boolean makeStream(byte[] key, InputStream is, OutputStream os) throws Exception {
        if (key == null || key.length == 0) {
            return false;
        }

        int x = 0;
        int y = 0;
        int xorIndex;
        byte tmp;

        int len = -1;
        byte[] buff = new byte[2048];
        while ((len = is.read(buff)) != -1) {
            for (int i = 0; i < len; i++) {
                x = (x + 1) & 0xff;
                y = ((key[x] & 0xff) + y) & 0xff;
                tmp = key[x];
                key[x] = key[y];
                key[y] = tmp;
                xorIndex = ((key[x] & 0xff) + (key[y] & 0xff)) & 0xff;
                buff[i] = (byte) (buff[i] ^ key[xorIndex]);
            }
            //输出
            os.write(buff, 0, len);
            os.flush();
        }

        return true;
    }

}
