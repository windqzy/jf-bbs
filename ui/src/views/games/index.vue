<template>
  <div>
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <el-row :gutter="16" style="margin-top: 20px">
          <el-col :xs="12" :sm="12" :md="6">
            <el-card shadow="never" class="game-card" v-for="game in gameList" :key="game.id">
              <div>
                <img :src="game.imgUrl" alt=""
                     style="width: 48px">
              </div>
              <p>{{game.title}}</p>
              <p><img src="../../../static/images/first.png" alt="">张三</p>
              <div class="grade">
                <el-button @click="begin(game.id, game.title)" size="mini">开始游戏</el-button>
              </div>
              <div>
                <el-button size="mini">积分榜</el-button>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>

  </div>
</template>

<script>
  import * as game from '@/api/game'

  export default {
    name: "index",
    data() {
      return {
        gameList: []
      }
    },
    created() {
      this.getGameList();
    },
    methods: {
      getGameList() {
        game.getList().then(res => {
          this.gameList = res.data;
          console.log(res.data)
        })
      },
      begin(id, title) {
        // let _this = this;
        layer.open({
          type: 2,
          title: title,
          area: ['300px', '555px'],
          fixed: false, //不固定
          maxmin: true,
          content: '../../static/game/flappybird/index.html?userId=' + id
        });
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
