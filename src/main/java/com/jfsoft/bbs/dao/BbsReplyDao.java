package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsReplyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
@Mapper
public interface BbsReplyDao extends BaseMapper<BbsReplyEntity> {

	List<BbsReplyEntity> getReplyByPostId(Integer postId);

	List<BbsReplyEntity> getTop(Map<String, Object> params);

	List<BbsReplyEntity> getReplyByPostId(Map<String,Object> params);

	Integer updateUpById(Map<String,Object> params);

}
