<!--用户中心-->
<template>
  <div class="layui-tab layui-tab-brief" lay-filter="user">
    <ul class="layui-tab-title" id="LAY_mine">
      <li data-type="mine-jie" lay-id="index" :class="{'layui-this': !isCollection}">
        我发的帖（<span>{{userPostList.length}}</span>）
      </li>
      <li data-type="collection" data-url="/collection/find/" lay-id="collection" :class="{'layui-this': isCollection}">
        我收藏的帖（<span>{{collectList.length}}</span>）
      </li>
    </ul>
    <div class="layui-tab-content" style="padding: 20px 0;">
      <div class="layui-tab-item" :class="{'layui-show': !isCollection}">
        <ul class="mine-view jie-row">
          <li v-for="userPost in userPostList">
            <router-link :to="'/post/detail?postId=' + userPost.id + '&labelId=' + userPost.labelId">{{userPost.title}}
            </router-link>
            <i>{{userPost.initTime}}</i>
            <router-link :to="'/add/index?postId=' +userPost.id" class="mine-edit">编辑</router-link>
            <em>{{userPost.replyCount}}答</em>
            <!--<em>661阅/{{userPost.replyCount}}答</em>--> <!-- 暂不显示阅读数量 -->
          </li>
          <!--<li>-->
          <!--<a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>-->
          <!--<i>2017/3/14 上午8:30:00</i>-->
          <!--<a class="mine-edit" href="/jie/edit/8116">编辑</a>-->
          <!--<em>661阅/10答</em>-->
          <!--</li>-->
          <!--<li>-->
          <!--<a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>-->
          <!--<i>2017/3/14 上午8:30:00</i>-->
          <!--<a class="mine-edit" href="/jie/edit/8116">编辑</a>-->
          <!--<em>661阅/10答</em>-->
          <!--</li>-->
        </ul>
        <div id="LAY_page"></div>
      </div>
      <div class="layui-tab-item" :class="{'layui-show': isCollection}">
        <ul class="mine-view jie-row">
          <li v-for="post in collectList">
            <router-link :to="'/post/detail?postId=' + post.id + '&labelId=' + post.labelId">{{post.title}}
            </router-link>
            <i>{{post.collectionTime}}</i>
          </li>
        </ul>
        <div id="LAY_page1"></div>
      </div>
    </div>
  </div>
</template>

<script>
  /*import Header from '@/components/Header';*/
  import * as post from '@/api/post';

  export default {
    name: "user",
    data() {
      return {
        isCollection: false,// 收藏
        userPostList: [],   //用户发表的帖子信息
        collectList: [],   //用户收藏的帖子信息
      }
    },
    created() {
      //  验证是否展示收藏贴
      if (this.$route.hash == '#collection') {
        this.isCollection = true;
      }
      ;
      this.getUserPostList();
      this.getCollecList();
    },
    mounted() {
      let _this = this;
      layui.use(['layer'], function () {
        _this.layer = layui.layer;
        _this.$ = layui.jquery
      });
    },
    methods: {
      getCollecList() {
        post.getCollecList().then(res => {
          console.log(res.data);
          this.collectList = res.data;
        })
      },
      getUserPostList() {
        post.getPersonList().then(res => {
          console.log(res.data);
          this.userPostList = res.data;
        })
      }
    }

  }
</script>

<style scoped>

</style>
