package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 21:02:32
 */
@TableName("bbs_label")
public class BbsLabelEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private String details;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private Integer postsCount;

    /**
     *
     */
    private Boolean postManage;

    /**
     *
     */
    private int parentId;

    private String icon;


    @TableField(exist = false)
    private List<BbsLabelEntity> children;

    /**
     * 最新回复时间
     */
    @TableField(exist = false)
    private Date lastReplyTime;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * 获取：
     */
    public String getDetails() {
        return details;
    }

    /**
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：
     */
    public void setPostsCount(Integer postsCount) {
        this.postsCount = postsCount;
    }

    /**
     * 获取：
     */
    public Integer getPostsCount() {
        return postsCount;
    }

    public Boolean getPostManage() {
        return postManage;
    }

    public void setPostManage(Boolean postManage) {
        this.postManage = postManage;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<BbsLabelEntity> getChildren() {
        return children;
    }

    public void setChildren(List<BbsLabelEntity> children) {
        this.children = children;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }
}
