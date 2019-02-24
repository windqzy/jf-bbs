package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 点赞记录表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-24 21:38:33
 */
@TableName("bbs_up")
public class BbsUpEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 评论ID
	 */
	private Integer replyId;
	/**
	 * 点赞人ID
	 */
	private Integer userId;
	/**
	 * 点赞时间
	 */
	private Integer initTime;
	/**
	 * 点赞状态
	 */
	private Boolean status;

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
	 * 设置：评论ID
	 */
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	/**
	 * 获取：评论ID
	 */
	public Integer getReplyId() {
		return replyId;
	}
	/**
	 * 设置：点赞人ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：点赞人ID
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：点赞时间
	 */
	public void setInitTime(Integer initTime) {
		this.initTime = initTime;
	}
	/**
	 * 获取：点赞时间
	 */
	public Integer getInitTime() {
		return initTime;
	}
	/**
	 * 设置：点赞状态
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}
	/**
	 * 获取：点赞状态
	 */
	public Boolean getStatus() {
		return status;
	}
}
