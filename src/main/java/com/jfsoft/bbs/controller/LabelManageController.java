/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LabelManageController
 * Author:   Administrator
 * Date:     2019/4/17 10:22
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsLabelManageEntity;
import com.jfsoft.bbs.service.BbsLabelManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/4/17
 * @since 1.0.0
 */
@RestController
@RequestMapping("/label/manage")
public class LabelManageController extends AbstractController {

    @Autowired
    private BbsLabelManageService bbsLabelManageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsLabelManageService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 根据labelId和userId判断用户是否是版主
     */
    @RequestMapping("/confirm/{labelId}")
    public R list(@PathVariable("labelId") Integer labelId){
        EntityWrapper<BbsLabelManageEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", getUserId());
        wrapper.eq("label_id", labelId);
        wrapper.eq("isuse", true);
        BbsLabelManageEntity labelManageEntity = bbsLabelManageService.selectOne(wrapper);
        if (labelManageEntity == null) {
            return R.ok().put("data", false);
        } else {
            return R.ok().put("data", true);
        }
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsLabelManageEntity labelManageEntity = bbsLabelManageService.selectById(id);

        return R.ok().put("bbsLabelManage", labelManageEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsLabelManageEntity bbsLabelManage){
        bbsLabelManageService.insert(bbsLabelManage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsLabelManageEntity bbsLabelManage){
//        ValidatorUtils.validateEntity(bbsLabel);
        bbsLabelManageService.updateAllColumnById(bbsLabelManage);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsLabelManageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}