/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BbsGameGradeServiceImpl
 * Author:   Administrator
 * Date:     2019/4/3 15:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsGameGradeDao;
import com.jfsoft.bbs.entity.BbsGameEntity;
import com.jfsoft.bbs.entity.BbsGameGradeEntity;
import com.jfsoft.bbs.service.BbsGameGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/3
 * @since 1.0.0
 */
@Service("bbsGameGradeService")
public class BbsGameGradeServiceImpl extends ServiceImpl<BbsGameGradeDao, BbsGameGradeEntity> implements BbsGameGradeService {

    @Autowired
    private BbsGameGradeDao bbsGameGradeDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BbsGameGradeEntity> page = this.selectPage(
                new Query<BbsGameGradeEntity>(params).getPage(),
                new EntityWrapper<BbsGameGradeEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public BbsGameGradeEntity getFirstGrade(Integer id) {
        return bbsGameGradeDao.getFirstGrade(id);
    }

    @Override
    public List<BbsGameEntity> getRankList(Integer gameId) {
        return bbsGameGradeDao.getRankList(gameId);
    }
}