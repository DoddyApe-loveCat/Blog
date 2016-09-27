package com.liwei.controller;

import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;
import com.liwei.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Liwei on 2016/8/1.
 * 博主 Controller
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {


    private static final Logger logger = LoggerFactory.getLogger(BloggerController.class);

    @Autowired
    private BloggerService bloggerService;


    /**
     * 关于博主模块
     * @return
     */
    @RequestMapping(value = "/aboutMe")
    public ModelAndView aboutMe(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle","关于博主");

        mav.addObject("mainPage","/foreground/blogger/info.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }


}
