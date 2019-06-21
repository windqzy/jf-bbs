package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BbsFaqDao extends BaseMapper<BbsFaqEntity> {

    List<BbsFaqEntity> getFaqList(Map<String, Object> params);
}
