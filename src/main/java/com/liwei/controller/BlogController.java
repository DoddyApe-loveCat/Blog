package com.liwei.controller;

import com.liwei.entity.Blog;
import com.liwei.entity.Comment;
import com.liwei.service.BlogService;
import com.liwei.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/4.
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    /**
     * 请求博客具体信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/articles/{id}")
    public ModelAndView details(@PathVariable(value = "id") Integer id, HttpServletRequest request){
        Blog blog = blogService.findByBlogId(id);
        ModelAndView mav = new ModelAndView();
        // 增加点击量
        Integer clickHit = blog.getClickHit();
        blog.setClickHit(++clickHit);
        blogService.update(blog);
        mav.addObject("blog",blog);

        // 获取上一页和下一页的文章链接文本
        String contentPath = request.getContextPath();
        String pageCode = getPreviousAndNextLink(id,contentPath);
        mav.addObject("pageCode",pageCode);

        // 获取评论列表
        Map<String,Object> params = new HashMap<>();
        params.put("blogId",blog.getId());
        params.put("state","1");
        List<Comment> commentList = commentService.list(params);
        mav.addObject("commentList",commentList);

        // 设置标题
        mav.addObject("pageTitle",blog.getTitle() + " 博文内容");
        mav.addObject("mainPage","foreground/blog/view.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

    /**
     * 根据当前文章的 id 获取上一篇文章和下一篇的链接
     * @param currentArticleId
     * @return
     *
     * <p>上一篇：<a href="${pageContext.request.contextPath}/articles/{id}"></a></p>
    <br>
    <p>下一篇：<a href="${pageContext.request.contextPath}/articles/{id}"></a></p>
     */
    private String getPreviousAndNextLink(Integer currentArticleId,String contentPath) {
        Blog previousBlog = blogService.getPrevious(currentArticleId);
        Blog nextBlog = blogService.getNext(currentArticleId);
        StringBuffer pageCode = new StringBuffer();
        pageCode.append("<p>上一篇：");
        if(previousBlog==null){
            pageCode.append("没有了");
        }else {
            pageCode.append("<a href=\"" + contentPath + "/blog/articles/" + previousBlog.getId()  +".html\">" + previousBlog.getTitle() + "</a>");
        }
        pageCode.append("</p><p>下一篇：");
        if(nextBlog==null){
            pageCode.append("没有了");
        }else {
            pageCode.append("<a href=\"" + contentPath + "/blog/articles/" + nextBlog.getId()  +".html\">" + nextBlog.getTitle() + "</a>");
        }
        pageCode.append("</p>");
        return pageCode.toString();
    }
}
