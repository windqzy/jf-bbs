package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("bbs_vest")
public class BbsVestEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private Date initTime;

	private String vest;

	private Integer userId;

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

	public String getVest() {
		return vest;
	}

	public void setVest(String vest) {
		this.vest = vest;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
