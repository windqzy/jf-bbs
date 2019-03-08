package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 签到表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@TableName("bbs_sign")
public class BbsSignEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 签到时间
	 */
	private Date initTime;
	/**
	 * 连续签到次数
	 */
	private Integer signCount;
	/**
	 * 签到人ID
	 */
	private Integer userId;

	@TableField(exist = false)
	private String username;

	@TableField(exist = false)
	private String icon;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	 * 设置：签到时间
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	/**
	 * 获取：签到时间
	 */
	public Date getInitTime() {
		return initTime;
	}

	public Integer getSignCount() {
		return signCount;
	}

	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}

	/**
	 * 设置：签到人ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：签到人ID
	 */
	public Integer getUserId() {
		return userId;
	}
}
