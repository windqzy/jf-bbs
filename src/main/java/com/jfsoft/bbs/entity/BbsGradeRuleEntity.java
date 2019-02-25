package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分规则表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@TableName("bbs_grade_rule")
public class BbsGradeRuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 大于 gt
小于 lt
大于等于 ge
小于等于 le
	 */
	private String expression;
	/**
	 * 天数
	 */
	private Integer days;
	/**
	 * 积分
	 */
	private Integer grade;
	/**
	 * 创建时间
	 */
	private Date initTime;

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
	 * 设置：大于 gt
小于 lt
大于等于 ge
小于等于 le
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
	/**
	 * 获取：大于 gt
小于 lt
大于等于 ge
小于等于 le
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * 设置：天数
	 */
	public void setDays(Integer days) {
		this.days = days;
	}
	/**
	 * 获取：天数
	 */
	public Integer getDays() {
		return days;
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
	 * 设置：创建时间
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getInitTime() {
		return initTime;
	}
}
