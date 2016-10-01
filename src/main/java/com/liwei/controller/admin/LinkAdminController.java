package com.liwei.controller.admin;

import com.liwei.entity.Link;
import com.liwei.entity.PageBean;
import com.liwei.service.LinkService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    /**
     * 友情链接查询,使用 JQuery EasyUI 组件
     * @param page
     * @param rows
     * @return
     */
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

    /**
     * 保存和更新使用同一个入口
     * @param link
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Map<String,Object> save(Link link){
        Integer updateNum = 0;
        if(link.getId() == null){
            updateNum = linkService.add(link);
        }else {
            updateNum = linkService.update(link);
        }
        logger.debug("updateNum => " + updateNum);
        Map<String,Object> result = new HashMap<>();
        if(updateNum > 0){
            result.put("success",true);
            result.put("successInfo","提交成功！");
        }else {
            result.put("success",false);
            result.put("errorInfo","提交失败！");
        }
        return result;
    }

    /**
     * 批量删除友情链接
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Map<String,Object> delete(String ids){
        String[] idStrArr = ids.split(",");
        Integer len = idStrArr.length;
        Integer updateNum = 0;
        if(len == 1){
            updateNum = linkService.delete(Integer.parseInt(idStrArr[0]));
        }else {
            List<Integer> idList = new ArrayList<>();
            for(String id:idStrArr){
                idList.add(Integer.parseInt(id));
            }
            updateNum = linkService.batchDelete(idList);
        }
        Map<String,Object> result = new HashMap<>();
        if(updateNum > 0){
            result.put("success",true);
            result.put("successInfo","成功删除了 " + updateNum + " 条数据！");
        }else {
            result.put("success",false);
            result.put("errorInfo","删除失败！");
        }
        return result;
    }
}
