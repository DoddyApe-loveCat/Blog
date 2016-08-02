package com.liwei.service.impl;

import com.liwei.entity.Blogger;
import com.liwei.entity.Link;
import com.liwei.service.BloggerService;
import com.liwei.service.LinkService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * Created by Liwei on 2016/8/2.
 * todo 标注一下这几个接口的意义和使用场景
 */
@Component
public class InitComponent implements ServletContextListener,ApplicationContextAware {

    /**
     * 声明成 static 特别重要，否则获取不到
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 把博主信息和友情链接列表这两个类放在这个域对象中，这个频繁的数据没有必要每次都向服务器请求
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        // 这里有强制类型转换
        BloggerService bloggerService = (BloggerService) applicationContext.getBean("bloggerService");
        // 这里我们可以看到，我们可以先写接口，以后再去实现都可以
        Blogger blogger = bloggerService.find(); // 获取博主的信息
        // 把敏感信息隐藏
        blogger.setPassword(null);
        servletContext.setAttribute("blogger",blogger);

        LinkService linkService =(LinkService) applicationContext.getBean("linkService");
        List<Link> linkList = linkService.list(null);// 查询所有的友情链接列表
        servletContext.setAttribute("linkList",linkList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
