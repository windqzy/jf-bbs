<!--用户中心-->
<template>
  <div class="layui-tab layui-tab-brief" lay-filter="user">
    <ul class="layui-tab-title" id="LAY_mine">
      <li data-type="mine-jie" lay-id="index" :class="{'layui-this': !isCollection}">我发的帖（<span>89</span>）</li>
      <li data-type="collection" data-url="/collection/find/" lay-id="collection" :class="{'layui-this': isCollection}">
        我收藏的帖（<span>16</span>）
      </li>
    </ul>
    <div class="layui-tab-content" style="padding: 20px 0;">
      <div class="layui-tab-item" :class="{'layui-show': !isCollection}">
        <ul class="mine-view jie-row">
          <li>
            <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
            <i>2017/3/14 上午8:30:00</i>
            <a class="mine-edit" href="/jie/edit/8116">编辑</a>
            <em>661阅/10答</em>
          </li>
          <li>
            <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
            <i>2017/3/14 上午8:30:00</i>
            <a class="mine-edit" href="/jie/edit/8116">编辑</a>
            <em>661阅/10答</em>
          </li>
          <li>
            <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
            <i>2017/3/14 上午8:30:00</i>
            <a class="mine-edit" href="/jie/edit/8116">编辑</a>
            <em>661阅/10答</em>
          </li>
        </ul>
        <div id="LAY_page"></div>
      </div>
      <div class="layui-tab-item" :class="{'layui-show': isCollection}">
        <ul class="mine-view jie-row">
          <li>
            <a class="jie-title" href="../jie/detail.html" target="_blank">基于 layui 的极简社区页面模版</a>
            <i>收藏于23小时前</i></li>
        </ul>
        <div id="LAY_page1"></div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "user",
    created() {
      //  验证是否展示收藏贴
      if (this.$route.hash == '#collection') {
        this.isCollection = true;
      }
    },
    data() {
      return {
        isCollection: false,// 收藏
      }
    }
  }
</script>

<style scoped>

</style>
