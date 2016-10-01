package com.liwei.controller.admin;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import com.liwei.entity.Blogger;
import com.liwei.entity.Link;
import com.liwei.service.BlogService;
import com.liwei.service.BlogTypeService;
import com.liwei.service.BloggerService;
import com.liwei.service.LinkService;
import com.liwei.service.impl.BlogIndexService;
import com.liwei.util.HtmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liwei on 16/8/25.
 */
@RequestMapping("/admin/system")
@Controller
public class SystemAdminController {
    @Autowired
    private BloggerService bloggerService;
    @Autowired
    private LinkService linkService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogTypeService blogTypeService;
    @Autowired
    private BlogIndexService blogIndexService;

    /**
     * 刷新缓存
     */
    @ResponseBody
    @RequestMapping(value = "/refreshCache",method = RequestMethod.GET)
    public void refreshCache(HttpServletRequest request){
        // ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
        ServletContext servletContext = request.getSession().getServletContext();
        // 要拿到 applicationContext ,得使用工具类
        // ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        // 这里我们可以看到，我们可以先写接口，以后再去实现都可以
        Blogger blogger = bloggerService.find(); // 获取博主的信息
        // 把敏感信息隐藏
        blogger.setPassword(null);
        servletContext.setAttribute("blogger",blogger);

        // 查询所有的友情链接列表
        List<Link> linkList = linkService.list(null);
        servletContext.setAttribute("linkList",linkList);

        // 根据博客类型分组查询博客数量放入 servletContext 中
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        servletContext.setAttribute("blogTypeCountList",blogTypeCountList);

        // 根据年份月份分组查询博客数量放入 servletContext 中
        List<Blog> blogCountList = blogService.countList();
        servletContext.setAttribute("blogCountList",blogCountList);
    }

    @ResponseBody
    @RequestMapping(value = "refreshBlogIndex",method = RequestMethod.GET)
    public void refreshBlogIndex() throws Exception{
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
