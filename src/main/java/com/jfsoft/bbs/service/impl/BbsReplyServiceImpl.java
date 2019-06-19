package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.BaseContants;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsReplyDao;
import com.jfsoft.bbs.entity.BbsReplyEntity;
import com.jfsoft.bbs.form.ReplayVo;
import com.jfsoft.bbs.service.BbsReplyService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("bbsReplyService")
public class BbsReplyServiceImpl extends ServiceImpl<BbsReplyDao, BbsReplyEntity> implements BbsReplyService {

    @Autowired
    BbsReplyDao bbsReplyDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsReplyEntity> page = this.selectPage(
                new Query<BbsReplyEntity>(params).getPage(),
                new EntityWrapper<BbsReplyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<BbsReplyEntity> getReplyByPostid(Integer postId, Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("userId", userId);
        return bbsReplyDao.getReplyByPostId(params);
    }

    @Override
    public List<BbsReplyEntity> getReplyByPid(Integer pid, Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("pid", pid);
        params.put("userId", userId);
        return bbsReplyDao.getReplyByPid(params);
    }

    @Override
    public List<BbsReplyEntity> getHotReplyByPostid(Integer postId, Integer userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("userId", userId);
        return bbsReplyDao.getHotReplyByPostid(params);
    }

    @Override
    public List<BbsReplyEntity> getTop(String beginTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("startPage", 0);
        params.put("endPage", 16);
        if (StringUtils.isNotBlank(beginTime)) {
            params.put("beginTime", beginTime + BaseContants.BEGIN_TIME_EXT);
        }
        if (StringUtils.isNotBlank(endTime)) {
            params.put("endTime", endTime + BaseContants.END_TIME_EXT);
        }
        return bbsReplyDao.getTop(params);
    }

    @Override
    public List<BbsReplyEntity> getPersonReplyList(Map<String, Object> param) {
        return bbsReplyDao.getPersonReplyList(param);
    }

    @Override
    public Integer getAccept(Integer id) {
        return bbsReplyDao.getAccept(id);
    }

    @Override
    public Integer trueAccept(Integer id) {
        return bbsReplyDao.trueAccept(id);
    }

    @Override
    public Integer getRewardGrade(Integer id) {
        return bbsReplyDao.getRewardGrade(id);
    }

    @Override
    public Integer upGrade(String unionId, Integer rewardgrade) {
        Map<String, Object> params = new HashMap<>();
        params.put("unionId", unionId);
        params.put("rewardgrade", rewardgrade);
        return bbsReplyDao.upGrade(params);
    }

    @Override
    public Integer upEnd(Integer id){
        return bbsReplyDao.upEnd(id);
    }

    @Override
    public List<ReplayVo> getReplayByPostsId(Integer postsId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postsId", postsId);
        return bbsReplyDao.getReplayByPostsId(params);
    }

    @Override
    public Integer replyCount(Integer id) {
        return bbsReplyDao.selectReplyCount(id);
    }

}
