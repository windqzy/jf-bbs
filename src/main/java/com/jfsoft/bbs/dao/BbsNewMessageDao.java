package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsNewMessageEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.form.MessageVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BbsNewMessageDao extends BaseMapper<BbsNewMessageEntity> {

    List<MessageVo> querySelfMessage(Map<String, Object> params);

    BbsUserEntity selectUserInfo(Map<String, Object> params);

}
