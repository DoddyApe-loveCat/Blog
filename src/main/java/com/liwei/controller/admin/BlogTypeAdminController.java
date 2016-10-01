package com.liwei.controller.admin;

import com.liwei.entity.BlogType;
import com.liwei.entity.PageBean;
import com.liwei.service.BlogService;
import com.liwei.service.BlogTypeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    /**
     * 使用 easyui 分页组件,注意接收参数和返回数据格式
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(
            String page,
            String rows){
        logger.debug("easyui 分页组件传来 page => " + page);
        logger.debug("easyui 分页组件传来 rows => " + rows);
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

    /**
     * 增加和修改使用同一个方法,
     * 如果请求参数 id 为 null ,则表示方法为添加
     * @param id
     * @param typeName
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Map<String,Object> add(
            @RequestParam(value = "id",required = false) String id,
            @RequestParam(value = "typeName",required = true) String typeName,
            @RequestParam(value = "orderNo",required = true) String orderNo,
            HttpServletRequest request){
        logger.debug("id => " + id);
        logger.debug("typeName => " + typeName);
        logger.debug("orderNo => " + orderNo);
        BlogType blogType = new BlogType();
        blogType.setTypeName(typeName);
        blogType.setOrderNo(Integer.parseInt(orderNo));
        Integer updateNum = 0;
        if(StringUtils.isBlank(id)){
            updateNum =blogTypeService.add(blogType);
        }else {
            blogType.setId(Integer.parseInt(id));
            updateNum = blogTypeService.update(blogType);
        }
        Map<String,Object> result = new HashMap<>();
        if(updateNum>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","添加失败。");
        }
        return result;
    }

    /**
     * 批量删除博客类型
     * 这里要注意当博客中还有的文章属于待删除的博客类型的情况
     * @param idList
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public Map<String,Object> delete(
            @RequestParam("deleteIds[]") List<Integer> idList,
            HttpServletRequest request){
        Integer deleteNum = 0;
        try{
            // 业务上处理可以先查询,查询有的文章还在待删除的博客类型下,就提示用户不能删除
            deleteNum = blogTypeService.deleteList(idList);
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
        }
        Map<String,Object> result = new HashMap<>();
        if(deleteNum > 0){
            result.put("success",true);
            logger.debug("成为删除了 " + deleteNum + "条记录。");
        }else {
            result.put("success",false);
            result.put("errorInfo","您选择的数据类型下还有文章,请检查。");
        }
        return result;
    }

    /**
     * 查询所有的博客类型信息(不带统计信息)
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<BlogType> findAll(){
        List<BlogType> blogTypeList = blogTypeService.findAll();
        return blogTypeList;
    }
}
