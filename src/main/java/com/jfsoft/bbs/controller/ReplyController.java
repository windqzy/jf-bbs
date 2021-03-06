package com.jfsoft.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.controller.socket.WebSocketServer;
import com.jfsoft.bbs.entity.*;
import com.jfsoft.bbs.form.ReplayVo;
import com.jfsoft.bbs.form.ReplyForm;
import com.jfsoft.bbs.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static com.jfsoft.bbs.controller.socket.WebSocketServer.getOnlineCount;


/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 18:10:32
 */
@RestController
@RequestMapping("/reply")
public class ReplyController extends AbstractController {
    @Autowired
    private BbsReplyService bbsReplyService;

    @Autowired
    private BbsPostsService bbsPostsService;

    @Autowired
    private BbsLogService bbsLogService;

    @Autowired
    private BbsUserService bbsUserService;

    @Autowired
    private BbsMessageService bbsMessageService;

    @Autowired
    private BbsNewMessageService bbsNewMessageService;

    @Autowired
    private BbsMessageUserService bbsMessageUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsReplyService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 获取回复周榜
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping("/top")
    public R top(String beginTime, String endTime) {
        List<BbsReplyEntity> topList = bbsReplyService.getTop(beginTime, endTime);
        return R.ok().put("data", topList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsReplyEntity bbsReply = bbsReplyService.selectById(id);
        return R.ok().put("data", bbsReply);
    }

    /**
     * 根据postId获取最新回复
     */
    @RequestMapping("/list/{postId}")
    public R postInfo(@PathVariable("postId") Integer postId) {
        List<BbsReplyEntity> replyList = bbsReplyService.getReplyByPostid(postId, getUserId());
        return R.ok().put("data", replyList);
    }

    /**
     * 根据postId获取热门回复
     */
    @RequestMapping("/hotList/{postId}")
    public R hotList(@PathVariable("postId") Integer postId) {
        List<BbsReplyEntity> replyList = bbsReplyService.getHotReplyByPostid(postId, getUserId());
        return R.ok().put("data", replyList);
    }

    /**
     * 回帖
     */
    @PostMapping("/save")
    public R addReply(@RequestBody ReplyForm ReplyForm) {
        BbsReplyEntity bbsReply = new BbsReplyEntity();
        BbsUserEntity user = getUser();
        bbsReply.setPostsId(ReplyForm.getPostsId());
        bbsReply.setInitTime(new Date());
        bbsReply.setUserId(user.getId());
        bbsReply.setAuthor(user.getUsername());
//        bbsReply.setParentId();
        bbsReply.setContent(ReplyForm.getContent());
        // 增加一条评论
        bbsReplyService.insert(bbsReply);
        // 更新post表的评论数
        BbsPostsEntity bbsPosts = bbsPostsService.selectById(ReplyForm.getPostsId());
        int count = bbsPosts.getReplyCount() + 1;
        bbsPosts.setReplyCount(count);
        bbsPostsService.updateById(bbsPosts);
        // 添加一条消息
        bbsMessageService.addMsg(1, user.getUsername() + "评论了您的帖子《" + bbsPosts.getTitle() + "》",
                bbsPosts.getId(), user.getId(), bbsPosts.getUserId(), true);
        return R.ok().put("data", "评论成功");
    }

    /**
     * 修改
     */
//    @RequestMapping("/update")
//    public R update(@RequestBody BbsReplyEntity bbsReply) {
////        ValidatorUtils.validateEntity(bbsReply);
//        bbsReplyService.updateAllColumnById(bbsReply);//全部更新
//
//        return R.ok();
//    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ReplyForm ReplyForm) {
        BbsReplyEntity bbsReply = new BbsReplyEntity();
        bbsReply.setId(ReplyForm.getId());
        bbsReply.setInitTime(new Date());
        bbsReply.setContent(ReplyForm.getContent());
        boolean b = bbsReplyService.updateById(bbsReply);
        return R.ok().put("data", b);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsReplyService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 根据评论id删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable("id") Integer id) {
        // 根据评论ID找到帖子ID，然后帖子评论数-1
        BbsReplyEntity replyEntity = bbsReplyService.selectById(id);
        BbsPostsEntity postsEntity = bbsPostsService.selectById(replyEntity.getPostsId());
        BbsPostsEntity newPost = new BbsPostsEntity();
        newPost.setId(postsEntity.getId());
        newPost.setReplyCount(postsEntity.getReplyCount() - 1);
        Boolean flag = bbsReplyService.deleteById(id);
        bbsPostsService.updateById(newPost);
        return R.ok().put("data", flag);
    }


    /**
     * 获取个人文章的所有回复
     */
    @RequestMapping("/personReply")
    public R getPersonReply(Integer userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (userId == null) {
            param.put("user_id", getUserId());
        } else {
            param.put("user_id", userId);
        }
        List<BbsReplyEntity> list = bbsReplyService.getPersonReplyList(param);
        return R.ok().put("data", list);
    }

    /*采纳回复*/
    @GetMapping("/acceptReply")
    public R accept(Integer id, Integer userId) {
        /*看是否存在已采纳的回复*/
        Integer bbsReply = bbsReplyService.getAccept(id);
        /* 获取帖子*/
        BbsPostsEntity postsEntity = bbsPostsService.getPostByReplyId(id);
        /* 获取被采纳用户的unionId*/
        BbsUserEntity userEntity = bbsUserService.selectById(userId);
        if (bbsReply == 0) {
            /*将该回复修改为已采纳状态*/
            bbsReplyService.trueAccept(id);
            /*获取该贴悬赏的飞吻数*/
            Integer rewardGrade = bbsReplyService.getRewardGrade(id);
            /*将飞吻加到被采纳的回复者账号上*/
            bbsReplyService.upGrade(userEntity.getUnionId(), rewardGrade);
            /*完结该贴，不予新采纳*/
            bbsReplyService.upEnd(id);
            // 被采纳钻石记录
            if (rewardGrade > 0) {
                BbsLogEntity bbslog = new BbsLogEntity();
                bbslog.setInitTime(new Date());
                bbslog.setUserId(userId);
                bbslog.setUnionId(userEntity.getUnionId());
                bbslog.setLogType(1);
                bbslog.setRemarks("回复被采纳获得" + rewardGrade + "钻石");
                bbsLogService.insert(bbslog);
            }
            // 添加采纳消息
            bbsMessageService.addMsg(2, userEntity.getUsername() + "您的回复被采纳",
                    postsEntity.getId(), postsEntity.getUserId(), userId, true);
            return R.ok("采纳成功");
        } else {
            return R.error("抱歉,该贴已经完成采纳");
        }
    }


    /**
     * 添加评论
     *
     * @Author Lxd
     * @Date 16:48 2019/6/14
     * @Param [replyEntity]
     * @Return
     **/
    @PostMapping("/add")
    public R addReply(@RequestBody BbsReplyEntity replyEntity) {

        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(replyEntity.getPostsId());

        /** 直接评论帖子 */
        if (replyEntity.getReplyTo() == null) {
            replyEntity.setParentId(0);
        }
        replyEntity.setInitTime(new Date());
        boolean i = bbsReplyService.insert(replyEntity);

        /** 添加消息，并向消息用户中间表中插入一条未读数据 */
        BbsNewMessageEntity bbsNewMessageEntity = new BbsNewMessageEntity();
        bbsNewMessageEntity.setContent(replyEntity.getContent());
        bbsNewMessageEntity.setCreatePer(replyEntity.getUserId());
        bbsNewMessageEntity.setCreateTime(new Date());
        /** 评论帖子，消息通知作者 */
        if (replyEntity.getParentId() == 0 || replyEntity.getParentId() == null) {
            bbsNewMessageEntity.setType("0");
        } else {
            bbsNewMessageEntity.setType("1");
        }
        bbsNewMessageEntity.setReplyId(replyEntity.getId());
        bbsNewMessageEntity.setPostsId(replyEntity.getPostsId());

        EntityWrapper<BbsNewMessageEntity> bbsNewMessageEntityEntityWrapper = new EntityWrapper<>();
        bbsNewMessageEntityEntityWrapper.eq("REPLY_ID", replyEntity.getParentId());
        BbsNewMessageEntity parentMessage = bbsNewMessageService.selectOne(bbsNewMessageEntityEntityWrapper);
        if (parentMessage != null) {
            bbsNewMessageEntity.setParentId(parentMessage.getId());
        }

        bbsNewMessageService.insert(bbsNewMessageEntity);


        /** 插入未读消息 */
        BbsMessageUserEntity bbsMessageUserEntity = new BbsMessageUserEntity();
        bbsMessageUserEntity.setMessageId(bbsNewMessageEntity.getId());
        bbsMessageUserEntity.setIsRead("0");

        /** 评论帖子，消息通知作者 */
        if (replyEntity.getParentId() == 0 || replyEntity.getParentId() == null) {
            bbsMessageUserEntity.setUserId(bbsPostsEntity.getUserId());
        } else {
            bbsMessageUserEntity.setUserId(replyEntity.getReplyTo());
        }

        bbsMessageUserService.insert(bbsMessageUserEntity);

        /** 查询出有几条未读消息 */
        EntityWrapper<BbsMessageUserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("USER_ID", bbsMessageUserEntity.getUserId());
        wrapper.eq("IS_READ", "0");
        int toReadCount = bbsMessageUserService.selectCount(wrapper);

        Map<String, Object> params = new HashMap<>();
        params.put("toReadCount", toReadCount);
        params.put("onLineUserCount", getOnlineCount());

        /** 推送消息 */
        try {
            WebSocketServer.sendInfo(JSON.toJSONString(params, SerializerFeature.WriteMapNullValue), bbsMessageUserEntity.getUserId() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (i) {
            return R.ok("评论成功");
        } else {
            return R.error("评论失败");
        }


    }


    /**
     * 查询评论内容
     *
     * @Author Lxd
     * @Date 14:34 2019/6/14
     * @Param [id] 帖子ID
     * @Return
     **/
    @GetMapping("/posts/{id}")
    public R getReplayByPostsId(@PathVariable Integer id) {

        List<ReplayVo> replyList = bbsReplyService.getReplayByPostsId(id);
        return R.ok().put("data", replyList);

    }

}
