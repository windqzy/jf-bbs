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
    var a_idx = 0;
    jQuery(document).ready(function($) {
      $("#app").click(function(e) {
        var a = new Array("❤加油❤","❤努力❤","❤金风易通❤","❤记得签到❤","❤金风社区❤","❤点赞❤","❤打赏❤","❤come on❤","❤推荐一下❤","❤支持一下❤","❤关注我❤","❤不将就❤");
        var $i = $("<span></span>").text(a[a_idx]);
        a_idx = (a_idx + 1) % a.length;
        var x = e.pageX,
          y = e.pageY;
        $i.css({
          "z-index": 999999999999999999999999999999999999999999999999999999999999999999999,
          "top": y - 20,
          "left": x,
          "position": "absolute",
          "font-weight": "bold",
          "color": "rgb("+~~(255*Math.random())+","+~~(255*Math.random())+","+~~(255*Math.random())+")"
        });
        $("body").append($i);
        $i.animate({
            "top": y - 180,
            "opacity": 0
          },
          1500,
          function() {
            $i.remove();
          });
      });
    });
  }
}
</script>

<style>
  /*#app {*/
    /*overflow: hidden;*/
  /*}*/
</style>
