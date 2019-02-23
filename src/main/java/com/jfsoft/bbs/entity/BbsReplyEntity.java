package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
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
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Date initTime;
	/**
	 * 
	 */
	private Integer up;
	/**
	 * 
	 */
	private Integer postsId;
	/**
	 * 
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
	private Boolean accept;

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
}
