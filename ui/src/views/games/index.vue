<template>
  <div>
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <el-row :gutter="16">
          <el-col :xs="24" :md="16" class="mt8">
            <el-card shadow="never" class="header">
              <img src="../../../static/images/music.jpg" alt="金风音乐会" style="max-width: 100%;">
            </el-card>
            <el-row :gutter="16" style="margin-top: 20px">
              <el-col :xs="12" :sm="12" :md="6" :xl="6" v-for="game in gameList" :key="game.id">
                <el-card shadow="never" class="game-card">
                  <div>
                    <img :src="game.imgUrl" alt=""
                         style="width: 48px">
                  </div>
                  <p>{{game.title}}</p>
                  <p>
                    <img src="../../../static/images/first.png" alt="">
                    <span class="overhide" :title="game.first">{{!game.first ? '暂无' : game.first}}</span>
                  </p>
                  <div class="grade">
                    <el-button @click="begin(game.id, game.title, game.startUrl)" size="mini">开始游戏</el-button>
                  </div>
                  <div>
                    <el-button size="mini" @click="getRankList(game.id)">积分榜</el-button>
                  </div>
                </el-card>
              </el-col>
            </el-row>
          </el-col>
          <el-col :xs="24" :md="8">
            <el-card class="mt8" shadow="never">
              <div slot="header">
                总积分榜
                <i class="fly-mid"></i>
                <a style="cursor: pointer" @click="gameDoc()" class="fly-link">说明</a>
              </div>

              <ul>
                <li v-for="(item, index) in sumRankList" class="rank">
                  <div :style="{color: index == 2?'#e6a23c':index == 1?'silver':index == 0?'gold':''}">
                    <i>{{index + 1}}</i>
                    <img :src="item.userIcon == null? defaultAvatar : item.userIcon" alt="">
                    <span class="rank-name overhide" :title="item.userName">{{item.userName}}</span>
                  </div>
                  <span class="rank-num">{{item.sumGrade}}</span>
                </li>
              </ul>
            </el-card>
            <div class="fly-panel mt8" style="padding: 20px 0; text-align: center;">
              <img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
              <p style="position: relative; color: #666;">微信扫码关注 金风推特 公众号</p>
            </div>
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
            <a style="cursor: pointer">
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
    <!-- 游戏积分规则 -->
    <div class="layui-text" style="padding: 20px; display: none" id="gameDoc">
      <blockquote class="layui-elem-quote">游戏积分规则如下</blockquote>
      <table class="layui-table">
        <thead>
        <tr>
          <th>游戏名次</th>
          <th>可获积分</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>1</td>
          <td>50</td>
        </tr>
        <tr>
          <td>2</td>
          <td>36</td>
        </tr>
        <tr>
          <td>3</td>
          <td>30</td>
        </tr>
        <tr>
          <td>4</td>
          <td>24</td>
        </tr>
        <tr>
          <td>5</td>
          <td>20</td>
        </tr>
        <tr>
          <td>6</td>
          <td>16</td>
        </tr>
        <tr>
          <td>7</td>
          <td>12</td>
        </tr>
        <tr>
          <td>8</td>
          <td>8</td>
        </tr>
        <tr>
          <td>9</td>
          <td>4</td>
        </tr>
        <tr>
          <td>10</td>
          <td>2</td>
        </tr>
        </tbody>
      </table>
    </div>
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
        sumRankList: []
      }
    },
    created() {
      this.getGameList();
      this.userId = this.$store.getters.user.id;
      this.getSumRankList();
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
      },
      getSumRankList() {
        game.getSumRankList().then(res => {
          this.sumRankList = res.data;
          console.log(res.data)
        })
      },
      gameDoc() {
        this.layer.open({
          type: 1
          , title: '游戏说明'
          , area: '300px'
          , shade: 0.8
          , shadeClose: true
          , content: this.$('#gameDoc')
        });
      },
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
  .mt8 {
    margin-top: 8px;
  }

  .overhide {
    max-width: 100px;
    display: inline-block;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  .header {
    /deep/ .el-card__body {
      padding: 4px 8px;
    }
  }

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
      height: 28px;

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

  /* 排行榜 */
  .rank {
    position: relative;
    padding-bottom: 8px;
    margin-bottom: 8px;
    border-bottom: 1px solid #eee;

    & > div {
      display: flex;
      align-items: center;
    }

    i {
      display: inline-block;
      width: 32px;
      font-size: 16px;
      text-align: center;
    }

    img {
      width: 36px;
      height: 36px;
    }

    &-name {
      display: inline-block;
      width: 150px !important;
      padding-left: 8px;
    }

    &-num {
      position: absolute;
      right: 0;
      top: 8px;
    }
  }
</style>
