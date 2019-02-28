/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsCollectionEntity
 * Author:   Administrator
 * Date:     2019/2/28 16:09
 * Description: 用户收藏帖子
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户收藏帖子〉
 *
 * @author Administrator
 * @create 2019/2/28
 * @since 1.0.0
 */
@TableName("bbs_collection")
public class BbsCollectionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer postId;
    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Date initTime;

    /**
     *
     */
    private Boolean status;

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}