<template>
  <el-header height="50px">
    <div id="header" class="layui-container">
      <!-- 移动端 -->
      <el-row type="flex" class="hidden-sm-and-up">
        <el-col :span="3">
          <img src="../assets/img/logo-green.png"/>
        </el-col>
        <el-col :span="21">
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1"><i class="el-icon-monitor"></i></el-menu-item>
            <!--<el-menu-item index="2"><i class="el-icon-postcard"></i></el-menu-item>-->
            <!--<el-menu-item index="3"><i class="el-icon-warning-outline"></i></el-menu-item>-->
            <!--<el-menu-item index="4"><i class="el-icon-document"></i></el-menu-item>-->
            <el-menu-item index="5"><i class="el-icon-search"></i></el-menu-item>
          </el-menu>
          <div class="search" v-show="activeIndex == '5'" @keyup.enter="search">
            <el-input v-model="searchText" ref="searchText">
              <i slot="prefix" class="el-input__icon el-icon-search"></i>
              <i slot="suffix" class="el-input__icon el-icon-close" @click.stop="searchText = ''"></i>
            </el-input>
          </div>
          <el-dropdown @command="handleCommand" trigger="click">
            <a class="el-dropdown-link">
              <img :src="$store.state.user.icon" alt="">
              <!--{{$store.state.user.username}}-->
            </a>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">个人中心</el-dropdown-item>
              <!--<el-dropdown-item command="b">切换账号</el-dropdown-item>-->
              <el-dropdown-item command="c">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>
      <!-- PC端 -->
      <el-row type="flex" align="middle" justify="space-between" :gutter="10" class="hidden-sm-and-down">
        <el-col :span="4">
          <router-link to="/" class="logo">
            <img src="../assets/img/logo-green.png"/>
            金风社区
          </router-link>
        </el-col>
        <el-col :span="10">
          <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">首页</el-menu-item>
            <!--<el-menu-item index="2">资讯</el-menu-item>-->
            <!--<el-menu-item index="3">FAQ</el-menu-item>-->
            <!--<el-menu-item index="4">需求墙</el-menu-item>-->
          </el-menu>
        </el-col>
        <el-col :span="10" class="tr right">
          <el-input size="small" placeholder="请输入关键词搜索" v-model="searchText">
            <i class="el-icon-search el-input__icon" slot="suffix" @click.stop="search"></i>
          </el-input>
          <el-button type="primary" size="small" @click="newPosts">写文章</el-button>
          <el-badge :value="count" :hidden="count == 0" class="item">
            <i class="el-icon-message-solid message" :class="{'active': $route.fullPath == '/msg/index'}"
               @click="toMessage"></i>
          </el-badge>
          <el-dropdown @command="handleCommand" trigger="click">
            <a class="el-dropdown-link">
              <img :src="$store.state.user.icon" alt="">
              <!--{{$store.state.user.username}}-->
            </a>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">个人中心</el-dropdown-item>
              <!--<el-dropdown-item command="b">切换账号</el-dropdown-item>-->
              <el-dropdown-item command="c">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
        <!--<el-col :span="2">
          <el-button type="primary" size="small" @click="newPosts">写文章</el-button>
        </el-col>-->
        <!--        <el-col :span="1">
                  <el-badge :value="count" :hidden="count == 0" class="item">
                    <i class="el-icon-message-solid message" :class="{'active': $route.fullPath == '/msg/index'}"
                       @click="toMessage"></i>
                  </el-badge>
                </el-col>
                <el-col :span="1" class="tr">
                  <el-dropdown @command="handleCommand" trigger="click">
                    <a class="el-dropdown-link">
                      <img :src="$store.state.user.icon" alt="">
                      &lt;!&ndash;{{$store.state.user.username}}&ndash;&gt;
                    </a>
                    <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item command="a">个人中心</el-dropdown-item>
                      &lt;!&ndash;<el-dropdown-item command="b">切换账号</el-dropdown-item>&ndash;&gt;
                      <el-dropdown-item command="c">注销</el-dropdown-item>
                    </el-dropdown-menu>
                  </el-dropdown>
                </el-col>-->
      </el-row>
    </div>
  </el-header>
</template>

<script>

  export default {
    name: "Header",
    data() {
      return {
        socket: null,
        count: '',
        activeIndex: '1',
        searchText: '',
      }
    },
    computed: {},
    created() {
      console.log(this.$route)
    },
    mounted() {
      let socketUrl = window.localStorage.baseUrl + "/websocket/" + this.$store.state.user.id;
      this.socket = new WebSocket(socketUrl.replace("http", "ws"));
      this.socket.onopen = function () {
        console.log("Socket 已打开");
        //socket.send("这是来自客户端的消息" + location.href + new Date());
      };
      this.socket.onmessage = e => {
        console.log(e, 'onmessage');
        let data = JSON.parse(e.data);
        this.count = +data.toReadCount;
        this.$store.commit('ONLINEUSERCOUNT', data.onLineUserCount);
        this.$emit('onLineUserCount',data.onLineUserCount)
        //发现消息进入    开始处理前端触发逻辑
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        this.activeIndex = key;
        switch (key) {
          case '1':
            this.$router.push('/home/index');
            break;
          case '3':
            this.$router.push('/faq/index');
            break;
          case '5':
            this.$nextTick(() => {
              this.$refs['searchText'].focus();
            })
            /*this.$router.push('/faq/index');*/
            break;
        }
      },
      handleCommand(command) {
        // this.$message('click on item ' + command);
        if (command == 'a') {
          this.$router.push('/user/home');
        } else if (command == 'c') {
          this.$router.push('/login');
          window.localStorage['B-Token'] = '';
        }
      },
      newPosts() {
        this.$router.push('/post/new');
      },
      /* 搜索文章 */
      search() {
        this.$router.push('/guide/index?type=0&search=' + this.searchText);
      },
      /* 跳转信息 */
      toMessage() {
        this.count = '0';
        this.$router.push('/msg/index')
      }
    }
  }
</script>

<style scoped lang="scss">
  .tr {
    text-align: right
  }

  .el-header {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 98;
    padding: 0;
    margin-bottom: 10px;
    background-color: #fff;
    border-bottom: 1px solid #ddd;
    .message {
      font-size: 22px;
      color: #71777c;
      cursor: pointer;
      &:hover {
        color: #009688;
      }
      &.active {
        color: #009688;
      }
    }
    img {
      width: 32px;
      height: 32px;
      border-radius: 50%;
      cursor: pointer;
    }
    .el-dropdown-link {
      cursor: pointer;
    }
    .el-icon-arrow-down {
      font-size: 12px;
    }
    .right {
      .el-input {
        width: 180px;
        /deep/ .el-input__inner {
          border-radius: 0;
        }
      }
      .el-button {
        margin: 0 16px;
      }
      .el-badge {
        margin-right: 16px;
      }
    }

  }

  #header {
    height: 49px;
    background-color: white;

    .logo {
      margin-left: 10px;
      font-size: 22px;
      color: #009688;
      display: flex;
      img {
        margin-right: 5px;
      }
    }

    /deep/ .el-button .el-button--text .el-popover__reference {
      i {
        font-size: 18px;
      }
    }
  }

  .el-menu-demo {
    display: inline-block;
    margin: 0 auto;
  }

  .el-menu--horizontal > .el-menu-item {
    height: 49px;
    line-height: 49px;
  }

  /* 移动端 */
  .hidden-sm-and-up {
    position: relative;
    img {
      margin: 10px 6px;
    }
    .el-col {
      text-align: right;
      .el-menu-item {
        padding: 0 16px;
      }
      .search {
        position: absolute;
        top: 50px;
        left: 0;
        z-index: 9;
        width: 100%;
        /deep/ input {
          border-radius: 0;
        }
      }
    }
    .el-dropdown {
      float: right;
    }
  }
</style>
