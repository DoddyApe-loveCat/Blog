package com.liwei.controller.admin;

import com.liwei.entity.Blog;
import com.liwei.entity.PageBean;
import com.liwei.service.impl.BlogIndexService;
import com.liwei.service.BlogService;
import com.liwei.service.impl.BlogIndexService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by Liwei on 2016/8/7.
 */
@RequestMapping("/admin/blog")
@Controller
public class BlogAdminController {

    private static final Logger logger = LoggerFactory.getLogger(BlogAdminController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogIndexService blogIndexService;

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Map<String,Object> save(Blog blog){

        blog.setReleaseDate(new Date());

        int resultTotal = 0;
        // 如果不带 id，就表明是一个新增的方法
        if(blog.getId() == null){
            resultTotal = blogService.add(blog);

            // 添加索引
            try {
                blogIndexService.addIndex(blog);
            }catch (RuntimeException e){
                e.printStackTrace();
            }

        }else{
            // 如果带上 id ，就表明是一个修改方法
            resultTotal = blogService.update(blog);
            // 更新索引
            // 测试的时候抛出了 org.apache.lucene.store.LockObtainFailedException
            // TODO: 2016/9/16 这样的异常捕获不好，暂时先这样处理，确保能够正常保存文章
            try {
                blogIndexService.updateIndex(blog);
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        }
        Map<String,Object> result = new HashMap<>();
        if(resultTotal > 0 ){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","后台服务出错！");
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(
            String page,
            String rows
    ){
        // easyui 的分页框架默认第一次请求第 1 页，每页 10 条数据
        // 而且请求参数的参数名分别叫 page 和 rows
        logger.debug("请求第几页：" + page);
        logger.debug("请求 pageSize ：" + rows);
        PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> params = new HashMap<>();
        params.put("start",pageBean.getStart());
        params.put("pageSize",pageBean.getPageSize());
        List<Blog> blogList = blogService.list(params);
        Long total = blogService.getTotal(params);

        JSONObject result = new JSONObject();
        // 注册一个配置
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));

        JSONArray jsonArray = JSONArray.fromObject(blogList,jsonConfig);
        result.put("rows",jsonArray);
        result.put("total",total);
        String resultStr = result.toString();
        return resultStr;
    }

    /**
     * 后台管理 ajax 方法，根据 blogId 查询 Blog 实体
     * @param blogId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findById")
    public Blog findById(@RequestParam("id") String blogId){
        return blogService.findByBlogId(Integer.valueOf(blogId));
    }



    @ResponseBody
    @RequestMapping(value="/deleteBlogList")
    public Map<String,Object> deleteBlogList(@RequestParam("ids") String ids){
        logger.debug("前端传来的 id 列表 => " + ids);
        String[] idsStrArr = ids.split(",");
        List<Integer> idList = new ArrayList<>();
        for(String id:idsStrArr){
            idList.add(Integer.valueOf(id));
            // 批量删除索引
            blogIndexService.deleteIndex(id);
        }
        Integer delNum = blogService.deleteBlogList(idList);
        Map<String,Object> result = new HashMap<>();
        if(delNum>0){
            result.put("success",true);
        }else {
            result.put("success",false);
            result.put("errorInfo","服务端出错");
        }
        return result;
    }

}
