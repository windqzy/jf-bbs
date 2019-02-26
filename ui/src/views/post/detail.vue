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

              <span v-if="postInfo.top" class="layui-badge layui-bg-black">置顶</span>
              <span v-if="postInfo.good" class="layui-badge layui-bg-red">精帖</span>

              <div class="fly-admin-box" data-id="123">
                <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>
                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->
                <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
              </div>
              <span class="fly-list-nums">
                <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> {{postInfo.replyCount}}</a>
                <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
              </span>
            </div>
            <div class="detail-about">
              <a class="fly-avatar" href="../user/home.html">
                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"
                     alt="postInfo.author">
              </a>
              <div class="fly-detail-user">
                <a href="../user/home.html" class="fly-link">
                  <cite>{{postInfo.author}}</cite>
                  <!--<i class="iconfont icon-renzheng" title="认证信息"></i>-->
                  <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                </a>
                <span>{{postInfo.initTime}}</span>
              </div>
              <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                <!--<span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span>-->
                <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
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
                  <a class="fly-avatar" href="">
                    <img :src="reply.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : reply.icon"
                         :alt="reply.author">
                  </a>
                  <div class="fly-detail-user">
                    <a href="" class="fly-link">
                      <cite>{{reply.author}}</cite>
                      <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                      <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                    </a>
                    <span v-if="reply.userId === postInfo.userId">(楼主)</span>
                    <!--
                    <span style="color:#5FB878">(管理员)</span>
                    <span style="color:#FF9E3F">（社区之光）</span>
                    <span style="color:#999">（该号已被封）</span>
                    -->
                  </div>
                  <div class="detail-hits">
                    <span>{{reply.initTime}}</span>
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
                  <span type="reply">
                  <i class="iconfont icon-svgmoban53"></i>
                    回复
                  </span>
                  <div class="jieda-admin">
                    <span type="edit">编辑</span>
                    <span type="del">删除</span>
                    <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                  </div>
                </div>
              </li>
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
                <a name="comment"></a>
                <div class="layui-input-block">
                    <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"
                              class="layui-textarea fly-editor" style="height: 150px;" v-model="content"></textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <input type="hidden" name="jid" value="123"/>
                <button class="layui-btn" lay-filter="*" @click="addReply()">提交回复</button>
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
              这里可作为广告区域
            </div>
            <div class="fly-panel-main">
              <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"
                 time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
            </div>
          </div>

          <div class="fly-panel" style="padding: 20px 0; text-align: center;">
            <img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
            <p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>
          </div>
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

  export default {
    name: "detail",
    /*  components: {
        'v-header': Header
      },*/
    data() {
      return {
        postId: '',
        labelId: '',
        postInfo: '',
        content: '',
        replyList: [],
        hotList: [],
        editIndex: '',
        layedit: null
      }
    },
    created() {
      this.postId = this.$route.query.postId;
      this.labelId = this.$route.query.labelId;
      this.getDetailById(this.postId);
      this.getWeekHot();
    },
    mounted() {
      let _this = this;
      layui.use('layedit', function(){
        _this.layedit = layui.layedit;
        _this.editIndex = _this.layedit.build('L_content', {
          height: 191
        }); //建立编辑器
      });
    },
    methods: {
      getDetailById(postId) {
        post.getDetail(postId).then(res => {
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
        // console.log(obj)
        post.getList(obj).then(res => {
          this.hotList = res.data;
        })
      },
      addReply() {
        let bbsReply = {
          postsId: this.postId,
          content: this.layedit.getContent(this.editIndex),
        }

        reply.addReply(bbsReply).then(res => {
          //TODO 提示回复成功
          this.getReplyList(this.postId);
          this.layedit.setContent(this.editIndex, '');
        })
      },
      replyUp(replyId) {
        reply.replyUp(replyId).then(res => {
          console.log(res.data)
          this.getReplyList(this.postId);
        })
      }
    }
  }
</script>

<style scoped>

</style>
