package com.liwei.lucene;

import com.liwei.service.impl.BlogIndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liwei on 16/9/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class BlogIndexTest {

    @Autowired
    private BlogIndexService blogIndexService;

    @Test
    public void getIndexWriter() throws Exception {

    }

    @Test
    public void addIndex() throws Exception {

    }

    @Test
    public void deleteIndex() throws Exception {

    }

    @Test
    public void updateIndex() throws Exception {

    }

    @Test
    public void searchBlog() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {
        blogIndexService.deleteAll();
    }

}