package com.liwei.service;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Liwei on 2016/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class BlogServiceTest {


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


    @Test
    public void testAddBlog(){
        Blog blog = new Blog();
        blog.setTitle("Shiro 学习(1)");
        blog.setSummary("Shiro 是一款简单易用的 Java 安全框架。");
        blog.setReleaseDate(new Date());
        blog.setClickHit(50);
        blog.setReplyHit(0);
        blog.setContent("Shiro,不错不错,四大名著,春风又绿江南岸。");
        BlogType blogType = new BlogType();
        blogType.setId(11);
        blog.setBlogType(blogType);
        blog.setKeyword("认证 授权 会话 加密");
        blogService.add(blog);
    }
}