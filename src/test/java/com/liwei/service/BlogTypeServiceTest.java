package com.liwei.service;

import com.liwei.entity.BlogType;
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
public class BlogTypeServiceTest {


    @Autowired
    private BlogTypeService blogTypeService;


    @Test
    public void testCountList() throws Exception {
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        for(BlogType blogType:blogTypeCountList){
            System.out.println(blogType);
        }
    }
}