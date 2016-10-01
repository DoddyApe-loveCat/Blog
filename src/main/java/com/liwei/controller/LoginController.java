package com.liwei.controller;

import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liwei on 16/9/27.
 * 与登录、退出登录相关的逻辑
 */
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(BloggerController.class);
    @Autowired
    private BloggerService bloggerService;

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 登录逻辑
     * @param blogger
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Blogger blogger, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();

        String userName = blogger.getUserName();
        String password = blogger.getPassword();
        logger.debug("用户在表单中填写的用户名 => " + userName);
        logger.debug("用户在表单中填写的密码 => " + password);
        // UsernamePasswordToken 就应该传递用户从表单中传来的用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        String msg = null;
        try{
            // Shiro 发挥了作用
            subject.login(token);
            // 重定向到一个 jsp 页面
            return "redirect:/admin/main.html";
        }catch (UnknownAccountException e){
            e.printStackTrace();
            msg = "用户名或者密码错误";
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            msg = "用户名或者密码错误";
        }
        request.setAttribute("blogger",blogger);
        request.setAttribute("msg",msg);
        return "login";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        Object pc = subject.getPrincipal();
        if(pc!=null){
            String userName = pc.toString();
            logger.debug("用户 " + userName + " 退出登录。");
            subject.logout();
        }
        return "login";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/admin/main",method = RequestMethod.GET)
    public String goToAdminIndex(){
        return "admin/main";
    }
}
