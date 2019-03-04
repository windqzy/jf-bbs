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
              <span v-if="postInfo.end" class="layui-badge" style="background-color: #999;">未结</span>
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

              </div>
              <span class="fly-list-nums">
                <a @click="$el.querySelector('#comment').scrollIntoView()" style="cursor: pointer">
                  <i class="iconfont" title="回答">&#xe60c;</i> {{postInfo.replyCount}}
                </a>
                <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
              </span>
            </div>
            <div class="detail-about">
              <router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-avatar">
                <img :src="postInfo.icon == null ? defaultAvatar : postInfo.icon"
                     :alt="postInfo.author">
              </router-link>
              <div class="fly-detail-user">
                <router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-link">
                  <cite>{{postInfo.author}}</cite>
                  <!--<i class="iconfont icon-renzheng" title="认证信息"></i>-->
                  <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                </router-link>
                <span>{{postInfo.initTime | dateStr}}</span>
              </div>
              <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                <span style="padding-right: 10px; color: #FF7200">悬赏：{{postInfo.rewardGrade}}钻石</span>
                <span v-if="userInfo.id == postInfo.userId" class="layui-btn layui-btn-xs jie-admin" type="edit">
                  <router-link :to="'/add/index?postId=' + postInfo.id">编辑此贴</router-link>
                </span>
              </div>
            </div>
            <!-- 文章内容 -->
            <div class="detail-body photos" v-html="postInfo.content"></div>
          </div>

          <!-- 回帖 -->
          <div class="fly-panel detail-box" id="flyReply">
            <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
              <legend>回帖</legend>
            </fieldset>
            <ul class="jieda" id="jieda">
              <li data-id="111" class="jieda-daan" v-for="reply in replyList">
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
                      <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                      <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                    </router-link>
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
                    <span type="accept" v-if="userInfo.id == postId">采纳</span>
                  </div>
                </div>
              </li>
              <!-- TODO：回复框 -->
              <!--<div class="reply" v-show="activeReplyed == index">-->
              <!--<input type="text" class="layui-input" :placeholder="'回复' + reply.author + '...'" id="replybtn">-->
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
                <a name="comment" id="comment"></a>
                <div class="layui-input-block">
                    <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"
                              class="layui-textarea fly-editor" style="height: 150px;" v-model="content">
                    </textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <input type="hidden" name="jid" value="123"/>
                <button class="layui-btn" lay-filter="*" @click="addReply()">提交回复</button>
                <button v-if="replyId != ''" class="layui-btn layui-btn-primary">取消</button>
              </div>
            </div>
          </div>
        </div>
        <div class="layui-col-md4">
          <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">本周热议</dt>
            <dd v-for="hot in hotList">
              <a style="cursor: pointer" @click="getDetailById(hot.id)">{{hot.title}}</a>
              <span><i class="iconfont icon-pinglun1"></i>{{hot.replyCount}}</span>
            </dd>

            <!-- 无数据时 -->
            <div v-if="hotList.length == 0" class="fly-none">没有相关数据</div>
          </dl>

          <div class="fly-panel">
            <div class="fly-panel-title">
              广告区域
            </div>
            <div class="fly-panel-main">
              <a @click="getAD" class="fly-zanzhu" style="background-color: #5FB878; cursor: pointer">敬请期待</a>
            </div>
          </div>

          <!--<div class="fly-panel" style="padding: 20px 0; text-align: center;">-->
          <!--<img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">-->
          <!--<p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>-->
          <!--</div>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  /*import Header from '@/components/Header';*/
  import * as post from '@/api/post';
  import * as reply from '@/api/reply';
  import * as time from '@/utils/time';
  import * as collection from '@/api/collection';

  export default {
    name: "detail",
    /*  components: {
        'v-header': Header
      },*/
    data() {
      return {
        postId: '',
        labelId: '',
        replyId: '',
        postInfo: '',
        content: '',
        replyList: [],
        hotList: [],
        editIndex: '',
        layedit: null,
        layer: null,
        userInfo: null,
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
        // activeReplyed: '-1',
      }
    },
    created() {
      this.postId = this.$route.query.postId;
      this.labelId = this.$route.query.labelId;
      this.userInfo = this.$store.getters.user;
      this.getDetailById(this.postId);
      this.getWeekHot();
    },
    mounted() {
      console.log('replyid+++++' + this.replyId)
      let _this = this;
      layui.use(['layedit', 'layer', 'upload'], function () {
        _this.layedit = layui.layedit;
        _this.layer = layui.layer;
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
      });
    },
    methods: {
      getDetailById(postId) {
        this.postId = postId;
        post.getDetail(postId).then(res => {
          console.log(res.data);
          this.postInfo = res.data;
          this.getReplyList(postId);
        })
      },
      getReplyList(postId) {
        reply.getList(postId).then(res => {
          console.log(res.data)
          this.replyList = res.data;
        })
      },
      getWeekHot() {
        console.log(this.labelId)
        let obj = {
          currPage: 1,
          pageSize: 10,
          sortType: 0,
          postType: 0,
          labelId: this.labelId,
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate(),
        }
        post.getList(obj).then(res => {
          this.hotList = res.data;
        })
      },
      addReply() {
        if (this.replyId == '') {
          let bbsReply = {
            postsId: this.postId,
            content: this.layedit.getContent(this.editIndex),
          }
          reply.addReply(bbsReply).then(res => {
            //TODO 提示回复成功
            this.getReplyList(this.postId);
            this.getDetailById(this.postId);
            this.getWeekHot();
            this.layedit.setContent(this.editIndex, '');
            this.layer.msg('回复成功')
          })
        } else {
          let bbsReply = {
            id: this.replyId,
            content: this.layedit.getContent(this.editIndex),
          }
          reply.updateReply(bbsReply).then(res => {
            //TODO 提示回复成功
            this.getReplyList(this.postId);
            this.getDetailById(this.postId);
            this.getWeekHot();
            this.layedit.setContent(this.editIndex, '');
            this.replyId = '';
            this.layer.msg('修改成功')
          })
        }
      },
      replyUp(replyId) {
        reply.replyUp(replyId).then(res => {
          console.log(res.data)
          this.getReplyList(this.postId);
        })
      },
      updateReply(content, replyId) {
        this.replyId = replyId;
        this.layedit.setContent(this.editIndex, content);
        this.$el.querySelector('#comment').scrollIntoView();
      },
      cancel() {
        this.layedit.setContent(this.editIndex, '');
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
        this.layedit.setContent(this.editIndex, user);
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

  .reply {
    padding: 8px;
    margin-top: 8px;
    background-color: #eee;
    .reply-btn {
      text-align: right;
      margin-top: 8px;
    }

  }
</style>
