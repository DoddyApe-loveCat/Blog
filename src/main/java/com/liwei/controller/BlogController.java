package com.liwei.controller;

import com.liwei.entity.Blog;
import com.liwei.entity.Comment;
import com.liwei.lucene.BlogIndex;
import com.liwei.service.BlogService;
import com.liwei.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/4.
 */
@Controller
@RequestMapping("/blog")
@PropertySource("classpath:config/config.properties")
public class BlogController {


    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    @Value("#{configProperties['searchPageSize']}")
    private Integer searchPageSize;

    private BlogIndex blogIndex = new BlogIndex();


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
        params.put("blog",blog);
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

    /**
     * 根据查询关键字查询搜索结果
     * 【重要方法】
     * @param q
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(value = "/q",method = RequestMethod.GET)
    public ModelAndView search(
            @RequestParam(value = "q",required = false) String q,
            @RequestParam(value = "page",required = false) String page,
            HttpServletRequest request
    ){

        // 现在每页显示 10 条记录
        Integer pageSize = searchPageSize;
        // 请求第几页数据
        page = StringUtils.isBlank(page) ? "1":page;
        Integer pageInt = Integer.parseInt(page);

        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle","关键字 " + q + " 的搜索结果");
        mav.addObject("mainPage","foreground/blog/searchResult.jsp");
        List<Blog> blogList = blogIndex.searchBlog(q);
        Integer totalNum = blogList.size();

        // 不应该将全部的查询结果显示到页面上
        // 可以使用 List 对象的 subList 方法截取须要的搜索结果
        // 但是底层还是每次全部都查询
        // 采用这种搜索策略，主要是因为一般我们的搜索结果总是会关注在前面几页
        // 很少会关注后面几页的搜索结果，所以采用这种查询比较符合业务需求

        // 截取 List 长度的开始索引
        Integer start = (pageInt-1)*pageSize;
        // 截取 List 长度的结束索引
        Integer end = 0;
        // 总页数
        Integer totalPage = totalNum % pageSize == 0 ? totalNum/pageSize: totalNum/pageSize + 1;

        if(pageInt < totalPage){
            // 如果请求页数小于总页数
            end = pageInt * pageSize;
        }else {
            end = totalNum;
        }

        mav.addObject("blogList",blogList.subList(start,end));
        mav.addObject("q",q);
        mav.addObject("resultTotal",totalNum);

        String contextPath = request.getContextPath();
        logger.debug("contextPath => " + contextPath);


        String pageCode = getPreviousAndNextLink(Integer.parseInt(page),pageSize,totalNum,q,request.getContextPath());
        mav.addObject("pageCode",pageCode);

        mav.setViewName("mainTemp");
        return mav;
    }


    /**
     * 获得搜索结果页的上一页、下一页的链接代码
     * @param page 当前第几页
     * @param pageSize 每页几条数据
     * @param totalNum 总记录数
     * @param q 查询关键字
     * @param projectContext 请求链接上下文
     * @return
     *
     *
     */
    private String getPreviousAndNextLink(Integer page,Integer pageSize,Integer totalNum,String q,String projectContext){
        /*<nav>
        <ul class="pager">
        <li><a href="#">Previous</a></li>
        <li><a href="#">Next</a></li>
        </ul>
        </nav>*/

        StringBuffer sb = new StringBuffer();
        sb.append("<nav>");
        sb.append("<ul class=\"pager\">");



        Integer totalPage = totalNum % pageSize == 0 ? totalNum/pageSize: totalNum/pageSize + 1;
        if(page == totalPage){

            if(page == 1){
                // 请求页数和总页数相同，说明只有一页，此时 上一页按钮 和 下一页按钮都禁用
                sb.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
                sb.append("<li class='next disabled'><a href='#'>下一页</a></li>");

            }else {
                // 请求页数和总页数相同，请求页数不是第 1 页，那就是请求最后一页，所以下一页禁用
                sb.append("<li class='previous'><a href='" + projectContext + "/blog/q.html?q=" + q + "&page=" + (page-1) +"'>上一页</a></li>");
                sb.append("<li class='next disabled'><a href='#'>下一页</a></li>");
            }

        }
        if(page < totalPage){
            if(page == 1){
                // 请求页数小于总页数，并且请求页数等于 1 ，说明上一页禁用
                sb.append("<li class='previous disabled'><a href='#'>上一页</a></li>");
                sb.append("<li class='next'><a href='" + projectContext + "/blog/q.html?q=" + q + "&page=" + (page+1) + "'>下一页</a></li>");
            }else {
                // 请求页数小于总页数，上一页和下一页都不禁用
                sb.append("<li class='previous'><a href='" + projectContext + "/blog/q.html?q=" + q + "&page=" + (page-1) +"'>上一页</a></li>");
                sb.append("<li class='next'><a href='" + projectContext + "/blog/q.html?q=" + q + "&page=" + (page+1) + "'>下一页</a></li>");

            }
        }
        return sb.toString().replaceAll("previous","").replaceAll("next","");
    }
}
