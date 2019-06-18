package com.jfsoft.bbs.form;

import com.jfsoft.bbs.entity.BbsNewMessageEntity;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;

/**
 * @ClassName MessageVo
 * @Description 消息封装类
 * @Author Lxd
 * @Date 2019/6/18 13:59
 * @Version 1.0
 **/
public class MessageVo {

    /** 发起人 */
    private BbsUserEntity sponsor;

    /** 帖子 */
    private BbsPostsEntity postsEntity;

    /** 消息体 */
    private BbsNewMessageEntity messageEntity;

    public BbsUserEntity getSponsor() {
        return sponsor;
    }

    public void setSponsor(BbsUserEntity sponsor) {
        this.sponsor = sponsor;
    }

    public BbsPostsEntity getPostsEntity() {
        return postsEntity;
    }

    public void setPostsEntity(BbsPostsEntity postsEntity) {
        this.postsEntity = postsEntity;
    }

    public BbsNewMessageEntity getMessageEntity() {
        return messageEntity;
    }

    public void setMessageEntity(BbsNewMessageEntity messageEntity) {
        this.messageEntity = messageEntity;
    }
}
