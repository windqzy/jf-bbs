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
 * @date 2019-02-23 18:10:32
 */
@TableName("bbs_reply")
public class BbsReplyEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *内容
     */
    private String content;
    /**
     *
     */
    private Date initTime;
    /**
     *置顶
     */
    private Integer up;
    /**
     *帖子ID
     */
    private Integer postsId;
    /**
     *评论用户ID
     */
    private Integer userId;

    /**
     *
     */
    @TableField(exist = false)
    private String author;
    /**
     *
     */
    @TableField(exist = false)
    private String icon;

    /**
     * 点赞
     */
    @TableField(exist = false)
    private Boolean status;

    @TableField(exist = false)
    private Integer replyCount;

    @TableField(exist = false)
    private String postsName;

    /**
     *
     */
    private Boolean accept;

    /**
     *评论ID主键
     */
    private Integer parentId;

    /**
     * 被回复人ID
     */
    private Integer replyTo;

    @TableField(exist = false)
    private List<BbsReplyEntity> replyList;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<BbsReplyEntity> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<BbsReplyEntity> replyList) {
        this.replyList = replyList;
    }

    public String getPostsName() {
        return postsName;
    }

    public void setPostsName(String postsName) {
        this.postsName = postsName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getAccept() {
        return accept;
    }

    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

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
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：
     */
    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    /**
     * 获取：
     */
    public Date getInitTime() {
        return initTime;
    }

    /**
     * 设置：
     */
    public void setUp(Integer up) {
        this.up = up;
    }

    /**
     * 获取：
     */
    public Integer getUp() {
        return up;
    }

    /**
     * 设置：
     */
    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    /**
     * 获取：
     */
    public Integer getPostsId() {
        return postsId;
    }

    /**
     * 设置：
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     */
    public Integer getUserId() {
        return userId;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }
}
