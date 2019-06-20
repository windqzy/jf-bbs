<template>
  <div>
    <v-header @change="changeLabel" @onLineUserCount="onLineUserCount = $event"></v-header>
    <template class="layui-container">
      <router-view v-if="isRouterAlive"></router-view>
    </template>
    <v-footer :onLineUserCount="onLineUserCount"></v-footer>
    <!-- 返回顶部 -->
    <el-backtop></el-backtop>
  </div>
</template>

<script>
  import Header from '@/components/Header';
  import Footer from '@/components/Footer';

  export default {
    name: "index",
    components: {
      'v-header': Header,
      'v-footer': Footer,
    },
    provide() {
      return {
        reload: this.reload
      }
    },
    data() {
      return {
        onLineUserCount: 0, // 在线人数
        isRouterAlive: true
      }
    },
    methods: {
      changeLabel() {

      },
      reload() {
        this.isRouterAlive = false;
        this.$nextTick(() => {
          this.isRouterAlive = true;
        })
      }
    }
  }
</script>

<style scoped>
  .layui-container {
    padding-top: 60px;
  }
</style>
