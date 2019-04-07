package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsSignEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 签到表
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@Mapper
public interface BbsSignDao extends BaseMapper<BbsSignEntity> {

    BbsSignEntity getSignByUserId(String unionId);

    int insertSign(Integer userId);

    int updateSign(BbsSignEntity bbsSignEntity);

    List<BbsSignEntity> getSignList(Map<String, Object> params);

}
