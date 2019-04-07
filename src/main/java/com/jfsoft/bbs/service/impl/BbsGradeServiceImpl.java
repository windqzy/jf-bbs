package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsGradeDao;
import com.jfsoft.bbs.dao.BbsRewardDao;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.entity.BbsRewardEntity;
import com.jfsoft.bbs.service.BbsGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bbsGradeService")
public class BbsGradeServiceImpl extends ServiceImpl<BbsGradeDao, BbsGradeEntity> implements BbsGradeService {
    @Autowired
    private  BbsGradeDao bbsGradeDao;
    @Autowired
    private BbsRewardDao bbsRewardDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsGradeEntity> page = this.selectPage(
                new Query<BbsGradeEntity>(params).getPage(),
                new EntityWrapper<BbsGradeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public BbsGradeEntity upGrade(Integer userId, Integer postsId, BbsPostsEntity rewardgrade) {
        Map<String,Object> params = new HashMap<>();
        params.put("userId",userId);
        params.put("postsId",postsId);
        params.put("rewardgrade",rewardgrade);
        return bbsGradeDao.upGrade(params);
    }

    @Override
    public List<BbsRewardEntity> finTopThree(String postsId) {
        return bbsRewardDao.findRewardTopThree(postsId);
    }
}
