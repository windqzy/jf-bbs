package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BbsFaqDao extends BaseMapper<BbsFaqEntity> {

    List<BbsFaqEntity> getFaqList(Integer typeId);
}
