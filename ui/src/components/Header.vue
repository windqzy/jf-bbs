<template>
  <div id="header">
    <div class="fly-header layui-bg-black">
      <div class="layui-container">
        <router-link class="fly-logo" to="/home/index">
          <img src="../../static/images/logo.png" alt="layui">
        </router-link>
        <ul class="layui-nav fly-nav layui-hide-xs">
          <li class="layui-nav-item layui-this">
            <router-link to="/home/index"><i class="iconfont icon-jiaoliu"></i>交流</router-link>
          </li>
          <li class="layui-nav-item">
            <router-link to="/case/index"><i class="iconfont icon-iconmingxinganli"></i>案例</router-link>
          </li>
          <!--<li class="layui-nav-item">-->
          <!--<a href="http://www.layui.com/" target="_blank"><i class="iconfont icon-ui"></i>框架</a>-->
          <!--</li>-->
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
            <a class="fly-nav-avatar" href="javascript:;">
              <cite class="layui-hide-xs">{{$store.getters.user.username}}</cite>
              <!--<i class="iconfont icon-renzheng layui-hide-xs" title="认证信息：layui 作者"></i>-->
              <!--<i class="layui-badge fly-badge-vip layui-hide-xs">VIP3</i>-->
              <img
                :src="userInfo.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : userInfo.icon">
            </a>
            <dl class="layui-nav-child">
              <dd>
                <router-link to="/set/index"><i class="layui-icon">&#xe620;</i>基本设置</router-link>
              </dd>
              <dd><a href="user/message.html"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
              <dd>
                <router-link to="/user/index">
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
            <a style="cursor: pointer" @click="getPost(0, -1)">首页</a>
          </li>
          <li v-for="(label, index) in labelList" class="layui-hide-xs" :class='{"layui-this":activeLabel==index}'>
            <a style="cursor: pointer" @click="getPost(label.id, index)">{{label.name}}</a>
          </li>
          <li v-for="(label, index) in labelList" class="layui-hide-sm layui-show-xs-block">
            <a @click="getPost(label.id, index)">{{label.name}}</a>
          </li>
          <!--<li><a >分享<span class="layui-badge-dot"></span></a></li>-->
          <!--<li><a href="jie/index.html">讨论</a></li>-->
          <!--<li><a href="jie/index.html">建议</a></li>-->
          <!--<li><a href="jie/index.html">公告</a></li>-->
          <!--<li><a href="jie/index.html">动态</a></li>-->
          <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li>

          <!-- 用户登入后显示 -->
          <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
            <router-link to="/set/index">我发表的贴</router-link>
          </li>
          <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">
            <router-link to="/set/index#collection">我收藏的贴</router-link>
          </li>
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
        userInfo: ''
      }
    },
    watch: {
      // 监听路由
      '$route': {
        handler(val) {
          if (val.path.indexOf('/home/index') !== -1 || val.path.indexOf('/post/detail') !== -1) {
            this.isChildMenu = true;
          } else {
            this.isChildMenu = false;
          }
        },
        immediate: true // 深度监听
      }
    },
    created() {
      this.getAllLabel();
      this.getUser();
    },
    mounted() {
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
            , content: ['<form action="http://cn.bing.com/search">'
              , '<input autocomplete="off" placeholder="搜索内容，回车跳转" type="text" name="q">'
              , '</form>'].join('')
            , success: function (layero) {
              var input = layero.find('input');
              input.focus();

              layero.find('form').submit(function () {
                var val = input.val();
                if (val.replace(/\s/g, '') === '') {
                  return false;
                }
                input.val('site:www.bjjfsoft.com ' + input.val());
              });
            }
          })
        });
      });
    },
    methods: {
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
        let token = window.localStorage['B-Token'];
        window.localStorage.clear();
        // window.localStorage['B-Token'] = token;
        this.$router.push('/');
      }
    }
  }
</script>

<style scoped>

</style>
