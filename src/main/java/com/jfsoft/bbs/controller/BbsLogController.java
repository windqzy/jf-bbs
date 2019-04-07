package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.service.BbsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/4/2  15:11
 */

@RestController
@RequestMapping("/log")
public class BbsLogController extends AbstractController {

    @Autowired
    private BbsLogService logService;

    @RequestMapping("/list/{type}")
    public R getLog(@PathVariable Integer type) {
        EntityWrapper<BbsLogEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("log_type", type);
        wrapper.eq("union_id", getUnionId());
        List<BbsLogEntity> bbsLogEntities = logService.selectList(wrapper);
        return R.ok().put("data", bbsLogEntities);
    }
}
