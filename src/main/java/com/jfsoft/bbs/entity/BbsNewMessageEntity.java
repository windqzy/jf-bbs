/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsMessageEntity
 * Author:   Administrator
 * Date:     2019/4/25 16:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/25
 * @since 1.0.0
 */
@TableName("bbs_new_message")
public class BbsNewMessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String content;

    private String type;

    private Date createTime;

    private Integer createPer;

    private String title;

    private String memo;

    private Integer postsId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatePer() {
        return createPer;
    }

    public void setCreatePer(Integer createPer) {
        this.createPer = createPer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getPostsId() {
        return postsId;
    }

    public void setPostsId(Integer postsId) {
        this.postsId = postsId;
    }
}