package com.liwei.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liwei.entity.Comment;
import com.liwei.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/5.
 */
@RequestMapping("/comment")
@Controller
public class CommentController {


    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 保存用户提交的评论
     * @param comment
     * @param imageCode
     * @param request
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/save")
    public Map<String,Object> save(Comment comment,
                       @RequestParam("imageCode")String imageCode,
                       HttpServletRequest request,
                       HttpSession session){
        logger.debug("用户在表单中填写的验证码：" + imageCode);
        logger.debug("Comment 属性 content => " + comment.getContent());
        logger.debug("Comment 属性 blog 级联属性 id => " + comment.getBlog().getId());

        /**
         * 存放在 Session 中的用户看到的验证码
         */
        String sRand = (String) session.getAttribute("sRand");

        Map<String,Object> map = new HashMap<>();

        // 如果用户填写验证码出错
        if(!imageCode.equals(sRand)){
            map.put("success",false);
            map.put("errorInfo","验证码填写错误");
        }else{
            // String userIp = request.getRemoteAddr();
            String userIp = request.getRemoteHost();
            comment.setUserIp(userIp);
            comment.setState(1);
            comment.setCommentDate(new Date());
            commentService.add(comment);
            map.put("success",true);
        }
        return map;
    }

}
