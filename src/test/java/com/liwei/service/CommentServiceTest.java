package com.liwei.service;

import com.liwei.entity.Blog;
import com.liwei.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Liwei on 2016/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application.xml"})
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testList() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("state",1);
        params.put("blogId",250);
        List<Comment> commentList = commentService.list(params);
        for(Comment comment:commentList){
            System.out.println(comment.getContent());
            // System.out.println(comment.getBlog().getContent());
        }
    }

    @Test
    public void testAdd() throws Exception {
        Comment comment = new Comment();
        comment.setUserIp("127.0.0.1");
        comment.setContent("今天是个好日子，心想的事儿都能成。");
        comment.setState(1);
        comment.setCommentDate(new Date());
        Blog blog = new Blog();
        blog.setId(250);
        comment.setBlog(blog);

        commentService.add(comment);
    }
}