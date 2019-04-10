<template>
  <div class="layui-container">
    <el-row :gutter="16" style="flex-direction: row-reverse">
      <el-col :xs="24" :sm="16" class="mt8">
        <el-card shadow="never" class="card-box">
          <el-row slot="header" type="flex" justify="space-between">
            <div class="layuiadmin-card-link fly-filter">
              <a v-for="(tag, index) in healthTagList" :class='{"layui-this":activeTag == index}' :key="tag.key"
                 @click="getHealthCNList(index, tag.key)">{{tag.value}}</a>
              <!--<span class="fly-mid"></span>-->
              <!--<a :class='{"layui-this":activeTag == 1}' @click="getHealthCNList(1, 'weekly')">本周最热</a>-->
              <!--<span class="fly-mid"></span>-->
              <!--<a :class='{"layui-this":activeTag == 2}' @click="getHealthCNList(2, 'monthly')">本月最热</a>-->
            </div>
          </el-row>
          <el-row type="flex" justify="flex-start" class="card-row">
            <div>
              <img src="http://placehold.it/64x64" alt="">
            </div>
            <div class="indextext-right">
              <div class="indextitle-text">
                <h2>国务院部署年度工作 盘点卫生健康三大管家重点任务</h2>
                <p class="indextext-ms">国家卫生健康委、国家医保局和国家药监局2019年重点工作有哪些，国务院给出明确指示。</p>
              </div>
              <div class="indexright-botbox">
                <a>aaaa</a>
                <a>2019-01-01</a>
                <a style="float: right">数据来源</a>
              </div>
            </div>
          </el-row>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="8" class="mt8">
        <el-card shadow="never">
          <div slot="header">分类</div>
          <ul>
            <!--<li v-for="tag in healthTagList">{{tag.value}}</li>-->
          </ul>
        </el-card>
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
        articleList: [],
        activeTag: 0,
        pageIndex: 1,
        pageSize: 10
      }
    },
    created() {
    },
    methods: {
      getHealthCNList(index, key) {
        this.activeTag = index;
        let type = 'health';
        let params = {
          start: this.pageIndex,
          size: this.pageSize,
          arctype: key
        }
        world.getList(type, params).then(res => {
          console.log(res.data)
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

      h2 {
        width: 100%;
        height: 34px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
        display: block;
        line-height: 160%;
        font-size: 22px;
        color: #333333;
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
        line-height: 24px;
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
