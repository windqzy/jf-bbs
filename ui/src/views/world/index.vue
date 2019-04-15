<template>
  <div class="layui-container">
    <el-row :gutter="16" style="flex-direction: row-reverse">
      <el-col :xs="24" :sm="16">
        <el-card shadow="never" class="card-box" v-loading="loading">
          <el-row slot="header" type="flex" justify="space-between" v-if="labelId != 2">
            <div class="layuiadmin-card-link fly-filter">
              <a v-for="(tag, index) in tagList" :class='{"layui-this":activeTag == index}' :key="tag.key"
                 @click="getArticleList(index, tag.key)">{{tag.value}}</a>
              <!--<span class="fly-mid"></span>-->
              <!--<a :class='{"layui-this":activeTag == 1}' @click="getHealthCNList(1, 'weekly')">本周最热</a>-->
              <!--<span class="fly-mid"></span>-->
              <!--<a :class='{"layui-this":activeTag == 2}' @click="getHealthCNList(2, 'monthly')">本月最热</a>-->
            </div>
          </el-row>
          <el-row type="flex" justify="flex-start" class="card-row" v-for="item in articleList" :key="item.id">
            <div>
              <img :src="item.cover" alt="">
            </div>
            <div class="indextext-right">
              <div class="indextitle-text">
                <!--<h2>{{item.title}}</h2>-->
                <router-link :to="'/world/detail?articleId=' + item.id + '&labelId=' + labelId">{{item.title}}
                </router-link>
                <p class="indextext-ms">{{item.description}}</p>
              </div>
              <div class="indexright-botbox">
                <a>{{item.author}}</a>
                <a>{{item.pubdate}}</a>
                <a style="float: right">数据来源：<span>{{item.source}}</span></a>
              </div>
            </div>
          </el-row>
          <div style="text-align: center" v-if="articleList.length > 0">
            <div class="laypage-main">
              <a style="cursor: pointer" @click="nextPage" class="laypage-next" v-if="labelId != 2">更多求解</a>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="8" v-show="$route.query.id == 0">
        <div class="fly-panel">
          <h3 class="fly-panel-title">72小时热文</h3>
          <ul class="fly-panel-main fly-list-static">
            <li v-for="health in health72">
              <!--<a href="http://fly.layui.com/jie/4281/" target="_blank">{{item.title}}</a>-->
              <router-link :to="'/world/detail?articleId=' + health.id">{{health.title}}
              </router-link>
            </li>
          </ul>
        </div>

        <div class="fly-panel">
          <h3 class="fly-panel-title">TOP20</h3>
          <ul class="fly-panel-main fly-list-static">
            <li v-for="health in health20">
              <!--<a href="http://fly.layui.com/jie/4281/" target="_blank">{{item.title}}</a>-->
              <router-link :to="'/world/detail?articleId=' + health.id">{{health.title}}
              </router-link>
            </li>
          </ul>
        </div>

        <!--<el-card shadow="never">-->
        <!--<div slot="header">72小时热文</div>-->
        <!--<ul>-->
        <!--<li v-for="health in health72">{{health.title}}</li>-->
        <!--</ul>-->
        <!--</el-card>-->
        <div class="fly-panel mt8" style="padding: 20px 0; text-align: center;">
          <img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
          <p style="position: relative; color: #666;">微信扫码关注 金风推特 公众号</p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import * as world from '@/api/world'

  export default {
    name: "index",
    data() {
      return {
        tagList: [],
        healthTagList: [
          {
            key: 1013,
            value: '政策'
          }, {
            key: 1014,
            value: '医改'
          }, {
            key: 1023,
            value: '人物'
          }, {
            key: 1016,
            value: '管理'
          }, {
            key: 1015,
            value: '临床'
          }, {
            key: 1017,
            value: '科技'
          }, {
            key: 1018,
            value: '产业'
          }, {
            key: 1019,
            value: '投资'
          }, {
            key: 1020,
            value: '海外'
          }, {
            key: 1021,
            value: '疾病'
          }, {
            key: 1022,
            value: '健康'
          }, {
            key: 1009,
            value: '视频'
          }, {
            key: 1029,
            value: '护理'
          }, {
            key: 1030,
            value: '养老'
          }, {
            key: 1031,
            value: '保险'
          },
        ],
        ttTagList: [
          {
            key: 0,
            value: '热点'
          }, {
            key: 1,
            value: '社会'
          }, {
            key: 2,
            value: '娱乐'
          }, {
            key: 3,
            value: '体育'
          }, {
            key: 4,
            value: '美文'
          }, {
            key: 5,
            value: '科技'
          }, {
            key: 6,
            value: '财经'
          }, {
            key: 7,
            value: '时尚'
          },
        ],
        krTagList: [
          {
            key: 303,
            value: '最新'
          },{
            key: 304,
            value: '推荐'
          },{
            key: 305,
            value: '创投'
          },{
            key: 306,
            value: '科技'
          },{
            key: 307,
            value: '生活'
          },{
            key: 309,
            value: '职场'
          },{
            key: 310,
            value: '出行'
          },{
            key: 325,
            value: '创新'
          },{
            key: 311,
            value: '房产'
          },{
            key: 312,
            value: '其他'
          },
        ],
        articleList: [],
        articleTag: [],
        activeTag: 0,
        pageIndex: 1,
        pageSize: 10,
        health72: [],
        health20: [],
        loading: false,
        labelId: 0
      }
    },
    created() {
      this.getHealthCN72();
      this.getHealthCN20();
      this.tagList = this.healthTagList;
      this.getHealthCNList(0, 1013)

      // this.activeTag = this.$route.query.id;
      this.changeLabel(this.$route.query.id);
    },
    watch: {
      '$route.query.id'(val) {
        this.changeLabel(val);
      }
    },
    methods: {
      getArticleList(index, key) {
        this.activeTag = index;
        this.articleTag = key;
        console.log(this.$route.query.id)
        switch (this.$route.query.id) {
          case "0": this.getHealthCNList(); break;
          case "1": this.getKrList(); break;
        }
      },
      changeLabel(e) {
        this.labelId = e;
        if (e == 0) {
          this.tagList = this.healthTagList;
          this.activeTag = 0;
          this.articleTag = 1013;
          this.getHealthCNList()
        } else if (e == 1) {
          this.activeTag = 0;
          this.articleTag = 303;
          this.tagList = this.krTagList;
          this.getKrList();
        } else if (e == 2) {
          this.tagList = this.ttTagList;
          this.getZhiHuList();
        }
      },
      getHealthCNList() {
        let type = 'health';
        let params = {
          start: this.pageIndex,
          size: this.pageSize,
          arctype: this.articleTag
        }
        this.loading = true;
        world.getList(type, params).then(res => {
          this.loading = false;
          this.articleList = res.data;
        })
      },
      getZhiHuList() {
        let type = 'zhihu';
        let params = {
          start: null,
          size: null,
          arctype: null
        };
        this.loading = true;
        world.getList(type, params).then(res => {
          this.loading = false;
          this.articleList = res.data;
        })
      },
      getKrList() {
        let type = 'kr';
        let params = {
          start: this.pageIndex,
          size: this.pageSize,
          arctype: this.articleTag
        };
        this.loading = true;
        world.getList(type, params).then(res => {
          this.loading = false;
          this.articleList = res.data;
          // console.log(res.data)
        })
      },
      getHealthCN72() {
        world.getHealthCN72().then(res => {
          this.health72 = res.data;
        })
      },
      getHealthCN20() {
        world.getHealthCN20().then(res => {
          this.health20 = res.data;
        })
      },
      nextPage() {
        this.pageIndex += 1;
        let params = {
          start: this.pageIndex,
          size: this.pageSize,
          arctype: this.articleTag
        };
        let type = 'health';
        this.loading = true;
        world.getList(type, params).then(res => {
          res.data.map(item => {
            this.loading = false;
            this.articleList.push(item);
          })
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  .mt8 {
    margin-top: 8px;
  }

  .layui-container {
    padding: 0 16px 16px;
  }

  /deep/ .el-card__header {
    padding: 13px 10px;
  }

  /deep/ .el-card__body {
    padding: 0px 5px 15px 20px;
  }

  /* 卡片 */
  .card-box {
    .fly-filter {
      a {
        line-height: 24px;
        cursor: pointer;
        display: inline-block;
      }
    }

    .card-row {
      width: 100%;
      overflow: hidden;
      border-bottom: 1px solid rgba(178, 186, 194, .25);
      padding: 15px 0px;
    }

    img {
      display: inline-block;
      width: 120px;
      height: 120px;
      position: relative;
      overflow: hidden;
    }

    .indextext-right {
      width: calc(100% - 135px);
      margin-left: 15px;
      position: relative;
    }

    .indextitle-text {
      width: 100%;
      height: 110px;
      overflow: hidden;

      a {
        width: 100%;
        height: 34px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        display: block;
        line-height: 160%;
        font-size: 18px;
      }

      .indextext-ms {
        line-height: 175%;
        color: #666666;
        margin-top: 5px;
        text-align: justify;
        height: 50px;
        overflow: hidden;
      }
    }

    .indexright-botbox {
      height: 24px;
      line-height: 24px;
      position: absolute;
      left: 0px;
      bottom: 0px;
      width: 100%;

      a {
        display: inline-block;
        height: 24px;
        line-height: 38px;
        font-size: 12px;
        color: #999999;
        margin-right: 5px;
      }
    }

  }

  @media screen and(max-width: 768px) {
    .card-box {
      img {
        width: 80px;
        height: 80px;
      }

      .indextext-right {
        width: calc(100% - 95px);
      }
    }
  }
</style>
