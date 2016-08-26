package com.liwei.controller.admin;

import com.liwei.entity.Blog;
import com.liwei.entity.BlogType;
import com.liwei.entity.Blogger;
import com.liwei.entity.Link;
import com.liwei.service.BlogService;
import com.liwei.service.BlogTypeService;
import com.liwei.service.BloggerService;
import com.liwei.service.LinkService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    /**
     * 刷新缓存
     */
    @ResponseBody
    @RequestMapping("/refreshCache")
    public void refreshCache(HttpServletRequest request){


        // ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();

        ServletContext servletContext = request.getSession().getServletContext();
        // 要拿到 applicationContext ,得使用工具类
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        // 这里有强制类型转换
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        // 这里我们可以看到，我们可以先写接口，以后再去实现都可以
        Blogger blogger = bloggerService.find(); // 获取博主的信息
        // 把敏感信息隐藏
        blogger.setPassword(null);
        servletContext.setAttribute("blogger",blogger);

        // 查询所有的友情链接列表
        LinkService linkService =(LinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.list(null);
        servletContext.setAttribute("linkList",linkList);

        // 根据博客类型分组查询博客数量放入 servletContext 中
        BlogTypeService blogTypeService = (BlogTypeService)applicationContext.getBean("blogTypeService");
        List<BlogType> blogTypeCountList = blogTypeService.countList();
        servletContext.setAttribute("blogTypeCountList",blogTypeCountList);

        // 根据年份月份分组查询博客数量放入 servletContext 中
        BlogService blogService = (BlogService) applicationContext.getBean("blogService");
        List<Blog> blogCountList = blogService.countList();
        servletContext.setAttribute("blogCountList",blogCountList);
    }
}
