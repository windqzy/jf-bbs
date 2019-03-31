package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsVestDao;
import com.jfsoft.bbs.entity.BbsVestEntity;
import com.jfsoft.bbs.service.BbsVestService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bbsVestService")
public class BbsVestServiceImpl extends ServiceImpl<BbsVestDao, BbsVestEntity> implements BbsVestService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<BbsVestEntity> page = this.selectPage(
				new Query<BbsVestEntity>(params).getPage(),
				new EntityWrapper<BbsVestEntity>()
		);
		return new PageUtils(page);
	}
}
