package com.jfsoft.bbs.entity;

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
	private Integer count;
	/**
	 * 签到人ID
	 */
	private Integer userId;

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
	/**
	 * 设置：连续签到次数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：连续签到次数
	 */
	public Integer getCount() {
		return count;
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
