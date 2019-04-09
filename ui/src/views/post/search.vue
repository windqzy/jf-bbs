<template>
  <div>
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
          <div class="fly-panel" style="margin-bottom: 0;">
            <div class="fly-panel-title fly-filter">
              <span class="fly-filter-right layui-hide-xs">
                <a href="" class="layui-this">全部</a>
                <span class="fly-mid"></span>
                <a href="">一天内</a>
                <span class="fly-mid"></span>
                <a href="">一周内</a>
                <span class="fly-mid"></span>
                <a href="">三月内</a>
              </span>
            </div>
            <ul class="fly-list">
              <li v-for="top in data">
                <!--<a href="user/home.html" class="fly-avatar">-->
                <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">-->
                <!--</a>-->
                <h2>
                  <a class="layui-badge">{{top.source.label}}</a>
                  <!--<a v-if="top.highlight.productName != null" v-html="top.highlight.productName"></a>-->
                  <router-link :to="'/post/detail?postId=' + top.source.id + '&labelId=' + top.source.label" v-html="top.highlight.productName"></router-link>
                </h2>
                <div class="fly-list-info">
                  <a href="user/home.html" link>
                    <cite>{{top.source.author}}</cite>
                  </a>
                  <span>{{top.source.createTime}}</span>
                  <span class="fly-list-nums">
              </span>
                </div>
                <div class="fly-list-badge">
                </div>
              </li>

            </ul>
            <div style="text-align: center">
              <div class="laypage-main">
                <a href="jie/index.html" class="laypage-next">更多求解</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as post from '@/api/post';
  export default {
    name: "search",
    data() {
      return {
        data:[]
      }
    },
    created() {
      this.keyword = this.$route.query.key;
      post.search(this.keyword).then(res => {
        console.log(res)
        this.data=res.data
      })
    },
  }
</script>

<style scoped>

</style>
