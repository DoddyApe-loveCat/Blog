package com.liwei.controller;

import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;
import com.liwei.util.CryptographyUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Liwei on 2016/8/1.
 * 博主 Controller
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @RequestMapping("/login")
    public String login(Blogger blogger, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(blogger.getUserName(),
                CryptographyUtil.md5(blogger.getPassword(),"liwei"));
        // 可能抛出运行时异常，须要捕获
        try{
            // Shiro 发挥了作用
            subject.login(token);
            return "redirect:/admin/main.jsp";
        }catch (Exception e){
            // TODO: 2016/8/1 应该记录日志
            e.printStackTrace();
            request.setAttribute("blogger",blogger);
            request.setAttribute("error","用户名或者密码错误！");
            return "login";
        }


    }

}
