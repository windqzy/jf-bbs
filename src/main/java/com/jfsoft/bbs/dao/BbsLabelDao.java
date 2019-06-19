package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsLabelEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 21:02:32
 */
@Mapper
public interface BbsLabelDao extends BaseMapper<BbsLabelEntity> {

    List<BbsLabelEntity> getLabelByUserId(Map<String, Object> params);

    List<BbsLabelEntity> getLabelById(Map<String, Object> params);
}
