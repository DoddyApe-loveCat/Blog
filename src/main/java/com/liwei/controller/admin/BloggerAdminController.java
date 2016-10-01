package com.liwei.controller.admin;

import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;

import com.liwei.util.CryptographyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/1.
 * 管理员博主 Controller 层
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    private static final Logger logger = LoggerFactory.getLogger(BloggerAdminController.class);

    @Autowired
    private BloggerService bloggerService;

    @ResponseBody
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Blogger find(String name){
        logger.debug("name => " + name);
        Blogger blogger = bloggerService.find();
        return blogger;
    }


    /**
     * 个人信息页面不修改密码,密码会在专门的页面进行修改
     * 并且个人信息页面应该禁止用户修改用户名,
     * 因为用户名是加密的盐,修改了用户名,等于修改了密码
     * @param blogger
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String,Object> save(Blogger blogger){
        logger.debug("id => " + blogger.getId());
        Integer upateNumber = null;
        if(blogger.getId() != 0){
            upateNumber = bloggerService.update(blogger);
        }
        Map<String,Object> result = new HashMap<>();
        if(upateNumber > 0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","修改博主信息失败！");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/modifyPassword")
    public Map<String,Object> modifyPassword(
            String userName,
            String bloggerId,
            String newPassword){
        logger.debug("bloggerId => " + bloggerId);
        String newPasswordCrypt = CryptographyUtil.md5(newPassword,userName);
        Blogger blogger = new Blogger();
        blogger.setId(Integer.parseInt(bloggerId));
        blogger.setPassword(newPasswordCrypt);
        Integer updateNum = bloggerService.update(blogger);

        Map<String,Object> result = new HashMap<>();
        if(updateNum>0){
            result.put("success",true);
            result.put("successInfo","修改密码成功!");
        }else {
            result.put("success",false);
            result.put("errorInfo","修改密码失败!");
        }
        return result;
    }


}
