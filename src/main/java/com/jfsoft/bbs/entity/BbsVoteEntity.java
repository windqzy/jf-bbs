/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsVoteEntity
 * Author:   Administrator
 * Date:     2019/3/7 11:48
 * Description:
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
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/7
 * @since 1.0.0
 */
@TableName("bbs_vote")
public class BbsVoteEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;

    private Date initTime;

    private String voteTopic;

    private Integer postId;

    private Boolean voteVisible;

    private Boolean voteStatus;

    private Date startTime;

    private Date endTime;

    private Boolean multiple;

    private Integer maxSel;

    private Integer minSel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public String getVoteTopic() {
        return voteTopic;
    }

    public void setVoteTopic(String voteTopic) {
        this.voteTopic = voteTopic;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Boolean getVoteVisible() {
        return voteVisible;
    }

    public void setVoteVisible(Boolean voteVisible) {
        this.voteVisible = voteVisible;
    }

    public Boolean getVoteStatus() {
        return voteStatus;
    }

    public void setVoteStatus(Boolean voteStatus) {
        this.voteStatus = voteStatus;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getMultiple() {
        return multiple;
    }

    public void setMultiple(Boolean multiple) {
        this.multiple = multiple;
    }

    public Integer getMaxSel() {
        return maxSel;
    }

    public void setMaxSel(Integer maxSel) {
        this.maxSel = maxSel;
    }

    public Integer getMinSel() {
        return minSel;
    }

    public void setMinSel(Integer minSel) {
        this.minSel = minSel;
    }
}