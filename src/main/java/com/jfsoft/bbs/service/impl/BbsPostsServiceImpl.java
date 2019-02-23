package com.jfsoft.bbs.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.Query;
import com.jfsoft.bbs.dao.BbsPostsDao;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bbsPostsService")
public class BbsPostsServiceImpl extends ServiceImpl<BbsPostsDao, BbsPostsEntity> implements BbsPostsService {

	@Autowired
	BbsPostsDao bbsPostsDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params, EntityWrapper<BbsPostsEntity> wrapper) {
		Page<BbsPostsEntity> page = this.selectPage(
				new Query<BbsPostsEntity>(params).getPage(),
				wrapper
		);

		return new PageUtils(page);
	}

	@Override
	public List<BbsPostsEntity> getList(Integer currPage, Integer pageSize, Integer sort, Integer searchType) {
		Map<String, Object> params = new HashMap<>();
		params.put("startPage", (currPage - 1) * pageSize);
		params.put("endPage", currPage * pageSize);
		if (sort == 0) {
			params.put("order", "init_time");
		} else {
			params.put("order", "reply_count");
		}
		if (searchType == 3) {
			params.put("good", true);
		} else if (searchType == 2) {
			params.put("end", true);
		} else if (searchType == 1) {
			params.put("end", false);
		}
		return bbsPostsDao.getList(params);
	}

	@Override
	public List<BbsPostsEntity> getTopList() {
		Map<String, Object> params = new HashMap<>();
		return bbsPostsDao.getTopList(params);
	}

}
