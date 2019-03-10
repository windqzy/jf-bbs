package com.jfsoft.bbs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsTagDao;
import com.jfsoft.bbs.entity.BbsTagEntity;
import com.jfsoft.bbs.service.BbsTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("bbsTabService")
public class BbsTagServiceImpl extends ServiceImpl<BbsTagDao, BbsTagEntity> implements BbsTagService {

	@Autowired
	private BbsTagDao bbsTagDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<BbsTagEntity> page = this.selectPage(
				new Query<BbsTagEntity>(params).getPage(),
				new EntityWrapper<BbsTagEntity>()
		);
		return new PageUtils(page);
	}

	@Override
	public List<BbsTagEntity> getTagByLabelId(Integer labelId) {
		return bbsTagDao.getTagByLabelId(labelId);
	}
}
