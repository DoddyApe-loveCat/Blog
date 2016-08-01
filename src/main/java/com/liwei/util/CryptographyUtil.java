package com.liwei.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Created by Liwei on 2016/8/1.
 * 加密工具
 */
public class CryptographyUtil {

    /**
     *
     * @param str
     * @param salt
     * @return
     */
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }


    public static void main(String[] args) {
        String password = "123456";
        System.out.println("MD5 加密：" + CryptographyUtil.md5(password,"liwei"));
    }
}
