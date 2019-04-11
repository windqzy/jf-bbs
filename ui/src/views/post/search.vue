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
                  <!--<a class="layui-badge">{{top.source.label}}</a>-->
                  <router-link :to="'/post/detail?postId=' + top.source.id + '&labelId=' + top.source.label"
                               v-html="top.highlight.productName"></router-link>
                </h2>
                <div class="fly-list-info">
                  <!--<a href="user/home.html" link>-->
                  <!--<cite>{{top.source.author}}</cite>-->
                  <!--</a>-->
                  <span>{{top.source.createTime | formatTime}}</span>
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
  import {formatTime} from '@/utils/time'

  export default {
    name: "search",
    data() {
      return {
        data: []
      }
    },
    created() {
      this.keyword = this.$route.query.key;
      post.search(this.keyword).then(res => {
        console.log(res)
        this.data = res.data
      })
    },
    filters: {
      formatTime(timestamp) {
        if (!timestamp) {
          return "";
        }
        let date = new Date(timestamp);
        let month = date.getMonth() + 1;
        let strDate = date.getDate();
        if (month >= 1 && month <= 9) {
          month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
          strDate = "0" + strDate;
        }
        let dateStr = date.getFullYear() + '-' + month + '-' + strDate +
          " " + (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ':' +
          (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
          ':' + (date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds());
        return dateStr;
      }
    }
  }
</script>

<style scoped>

</style>
