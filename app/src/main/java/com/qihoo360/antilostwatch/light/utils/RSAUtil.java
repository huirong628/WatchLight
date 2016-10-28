package com.qihoo360.antilostwatch.light.utils;

import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by HuirongZhang on 2016/10/26.
 */

public class RSAUtil {
    private static final int RSA_KEY_LEN = 128;
    private static final String RSA_KEY_PREFIX_PATTERN = "-BEGIN";
    public static final byte[] PUBLIC_KEY_IN_BYTES = new byte[]{(byte)48, (byte)-127, (byte)-97, (byte)48, (byte)13, (byte)6, (byte)9, (byte)42, (byte)-122, (byte)72, (byte)-122, (byte)-9, (byte)13, (byte)1, (byte)1, (byte)1, (byte)5, (byte)0, (byte)3, (byte)-127, (byte)-115, (byte)0, (byte)48, (byte)-127, (byte)-119, (byte)2, (byte)-127, (byte)-127, (byte)0, (byte)-87, (byte)67, (byte)63, (byte)-4, (byte)5, (byte)-87, (byte)83, (byte)-48, (byte)32, (byte)30, (byte)-71, (byte)-63, (byte)-107, (byte)-99, (byte)60, (byte)-72, (byte)26, (byte)-120, (byte)109, (byte)-15, (byte)-17, (byte)-89, (byte)-13, (byte)109, (byte)43, (byte)-26, (byte)67, (byte)-60, (byte)23, (byte)-126, (byte)-92, (byte)87, (byte)-39, (byte)-56, (byte)58, (byte)-24, (byte)63, (byte)52, (byte)55, (byte)115, (byte)-67, (byte)-25, (byte)115, (byte)93, (byte)36, (byte)-25, (byte)33, (byte)12, (byte)-62, (byte)-47, (byte)102, (byte)117, (byte)-40, (byte)-121, (byte)-42, (byte)31, (byte)-101, (byte)34, (byte)107, (byte)47, (byte)-120, (byte)-40, (byte)48, (byte)-45, (byte)-117, (byte)88, (byte)60, (byte)77, (byte)79, (byte)62, (byte)-88, (byte)105, (byte)-6, (byte)-36, (byte)92, (byte)-66, (byte)22, (byte)-120, (byte)42, (byte)-4, (byte)-26, (byte)47, (byte)-34, (byte)-43, (byte)-98, (byte)95, (byte)92, (byte)111, (byte)92, (byte)-88, (byte)43, (byte)12, (byte)-38, (byte)-38, (byte)-107, (byte)97, (byte)-126, (byte)88, (byte)-93, (byte)-128, (byte)60, (byte)21, (byte)-14, (byte)-71, (byte)7, (byte)-79, (byte)-109, (byte)-104, (byte)-18, (byte)71, (byte)5, (byte)49, (byte)51, (byte)0, (byte)69, (byte)-126, (byte)-103, (byte)-54, (byte)81, (byte)109, (byte)-52, (byte)120, (byte)123, (byte)-20, (byte)77, (byte)-46, (byte)123, (byte)-105, (byte)2, (byte)3, (byte)1, (byte)0, (byte)1};

    public RSAUtil() {
    }

    public static boolean decryptByPublicKey(byte[] keyInByte, byte[] source, byte[] sign) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        KeyFactory mykeyFactory = KeyFactory.getInstance("RSA");
        Signature sig = Signature.getInstance("SHA1withRSA");
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(keyInByte);
        PublicKey pubKey = mykeyFactory.generatePublic(pubSpec);
        sig.initVerify(pubKey);
        sig.update(source);
        boolean verified = sig.verify(sign);
        return verified;
    }

    public static byte[] MdigestSHA(String source) {
        try {
            MessageDigest e = MessageDigest.getInstance("SHA");
            byte[] digest = e.digest(source.getBytes("UTF8"));
            return digest;
        } catch (Exception var3) {
            return null;
        }
    }

    public static String encryptByPublic(String content, String key) {
        try {
            PublicKey e = getPublicKeyFromX509("RSA", key);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, e);
            byte[] plaintext = content.getBytes("UTF-8");
            byte splitLen = 117;
            int x = plaintext.length / splitLen;
            int y = plaintext.length % splitLen;
            byte z = 0;
            if(y != 0) {
                z = 1;
            }

            byte[] output = new byte[128 * (x + z)];
            Object outputTemp = null;
            Object inputTemp = null;
            boolean doCount = false;

            for(int s = 0; s < plaintext.length; s += splitLen) {
                int doCount1 = s + splitLen > plaintext.length?plaintext.length - s:splitLen;
                byte[] inputTemp1 = new byte[doCount1];
                System.arraycopy(plaintext, s, inputTemp1, 0, doCount1);
                byte[] outputTemp1 = cipher.doFinal(inputTemp1);
                System.arraycopy(outputTemp1, 0, output, s / splitLen * 128, outputTemp1.length);
            }

            String s1 = new String(Base64.encode(output, 3));
            return s1;
        } catch (Exception var14) {
            var14.printStackTrace();
            return null;
        }
    }

    public static PublicKey getPublicKeyFromX509(String algorithm, String bysKey) throws Exception {
        if(bysKey != null && bysKey.contains("-BEGIN")) {
            int decodedKey = bysKey.indexOf("\n");
            int x509 = bysKey.lastIndexOf("\n");
            bysKey = bysKey.substring(decodedKey + 1, x509);
            x509 = bysKey.lastIndexOf("\n");
            bysKey = bysKey.substring(0, x509);
        }

        byte[] decodedKey1 = Base64.decode(bysKey, 0);
        X509EncodedKeySpec x5091 = new X509EncodedKeySpec(decodedKey1);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(x5091);
    }
}
