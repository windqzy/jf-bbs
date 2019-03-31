package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsLogDao;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.service.BbsLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("bbsLogService")
public class BbsLogServiceImpl extends ServiceImpl<BbsLogDao, BbsLogEntity> implements BbsLogService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<BbsLogEntity> page = this.selectPage(
				new Query<BbsLogEntity>(params).getPage(),
				new EntityWrapper<BbsLogEntity>()
		);
		return new PageUtils(page);
	}

}
