package com.liwei.util;

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
}