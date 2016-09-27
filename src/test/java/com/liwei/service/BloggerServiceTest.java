package com.liwei.service;

import com.liwei.entity.Blogger;
import com.liwei.util.CryptographyUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Liwei on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class BloggerServiceTest {

    @Autowired
    private BloggerService bloggerService;

    @Test
    public void testGetByUserName() throws Exception {
        System.out.println(bloggerService.hashCode());
        Blogger blogger = bloggerService.getByUserName("liwei");
        System.out.println(blogger.getNickName());
        System.out.println(blogger.getUserName());
    }

    @Test
    public void testAdd() throws Exception {
        Blogger blogger = new Blogger();
        blogger.setUserName("admin");
        blogger.setPassword(CryptographyUtil.md5("123456",blogger.getUserName()));
        blogger.setNickName("李威威");
        blogger.setProfile("个人简介待添加");
        blogger.setSign("简单,诚实。");
        blogger.setImageName("/static/userImages/liwei.jpg");
        bloggerService.add(blogger);
    }
}