package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsLabelEntity;
import com.jfsoft.bbs.service.BbsLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 21:02:32
 */
@RestController
@RequestMapping("/label")
public class LabelController extends AbstractController {

    @Autowired
    private BbsLabelService bbsLabelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = bbsLabelService.queryPage(params);
        return R.ok().put("data", page);
    }

    /**
     * 发帖时根据用户ID加载版块
     */
    @RequestMapping("/userlist")
    public R getLabelByUserId(){
        Map<String, Object> params = new HashMap<>();
        params.put("userId", getUserId());
        List<BbsLabelEntity> labels = bbsLabelService.getLabelByUserId(params);
        return R.ok().put("data", labels);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        BbsLabelEntity bbsLabel = bbsLabelService.selectById(id);

        return R.ok().put("bbsLabel", bbsLabel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsLabelEntity bbsLabel){
        bbsLabelService.insert(bbsLabel);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsLabelEntity bbsLabel){
//        ValidatorUtils.validateEntity(bbsLabel);
        bbsLabelService.updateAllColumnById(bbsLabel);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        bbsLabelService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
