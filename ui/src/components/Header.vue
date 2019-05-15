<template>
  <el-header height="50px">
    <div id="header" class="layui-container">
      <!--<i class="el-icon-message-solid"></i>-->
      <el-row type="flex" align="middle" justify="space-between" :gutter="10">
        <el-col :span="4">
          <div class="logo"></div>
        </el-col>
        <el-col :span="10">
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">首页</el-menu-item>
            <el-menu-item index="2">资讯</el-menu-item>
          </el-menu>
        </el-col>
        <el-col :span="4">
          <el-input size="small" placeholder="请输入关键词搜索">
            <i
              class="el-icon-search el-input__icon"
              slot="suffix">
            </i>
          </el-input>
        </el-col>
        <el-col :span="2">
          <el-button type="primary" size="small" @click="newPosts">写文章</el-button>
        </el-col>
        <el-col :span="1">
          <i class="el-icon-message-solid message" ></i>
        </el-col>
        <el-col :span="2">
          <el-dropdown @command="handleCommand">
            <router-link to="/user/home" class="el-dropdown-link">
              <img src="http://iph.href.lu/32x32" alt="">
              阿拉灯
            </router-link>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">个人中心</el-dropdown-item>
              <el-dropdown-item command="b">切换账号</el-dropdown-item>
              <el-dropdown-item command="c">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
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
      },
      handleCommand(command) {
        this.$message('click on item ' + command);
      },
      newPosts() {
        this.$router.push('/post/new')
      }
    }
  }
</script>

<style scoped lang="scss">
  .el-header {
    margin-bottom: 10px;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
    .message {
      font-size: 23px;
      color: #71777c;
      cursor: pointer;
      &:hover {
        color: #009688;
      }
    }
    img {
      width: 32px;
      border-radius: 50%;
      cursor: pointer;
    }
    .el-dropdown-link {
      cursor: pointer;
    }
    .el-icon-arrow-down {
      font-size: 12px;
    }
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
    /*.el-input {*/
      /*float: right;*/
      /*width: 200px;*/
      /*margin-top: 10px;*/
    /*}*/
    /*.el-button {*/
      /*float: right;*/
    /*}*/
  }
  .el-menu-demo {
    display: inline-block;
    margin: 0 auto;
  }
  .el-menu--horizontal > .el-menu-item {
    height: 49px;
    line-height: 49px;
  }
</style>
