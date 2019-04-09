<template>
  <div>
    <!--<v-header></v-header>-->
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
          <div class="fly-panel detail-box">
            <h1>{{postInfo.title}}</h1>
            <div class="fly-detail-info">
              <!-- <span class="layui-badge">审核中</span> -->
              <!--<span class="layui-badge layui-bg-green fly-detail-column">动态</span>-->
              <span v-if="!postInfo.end" class="layui-badge" style="background-color: #999;">未结</span>
              <span v-else class="layui-badge" style="background-color: #5FB878;">已结</span>

              <!--<span v-if="postInfo.top" class="layui-badge layui-bg-black">置顶</span>-->
              <!--<span v-if="postInfo.good" class="layui-badge layui-bg-red">精帖</span>-->

              <div class="fly-admin-box" data-id="123">
                <span v-if="userInfo.isAdmin" class="layui-btn layui-btn-danger layui-btn-xs jie-admin" type="del"
                      @click="delPostConfirm(postInfo.id)">删除</span>
                <span v-if="userInfo.isAdmin">
                  <span v-if="postInfo.top" class="layui-btn layui-btn-xs jie-admin" type="set" field="stick"
                        rank="0" @click="updatTop(postInfo.id)">取消置顶</span>
                  <span v-else class="layui-btn layui-btn-xs jie-admin" type="set" field="stick"
                        rank="1" @click="updatTop(postInfo.id)">置顶</span>
                </span>
                <span v-if="userInfo.isAdmin">
                  <span v-if="postInfo.good" class="layui-btn layui-btn-xs jie-admin" type="set" field="status"
                        rank="0" @click="updatGood(postInfo.id)">取消加精</span>
                  <span v-else class="layui-btn layui-btn-xs jie-admin" type="set" field="status"
                        rank="1" @click="updatGood(postInfo.id)">加精</span>
                </span>
                <span v-if="postInfo.collectionStatus" class="layui-btn layui-btn-xs jie-admin" type="set"
                      field="status" rank="0" style="background-color:#ccc;"
                      @click="collection(postInfo.id)">取消收藏</span>
                <span v-else class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1"
                      @click="collection(postInfo.id)">收藏</span>
                <span v-if="userInfo.id != postInfo.userId" class="layui-btn layui-btn-xs jie-admin" type="set"
                      field="status" rank="1"
                      @click="openReward">打赏</span>
              </div>
              <span class="fly-list-nums">
                <a @click="$el.querySelector('#comment').scrollIntoView()" style="cursor: pointer">
                  <i class="iconfont" title="回答">&#xe60c;</i> {{postInfo.replyCount}}
                </a>
                <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
              </span>
            </div>
            <div class="detail-about">
              <!--<router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-avatar">-->
              <!--<img :src="postInfo.icon == null ? defaultAvatar : postInfo.icon"-->
              <!--:alt="postInfo.author">-->
              <!--</router-link>-->
              <a class="fly-avatar">
                <img :src="postInfo.icon == null ? defaultAvatar : postInfo.icon"
                     :alt="postInfo.author">
              </a>
              <div class="fly-detail-user">
                <!--<router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-link">-->
                <!--<cite>{{postInfo.author}}</cite>-->
                <!--&lt;!&ndash;<i class="iconfont icon-renzheng" title="认证信息"></i>&ndash;&gt;-->
                <!--&lt;!&ndash;<i class="layui-badge fly-badge-vip">VIP3</i>&ndash;&gt;-->
                <!--</router-link>-->
                <a class="fly-link">
                  <cite>{{postInfo.author}}</cite>
                </a>
                <span>{{postInfo.initTime | dateStr}}</span>
              </div>
              <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                <span style="padding-right: 10px; color: #FF7200">悬赏：{{postInfo.rewardGrade}}钻石</span>
                <span v-if="userInfo.id == postInfo.userId && !postInfo.end && !postInfo.vote"
                      class="layui-btn layui-btn-xs jie-admin"
                      type="edit">
                  <router-link :to="'/add/index?postId=' + postInfo.id">编辑此贴</router-link>
                </span>
              </div>
            </div>
            <!-- 文章内容 -->
            <div class="detail-body photos" id="detail-body" v-html="postInfo.content" v-loading="loadDetail"></div>
            <!-- 投票 -->
            <div class="vote-box" v-if="postInfo.vote">
              {{'最多选' + voteInfo.maxSel + '项(截止日期：'+ voteInfo.endTime +')'}}
              <el-checkbox-group v-model="voteArr" @change="changeVote" :max="voteInfo.maxSel">
                <div v-for="item in voteInfo.optionList" style="margin: 10px 0;">
                  <el-checkbox :label="item.id" v-bind:checked="item.sel" :disabled="isVote">
                    {{item.content}}
                  </el-checkbox>
                  <div style="display: flex; align-items: center;">
                    <el-progress v-if="isVote || voteInfo.voteVisible" :percentage="item.optionCount" :show-text="false"
                                 stroke-width="12"></el-progress>
                    <el-tag size="mini" style="margin-left: 10px">{{item.optionCount}}已投票</el-tag>
                  </div>
                </div>
              </el-checkbox-group>
              <!-- TODO 根据用户是否对帖子投票判断是否显示 -->
              <button v-if="!isVote" class="layui-btn" @click="vote">投票</button>
            </div>
          </div>
          <!-- 热门回帖 -->
          <div class="fly-panel detail-box" id="flyReply" v-if="replyHotList.length != 0">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
              <legend>热门回复</legend>
            </fieldset>
            <!--<ul class="jieda" id="jieda">
              <li data-id="111" class="jieda-daan" v-for="reply in replyHotList">
                <a name="item-1111111111"></a>
                <div class="detail-about detail-about-reply">
                  <router-link :to="'/user/index?userId='+ reply.userId" class="fly-avatar">
                    <img
                      :src="reply.icon == null ? defaultAvatar : reply.icon"
                      :alt="reply.author">
                  </router-link>
                  <div class="fly-detail-user">
                    <router-link :to="'/user/index?userId='+ reply.userId" class="fly-link">
                      <cite>{{reply.author}}</cite>
                    </router-link>
                    <span v-if="reply.userId === postInfo.userId">(楼主)</span>
                  </div>
                  <div class="detail-hits">
                    <span>{{reply.initTime | dateStr}}</span>
                  </div>
                  <i v-if="reply.accept" class="iconfont icon-caina" title="最佳答案"></i>
                </div>
                &lt;!&ndash; 回复内容 &ndash;&gt;
                <div class="detail-body jieda-body photos" v-html="reply.content">

                </div>
                <div class="jieda-reply">
                  <span :class="{ zanok : reply.status}" class="jieda-zan" type="zan">
                    <i @click="replyUp(reply.id)" class="iconfont icon-zan"></i>
                    <em>{{reply.up}}</em>
                  </span>
                  <span type="reply" @click="childReply(reply)">
                  <i class="iconfont icon-svgmoban53"></i>
                    回复
                  </span>
                  <div class="jieda-admin">
                    <a href="javascript:;">
                      <span type="edit" v-if="userInfo.id == reply.userId"
                            @click="updateReply(reply.content, reply.id)">编辑</span>
                    </a>
                    <span type="del" v-if="userInfo.id == reply.userId" @click="delReply(reply.id)">删除</span>
                    <span type="accept" v-if="userInfo.id == postInfo.userId" v-show="!postInfo.end" @click="acceptReply(reply.id)">采纳</span>
                  </div>
                </div>
              </li>
            </ul>-->
            <ul class="jieda" id="jieda">
              <li data-id="111" class="jieda-daan" v-for="reply in replyHotList">
                <a name="item-1111111111"></a>
                <div class="detail-about detail-about-reply">
                  <!--<router-link :to="'/user/index?userId='+ reply.userId" class="fly-avatar">-->
                  <!--<img-->
                  <!--:src="reply.icon == null ? defaultAvatar : reply.icon"-->
                  <!--:alt="reply.author">-->
                  <!--</router-link>-->
                  <a class="fly-avatar">
                    <img
                      :src="reply.icon == null ? defaultAvatar : reply.icon"
                      :alt="reply.author">
                  </a>
                  <div class="fly-detail-user">
                    <!--<router-link :to="'/user/index?userId='+ reply.userId" class="fly-link">-->
                    <!--<cite>{{reply.author}}</cite>-->
                    <!--&lt;!&ndash;<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>&ndash;&gt;-->
                    <!--&lt;!&ndash;<i class="layui-badge fly-badge-vip">VIP3</i>&ndash;&gt;-->
                    <!--</router-link>-->
                    <a class="fly-link">
                      <cite>{{reply.author}}</cite>
                    </a>
                    <span v-if="reply.userId === postInfo.userId">(楼主)</span>
                    <!--
                    <span style="color:#5FB878">(管理员)</span>
                    <span style="color:#FF9E3F">（社区之光）</span>
                    <span style="color:#999">（该号已被封）</span>
                    -->
                  </div>
                  <div class="detail-hits">
                    <span>{{reply.initTime | dateStr}}</span>
                  </div>
                  <i v-if="reply.accept" class="iconfont icon-caina" title="最佳答案"></i>
                </div>
                <!-- 回复内容 -->
                <div class="detail-body jieda-body photos" v-html="reply.content">

                </div>
                <div class="jieda-reply">
                  <span :class="{ zanok : reply.status}" class="jieda-zan" type="zan">
                    <i @click="replyUp(reply.id)" class="iconfont icon-zan"></i>
                    <em>{{reply.up}}</em>
                  </span>
                  <span type="reply" @click="childReply(reply)">
                  <i class="iconfont icon-svgmoban53"></i>
                    回复
                  </span>
                  <div class="jieda-admin">
                    <a href="javascript:;">
                      <span type="edit" v-if="userInfo.id == reply.userId"
                            @click="updateReply(reply.content, reply.id)">编辑</span>
                    </a>
                    <span type="del" v-if="userInfo.id == reply.userId" @click="delReply(reply.id)">删除</span>
                    <span type="accept" v-if="userInfo.id == postInfo.userId" v-show="!postInfo.end"
                          @click="acceptReply(reply.id)">采纳</span>
                  </div>
                </div>
              </li>
            </ul>
          </div>
          <!-- 回帖 -->
          <div class="fly-panel detail-box" id="flyReply1" v-loading="loadReply">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
              <legend>最新回复</legend>
            </fieldset>
            <ul class="jieda" id="jieda">
              <li data-id="111" class="jieda-daan" v-for="reply in replyList">
                <a name="item-1111111111"></a>
                <div class="detail-about detail-about-reply">
                  <!--<router-link :to="'/user/index?userId='+ reply.userId" class="fly-avatar">-->
                  <!--<img-->
                  <!--:src="reply.icon == null ? defaultAvatar : reply.icon"-->
                  <!--:alt="reply.author">-->
                  <!--</router-link>-->
                  <a class="fly-avatar">
                    <img
                      :src="reply.icon == null ? defaultAvatar : reply.icon"
                      :alt="reply.author">
                  </a>
                  <div class="fly-detail-user">
                    <!--<router-link :to="'/user/index?userId='+ reply.userId" class="fly-link">-->
                    <!--<cite>{{reply.author}}</cite>-->
                    <!--&lt;!&ndash;<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>&ndash;&gt;-->
                    <!--&lt;!&ndash;<i class="layui-badge fly-badge-vip">VIP3</i>&ndash;&gt;-->
                    <!--</router-link>-->
                    <a class="fly-link">
                      <cite>{{reply.author}}</cite>
                    </a>
                    <span v-if="reply.userId === postInfo.userId">(楼主)</span>
                    <!--
                    <span style="color:#5FB878">(管理员)</span>
                    <span style="color:#FF9E3F">（社区之光）</span>
                    <span style="color:#999">（该号已被封）</span>
                    -->
                  </div>
                  <div class="detail-hits">
                    <span>{{reply.initTime | dateStr}}</span>
                  </div>
                  <i v-if="reply.accept" class="iconfont icon-caina" title="最佳答案"></i>
                </div>
                <!-- 回复内容 -->
                <div class="detail-body jieda-body photos" v-html="reply.content">

                </div>
                <div class="jieda-reply">
                  <span :class="{ zanok : reply.status}" class="jieda-zan" type="zan">
                    <i @click="replyUp(reply.id)" class="iconfont icon-zan"></i>
                    <em>{{reply.up}}</em>
                  </span>
                  <span type="reply" @click="childReply(reply)">
                  <i class="iconfont icon-svgmoban53"></i>
                    回复
                  </span>
                  <div class="jieda-admin">
                    <a href="javascript:;">
                      <span type="edit" v-if="userInfo.id == reply.userId"
                            @click="updateReply(reply.content, reply.id)">编辑</span>
                    </a>
                    <span type="del" v-if="userInfo.id == reply.userId" @click="delReply(reply.id)">删除</span>
                    <span type="accept" v-if="userInfo.id == postInfo.userId" v-show="!postInfo.end"
                          @click="acceptReply(reply.id,reply.userId)">采纳</span>
                  </div>
                </div>
              </li>
              <!-- TODO：回复框 -->
              <!--<div class="reply">-->
              <!--<input type="text" class="layui-input"  id="replybtn">-->
              <!--<div class="reply-btn">-->
              <!--<button class="layui-btn layui-btn-sm">表情</button>-->
              <!--<button class="layui-btn layui-btn-sm" @click="childReply(reply)">评 论</button>-->
              <!--</div>-->
              <!--</div>-->

              <!--<li data-id="111">-->
              <!--<a name="item-1111111111"></a>-->
              <!--<div class="detail-about detail-about-reply">-->
              <!--<a class="fly-avatar" href="">-->
              <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">-->
              <!--</a>-->
              <!--<div class="fly-detail-user">-->
              <!--<a href="" class="fly-link">-->
              <!--<cite>贤心</cite>-->
              <!--</a>-->
              <!--</div>-->
              <!--<div class="detail-hits">-->
              <!--<span>2017-11-30</span>-->
              <!--</div>-->
              <!--</div>-->
              <!--<div class="detail-body jieda-body photos">-->
              <!--<p>蓝瘦那个香菇，这是一条没被采纳的回帖</p>-->
              <!--</div>-->
              <!--<div class="jieda-reply">-->
              <!--<span class="jieda-zan" type="zan">-->
              <!--<i class="iconfont icon-zan"></i>-->
              <!--<em>0</em>-->
              <!--</span>-->
              <!--<span type="reply">-->
              <!--<i class="iconfont icon-svgmoban53"></i>-->
              <!--回复-->
              <!--</span>-->
              <!--<div class="jieda-admin">-->
              <!--<span type="edit">编辑</span>-->
              <!--<span type="del">删除</span>-->
              <!--<span class="jieda-accept" type="accept">采纳</span>-->
              <!--</div>-->
              <!--</div>-->
              <!--</li>-->
              <!-- 无数据时 -->
              <li class="fly-none" v-if="replyList.length == 0">消灭零回复</li>
            </ul>

            <div class="layui-form layui-form-pane">
              <div class="layui-form-item layui-form-text">

                <div class="layui-input-block">
                  <!--<textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"-->
                  <!--class="layui-textarea fly-editor" style="height: 150px;" v-model="content">-->
                  <!--</textarea>-->
                  <div name="comment" id="comment" style="padding-top: 100px"></div>
                  <div ref="editor" style="text-align:left"></div>
                </div>
              </div>
              <div class="layui-form-item">
                <input type="hidden" name="jid" value="123"/>
                <button class="layui-btn" lay-filter="*" @click="addReply()">提交回复</button>
                <button v-if="replyId != ''" class="layui-btn layui-btn-primary" @click="cancel()">取消</button>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-col-md4">
          <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">打赏榜</dt>
            <dd v-for="reward in rewardList">
              <a style="cursor: pointer">{{reward.rewardName}}</a>
              <span><i class="layui-icon layui-icon-diamond"></i>{{reward.rewardMoney}}</span>
            </dd>


            <!-- 无数据时 -->
            <div v-if="hotList.length == 0" class="fly-none">没有相关数据</div>
          </dl>


          <!--<div class="fly-panel">-->
          <!--<div class="fly-panel-title">-->
          <!--广告区域-->
          <!--</div>-->
          <!--<div class="fly-panel-main">-->
          <!--<a @click="getAD" class="fly-zanzhu" style="background-color: #5FB878; cursor: pointer">敬请期待</a>-->
          <!--</div>-->
          <!--</div>-->

          <div class="fly-panel" style="padding: 20px 0; text-align: center;">
            <img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
            <p style="position: relative; color: #666;">微信扫码关注 金风推特 公众号</p>
          </div>
        </div>
      </div>
    </div>
    <!-- 悬赏 -->
    <el-dialog
      title="打赏作者"
      :visible.sync="rewardBox" width="280px">
      <el-radio-group v-model="rewardGrade">
        <el-radio-button v-if="currGrade >= 10" label="10"></el-radio-button>
        <el-radio-button v-if="currGrade >= 20" label="20"></el-radio-button>
        <el-radio-button v-if="currGrade >= 50" label="50"></el-radio-button>
        <el-radio-button v-if="currGrade >= 100" label="100"></el-radio-button>
      </el-radio-group>
      <span slot="footer" class="dialog-footer">
        <el-button @click="rewardBox = false">取 消</el-button>
        <el-button type="primary" @click="reward">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import * as post from '@/api/post';
  import * as vote from '@/api/vote';
  import * as reply from '@/api/reply';
  import * as time from '@/utils/time';
  import * as collection from '@/api/collection';
  import * as grade from '@/api/grade';
  import E from 'wangeditor'
  import * as face from '@/assets/face.json'

  export default {
    name: "detail",
    data() {
      return {
        postId: '',
        labelId: '',
        replyId: '',
        postInfo: '',
        content: '',
        replyHotList: [],// 热门回帖
        replyList: [],
        rewardList: [],
        hotList: [],
        editIndex: '',
        layedit: null,
        layelement: null,
        layer: null,
        form: null,
        userInfo: null,
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
        // activeReplyed: '-1',
        voteArr: [],
        voteMin: 1,
        voteMax: 2,
        voteInfo: '',
        isVote: '',
        loadReply: false,
        loadDetail: false,
        rewardBox: false,
        rewardGrade: 0,
        currGrade: 0,
        editorContent: '',
        faceList: [],
        editor: null
      }
    },
    created() {
      this.postId = this.$route.query.postId;
      this.labelId = this.$route.query.labelId;
      this.userInfo = this.$store.getters.user;
      this.getDetailById(this.postId);
      this.getTopThree(this.$route.query.postId)
      this.getWeekHot();
      this.isVoted();
      this.getCurrGrade();
      this.getFace();
    },
    mounted() {
      this.layui();
      this.editor = new E(this.$refs.editor)
      this.editor.customConfig.onchange = (html) => {
        this.editorContent = html
      }
      this.editor.customConfig.uploadImgServer = window.localStorage.baseUrl + '/upload/file2';
      this.editor.customConfig.uploadFileName = 'file';
      this.editor.customConfig.zIndex = 100
      // 自定义菜单配置
      this.editor.customConfig.menus = [
        'emoticon',
        'image',
        'link',
        'code'
      ];
      this.editor.customConfig.emotions = [
        {
          // tab 的标题
          title: '默认',
          // type -> 'emoji' / 'image'
          type: 'image',
          // content -> 数组
          content: face
        },
        // {
        //   // tab 的标题
        //   title: 'emoji',
        //   // type -> 'emoji' / 'image'
        //   type: 'emoji',
        //   // content -> 数组
        //   content: [
        //     '😀', '😁', '😂', '🤣', '😃', '😅', '😆', '😉', '😊', '😋', '😎', '😍', '😘', '🥰', '😗', '😙', '😚', '💩', '🤮',
        //     '☺', '🙂', '🤗', '🤩', '🤔', '🤨', '😐', '😑', '😶', '🙄', '😏', '😣', '😥', '😮', '🤐', '😯', '😪', '😫', '😴',
        //     '😌', '😛', '😜', '😝', '🤤 ', '😒', '😓', '😕', '🙃', '🤑', '😲', '☹', '🙁',
        //   ]
        // }
      ];
      this.editor.create()
    },
    beforeDestroy() {
      this.layer.closeAll();
    },
    methods: {
      openReward() {
        if (this.currGrade >= 10) {
          this.rewardBox = true;
        } else {
          layer.msg('你太穷了，不能打赏')
        }
      },
      getFace() {
        // let arr = [];
        // face.map(item => {
        //   arr.push({
        //     alt: item.value,
        //     src: item.icon
        //   })
        // });
        // this.faceList = arr;
      },
      layui() {
        let _this = this;
        layui.use(['layedit', 'layer', 'upload', 'form', 'element'], function () {
          _this.layedit = layui.layedit;
          _this.layer = layui.layer;
          _this.form = layui.form;
          _this.layelement = layui.element;
          _this.layedit.set({
            uploadImage: {
              url: window.localStorage.baseUrl + '/upload/file',
              type: 'post' //默认post
            }
          });

          _this.editIndex = _this.layedit.build('L_content', {
            height: 191,
            tool: ['face', 'image', 'link', 'code'],
          }); //建立编辑器

          _this.layer.photos({
            photos: '#detail-body',
            anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
          });
        });
      },
      getDetailById(postId) {
        this.postId = postId;
        this.loadDetail = true;
        post.getDetail(postId).then(res => {
          this.loadDetail = false;
          this.postInfo = res.data;
          this.getReplyList(postId);
          this.$nextTick(() => {
            this.layui();
          });
          // 判断是否为投票贴
          if (this.postInfo.vote) {
            this.getVoteList(this.postInfo.id)
          }
        })
      },
      getReplyList(postId) {
        this.loadDetail = true;
        reply.getList(postId).then(res => {
          this.loadDetail = false;
          this.replyList = res.data;
        })
        reply.getHotList(postId).then(res => {
          this.loadDetail = false;
          this.replyHotList = res.data;
        })
      },
      getWeekHot() {
        // console.log(this.labelId)
        let obj = {
          currPage: 1,
          pageSize: 10,
          sortType: 0,
          postType: 0,
          labelId: this.labelId,
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate(),
          //TODO
          tagId: ''
        }
        post.getList(obj).then(res => {
          this.hotList = res.data;
        })
      },
      getTopThree(postsId){
      console.log(postsId)
      grade.getTopThree(postsId).then(res=>{
        console.log(res.data)
        this.rewardList=res.data
      })
      },
      addReply() {
        if (this.replyId == '') {
          let bbsReply = {
            postsId: this.postId,
            content: this.editorContent,
          }
          reply.addReply(bbsReply).then(res => {
            this.editor.txt.clear();
            //  提示回复成功
            this.getReplyList(this.postId);
            this.getDetailById(this.postId);
            this.getWeekHot();
            this.layer.msg('回复成功')
          })
        } else {
          let bbsReply = {
            id: this.replyId,
            content: this.editorContent,
          }
          reply.updateReply(bbsReply).then(res => {
            // 提示回复成功
            this.getReplyList(this.postId);
            this.getDetailById(this.postId);
            this.getWeekHot();
            this.editor.txt.clear();
            this.replyId = '';
            this.layer.msg('修改成功')
          })
        }
      },
      replyUp(replyId) {
        reply.replyUp(replyId).then(res => {
          // console.log(res.data)
          this.getReplyList(this.postId);
        })
      },
      updateReply(content, replyId) {
        this.replyId = replyId;
        //this.layedit.setContent(this.editIndex, content);
        this.editor.txt.html(content);
        this.$el.querySelector('#comment').scrollIntoView();
      },
      cancel() {
        this.editor.txt.clear();
        this.replyId = '';
      },
      delReply(replyId) {
        let _this = this;
        this.layer.confirm('是否删除该评论？', {
          btn: ['是', '否'] //按钮
        }, function () {
          reply.delReply(replyId).then(res => {
            _this.getReplyList(_this.postId);
            _this.getDetailById(_this.postId);
            _this.getWeekHot();
            _this.layer.msg("删除成功");
          })
        })
      },
      acceptReply(replyId, userId) {
        let _this = this;
        this.layer.confirm('是否采纳该评论？', {
          btn: ['是', '否'] //按钮
        }, function () {
          reply.acceptReply(replyId, userId).then(res => {
            _this.getReplyList(_this.postId);
            _this.getDetailById(_this.postId);
            _this.getWeekHot();
            _this.layer.msg("采纳成功");
          })
        })
      },
      collection(postId) {
        collection.addCollection(postId).then(res => {
          // console.log(res.data)
          this.getDetailById(this.postId);
          this.layer.msg(res.data);
        })
      },
      updatTop(id) {
        post.updatTop(id).then(res => {
          this.getDetailById(this.postId);
          this.layer.msg(res.msg);
        })
      },
      updatGood(id) {
        post.updatGood(id).then(res => {
          this.getDetailById(this.postId);
          this.layer.msg(res.msg);
        })
      },
      delPostConfirm(id) {
        let _this = this;
        this.layer.confirm('是否删除此贴？', {
          btn: ['是', '否'] //按钮
        }, function () {
          _this.delPost(id);
        })
      },
      delPost(id) {
        post.del(id).then(res => {
          this.$router.push('/home/index')
          this.layer.msg(res.msg);
        })
      },
      getAD() {
        this.layer.msg('多攒的钻石，就可以买广告位了');
      },
      //  评论回复
      childReply(e) {
        this.$el.querySelector('#comment').scrollIntoView();
        let user = '<a href="#/user/index?userId=' + e.userId + '" class="fly-link">@' + e.author + '&nbsp;</a>';
        this.editor.txt.html(user);
      },
      // TODO:投票
      changeVote(val) {
        // console.log("changeVote" + val);
      },
      vote() {
        if (this.voteArr.length < this.voteInfo.minSel) {
          this.layer.msg("您至少选择" + this.voteInfo.minSel + "个选项！！！");
        } else {
          // console.log("voteArr  " + this.voteArr)
          vote.userVote(this.voteInfo.id, this.voteArr).then(res => {
            this.layer.msg(res.data);
            this.isVoted();
            this.getVoteList(this.postId);
          })
        }
      },
      isVoted() {
        vote.isVoted(this.postId).then(res => {
          this.isVote = res.data;
        })
      },
      getVoteList(postId) {
        vote.getList(postId).then(res => {
          this.voteInfo = res.data;
        })
      },
      // 打赏
      reward() {
        grade.reward(this.userInfo.id, this.postInfo.userId, this.rewardGrade, this.postId).then(res => {
          this.rewardBox = false;
          this.getTopThree(this.postId);
          this.getCurrGrade();
          layer.msg(res.msg);
        })
      },
      // 获取当前钻石
      getCurrGrade() {
        grade.getGrade(this.userInfo.id).then(res => {
          this.currGrade = res.data.grade;
        })
      }
    },
    filters: {
      dateStr(dateTimeStamp) {
        var minute = 1000 * 60;
        var hour = minute * 60;
        var day = hour * 24;
        var halfamonth = day * 15;
        var month = day * 30;
        if (dateTimeStamp == undefined) {
          return false;
        } else {
          dateTimeStamp = dateTimeStamp.replace(/\-/g, "/");
          var sTime = new Date(dateTimeStamp).getTime();//把时间pretime的值转为时间戳
          var now = new Date().getTime();//获取当前时间的时间戳
          var diffValue = now - sTime;
          if (diffValue < 0) {
            console.log("结束日期不能小于开始日期！");
          }
          var monthC = diffValue / month;
          var weekC = diffValue / (7 * day);
          var dayC = diffValue / day;
          var hourC = diffValue / hour;
          var minC = diffValue / minute;
          if (monthC >= 1) {
            return parseInt(monthC) + "个月前";
          } else if (weekC >= 1) {
            return parseInt(weekC) + "周前";
          } else if (dayC >= 1) {
            return parseInt(dayC) + "天前";
          } else if (hourC >= 1) {
            return parseInt(hourC) + "个小时前";
          } else if (minC >= 1) {
            return parseInt(minC) + "分钟前"
          } else {
            return "刚刚";
          }
        }
      },
    }
  };
</script>

<style scoped lang="scss">
  video {
    width: 100%;
  }

  .fly-list-one dd span {
    float: right;
  }

  .icon-pinglun1 {
    right: 5px;
  }

  .layui-icon-diamond {
    right: 5px;
  }

  .detail-body {
    img {
      cursor: zoom-in;
    }
  }

  /deep/ .w-e-text {
    overflow-y: auto;

    p {
      margin: 10px 0;
    }
  }

  /deep/ .el-progress {
    width: 50%;
  }

  /deep/ .w-e-toolbar {
    height: 43px;
    line-height: 36px;
    background-color: #fff !important;

    i {
      color: #009688 !important;
      font-size: 16px;
    }
  }

  /deep/ .w-e-panel-container {
    width: 66% !important;
    margin-left: -50% !important;
  }

  /deep/ .w-e-text-container {
    height: 190px !important;
  }

  /deep/ .w-e-text-container .w-e-panel-container .w-e-emoticon-container .w-e-item {
    margin: 1px;
  }
  /deep/ .w-e-text-container .w-e-panel-container .w-e-panel-tab-content {
    padding: 10px 0px 10px 9px;
  }

    /* 投票 */
  .vote-box {
    li {
      position: relative;
      margin-top: 4px;

      .progress {
        height: 20px;
        margin: 4px 0 10px;
        background-color: #fff;

        div {
          height: 100%;
          background-color: #2baee9;
        }
      }
    }
  }

  /*滚动条样式*/
  /deep/ .w-e-panel-tab-content::-webkit-scrollbar { /*高宽分别对应横竖滚动条的尺寸*/
    width: 4px;
    height: 10px;
  }
  /deep/ .w-e-panel-tab-content::-webkit-scrollbar-thumb {/*滚动条里面小方块*/
    border-radius: 2px;
    /*-webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);*/
    background: hsla(220, 5%, 80%, .5);
  }
  /deep/ .w-e-panel-tab-content::-webkit-scrollbar-track {/*滚动条里面轨道*/
    /*-webkit-box-shadow: inset 0 0 5px rgba(0,0,0,0.2);*/
    border-radius: 0;
    background: rgba(0, 0, 0, 0);
  }
</style>
