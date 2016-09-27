package com.liwei.lucene;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import com.liwei.service.BlogService;
import com.liwei.service.impl.BlogIndexService;
import com.liwei.util.HtmlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by liwei on 16/9/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml",
        "classpath:spring/spring-dao.xml"})
public class BlogIndexTest {

    @Autowired
    private BlogIndexService blogIndexService;

    @Autowired
    private BlogService blogService;

    @Test
    public void getIndexWriter() throws Exception {

    }

    /**
     * 测试添加一片文章的同时为这篇文章添加索引
     * @throws Exception
     */
    @Test
    public void addIndex() throws Exception {
        Blog blog = new Blog();
        blog.setTitle("Git 入门知识");
        blog.setSummary("Git 是目前全世界最流行的版本管理软件");
        blog.setReleaseDate(new Date());
        blog.setClickHit(19);
        blog.setReplyHit(0);
        blog.setContent("<p>Git 是一款分布式的版本管理软件,入门有点难度,一旦掌握以后,便感觉如获至宝。</p>");
        BlogType blogType = new BlogType();
        blogType.setId(12);
        blog.setBlogType(blogType);
        blog.setKeyword("GitHub 版本管理");

        // 添加博客文章到数据库中
        blogService.add(blog);
        System.out.println("id => " + blog.getId());

        // 为刚刚添加的博客文章添加索引
        blogIndexService.addIndex(blog);

    }

    // 测试删除特定的索引
    @Test
    public void deleteIndex() throws Exception {
        blogIndexService.deleteIndex("41");
    }

    @Test
    public void updateIndex() throws Exception {

    }

    @Test
    public void searchBlog() throws Exception {

    }

    /**
     * 清空索引
     * @throws Exception
     */
    @Test
    public void deleteAll() throws Exception {
        blogIndexService.deleteAll();
    }


    @Test
    public void rebuildIndex() throws IOException {
        // 删除所有的索引
        blogIndexService.deleteAll();
        // 博文数据重新从数据库里取出来
        List<Blog> blogList = blogService.listAll();
        for(Blog blog:blogList){
            // 针对每篇文章依次创建索引(文章应该去掉 html 标签)
            String originText = blog.getContent();
            String text = HtmlUtil.getTextFromHtml(originText);
            blog.setContentNoTag(text);
            blogIndexService.addIndex(blog);
        }
    }



}