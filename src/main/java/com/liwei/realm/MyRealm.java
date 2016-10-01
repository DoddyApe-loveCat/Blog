package com.liwei.realm;

import com.liwei.entity.Blogger;
import com.liwei.service.BloggerService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Liwei on 2016/8/1.
 *
 * 自定义的 Realm
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private BloggerService bloggerService;

    /**
     * 为当前登录的用户授权角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 验证当前登录的用户
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String)token.getPrincipal();
        Blogger blogger = bloggerService.getByUserName(userName);
        if(blogger!=null){
            // 验证信息
            // 应该用 token 的密码去做验证，马上试试，只要用户名对就可以登录
            // 这里相当于没有验证密码一样
            SimpleAuthenticationInfo authcInfo = new SimpleAuthenticationInfo(blogger.getUserName(),blogger.getPassword(),"xxx");
            authcInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
            // Session 管理
            SecurityUtils.getSubject().getSession().setAttribute("currentUser",blogger);
            return authcInfo;
        }else {
            return null;
        }
    }
}
