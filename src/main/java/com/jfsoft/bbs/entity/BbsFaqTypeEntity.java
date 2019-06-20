package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.util.List;

@TableName("bbs_faq_type")
public class BbsFaqTypeEntity {

    @TableId
    private Integer id;

    private String name;

    private Integer parentId;


    @TableField(exist = false)
    private List<BbsFaqTypeEntity> children;

    public List<BbsFaqTypeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<BbsFaqTypeEntity> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}