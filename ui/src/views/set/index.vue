<template>
  <div class="layui-container fly-marginTop fly-user-main">
    <!--layui-this-->
    <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
      <li class="layui-nav-item" v-for="(item,index) in menuList" :class="{'layui-this': index == activeIndex}">
        <a @click="routerlink(index)">
          <i class="layui-icon" v-html="item.icon"></i>
          {{item.name}}
        </a>
      </li>
    </ul>

    <div class="site-tree-mobile layui-hide">
      <i class="layui-icon">&#xe602;</i>
    </div>
    <div class="site-mobile-shade"></div>



    <div class="fly-panel fly-panel-user" pad20>
      <basic v-if="activeIndex == 2"></basic>
      <user v-else-if="activeIndex==1"></user>
      <message v-else-if="activeIndex==3"></message>
    </div>
  </div>
</template>

<script>
  import user from './components/user'
  import basic from './components/basic'
  import message from './components/message'
  export default {
    name: "index",
    components:{
      user,basic,message
    },
    data() {
      return {
        activeIndex: 1,
        menuList:[
          {
            icon:'&#xe609;',
            name:'我的主页'
          },{
            icon:'&#xe612;',
            name:'用户中心'
          },{
            icon:'&#xe620;',
            name:'基本设置'
          },{
            icon:'&#xe611;',
            name:'我的消息'
          },
        ]
      }
    },
    methods:{
      routerlink(index) {
        this.activeIndex = index;
        if(index == 0) {
          this.$router.push('/user/index');
        }
      }
    }
  }
</script>

<style scoped>

</style>
