package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.*;
import com.jfsoft.bbs.es.document.ProductDocument;
import com.jfsoft.bbs.es.document.ProductDocumentBuilder;
import com.jfsoft.bbs.es.service.EsSearchService;
import com.jfsoft.bbs.service.*;
import org.elasticsearch.client.transport.NoNodeAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@RestController
@RequestMapping("/posts")
public class PostsController extends AbstractController {

    @Autowired
    private BbsPostsService bbsPostsService;

    @Autowired
    private BbsReplyService bbsReplyService;

    @Autowired
    private BbsLogService bbsLogService;

    @Autowired
    private BbsUserService bbsUserService;

    @Autowired
    private EsSearchService esSearchService;

    @Autowired
    private BbsGradeService bbsGradeService;

    @Autowired
    private BbsLabelService bbsLabelService;

    @Autowired
    private BbsCollectionService bbsCollectionService;

    @Autowired
    private BbsLabelManageService bbsLabelManageService;

    @Autowired
    private BbsPostsFileService bbsPostsFileService;


    /**
     * 首页列表查询
     *
     * @param currPage
     * @param pageSize
     * @param labelId  板块ID
     * @param sortType 排序：0 时间， 1 热度
     * @param postType 查询：0 所有， 1 未结， 2 已结， 3 精华
     * @return
     */
    @RequestMapping("/list")
    public R list(Integer currPage, Integer pageSize, Integer sortType, Integer postType, Integer labelId, Integer tagId,
                  String beginTime, String endTime) {
        List<BbsPostsEntity> list = bbsPostsService.getList(currPage, pageSize, sortType, postType, labelId, tagId, beginTime, endTime);
        return R.ok().put("data", list);
    }


    /**
     * 获取用户的帖子列表
     *
     * @Author Mjp
     * @Date 16:20 2019/6/18
     * @Param [userId 传入的userId, type 获取的列表类型(0 帖子 1 收藏 2 草稿),
     * order 排序方式(0 按发表时间排序 1 按回复数排序)]
     * @Return
     **/
    @RequestMapping("/personList")
    public R getPersonList(Integer userId, String type, String order) {
        if (userId == null) {
            userId = getUserId();
        }
        if (type.equals("0")) {
            EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.eq("is_del", false);
            wrapper.eq("is_temp", false);
            List<BbsPostsEntity> posts = bbsPostsService.selectList(wrapper);
            for (int i = 0; i < posts.size(); i++) {
                Integer id = posts.get(i).getId();
                posts.get(i).setReplyCount(bbsReplyService.replyCount(id));
                Integer uid = posts.get(i).getUserId();
                BbsUserEntity userEntity = bbsUserService.selectById(uid);
                posts.get(i).setAuthor(userEntity.getUsername());
                posts.get(i).setIcon(userEntity.getIcon());
            }
            if (order.equals("0")) {
                List<BbsPostsEntity> list = posts.stream().sorted(Comparator.comparing(BbsPostsEntity::getInitTime).reversed()).collect(Collectors.toList());
                return R.ok().put("data", list);
            } else {
                List<BbsPostsEntity> list = posts.stream().sorted(Comparator.comparing(BbsPostsEntity::getReplyCount).reversed()).collect(Collectors.toList());
                return R.ok().put("data", list);
            }
        } else if (type.equals("1")) {
            EntityWrapper<BbsCollectionEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.eq("status", true);
            List<BbsCollectionEntity> list = bbsCollectionService.selectList(wrapper);
            List<BbsPostsEntity> posts = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                Integer pid = list.get(i).getPostId();
                EntityWrapper<BbsPostsEntity> postWrapper = new EntityWrapper<>();
                postWrapper.eq("id", pid);
                postWrapper.eq("is_temp", false);
                postWrapper.eq("is_del", false);
                BbsPostsEntity postsEntity = bbsPostsService.selectOne(postWrapper);
                Integer id = postsEntity.getId();
                postsEntity.setReplyCount(bbsReplyService.replyCount(id));
                Integer uid = postsEntity.getUserId();
                BbsUserEntity userEntity = bbsUserService.selectById(uid);
                postsEntity.setAuthor(userEntity.getUsername());
                postsEntity.setIcon(userEntity.getIcon());
                posts.add(postsEntity);
            }
            if (order.equals("0")) {
                List<BbsPostsEntity> postList = posts.stream().sorted(Comparator.comparing(BbsPostsEntity::getInitTime).reversed()).collect(Collectors.toList());
                return R.ok().put("data", postList);
            } else {
                List<BbsPostsEntity> postList = posts.stream().sorted(Comparator.comparing(BbsPostsEntity::getReplyCount).reversed()).collect(Collectors.toList());
                return R.ok().put("data", postList);
            }
        } else {
            EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("user_id", userId);
            wrapper.eq("is_temp", true);
            wrapper.eq("is_del", false);
            wrapper.orderDesc(Arrays.asList(new String[]{"init_time"}));
            List<BbsPostsEntity> list = bbsPostsService.selectList(wrapper);
            for (int i = 0; i < list.size(); i++) {
                Integer uid = list.get(i).getUserId();
                BbsUserEntity userEntity = bbsUserService.selectById(uid);
                list.get(i).setAuthor(userEntity.getUsername());
                list.get(i).setIcon(userEntity.getIcon());
            }
            return R.ok().put("data", list);
        }

    }


    /**
     * 查询置顶
     *
     * @return
     */
    @RequestMapping("/top")
    public R top() {
        List<BbsPostsEntity> topList = bbsPostsService.getTopList();
        return R.ok().put("data", topList);
    }


    /**
     * 信息
     */
    @RequestMapping("/detail/{id}")
    public R info(@PathVariable("id") Integer id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("userId", getUserId());
        BbsPostsEntity bbsPosts = bbsPostsService.getPostByID(params);
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsPostsEntity bbsPosts) {
        // TODO 增加版主功能,在发帖的时候需要先判断版块是否只能是版主发帖
        EntityWrapper<BbsLabelEntity> wrapperLabel = new EntityWrapper<>();
        wrapperLabel.eq("id", bbsPosts.getLabelId());
        BbsLabelEntity labelEntity = bbsLabelService.selectOne(wrapperLabel);
        if (labelEntity.getPostManage()) {
            // 如果只能版主发帖
            // 用户是否是版主
            EntityWrapper<BbsLabelManageEntity> wrapperLabelManage = new EntityWrapper<>();
            wrapperLabelManage.eq("user_id", getUserId());
            wrapperLabelManage.eq("label_id", bbsPosts.getLabelId());
            wrapperLabelManage.eq("isuse", true);
            BbsLabelManageEntity labelManageEntity = bbsLabelManageService.selectOne(wrapperLabelManage);
            if (labelManageEntity == null) {
                return R.error("此版块只能版主发帖！！！");
            }
        }
        // 开始发帖
        Integer postId = bbsPosts.getId();
        String username = getUser().getUsername();
        String unionId = getUnionId();
        EntityWrapper<BbsGradeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("union_id", unionId);
        BbsGradeEntity gradeEntity = bbsGradeService.selectOne(wrapper);
        Integer grade = gradeEntity.getGrade();
        if (grade - bbsPosts.getRewardGrade() >= 0) {
            //
            gradeEntity.setGrade(grade - bbsPosts.getRewardGrade());
            //更新该用户的钻石
            bbsGradeService.updateById(gradeEntity);
            bbsPosts.setInitTime(new Date());
            if (postId == null) {
                // 新增
                bbsPosts.setUserId(getUserId());
                // 帖子显示发帖时的用户姓名
                bbsPosts.setAuthor(username);
                bbsPostsService.insert(bbsPosts);
                ProductDocument productDocument = ProductDocumentBuilder.create()
                        .addId(bbsPosts.getId().toString())
                        .addProductName(bbsPosts.getTitle())
                        .addLabel(bbsPosts.getLabelId().toString())
                        .addProductDesc(bbsPosts.getContent())
                        .addCreateTime(bbsPosts.getInitTime())
                        .builder();
                try {
                    esSearchService.save(productDocument);
                } catch (NoNodeAvailableException e) {
                    e.printStackTrace();
                }
            } else {
                bbsPostsService.updateById(bbsPosts);

            }
            // 发帖钻石记录
            if (bbsPosts.getRewardGrade() > 0) {
                BbsLogEntity bbslog = new BbsLogEntity();
                bbslog.setInitTime(new Date());
                bbslog.setUserId(getUserId());
                bbslog.setUnionId(getUnionId());
                bbslog.setLogType(1);
                bbslog.setRemarks(username + "发帖悬赏 " + bbsPosts.getRewardGrade() + " 钻石");
                bbsLogService.insert(bbslog);

            }
            return R.ok().put("data", bbsPosts);
        }
        return R.error("账号钻石不足，发帖失败");
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsPostsEntity bbsPosts) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(bbsPosts.getId());
        bbsPostsService.updateById(bbsPosts);
        // 发帖钻石记录
        if (bbsPosts.getRewardGrade() > 0) {
            BbsLogEntity bbslog = new BbsLogEntity();
            bbslog.setInitTime(new Date());
            bbslog.setUserId(getUserId());
            bbslog.setUnionId(getUnionId());
            bbslog.setLogType(1);
            bbslog.setRemarks("帖子" + bbsPostsEntity.getTitle()
                    + " 修改钻石悬赏，由 " + bbsPostsEntity.getRewardGrade()
                    + "修改为 " + bbsPosts.getRewardGrade() + " 钻石");
            bbsLogService.insert(bbslog);
        }
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 查询置顶
     *
     * @return
     */
    @RequestMapping("/collection")
    public R collection() {
        List<BbsPostsEntity> collectList = bbsPostsService.getPostByCollection(getUserId());
        return R.ok().put("data", collectList);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        bbsPostsEntity.setDel(true);
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }

    @RequestMapping("/good/{id}")
    public R good(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        Boolean flag = bbsPostsEntity.getGood();
        if (flag) {
            bbsPostsEntity.setGood(false);
        } else {
            bbsPostsEntity.setGood(true);
        }
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }

    @RequestMapping("/top/{id}")
    public R top(@PathVariable Integer id) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(id);
        Boolean flag = bbsPostsEntity.getTop();
        if (flag) {
            bbsPostsEntity.setTop(false);
        } else {
            bbsPostsEntity.setTop(true);
        }
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok("操作成功");
    }

    /**
     * 根据key值查询所有带key的数据
     *
     * @param keyword
     * @return
     */
    @RequestMapping("/search/{keyword}")
    public R search(@PathVariable String keyword) {
        String[] fieldNames = {"productName", "productDesc"};
        List<Map<String, Object>> list = esSearchService.queryHit(keyword, "orders", fieldNames);
        return R.ok().put("data", list);
    }


    /**
     * 获取文章更新数量
     *
     * @return
     */
    @RequestMapping("/getUpdateCount")
    public R getUpdateCount() {
        Map<String, Object> result = new HashMap<>();
        int todayCount = bbsPostsService.getTodayCount();
        int yesterdayCount = bbsPostsService.getYesterdayCount();
        int count = bbsPostsService.selectCount(new EntityWrapper<>());
        result.put("todayCount", todayCount);
        result.put("yesterdayCount", yesterdayCount);
        result.put("count", count);
        return R.ok().put("data", result);
    }

    /**
     * 获取首页置顶
     *
     * @return
     */
    @RequestMapping("/getNewPosts")
    public R getTopPosts(String currPage, String limit) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> page = new HashMap<>();
        page.put("currPage", currPage == null ? "0" : currPage);
        page.put("limit", limit == null ? "10" : limit);
        // 最新精华
        EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("good", true);
        wrapper.eq("is_del", false);
        wrapper.eq("is_temp", false);
        PageUtils goodList = bbsPostsService.queryPage(page, wrapper);
        // 最新发布
        EntityWrapper<BbsPostsEntity> wrapper2 = new EntityWrapper<>();
        wrapper2.eq("is_del", false);
        wrapper2.eq("is_temp", false);
        wrapper2.orderBy("init_time desc");
        PageUtils publishList = bbsPostsService.queryPage(page, wrapper2);
        // 最新回复
        List<BbsPostsEntity> replyList = bbsPostsService.getListByReplyTime(page);
        result.put("goodList", goodList.getList());
        result.put("publishList", publishList.getList());
        result.put("replyList", replyList);
        return R.ok().put("data", result);
    }

    /**
     * 查询文章列表
     * sortType (0 最新， 1 热门  2 精华)
     *
     * @param pageIndex
     * @param pageSize
     * @param sortType
     * @param tagId
     * @return
     */
    @RequestMapping("/getPostsList")
    public R getPostsList(Integer pageIndex, Integer pageSize, Integer sortType, Integer tagId, Integer labelId) {
        List<BbsPostsEntity> postsList = bbsPostsService.getPostsList(pageIndex, pageSize, sortType, tagId, labelId);
        return R.ok().put("data", postsList);
    }

    /**
     * 发布文章2
     *
     * @param bbsPosts
     * @return
     */
    @RequestMapping("/publish")
    public R publish(@RequestBody BbsPostsEntity bbsPosts) {
        bbsPosts.setInitTime(new Date());
//        bbsPosts.setUpdateTime(new Date());
        if (bbsPosts.getAnonymous() == false) {
            bbsPosts.setUserId(getUserId());
        }
        bbsPosts.setReadCount(0);
        bbsPostsService.insert(bbsPosts);
        ProductDocument productDocument = ProductDocumentBuilder.create()
                .addId(bbsPosts.getId().toString())
                .addProductName(bbsPosts.getTitle())
                .addLabel(bbsPosts.getLabelId().toString())
                .addProductDesc(bbsPosts.getContent())
                .addCreateTime(bbsPosts.getInitTime())
                .builder();
        try {
            esSearchService.save(productDocument);
        } catch (NoNodeAvailableException e) {
            e.printStackTrace();
        }
        return R.ok().put("data", bbsPosts);
    }

    /**
     * 更新文件路径
     *
     * @param bbsPostsFiles
     * @return
     */
    @PostMapping("/updateFile")
    public R updateFile(@RequestBody List<BbsPostsFileEntity> bbsPostsFiles) {
        bbsPostsFileService.insertBatch(bbsPostsFiles);
        return R.ok();
    }

    /**
     * 根据文章ID
     *
     * @param postsId
     * @return
     */
    @GetMapping("/getFileList/{postsId}")
    public R getFileList(@PathVariable Integer postsId) {
        EntityWrapper<BbsPostsFileEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("posts_id", postsId);
        List<BbsPostsFileEntity> bbsPostsFileList = bbsPostsFileService.selectList(wrapper);
        return R.ok().put("data", bbsPostsFileList);
    }

    /**
     * 增加用户阅读量
     *
     * @Author Mjp
     * @Date 13:18 2019/6/19
     * @Param [postsId]
     * @Return
     **/
    @GetMapping("/addRead/{postsId}")
    public R addRead(@PathVariable Integer postsId) {
        BbsPostsEntity bbsPostsEntity = bbsPostsService.selectById(postsId);
        if (bbsPostsEntity.getReadCount() == null) {
            bbsPostsEntity.setReadCount(1);
        } else {
            Integer count = bbsPostsEntity.getReadCount();
            bbsPostsEntity.setReadCount(count + 1);
        }
        bbsPostsService.updateById(bbsPostsEntity);
        return R.ok();
    }
}
