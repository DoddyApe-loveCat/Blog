package com.liwei.service;

import com.liwei.entity.Blog;
import com.liwei.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by Liwei on 2016/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void testList() throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("state",0);
        params.put("blogId",1);
        List<Comment> commentList = commentService.list(params);
        for(Comment comment:commentList){
            System.out.println(comment.getContent() + "\t" + comment.getBlog().getTitle());
            // System.out.println(comment.getBlog().getContent());
        }
    }



    @Test
    public void testAdd() throws Exception {
        Comment comment = new Comment();
        comment.setUserIp("127.0.0.1");
        comment.setContent("今天是个好日子，心想的事儿都能成。");
        comment.setState(0);
        comment.setCommentDate(new Date());
        Blog blog = new Blog();
        blog.setId(1);
        comment.setBlog(blog);

        Integer addNum =  commentService.add(comment);
        System.out.println(addNum);
    }

    @Test
    public void testBatchAdd() throws Exception {
        Comment comment = new Comment();
        comment.setUserIp("127.0.0.1");

        comment.setState(0);
        comment.setCommentDate(new Date());
        for(int i=0;i<17;i++){
            Blog blog = new Blog();
            blog.setId(1);
            comment.setContent( i + " 今天是个好日子，心想的事儿都能成。");
            comment.setBlog(blog);
            Integer addNum =  commentService.add(comment);
            System.out.println(addNum);
        }
    }

    @Test
    public void testBatchUpdateState(){
        List<Integer> ids = new ArrayList<>();
        ids.add(10);
        ids.add(11);
        ids.add(12);
        Integer state = 1;
        Integer updateNum =
        commentService.batchUpdateState(ids,state);
        System.out.println(updateNum);
    }

    @Test
    public void testBatchDelete(){
        List<Integer> idList = new ArrayList<>();
        idList.add(16);
        idList.add(17);
        idList.add(18);
        idList.add(19);
        Integer deleteNum = commentService.batchDelete(idList);
        System.out.println(deleteNum);
    }




}