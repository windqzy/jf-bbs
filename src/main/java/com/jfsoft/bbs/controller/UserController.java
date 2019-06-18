package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsLogEntity;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.form.UserForm;
import com.jfsoft.bbs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 14:33:16
 */
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController {

    @Autowired
    private BbsUserService bbsUserService;

    @Autowired
    private BbsLogService bbsLogService;

    @Autowired
    private BbsVestService bbsVestService;

    @Autowired
    private BbsGradeService bbsGradeService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsUserService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 获取传入的用户信息
     */
    @RequestMapping("/infoByUserId")
    public R getInfoById(@RequestBody Integer userId) {
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);

        return R.ok().put("data", bbsUser);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/info")
    public R info() {
        Integer userId = getUserId();
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);
        return R.ok().put("data", bbsUser);
    }

    /**
     * 保存
     * 钻石减少500钻石
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsUserEntity bbsUser) {
        EntityWrapper<BbsGradeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("union_id", getUnionId());

        // 扣除钻石
        // BbsGradeEntity gradeEntity = bbsGradeService.selectById(userId);
        BbsGradeEntity gradeEntity = bbsGradeService.selectOne(wrapper);
        Integer grade = gradeEntity.getGrade();
        if (grade >= 500) {
            gradeEntity.setGrade(grade - 500);
            gradeEntity.setInitTime(new Date());
            bbsGradeService.updateById(gradeEntity);
            // 先扣钻石再添加账号
            bbsUser.setInitTime(new Date());
            bbsUserService.insert(bbsUser);
            // 增加钻石记录
            BbsLogEntity fromLog = new BbsLogEntity();
            fromLog.setInitTime(new Date());
            fromLog.setLogType(1);
            fromLog.setUserId(bbsUser.getId());
            fromLog.setUnionId(getUnionId());
            fromLog.setRemarks("购买子账号花费 500 钻石");
            bbsLogService.insert(fromLog);
            return R.ok();
        } else {
            return R.error("钻石不足，购买失败！");
        }
    }

    /**
     * 更新钻石，新增用户
     * @param userId
     * @return
     */
    @RequestMapping("/update/grade")
    public R update(Integer userId) {


        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserForm userForm) {
//        ValidatorUtils.validateEntity(bbsUser);
//        bbsUserService.updateAllColumnById(bbsUser);//全部更新
        Integer userId = getUserId();
        String unionId = getUnionId();
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);
        // 查找这个unionId是否已存在账号
		EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
		wrapper.eq("union_id", getUnionId());
		List<BbsUserEntity> userEntities = bbsUserService.selectList(wrapper);
		if (userEntities == null) {
            // 每个人初始化100积分
            BbsGradeEntity gradeEntity = new BbsGradeEntity();
            gradeEntity.setGrade(100);
            gradeEntity.setInitTime(new Date());
            gradeEntity.setUserId(userId);
            gradeEntity.setUnionId(unionId);
            bbsGradeService.insert(gradeEntity);
            // 记录钻石
            BbsLogEntity logEntity = new BbsLogEntity();
            logEntity.setInitTime(new Date());
            logEntity.setLogType(1);
            logEntity.setUserId(userId);
            logEntity.setUnionId(unionId);
            logEntity.setRemarks("创建账号,系统赠送 100 钻石");
            bbsLogService.insert(logEntity);
            // 初始化马甲
//            BbsVestEntity vestEntity = new BbsVestEntity();
//            vestEntity.setInitTime(new Date());
//            vestEntity.setVest(userForm.getUsername());
//            vestEntity.setUserId(getUserId());
//            vestEntity.setIcon(userForm.getIcon());
//            bbsVestService.insert(vestEntity);
        }
        bbsUser.setIcon(userForm.getIcon());
        bbsUser.setEmail(userForm.getEmail());
        bbsUser.setUsername(userForm.getUsername());
        bbsUser.setCity(userForm.getCity());
        bbsUser.setSignature(userForm.getSignature());
        bbsUser.setMobile(userForm.getMobile());
        bbsUser.setSex(userForm.getSex());
        bbsUser.setBackground(userForm.getBackground());
        bbsUserService.updateById(bbsUser);
        return R.ok().put("data", bbsUser);
    }

    @RequestMapping("/update/icon")
    public R upUserIcon(@RequestBody String url) {
        Integer userId = getUserId();
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);
        try {
            bbsUser.setIcon(URLDecoder.decode(url, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        bbsUserService.updateById(bbsUser);
        return R.ok().put("data", bbsUser);
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsUserService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * @return
     */
    @RequestMapping("/getAccount")
    public R getAccount() {
        Integer userId = getUserId();
        EntityWrapper<BbsUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("id", userId);
        BbsUserEntity bbsUserEntity = bbsUserService.selectOne(wrapper);
        EntityWrapper<BbsUserEntity> wrapper2 = new EntityWrapper<>();
        wrapper2.eq("union_id", bbsUserEntity.getUnionId());
        List<BbsUserEntity> bbsUserEntities = bbsUserService.selectList(wrapper2);
        return R.ok().put("data", bbsUserEntities);
    }

}
