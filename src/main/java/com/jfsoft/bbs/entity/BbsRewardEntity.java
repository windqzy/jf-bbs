package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author ChenXc
 * @version V1.0
 * @Date 2019/4/7 3:38 PM
 * @Description TODO(一句话描述类作用)
 */
@TableName("bbs_reward")
public class BbsRewardEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    private String rewardName;
    private String rewardMoney;
    private String postsId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public String getPostsId() {
        return postsId;
    }

    public void setPostsId(String postsId) {
        this.postsId = postsId;
    }
}
