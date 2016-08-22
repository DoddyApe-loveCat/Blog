package com.liwei.controller.admin;

import com.liwei.entity.Link;
import com.liwei.entity.PageBean;
import com.liwei.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/22.
 */
@RequestMapping("/admin/link")
@Controller
public class LinkAdminController {

    private static final Logger logger = LoggerFactory.getLogger(LinkAdminController.class);

    @Autowired
    private LinkService linkService;

    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Map<String,Object> list(
            String page,
            String rows
    ){
        logger.debug("start => " + page);
        logger.debug("rows => " + rows);
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> params = new HashMap<>();
        params.put("start",pageBean.getStart());
        params.put("pageSize",pageBean.getPageSize());
        List<Link> linkList = linkService.list(params);
        Long total = linkService.getTotal(params);
        Map<String,Object> result = new HashMap<>();
        result.put("total",total);
        result.put("rows",linkList);
        return result;

    }

}
