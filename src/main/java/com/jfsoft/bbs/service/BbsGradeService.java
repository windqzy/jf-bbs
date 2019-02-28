package com.jfsoft.bbs.service;

import com.baomidou.mybatisplus.service.IService;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsPostsEntity;

import java.util.Map;

/**
 * 用户积分表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
public interface BbsGradeService extends IService<BbsGradeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    BbsGradeEntity upGrade(Integer userId, Integer postsId, BbsPostsEntity rewardgrade);
}

