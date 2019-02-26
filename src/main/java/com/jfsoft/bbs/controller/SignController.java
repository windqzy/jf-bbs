package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 签到表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@RestController
@RequestMapping("/sign")
public class SignController extends AbstractController {
    @Autowired
    private BbsSignService bbsSignService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsSignService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsSignEntity bbsSign = bbsSignService.selectById(id);

        return R.ok().put("bbsSign", bbsSign);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsSignEntity bbsSign) {
        bbsSignService.insert(bbsSign);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update() {
        BbsSignEntity bbsSign = bbsSignService.getSignByUserId(getUserId());
        if (bbsSign == null) {
            bbsSignService.insertSign(getUserId());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long days = ChronoUnit.DAYS.between(LocalDate.parse(sdf.format(bbsSign.getInitTime())), LocalDate.parse(sdf.format(new Date())));
            if (days == 1) {
                bbsSign.setSignCount(bbsSign.getSignCount() + 1); //正在连续签到
            } else {
                bbsSign.setSignCount(1);                          //连续签到中断
            }
            bbsSign.setInitTime(new Date());
            bbsSignService.updateSign(bbsSign);
        }

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsSignService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
