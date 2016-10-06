package com.liwei.shiro.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by liwei on 16/10/2.
 */

public class RememberAuthenticationFilter extends FormAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(RememberAuthenticationFilter.class);

    /**
     * 这个方法决定是是否让用户登录
     * @param request
     * @param response
     * @param mappedValue 表示括号里面的那个字符串,是一个 String 类型的数组
     * @return 返回 true ,表示验证通过, 返回 false 表示拦截成功,按照配置跳转到一个登陆页面
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request,response);
        boolean isAuthenticated = subject.isAuthenticated();
        boolean isRemembered = subject.isRemembered();
        // 如果 isAuthenticated 为 false 证明不是登录过的，
        // 同时 isRememberd 为 true 证明是没登陆直接通过记住我功能进来的
        // 特别注意 isAuthenticated 和 isRememberd 是互斥的
        // 即其中一个为真,另一个就为假,要么就全部为假,不可能同时为真
        if(!isAuthenticated && isRemembered){
            Session session = subject.getSession(true);
            String userName = (String)session.getAttribute("user");
            if(userName==null){
                subject.logout();
                return false;
            }
        }
        // return super.isAccessAllowed(request, response, mappedValue);
        isAuthenticated = subject.isAuthenticated();
        isRemembered = subject.isRemembered();
        return isAuthenticated || isRemembered;
    }
}