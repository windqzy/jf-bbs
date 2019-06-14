package com.jfsoft.bbs.form;

import com.jfsoft.bbs.entity.BbsUserEntity;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ReplayVo
 * @Description 评论实体类
 * @Author Lxd
 * @Date 2019/6/14 14:35
 * @Version 1.0
 **/
public class ReplayVo {

    /**
     * 评论主键
     */
    private Integer id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date initTime;

    /**
     * 是否含有子评论
     */
    private Integer parentId;

    /**
     * 帖子ID
     */
    private Integer postsId;

    /**
     * 作者ID
     */
    private Integer userId;

    /**
     * 点赞数量
     */
    private Integer upCount;

    /**
     * 作者姓名
     */
    private String author;

    /**
     * 该评论下回复信息
     */
    private List<ReplayVo> replayVoList;

    /**
     * 被回复人信息
     */
    private BbsUserEntity replyToUser;

    private BbsUserEntity replyUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<ReplayVo> getReplayVoList() {
        return replayVoList;
    }

    public void setReplayVoList(List<ReplayVo> replayVoList) {
        this.replayVoList = replayVoList;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }

    public BbsUserEntity getReplyToUser() {
        return replyToUser;
    }

    public void setReplyToUser(BbsUserEntity replyToUser) {
        this.replyToUser = replyToUser;
    }

    public BbsUserEntity getReplyUser() {
        return replyUser;
    }

    public void setReplyUser(BbsUserEntity replyUser) {
        this.replyUser = replyUser;
    }

    public Integer getUpCount() {
        return upCount;
    }

    public void setUpCount(Integer upCount) {
        this.upCount = upCount;
    }
}
