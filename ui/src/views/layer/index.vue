<template>
  <div>
    <v-header @change="changeLabel" @onLineUserCount="onLineUserCount = $event"></v-header>
    <template class="layui-container">
      <router-view></router-view>
    </template>
    <v-footer :onLineUserCount="onLineUserCount"></v-footer>
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
    data() {
      return {
        onLineUserCount: 0, // 在线人数
      }
    },
    methods: {
      changeLabel() {

      }
    }
  }
</script>

<style scoped>
  .layui-container {
    padding-top: 60px;
  }
</style>
