package com.liwei.service;

import com.liwei.entity.Blogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
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
    public void testFind() throws Exception {

    }
}