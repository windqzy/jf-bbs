package com.jfsoft.bbs.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import com.jfsoft.bbs.entity.BbsFaqTypeEntity;
import com.jfsoft.bbs.service.BbsFaqLogService;
import com.jfsoft.bbs.service.BbsFaqService;
import com.jfsoft.bbs.service.BbsFaqTypeService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 常见问题解答
 *
 * @Author Mjp
 * @Date 11:27 2019/6/20
 * @Param
 * @Return
 **/
@RestController
@RequestMapping("/faq")
public class FaqController {

    @Autowired
    private BbsFaqService bbsFaqService;

    @Autowired
    private BbsFaqTypeService bbsFaqTypeService;

    @Autowired
    private BbsFaqLogService bbsFaqLogService;


    /**
     * 获取问题列表
     *
     * @Author Mjp
     * @Date 11:36 2019/6/20
     * @Return
     **/
    @RequestMapping("/list")
    public R getList() {
        EntityWrapper<BbsFaqTypeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", 0);
        List<BbsFaqTypeEntity> list = bbsFaqTypeService.selectList(wrapper);
        list.forEach(e -> {
            EntityWrapper<BbsFaqTypeEntity> faqWrapper = new EntityWrapper<>();
            faqWrapper.eq("parent_id", e.getId());
            List<BbsFaqTypeEntity> childrenList = bbsFaqTypeService.selectList(faqWrapper);
            e.setChildren(childrenList);
            for (int i = 0; i < childrenList.size(); i++) {
                EntityWrapper<BbsFaqTypeEntity> fWrapper = new EntityWrapper<>();
                fWrapper.eq("parent_id", childrenList.get(i).getId());
                List<BbsFaqTypeEntity> children = bbsFaqTypeService.selectList(fWrapper);
                childrenList.get(i).setChildren(children);
            }
        });
        return R.ok().put("data", list);
    }


    /**
     * 根据板块ID查询所属问题
     *
     * @Author Mjp
     * @Date 13:50 2019/6/20
     * @Param [typeId 板块ID]
     * @Return
     **/
    @RequestMapping("/faqList")
    public R getFaqList(Integer typeId) {
        EntityWrapper<BbsFaqEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("type_id", typeId);
        List<BbsFaqEntity> list = bbsFaqService.selectList(wrapper);
        return R.ok().put("data", list);
    }

}
