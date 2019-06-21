package com.jfsoft.bbs.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsFaqLogEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BbsFaqLogDao extends BaseMapper<BbsFaqLogEntity> {

    Integer useCount(Map<String, Object> map);
}
