package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@TableName("bbs_posts")
public class BbsPostsEntity implements Serializable {
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
	private Boolean good;
	/**
	 * 
	 */
	private Date initTime;
	/**
	 * 
	 */
	private Integer labelId;
	/**
	 * 
	 */
	private Integer replyCount;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private Boolean top;
	/**
	 * 
	 */
	private Integer userId;

	/**
	 *
	 */
	private Boolean end;
	/**
	 *
	 */
	private Integer rewardGrade;
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

	@TableField(exist = false)
	private String labelName;

	@TableField(exist = false)
	private Integer collectionStatus;

	@TableField(exist = false)
	private Date collectionTime;

	@TableField(exist = false)
	private Date replytime;

	private Boolean isVote;

	public Boolean getVote() {
		return isVote;
	}

	public void setVote(Boolean vote) {
		isVote = vote;
	}

	public Date getReplytime() {
		return replytime;
	}

	public void setReplytime(Date replytime) {
		this.replytime = replytime;
	}

	private Boolean isDel;

	public Boolean getDel() {
		return isDel;
	}

	public void setDel(Boolean del) {
		isDel = del;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	public Integer getCollectionStatus() {
		return collectionStatus;
	}

	public void setCollectionStatus(Integer collectionStatus) {
		this.collectionStatus = collectionStatus;
	}

	public Integer getRewardGrade() {
		return rewardGrade;
	}

	public void setRewardGrade(Integer rewardGrade) {
		this.rewardGrade = rewardGrade;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
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

	public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
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
	public void setGood(Boolean good) {
		this.good = good;
	}
	/**
	 * 获取：
	 */
	public Boolean getGood() {
		return good;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getInitTime() {
		return initTime;
	}
	/**
	 * 设置：
	 */
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	/**
	 * 获取：
	 */
	public Integer getLabelId() {
		return labelId;
	}
	/**
	 * 设置：
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	/**
	 * 获取：
	 */
	public Integer getReplyCount() {
		return replyCount;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setTop(Boolean top) {
		this.top = top;
	}
	/**
	 * 获取：
	 */
	public Boolean getTop() {
		return top;
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
