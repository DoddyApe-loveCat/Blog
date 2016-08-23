package com.liwei.controller;

import com.liwei.entity.Blog;
import com.liwei.entity.PageBean;
import com.liwei.service.BlogService;
import com.liwei.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liwei on 2016/8/4.
 * 请求主页的 Controller
 */
@RequestMapping("/")
@Controller
@PropertySource("classpath:config/config.properties")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private BlogService blogService;

    /**
     * todo 这里应该设置在配置文件中
     */
    @Value("#{configProperties['indexPageSize']}")
    private Integer pageSize;


    @Value("#{configProperties['birthDay']}")
    private String birthDay;

    @Value("#{configProperties['startLearnJavaDay']}")
    private String startLearnJavaDay;

    /**
     * 请求主页
     * @param page
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(value = "page",required = false) String page,
            @RequestParam(value = "typeId",required = false)String typeId,
            @RequestParam(value = "releaseDateStr",required = false)String releaseDateStr,
            HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        // 如果不带请求参数，默认显示第 1 页
        if(StringUtils.isBlank(page)){
            page = "1";
        }
        logger.debug("pageSize => " + pageSize);
        PageBean pageBean = new PageBean(Integer.valueOf(page),pageSize);
        Map<String,Object> params = new HashMap<>();
        params.put("start",pageBean.getStart());
        params.put("pageSize",pageBean.getPageSize());

        StringBuffer paramstr = new StringBuffer();
        if(!StringUtils.isBlank(typeId)){
            params.put("type_id",typeId);
            paramstr.append("&").append("typeId=").append(typeId);
        }
        if(!StringUtils.isBlank(releaseDateStr)){
            params.put("releaseDateStr",releaseDateStr);
            paramstr.append("&").append("releaseDateStr=").append(releaseDateStr);
        }

        List<Blog> blogList = blogService.list(params);
        Long totalNum = blogService.getTotal(params);
        mav.addObject("blogList",blogList);
        logger.debug("一共多少数据 totalNum ：" + totalNum);
        logger.debug("当前请求第几页数据 page ：" + page);
        logger.debug("每页多少条数据 pageSize：" + pageSize);


        //  TODO: 2016/8/23
        Long passedDayNumBirth = getPassedDayNum(birthDay);
        Long passedDayNumJava = getPassedDayNum(startLearnJavaDay);

        request.getSession().getServletContext().setAttribute("passedDayNumBirth",passedDayNumBirth);
        request.getSession().getServletContext().setAttribute("passedDayNumJava",passedDayNumJava);

        mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath() + "/index.html",totalNum,Integer.parseInt(page),pageSize,paramstr.toString()));
        mav.addObject("pageTitle","Java 开源博客系统");
        mav.addObject("mainPage","foreground/blog/list.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

    private Long getPassedDayNum(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date learnJavaStartDate = sdf.parse(dateStr);
            Long learnJavaStart = learnJavaStartDate.getTime();
            Long passed = System.currentTimeMillis() - learnJavaStart;
            Long passedDayNum = passed / 24 / 60 / 60 / 1000;
            return passedDayNum;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 本站源码下载
     * @return
     */
    @RequestMapping(value = "/srcDownload")
    public ModelAndView srcDownload(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("pageTitle","本站源码下载");
        mav.addObject("mainPage","foreground/system/download.jsp");
        mav.setViewName("mainTemp");
        return mav;
    }

}

