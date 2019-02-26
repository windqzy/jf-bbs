<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  created() {
    // layui.config({
    //   version: "3.0.0"
    //   , base: 'static/mods/' //这里实际使用时，建议改成绝对路径
    // }).extend({
    //   fly: 'index'
    // }).use(['fly', 'face'], function () {
    //   var $ = layui.$
    //     , fly = layui.fly;
    //   //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
    //   /*
    //   $('.detail-body').each(function(){
    //     var othis = $(this), html = othis.html();
    //     othis.html(fly.content(html));
    //   });
    //   */
    // });
  }
}
</script>

<style>
  /*#app {*/
    /*overflow: hidden;*/
  /*}*/
</style>
