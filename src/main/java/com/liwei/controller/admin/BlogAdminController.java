package com.liwei.controller.admin;

import com.liwei.entity.Blog;
import com.liwei.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/7.
 */
@RequestMapping("/admin/blog")
@Controller
public class BlogAdminController {

    @Autowired
    private BlogService blogService;

    @ResponseBody
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public Map<String,Object> save(Blog blog){
        int resultTotal = 0;
        // 如果不带 id，就表明是一个新增的方法
        if(blog.getId() == null){
            resultTotal = blogService.add(blog);
        }else{
            // 如果带上 id ，就表明是一个修改方法
        }
        System.out.println("数据改变数量：" + resultTotal);
        Map<String,Object> result = new HashMap<>();
        if(resultTotal > 0 ){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","添加博客出错！");
        }
        return result;
    }
}
