package com.jfsoft.bbs.controller;


import com.jfsoft.bbs.entity.BbsUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2018/3/26  10:16
 * @Description TODO(一句话描述类作用)
 */
public abstract class AbstractController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected BbsUserEntity getUser() {
        return (BbsUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    protected Integer getUserId() {
        return getUser().getId();
    }
}
