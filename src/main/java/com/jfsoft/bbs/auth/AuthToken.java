package com.jfsoft.bbs.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2018/6/22  15:57
 * @Description TODO(一句话描述类作用)
 */
public class AuthToken implements AuthenticationToken {

    private String token;

    public AuthToken(String token) {
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
