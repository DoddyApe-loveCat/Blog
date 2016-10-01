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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
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

    /**
     * 获取博主个人信息
     * @param name
     * @return
     */
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
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Map<String,Object> save(
            @RequestParam("imageFile")  MultipartFile imageFile,
            Blogger blogger,
            HttpServletRequest request){
        if(!imageFile.isEmpty()){
            String serverBasePath = request.getSession().getServletContext().getRealPath("/");
            logger.debug("basePath => "  + serverBasePath);
            String originFileName = imageFile.getOriginalFilename();
            logger.debug("originFileName => " + originFileName);
            String imageName = System.currentTimeMillis() + "." + originFileName.split("\\.")[1];
            logger.debug("imageName => " + imageName);
            String baseFolder = File.separator + "static"
                    + File.separator + "userImages" + File.separator;
            File folder = new File(serverBasePath + baseFolder);
            String[] files = folder.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return !name.startsWith("liwei");
                }
            });
            for(String file:files){
                new File(serverBasePath + baseFolder + file).delete();
            }
            String realFilePath = baseFolder + imageName;
            logger.debug(realFilePath);
            try {
                // 完成了文件上传
                imageFile.transferTo(new File(serverBasePath + realFilePath));
                blogger.setImageName(realFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * 修改密码
     * @param userName 用户名这里要作为加密的盐值
     * @param bloggerId 和 userName 冗余
     * @param newPassword 新设置的密码
     * @return
     */
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
