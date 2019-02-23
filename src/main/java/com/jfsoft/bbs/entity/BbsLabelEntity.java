package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
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
}
