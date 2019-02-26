package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsSignService;
import com.jfsoft.bbs.common.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsSignEntity bbsSign = bbsSignService.selectById(id);
        return R.ok().put("data", bbsSign);
    }

    /**
     * 查询签到表信息
     *
     * @return
     */
    @RequestMapping("/count")
    public R selectCount() {
        Integer userId = getUserId();

        EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);

        return R.ok().put("data", bbsSign);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save() {
        Integer userId = getUserId();

        // 获取当前方法执行时的时间戳
        Date initTime = new Date();
        // 检查是否有该用户的签到记录
        EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);
        // 向签到表中存储的数据
        BbsSignEntity newBbsSign = new BbsSignEntity();

        if (bbsSign == null) {
            newBbsSign.setInitTime(initTime);
            newBbsSign.setUserId(userId);
            newBbsSign.setSignCount(1);
            bbsSignService.insert(newBbsSign);
        } else {
            // 最后签到时间
            Date singTime = bbsSign.getInitTime();
            // 获取当前天数差
            int day = DateUtil.getIntervalDayTimes(singTime, initTime);
            // 当前时间与最后签到时间作比较
            if (day == 1) {
                // 如果间隔一天，则是连续签到
                bbsSign.setInitTime(initTime);
                bbsSign.setSignCount(bbsSign.getSignCount() + 1);
                // 更新新的签到
                bbsSignService.updateById(bbsSign);
                return R.ok("连续签到");
            } else if (day > 1) {
                // 断签，直接清零
                bbsSign.setInitTime(initTime);
                bbsSign.setSignCount(1);
                // 更新新的签到记录
                bbsSignService.updateById(bbsSign);
                return R.ok("您断签了，从头开始");
            }
        }

        return R.ok("执行结束");
    }

    @RequestMapping("/bool")
    public R isSign() {
        Map<String, Object> map = new HashMap<>();
        Integer userId = getUserId();
        //当前时间
        Date initTime = new Date();
        //检查是否有该用户的签到记录
        EntityWrapper<BbsSignEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        BbsSignEntity bbsSign = bbsSignService.selectOne(wrapper);

        if (bbsSign == null) {
            map.put("isSign", false);
            map.put("count", 0);
        } else {
            //最后签到时间
            Date lastSingTime = bbsSign.getInitTime();
            int day = DateUtil.getIntervalDays(lastSingTime, initTime);
            if (day == 0) {
                map.put("isSign", true);
            } else {
                map.put("isSign", false);
            }
            map.put("count", bbsSign.getSignCount());

        }

        return R.ok().put("data", map);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsSignEntity bbsSign) {
//        ValidatorUtils.validateEntity(bbsSign);
        bbsSignService.updateAllColumnById(bbsSign);//全部更新

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
