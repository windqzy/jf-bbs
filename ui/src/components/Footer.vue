<template>
  <div class="fly-footer">
    <p>
      <a href="javascript:;" target="_blank">金风社区</a>
      2019 &copy; <a href="javascript:;" target="_blank">金风易通科技有限公司出品</a>
      <a class="onLineUserCount">当前在线人数：<span>{{onLineUserCount}}</span></a>
    </p>

    <!--<p>-->
    <!--<a href="http://fly.layui.com/jie/3147/" target="_blank">付费计划</a>-->
    <!--<a href="http://www.layui.com/template/fly/" target="_blank">获取Fly社区模版</a>-->
    <!--<a href="http://fly.layui.com/jie/2461/" target="_blank">微信公众号</a>-->
    <!--</p>-->
  </div>
</template>

<script>
  export default {
    name: "Footer",
    props:{
      onLineUserCount: Number
    },
    data() {
      return {
        layUtil: null
      }
    },
    mounted() {
      // this.onLineUserCount = this.$store.state.user.onLineUserCount;
      // let _this = this;
      // layui.use(['layer', 'util', 'element'], function () {
      //   _this.layUtil = layui.util;
      //   _this.layUtil.fixbar({
      //     bar1: '&#xe642;'
      //     , bgcolor: '#009688'
      //     , click: function (type) {
      //       if (type === 'bar1') {
      //         _this.$router.push('/add/index');
      //         // layer.msg('打开 index.js，开启发表新帖的路径');
      //         //location.href = 'jie/add.html';
      //       }
      //     }
      //   });
      // });
    }
  }
</script>

<style scoped lang="scss">
.onLineUserCount {
  color: #666;
  span {
    color: #f00;
  }
}
</style>
