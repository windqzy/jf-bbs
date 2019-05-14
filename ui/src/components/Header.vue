<template>
  <el-header height="50px">
    <div id="header" class="layui-container">
      <div class="logo"></div>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1">首页</el-menu-item>
      </el-menu>
      <el-input size="small" placeholder="请输入关键词搜索">
        <i
          class="el-icon-search el-input__icon"
          slot="suffix">
        </i>
      </el-input>
    </div>
  </el-header>
</template>

<script>
  export default {
    name: "Header",
    data() {
      return {
        activeIndex: '1',
      }
    },
    computed: {},
    created() {

    },
    mounted() {

    },
    methods: {
      handleSelect(key, keyPath) {
        switch (key) {
          case '1':this.$router.push('/home/index');break
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .el-header {
    margin-bottom: 10px;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
  }
  #header {
    height: 49px;
    background-color: white;

    .logo {
      display: inline-block;
      /*width: 153px;*/
      height: 50px;
      /*background: url(../../static/images/logo.png) no-repeat 50%;*/
      background-size: 100%;
      margin-right: 24px;
    }
    .el-input {
      float: right;
      width: 200px;
      margin-top: 10px;
    }
  }
  .el-menu-demo {
    display: inline-block;
    margin: 0 auto;
  }
  .el-menu--horizontal > .el-menu-item {
    height: 50px;
    line-height: 50px;
  }
</style>
