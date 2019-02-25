package com.jfsoft.bbs.auth;


import com.alibaba.fastjson.JSON;
import com.jfsoft.bbs.common.utils.HttpContextUtil;
import com.jfsoft.bbs.common.utils.R;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2018/6/22  15:39
 * @Description TODO(一句话描述类作用)
 */
public class ShiroFilter extends BasicHttpAuthenticationFilter {

    //    @Override
//    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
//        //获取请求token
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader("Authorization");
//
//
//        if(StringUtils.isBlank(token)){
//            return null;
//        }
//
//        return new OAuth2Token(token);
//    }

    /**
     * 判断用户是否想要登入
     * 检测header里面是否包含Authorization字段即可
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        // 获取请求token
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("B-Token");
        return authorization != null;
    }

    /**
     * 执行登录
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader("B-Token");
        AuthToken token = new AuthToken(authorization);
        // 提交给realm进行登入，如果错误会抛出异常并被捕获
        getSubject(request, response).login(token);
        // 如果没有抛出异常则代表登录成功，返回true
        return true;
    }

    /**
     * 判断请求是否被允许
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }

    /**
     * 未携带token
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //获取请求token，如果token不存在，直接返回401
        String authorization = httpServletRequest.getHeader("B-Token");
        if (StringUtils.isBlank(authorization)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());

            String json = JSON.toJSONString(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
            httpResponse.getWriter().print(json);

            return false;
        }
        return executeLogin(request, response);
    }

    /**
     * 登录失败
     *
     * @param token
     * @param e
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            R r = R.error(HttpStatus.SC_UNAUTHORIZED, throwable.getMessage());

            String json = JSON.toJSONString(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {

        }
        return false;
    }
}
