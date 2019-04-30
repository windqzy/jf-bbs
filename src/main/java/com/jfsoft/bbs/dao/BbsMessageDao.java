package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsMessageEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BbsMessageDao extends BaseMapper<BbsMessageEntity> {

    List<BbsMessageEntity> getMsgsByUser(Integer userId);

    Integer addMessage(Map<String, Object> params);

//    Integer readMsg();

}
