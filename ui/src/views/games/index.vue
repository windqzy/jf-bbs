<template>
  <div>
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <el-row :gutter="16" style="margin-top: 20px">
          <el-col :xs="12" :sm="12" :md="6" :xl="6"  v-for="game in gameList" :key="game.id">
            <el-card shadow="never" class="game-card">
              <div>
                <img :src="game.imgUrl" alt=""
                     style="width: 48px">
              </div>
              <p>{{game.title}}</p>
              <p><img src="../../../static/images/first.png" alt="">{{game.first}}</p>
              <div class="grade">
                <el-button @click="begin(game.id, game.title, game.startUrl)" size="mini">开始游戏</el-button>
              </div>
              <div>
                <el-button size="mini" @click="getRankList(game.id)">积分榜</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- rank -->
    <el-dialog
      title="排行榜"
      :visible.sync="rankBox"
      width="320px"
      >
      <div class="layui-tab-content fly-signin-list">
        <ul class="layui-tab-item layui-show">
          <li v-for="rank in rankList">
            <a style="cursor: pointer" >
              <img :src="rank.userIcon == null ? defaultAvatar : rank.userIcon">
              <cite class="fly-link" title="sign.username">{{rank.userName | getUserName}}</cite>
            </a>
            <!--<a href="" target="_blank">-->
            <!--<img :src="sign.icon == null ? defaultAvatar : sign.icon">-->
            <!--<cite class="fly-link">{{sign.username}}</cite>-->
            <!--</a>-->
            <span class="fly-grey">{{rank.grade}}</span>
          </li>
        </ul>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import * as game from '@/api/game'

  export default {
    name: "index",
    data() {
      return {
        gameList: [],
        rankList: [],
        userId: '',
        $: null,
        layer: null,
        rankBox: false,
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
      }
    },
    created() {
      this.getGameList();
      this.userId = this.$store.getters.user.id;
    },
    mounted() {
      let _this = this;
      layui.use(['jquery', 'layer'], function () {
          _this.$ = layui.jquery;
          _this.layer = layui.layer;
      })
    },
    methods: {
      getGameList() {
        game.getList().then(res => {
          this.gameList = res.data;
          console.log(res.data)
        })
      },
      begin(id, title, startUrl) {
        // let _this = this;
        this.layer.open({
          type: 2,
          title: title,
          area: ['300px', '555px'],
          fixed: false, //不固定
          maxmin: true,
          content: startUrl + '?userId=' + this.userId + '&gameId=' + id
        });
      },
      getRankList(gameId) {
        game.getRankList(gameId).then(res => {
          this.rankList = res.data;
          this.rankBox = true;
        })
      }
    },
    filters: {
      getUserName(str) {
        if (!str) {
          return '暂无'
        } else {
          return str;
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .game-card {
    margin-bottom: 16px;
    text-align: center;

    .grade {
      margin-top: 8px;
      margin-bottom: 8px;
    }

    p {
      display: flex;
      align-items: center;
      justify-content: center;
      line-height: 28px;

      img {
        width: 20px;
        margin-right: 8px;
      }
    }

    .el-button {
      width: 100px;
      border-radius: 0;
    }

  }
</style>
