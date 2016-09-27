package com.liwei.service;

import com.liwei.entity.Link;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/21.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class LinkServiceTest {

    @Autowired
    private LinkService linkService;

    @Test
    public void testList() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("linkName","Redis");
        List<Link> linkList = linkService.list(params);
        for(Link link:linkList){
            System.out.println(link);
        }
    }

    @Test
    public void testGetTotal() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("linkName","Redis");
        Long total = linkService.getTotal(params);
        System.out.println(total);
    }

    @Test
    public void testAdd() throws Exception {
        Link link = new Link();
        link.setOrderNo(6);
        link.setLinkUrl("http://www.baidu.com/");
        link.setLinkName("百度");
        Integer insertNum = linkService.add(link);
        System.out.println(insertNum);
    }

    @Test
    public void testBatchAdd() throws Exception {
        for(int i=0;i<18;i++){
            Link link = new Link();
            link.setOrderNo(6 + i);
            link.setLinkUrl("http://www.baidu.com/");
            link.setLinkName("百度 " + i);
            Integer insertNum = linkService.add(link);
            System.out.println(insertNum);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Link link = new Link();
        link.setId(6);
        link.setOrderNo(20);
        link.setLinkUrl("http://www.baidu.com/");
        // link.setLinkName("百度");

        Integer updateNum = linkService.update(link);
        System.out.println(updateNum);
    }

    @Test
    public void testDelete() throws Exception {
        Integer deleteNum = linkService.delete(6);
        System.out.println(deleteNum);
    }
}