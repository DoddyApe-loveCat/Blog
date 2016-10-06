package com.liwei.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by liwei on 16/9/27.
 */
public class CryptographyUtilTest {


    /**
     * 管理员账户密码加密规则
     * 明文密码经过 MD5 算法加密,使用盐值为用户名
     * @throws Exception
     */
    @Test
    public void md5_1() throws Exception {
        String password = "123456";
        String salt = "admin";
        System.out.println("MD5 加密：" + CryptographyUtil.md5(password,salt));
    }

    @Test
    public void md5_2() throws Exception {
        String password = "123456";
        String salt = "admin123";
        System.out.println("MD5 加密：" + CryptographyUtil.md5(password,salt));
    }


    @Test
    public void base64() throws Exception {
        String username = "admin";
        String base64Encoded = Base64.encodeToString(username.getBytes());

        String str2 = Base64.decodeToString(base64Encoded);
        System.out.println(base64Encoded);
        System.out.println(str2);
    }

    @Test
    public void base64_16(){
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        System.out.println(base64Encoded);
        System.out.println(str2);
    }
}