package com.liwei.controller.admin;

import com.liwei.entity.BlogType;
import com.liwei.entity.PageBean;
import com.liwei.service.BlogService;
import com.liwei.service.BlogTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/21.
 */
@RequestMapping("/admin/blogType")
@Controller
public class BlogTypeAdminController {

    private static final Logger logger = LoggerFactory.getLogger(BlogTypeAdminController.class);
    @Autowired
    private BlogTypeService blogTypeService;

    @Autowired
    private BlogService blogService;


    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(
            String page,
            String rows){
        logger.debug(page);
        logger.debug(rows);
        PageBean pageBean = new PageBean();
        pageBean.setPage(Integer.parseInt(page));
        pageBean.setPageSize(Integer.parseInt(rows));

        Map<String,Object> params = new HashMap<>();
        params.put("start",pageBean.getStart());
        params.put("pageSize",pageBean.getPageSize());
        List<BlogType> blogTypeList = blogTypeService.list(params);
        Long total = blogTypeService.getTotal(params);

        Map<String,Object> result = new HashMap<>();
        result.put("total",total);
        result.put("rows",blogTypeList);
        return result;

    }

}
