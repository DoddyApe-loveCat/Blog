package com.liwei.service;

import com.liwei.entity.BlogType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/8/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class BlogTypeServiceTest {

    @Autowired
    protected BlogTypeService blogTypeService;

    @Test
    public void testCountList() throws Exception {
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        for(BlogType blogType:blogTypeCountList){
            System.out.println(blogType);
        }
    }

    @Test
    public void testList() throws Exception {
        Map<String,Object> params = new HashMap<>();
        // 这里不能写成 "1"，"2"
        params.put("start",1);
        params.put("pageSize",2);
        List<BlogType> blogTypeList = blogTypeService.list(params);
        for(BlogType blogType:blogTypeList){
            System.out.println(blogType);
        }
    }

    @Test
    public void testGetTotal() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("typeName","个人随笔");
        Long total = blogTypeService.getTotal(params);
        System.out.println(total);

    }

    @Test
    public void testAdd() throws Exception {
        BlogType blogType = new BlogType();
        blogType.setTypeName("Java 专区");
        blogType.setOrderNo(6);
        Integer insertNum = blogTypeService.add(blogType);
        System.out.println(insertNum);
    }

    @Test
    public void testUpdate() throws Exception {
        BlogType blogType = new BlogType();
        blogType.setId(6);
        blogType.setTypeName("Groovy 专区");
        blogType.setOrderNo(20);
        Integer updateNum = blogTypeService.update(blogType);
        System.out.println(updateNum);
    }

    @Test
    public void testDelete() throws Exception {
        Integer blogTypeId = 7;
        Integer deleteNum = blogTypeService.delete(blogTypeId);
        System.out.println(deleteNum);
    }
}