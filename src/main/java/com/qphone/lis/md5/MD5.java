package com.qphone.lis.md5;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author Administrator
 */
public class MD5 {
    public static String getMD5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());
            return new BigInteger(1, md5.digest()).toString(16);
        } catch (Exception e) {
            return new Exception("MD5加密出现错误").toString();
        }
    }
}
