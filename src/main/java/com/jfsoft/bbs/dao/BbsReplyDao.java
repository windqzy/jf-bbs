package com.jfsoft.bbs.dao;

import com.jfsoft.bbs.entity.BbsReplyEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.form.ReplayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
@Mapper
public interface BbsReplyDao extends BaseMapper<BbsReplyEntity> {

//    List<BbsReplyEntity> getReplyByPostId(Integer postId);

    // 根据parent_id
    List<BbsReplyEntity> getReplyByPid(Map<String, Object> params);

    List<BbsReplyEntity> getTop(Map<String, Object> params);

    List<BbsReplyEntity> getReplyByPostId(Map<String, Object> params);

    List<BbsReplyEntity> getHotReplyByPostid(Map<String, Object> params);

    Integer updateUpById(Map<String, Object> params);

    List<BbsReplyEntity> getPersonReplyList(Map<String, Object> param);

    Integer getAccept(@Param("id") Integer id);

    Integer trueAccept(@Param("id") Integer id);

    Integer getRewardGrade(@Param("id") Integer id);

    Integer upGrade(Map<String, Object> params);

    Integer upEnd(@Param("id") Integer id);

    List<ReplayVo> getReplayByPostsId(Map<String, Object> params);

    List<ReplayVo> findOtherReplay(Map<String, Object> params);

    BbsUserEntity selectUserInfo(Map<String, Object> params);

    Integer selectUpCount(Map<String, Object> params);

    Integer selectReplyCount(Integer id);
}
