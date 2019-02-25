package com.jfsoft.bbs.auth;


import com.jfsoft.bbs.common.utils.JWTUtils;

import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.service.BbsUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ChenXc
 * @version V1.0
 * @Date 2018/6/22  16:00
 * @Description TODO(一句话描述类作用)
 */
@Service
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private BbsUserService userService;


    /**
     * 大坑！必须重写此方法，不然shiro会报错
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AuthToken;
    }


    /**
     * 授权(验证权限时调用)
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        BbsUserEntity user = (BbsUserEntity) principals.getPrimaryPrincipal();
//        Integer userId = user.getId();
//
//        //用户权限列表
//        Set<String> permsSet = shiroService.getUserPermissions(userId);
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setStringPermissions(permsSet);
        return null;
    }

    /**
     * 认证(登录时调用)
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String accessToken = (String) token.getPrincipal();

        // 根据accessToken，查询用户信息
//        SysUserTokenEntity tokenEntity = shiroService.queryByToken(accessToken);
        // 解密获得username，用户和数据对比
        String userId = JWTUtils.getUserId(accessToken);
//        String areacode = JWTUtils.getUserAreacode(accessToken);
        if (userId == null) {
            throw new IncorrectCredentialsException("token失效，请重新登录");
        }

//        //token失效
//        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
//            throw new IncorrectCredentialsException("token失效，请重新登录");
//        }

        // 查询用户信息
        BbsUserEntity user = userService.selectById(userId);
//        RUser user = userService.queryByUserName(username, areacode);
        //账号锁定
//        if(user.getLocked() == 1){
//            throw new LockedAccountException("账号已被锁定,请联系管理员");
//        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, getName());
        return info;
    }
}
