<template>
  <div id="header">
    <div class="fly-header layui-bg-black">
      <div class="layui-container">
        <a class="fly-logo" @click="toNav(0)" style="cursor: pointer" >
          <img src="../../static/images/logo.png" alt="layui" class="layui-hide-xs">
          <img src="../../static/images/avatar/00.jpg" class="layui-hide-sm layui-show-xs-block" style="width: 40px" alt="layui">
        </a>
        <ul class="layui-nav fly-nav">
          <li class="layui-nav-item layui-hide-xs" :class='{"layui-this":activeNav == 0}'>
            <a style="cursor: pointer" @click="toNav(0)">论坛</a>
          </li>
          <li class="layui-nav-item layui-hide-xs" :class='{"layui-this":activeNav == 1}'>
            <a style="cursor: pointer" @click="toNav(1)">游戏</a>
          </li>
          <li class="layui-nav-item layui-hide-xs" :class='{"layui-this":activeNav == 2}'>
            <a style="cursor: pointer" @click="toNav(2)">Gitter</a>
          </li>
          <li class="layui-nav-item layui-hide-xs" :class='{"layui-this":activeNav == 2}'>
            <a style="cursor: pointer" @click="toNav(3)">大世界</a>
          </li>
        </ul>
        <ul class="layui-nav layui-hide-sm layui-show-xs-block" style="margin-left: 138px;">
          <li class="layui-nav-item">
            <a style="cursor: pointer" @click="toNav(0)">首页</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a style="cursor: pointer" @click="toNav(1)">游戏</a></dd>
              <dd><a style="cursor: pointer" @click="toNav(2)">Gitter</a></dd>
              <dd><a style="cursor: pointer" @click="toNav(3)">大世界</a></dd>
              <!--<dd><a href="">电商平台</a></dd>-->
            </dl>
          </li>
        </ul>

        <ul class="layui-nav fly-nav-user">
          <!-- 未登入的状态 -->
          <!--<li class="layui-nav-item">-->
          <!--<a class="iconfont icon-touxiang layui-hide-xs" href="user/login.html"></a>-->
          <!--</li>-->
          <!--<li class="layui-nav-item">-->
          <!--<a href="user/login.html">登入</a>-->
          <!--</li>-->
          <!--<li class="layui-nav-item">-->
          <!--<a href="user/reg.html">注册</a>-->
          <!--</li>-->
          <!--<li class="layui-nav-item layui-hide-xs">-->
          <!--<a href="/app/qq/" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" title="QQ登入" class="iconfont icon-qq"></a>-->
          <!--</li>-->
          <!--<li class="layui-nav-item layui-hide-xs">-->
          <!--<a href="/app/weibo/" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" title="微博登入" class="iconfont icon-weibo"></a>-->
          <!--</li>-->
          <!-- 登入后的状态 -->
          <li class="layui-nav-item">
            <!--<a class="fly-nav-avatar" href="javascript:;">-->
            <router-link :to="'/user/index?userId='+$store.getters.user.id" class="fly-nav-avatar">
              <cite class="layui-hide-xs">{{$store.getters.user.username}}</cite>
              <!--<i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>-->
              <!--<i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>-->
              <img :src="defaultAvatar">
            </router-link>
            <dl class="layui-nav-child">
              <dd>
                <router-link to="/set/index#basic">
                  <i class="layui-icon">&#xe620;</i>基本设置
                </router-link>
              </dd>
              <dd>
                <router-link to="/set/index#message">
                  <i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息
                </router-link>
              </dd>
              <dd>
                <router-link :to="'/user/index?userId=' + $store.getters.user.id">
                  <i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页
                </router-link>
              </dd>
              <hr style="margin: 5px 0;">
              <dd><a @click="logOut()" style="text-align: center; cursor: pointer">退出</a></dd>
            </dl>
          </li>
        </ul>
      </div>
    </div>
    <!-- 板块 -->
    <div class="fly-panel fly-column" v-show="isChildMenu">
      <div class="layui-container">
        <ul class="layui-clear">
          <li class="layui-hide-xs" :class='{"layui-this":activeLabel==-1}'>
            <a style="cursor: pointer;" @click="getPost(0, -1)">推荐</a>
          </li>
          <li v-for="(label, index) in labelList" class="layui-hide-xs" :class='{"layui-this":activeLabel==index}'>
            <a style="cursor: pointer;" @click="getPost(label.id, index)">{{label.name}}</a>
          </li>
          <li v-for="(label, index) in labelList" class="layui-hide-sm layui-show-xs-block" :class='{"layui-this":activeLabel==index}'>
            <a @click="getPost(label.id, index)">{{label.name}}</a>
          </li>
          <!--<li><a >分享<span class="layui-badge-dot"></span></a></li>-->
          <!--<li><a href="jie/index.html">讨论</a></li>-->
          <!--<li><a href="jie/index.html">建议</a></li>-->
          <!--<li><a href="jie/index.html">公告</a></li>-->
          <!--<li><a href="jie/index.html">动态</a></li>-->
          <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>-->

          <!--&lt;!&ndash; 用户登入后显示 &ndash;&gt;-->
          <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">-->
            <!--<router-link to="/set/index">我发表的贴</router-link>-->
          <!--</li>-->
          <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">-->
            <!--<router-link to="/set/index#collection">我收藏的贴</router-link>-->
          <!--</li>-->
        </ul>

        <div class="fly-column-right layui-hide-xs">
          <span class="fly-search"><i class="layui-icon"></i></span>
          <a @click="toAdd" class="layui-btn">发表新帖</a>
        </div>
        <div class="layui-hide-sm layui-show-xs-block"
             style="margin-top: -10px; padding-bottom: 10px; text-align: center;">
          <a @click="toAdd" class="layui-btn">发表新帖</a>
        </div>
      </div>
    </div>
    <!-- search -->
    <div id="searchBox" style="display: none">
      <input autocomplete="off" placeholder="搜索内容，回车跳转" v-model="searchText" type="text" @keyup.enter="search">
    </div>
  </div>
</template>

<script>
  import * as user from '@/api/user';
  import * as label from '@/api/label';

  export default {
    name: "Header",
    data() {
      return {
        labelList: [],
        activeLabel: -1,
        isChildMenu: true,
        userInfo: '',
        searchText: '',
        activeNav: 0
        // defaultAvatar: require('../../static/images/avatar/4.jpg')
      }
    },
    computed: {
      defaultAvatar() {
        if (!this.$store.getters.user.icon) {
          return require('../../static/images/avatar/4.jpg')
        } else {
          return this.$store.getters.user.icon
        }
      }
    },
    watch: {
      // 监听路由
      '$route': {
        handler(val) {
          if (val.path.indexOf('/home/index') !== -1 || val.path.indexOf('/post/detail') !== -1 || val.path.indexOf('/post/search') !== -1) {
            this.isChildMenu = true;
          } else {
            this.isChildMenu = false;
          }

          if (val.path.indexOf('/home/index') !== -1) {
            this.activeLabel = val.query.id - 1;
          }
        },
        immediate: true // 深度监听
      }
    },
    created() {
      this.getAllLabel();
      this.userInfo = this.$store.getters.user;
      // this.getUser();
    },
    mounted() {
      let _this = this;
      layui.use('element', function () {
        let element = layui.element;
        let $ = layui.jquery;
        //搜索
        $('.fly-search').on('click', function () {
          layer.open({
            type: 1
            , title: false
            , closeBtn: false
            //,shade: [0.1, '#fff']
            , shadeClose: true
            , maxWidth: 10000
            , skin: 'fly-layer-search'
            , content: $('#searchBox')
            , success: function (layero) {
              var input = layero.find('input');
              input.focus();
              // let key = encodeURIComponent(input.val()) ;
              // _this.$router.push('/post/search?key=' + key);
              // layero.find('form').submit(function () {
              //   var val = input.val();
              //   if (val.replace(/\s/g, '') === '') {
              //     return false;
              //   }
              //   input.val('site:www.bjjfsoft.com ' + input.val());
              // });
            }
          })
        });
      });
    },
    methods: {
      toNav(index) {
        this.activeNav = index;
        switch (index) {
          case 0: this.$router.push('/home/index?id=0'); break;
          case 1: this.$router.push('/games/index?id=1'); break;
          case 2: this.$router.push('/gitter/index?id=2'); break;
          case 3: this.$router.push('/world/index?id=2'); break;
        }
      },
      search() {
        this.$router.push('/post/search?key=' + this.searchText);
        console.log(this.searchText)
        layer.closeAll();
      },
      // 查询所有 Label
      getAllLabel() {
        label.getList().then(res => {
          console.log(res.data)
          this.labelList = res.data.list;
        })
      },
      getPost(id, index) {
        this.activeLabel = index;
        this.$router.push('/home/index?id=' + id);
      },
      toAdd() {
        this.isChildMenu = false;
        this.$router.push('/add/index');
      },
      getUser() {
        user.getUser().then(res => {
          console.log(res.data);
          this.userInfo = res.data;
        })
      },
      logOut() {
        window.localStorage.clear();
        this.$router.push('/login');
        window.location.reload();
      }
    }
  }
</script>

<style scoped>

</style>
