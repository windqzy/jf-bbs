package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.entity.BbsVestEntity;
import com.jfsoft.bbs.service.BbsUserService;
import com.jfsoft.bbs.service.BbsVestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("vest")
public class BbsVestController extends AbstractController {

	@Autowired
	private BbsVestService bbsVestService;

	@Autowired
	private BbsUserService bbsUserService;

	@RequestMapping("/add")
	public R addVest(String vest) {
		BbsVestEntity bbsVestEntity = new BbsVestEntity();
		bbsVestEntity.setInitTime(new Date());
		bbsVestEntity.setUserId(getUserId());
		bbsVestEntity.setVest(vest);
		boolean insert = bbsVestService.insert(bbsVestEntity);
		return R.ok().put("data", "添加马甲成功!");
	}

	@RequestMapping("/rename/id")
	public R updateUsername(@PathVariable("vest") String vest) {
		BbsUserEntity bbsUserEntity = new BbsUserEntity();
		bbsUserEntity.setId(getUserId());
		bbsUserEntity.setUsername(vest);
		bbsUserService.updateById(bbsUserEntity);
		return R.ok().put("data", "切换马甲成功!");
	}


	@RequestMapping("/update")
	public R update(BbsVestEntity bbsVestEntity) {
		bbsVestService.updateById(bbsVestEntity);
		return R.ok();
	}

}
