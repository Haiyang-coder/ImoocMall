package com.shk.mall.utils;

import com.shk.mall.common.MallCommon;
import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: sunhengkang
 * @date:2022/10/12
 *获取字符串的MD5码
 */
public class GetMD5Util {
    public static String getMd5Code(String password){
        String StingByMd5 = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(password.getBytes());
            StingByMd5 = Base64.encodeBase64String(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return StingByMd5;
    }

    /**
     * 给字符串加烟值
     * @param stringValue
     * @return
     */
    public static String addSalt(String stringValue){
        String temp = stringValue + MallCommon.SAULT;
        return temp;
    }
}
