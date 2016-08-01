package com.liwei.service;

import com.liwei.entity.Blogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Liwei on 2016/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class BloggerServiceTest {

    @Autowired
    private BloggerService bloggerService;

    @Test
    public void test01(){
        System.out.println(bloggerService.hashCode());
        Blogger blogger = bloggerService.getByUserName("liwei");
        System.out.println(blogger.getNickName());
        System.out.println(blogger.getUserName());
    }
}
