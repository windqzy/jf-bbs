<template>
  <div class="user_index">
    <div class="fly-home fly-panel">
      <img
        :src="userInfo.icon == null ? defaultAvatar : userInfo.icon">
      <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">-->
      <!--<i class="iconfont icon-renzheng" title="Fly社区认证"></i>-->
      <h1>
        {{userInfo.username}}
        <i class="iconfont icon-nan" v-if="userInfo.sex == 1"></i>
        <i class="iconfont icon-nv" v-if="userInfo.sex == 0"></i>
        <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
        <!--
        <span style="color:#c00;">（管理员）</span>
        <span style="color:#5FB878;">（社区之光）</span>
        <span>（该号已被封）</span>
        -->
      </h1>

      <!--<p style="padding: 10px 0; color: #5FB878;">认证信息：layui 作者</p>-->

      <p class="fly-home-info">
        <i class="layui-icon layui-icon-diamond" title="钻石"></i><span style="color: #FF7200;">{{grade}} 钻石</span>
        <i class="iconfont icon-shijian"></i><span>{{userInfo.initTime | filterDate}} 加入</span>
        <i class="iconfont icon-chengshi"></i><span>来自{{userInfo.city == null ? '地球' : userInfo.city}}</span>
      </p>

      <p class="fly-home-sign">{{ userInfo.signature}}</p>

      <!--<div class="fly-sns" data-user="">
        <a href="javascript:;" class="layui-btn layui-btn-primary fly-imActive" data-type="addFriend">加为好友</a>
        <a href="javascript:;" class="layui-btn layui-btn-normal fly-imActive" data-type="chat">发起会话</a>
      </div>-->

    </div>

    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md6 fly-home-jie">
          <div class="fly-panel" v-if="userInfo.enable == 1 || loginUserId == userInfo.id">
            <h3 class="fly-panel-title">{{userInfo.username}} 最近发布的文章</h3>
            <ul class="jie-row">
              <li v-for="post in postList">
                <span class="fly-jing" v-if="post.good">精</span>
                <router-link class="jie-title" :to="'/post/detail?postId=' + post.id">{{post.title}}</router-link>
                <i>{{post.initTime}}</i>
                <em class="layui-hide-xs">{{post.replyCount}}答</em>
              </li>
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>1天前</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>2017-10-30</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>1天前</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>1天前</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>1天前</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!--<li>-->
              <!--<a href="" class="jie-title"> 基于 layui 的极简社区页面模版</a>-->
              <!--<i>1天前</i>-->
              <!--<em class="layui-hide-xs">1136阅/27答</em>-->
              <!--</li>-->
              <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div> -->
            </ul>
          </div>
        </div>

        <div class="layui-col-md6 fly-home-da">
          <div class="fly-panel" v-if="userInfo.enable == 1 || loginUserId == userInfo.id">
            <h3 class="fly-panel-title">{{userInfo.username}} 最近的回答</h3>
            <ul class="home-jieda">
              <li v-for="reply in replyList">
                <p>
                  <span>{{reply.initTime}}</span>
                  <!--<a href="" target="_blank">{{reply.postsName}}</a>-->
                  在
                  <router-link class="jie-title" :to="'/post/detail?postId=' + reply.postsId">{{reply.postsName}}中回答：
                  </router-link>
                </p>
                <div class="home-dacontent" v-html="reply.content">

                </div>
              </li>
              <!-- <li>
                 <p>
                   <span>5分钟前</span>
                   在<a href="" target="_blank">在Fly社区用的是什么系统啊?</a>中回答：
                 </p>
                 <div class="home-dacontent">
                   Fly社区采用的是NodeJS。分享出来的只是前端模版
                 </div>
               </li>-->
              <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as grade from '@/api/grade'

  import * as post from '@/api/post'

  import * as reply from '@/api/reply'

  import * as user from '@/api/user'

  export default {
    name: "home",
    data() {
      return {
        userInfo: '',
        grade: 0,
        postList: [],
        replyList: [],
        userId: '',
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
        loginUserId: ''
      }
    },
    created() {
      this.userInfo = this.$store.getters.user;
      this.loginUserId = this.userInfo.id;
      this.userId = this.$route.query.userId;
      if (!this.userId) {
        this.userId = this.userInfo.id;
      } else {
        this.getOther();
      }
      this.getGrade();
      this.getPersonList();
      this.getPersonReplyList();
    },
    methods: {
      //获取用户信息
      getUser() {
        user.getUser().then(res => {
          console.log(res.data);
        })
      },
      //获取他人主页用户信息
      getOther() {
        user.getOther(this.userId).then(res => {
          console.log(res.data);
          this.userInfo = res.data;
        })
      },
      //获取积分
      getGrade() {
        let id = this.userId == undefined ? null : this.userId;
        grade.getGrade(id).then(res => {
          console.log(res.data);
          this.grade = res.data.grade;
        })
      },

      //获取用户发帖信息
      getPersonList() {
        let id = this.userId == undefined ? null : this.userId;
        console.log(id)
        post.getPersonList(id).then(res => {
          console.log(res.data);
          this.postList = res.data;
        })
      },

      //获取用户回复列表
      getPersonReplyList() {
        let id = this.userId == undefined ? null : this.userId;
        reply.getPersonReplyList(id).then(res => {
          this.replyList = res.data;
        })
      }
    },
    filters: {
      filterDate(data) {
        return data.substr(0, 10);
      }
    }
  }
</script>

<style scoped>

</style>
