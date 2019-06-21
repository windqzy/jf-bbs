package com.jfsoft.bbs.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsFaqEntity;
import com.jfsoft.bbs.entity.BbsFaqLogEntity;
import com.jfsoft.bbs.entity.BbsFaqTypeEntity;
import com.jfsoft.bbs.service.BbsFaqLogService;
import com.jfsoft.bbs.service.BbsFaqService;
import com.jfsoft.bbs.service.BbsFaqTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
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
public class FaqController extends AbstractController {

    @Autowired
    private BbsFaqService bbsFaqService;

    @Autowired
    private BbsFaqTypeService bbsFaqTypeService;

    @Autowired
    private BbsFaqLogService bbsFaqLogService;


    /**
     * 获取板块列表
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
        List<BbsFaqEntity> list = bbsFaqService.getFaqList(typeId);
        return R.ok().put("data", list);
    }


    /**
     * 增添、修改相关FAQ
     *
     * @Author Mjp
     * @Date 14:50 2019/6/20
     * @Param []
     * @Return
     **/
    @RequestMapping("/update")
    public R updateFaq(@RequestBody BbsFaqEntity faqEntity) {
        if (faqEntity.getId() == null) {
            // 即为新增FAQ
            BbsFaqEntity bbsFaqEntity = new BbsFaqEntity();
            bbsFaqEntity.setQuestion(faqEntity.getQuestion());
            bbsFaqEntity.setAnswer(faqEntity.getAnswer());
            bbsFaqEntity.setTypeId(faqEntity.getTypeId());
            bbsFaqEntity.setUserId(getUserId());
            bbsFaqEntity.setInitTime(new Date());
            bbsFaqEntity.setUpdateTime(new Date());
            bbsFaqService.insert(bbsFaqEntity);
        } else {
            // 即为更新FAQ
            BbsFaqEntity bbsFaqEntity = new BbsFaqEntity();
            bbsFaqEntity.setId(faqEntity.getId());
            bbsFaqEntity.setQuestion(faqEntity.getQuestion());
            bbsFaqEntity.setAnswer(faqEntity.getAnswer());
            bbsFaqEntity.setTypeId(faqEntity.getTypeId());
            bbsFaqEntity.setUserId(getUserId());
            bbsFaqEntity.setUpdateTime(new Date());
            bbsFaqService.updateById(bbsFaqEntity);
        }
        return R.ok("提交成功！");
    }

    /**
     * 点赞有用
     *
     * @Author Mjp
     * @Date 15:16 2019/6/20
     * @Param []
     * @Return
     **/
    @RequestMapping("/good")
    public R isGood(Integer faqId, boolean isGood) {
        EntityWrapper<BbsFaqLogEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("faq_id", faqId);
        wrapper.eq("user_id", getUserId());
        BbsFaqLogEntity bbsFaqLogEntity = bbsFaqLogService.selectOne(wrapper);
        if (bbsFaqLogEntity == null) {
            BbsFaqLogEntity faqLogEntity = new BbsFaqLogEntity();
            faqLogEntity.setFaqId(faqId);
            faqLogEntity.setGood(isGood);
            faqLogEntity.setUserId(getUserId());
            bbsFaqLogService.insert(faqLogEntity);
            return R.ok("谢谢您的评价");
        } else {
            return R.ok("false");
        }
    }

    /**
     * 删除FAQ
     *
     * @Author Mjp
     * @Date 17:11 2019/6/20
     * @Param [id]
     * @Return
     **/
    @RequestMapping("/del/{id}")
    public R del(@PathVariable("id") Integer id) {
        BbsFaqEntity bbsFaqEntity = bbsFaqService.selectById(id);
        bbsFaqEntity.setDel(true);
        bbsFaqService.updateById(bbsFaqEntity);
        return R.ok("删除成功");
    }

}
