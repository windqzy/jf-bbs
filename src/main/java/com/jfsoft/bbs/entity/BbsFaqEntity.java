package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;


@TableName("bbs_faq")
public class BbsFaqEntity {

    @TableId
    private Integer id;

    private String question;

    private String answer;

    private Integer userId;

    private Date initTime;

    private Integer typeId;

    private Date updateTime;

    private Boolean isDel;

    @TableField(exist = false)
    private Boolean thought;

    @TableField(exist = false)
    private Integer useful;

    @TableField(exist = false)
    private Integer Useless;

    public Integer getUseful() {
        return useful;
    }

    public void setUseful(Integer useful) {
        this.useful = useful;
    }

    public Integer getUseless() {
        return Useless;
    }

    public void setUseless(Integer useless) {
        Useless = useless;
    }

    public Boolean getThought() {
        return thought;
    }

    public void setThought(Boolean thought) {
        this.thought = thought;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDel() {
        return isDel;
    }

    public void setDel(Boolean del) {
        isDel = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getInitTime() {
        return initTime;
    }

    public void setInitTime(Date initTime) {
        this.initTime = initTime;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
