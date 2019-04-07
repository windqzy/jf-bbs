package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户积分表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@TableName("bbs_grade")
public class BbsGradeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 记录时间
	 */
	private Date initTime;
	/**
	 * 积分
	 */
	private Integer grade;
	/**
	 * 用户ID
	 */
	private Integer userId;

	private String unionId;

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
	 * 设置：记录时间
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	/**
	 * 获取：记录时间
	 */
	public Date getInitTime() {
		return initTime;
	}
	/**
	 * 设置：积分
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	/**
	 * 获取：积分
	 */
	public Integer getGrade() {
		return grade;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
}
