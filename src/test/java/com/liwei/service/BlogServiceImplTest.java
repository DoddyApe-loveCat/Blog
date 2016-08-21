package com.liwei.service;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import com.liwei.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class BlogServiceImplTest {


    @Autowired
    private BlogService blogService;

    /**
     * 根据年份月份分组查询博客数量
     * @throws Exception
     */
    @Test
    public void testCountList() throws Exception {
        List<Blog> blogList = blogService.countList();
        for(Blog blog:blogList){
            System.out.println("blog => " + blog);
        }
    }

    @Test
    public void testFindByBlogId() throws Exception {
        System.out.println(blogService);
        Blog blog1 = blogService.findByBlogId(1);
        System.out.println("content => " + blog1.getContent());
        BlogType blogType = blog1.getBlogType();
        System.out.println("blog_type => " + blogType.getTypeName());
        System.out.println("blog_type_order => " + blogType.getOrderNo());
    }

    @Test
    public void testGetBlogNumByTypeId(){
        Integer num = blogService.getBlogNumByTypeId(1);
        System.out.println(num);
    }
}