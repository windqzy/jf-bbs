package com.jfsoft.bbs.controller;

import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsUserEntity;
import com.jfsoft.bbs.form.UserForm;
import com.jfsoft.bbs.service.BbsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
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

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsUserService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R getInfoById(@PathVariable("id") Integer id) {
        BbsUserEntity bbsUser = bbsUserService.selectById(id);

        return R.ok().put("bbsUser", bbsUser);
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
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsUserEntity bbsUser) {
        bbsUserService.insert(bbsUser);
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
        BbsUserEntity bbsUser = bbsUserService.selectById(userId);
        bbsUser.setEmail(userForm.getEmail());
        bbsUser.setUsername(userForm.getUsername());
        bbsUser.setCity(userForm.getCity());
        bbsUser.setSignature(userForm.getSignature());
        bbsUser.setMobile(userForm.getMobile());

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

}
