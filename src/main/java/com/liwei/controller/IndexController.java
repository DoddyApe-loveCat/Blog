package com.liwei.controller;

import com.liwei.entity.Blog;
import com.liwei.entity.PageBean;
import com.liwei.service.BlogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/4.
 * 请求主页的 Controller
 */
@RequestMapping("/")
@Controller
public class IndexController {


    @Autowired
    private BlogService blogService;

    /**
     * 请求主页
     * @param page
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(value = "page",required = false) String page){
        ModelAndView mav = new ModelAndView();
        // 如果不带请求参数，默认显示第 1 页
        if(StringUtils.isBlank(page)){
            page = "1";
        }
        PageBean pageBean = new PageBean(Integer.valueOf(page),5);
        Map<String,Object> params = new HashMap<>();
        params.put("start",pageBean.getStart());
        params.put("pageSize",pageBean.getPageSize());
        List<Blog> blogList = blogService.list(params);
        mav.addObject("blogList",blogList);
        mav.addObject("paeTitle","Java 开源博客系统");
        mav.addObject("mainPage","foreground/blog/list.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

}

