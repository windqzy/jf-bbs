<template>
  <div id="post" class="layui-container">
    <el-row :gutter="10">
      <el-col :span="16">
        <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />
        <el-card shadow="never">
          <div class="post-divider">
            <span>全部<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类一<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类二<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类三<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类四<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类五<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类六<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类七<em> (25)</em></span>
            <el-divider direction="vertical"></el-divider>
            <span>分类八<em> (25)</em></span>
          </div>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            <div class="post-type">
              <span>最新</span>
              <el-divider direction="vertical" content-position="right"></el-divider>
              <span>热门</span>
              <el-divider direction="vertical"></el-divider>
              <span>热帖</span>
            </div>
          </div>
          <div class="post-list">
            <ul>
              <li>
                <router-link :to="'/post/detail?id=' + '1'">
                  <img src="http://placehold.it/64x64" alt="">
                  <div>
                    <h3>这是文章的标题</h3>
                    <p>
                      张三 于 2019-01-01 发表 最后回复 6 天前
                      <span>
                        <i class="el-icon-view"></i>1212
                        <i class="el-icon-chat-line-round"></i>1212
                      </span>
                    </p>
                  </div>
                </router-link>
              </li>
              <li>
                <a href="">
                  <img src="http://placehold.it/64x64" alt="">
                  <div>
                    <h3>这是文章的标题</h3>
                    <p>
                      张三 于 2019-01-01 发表 最后回复 2019-01-02 10:01:01
                      <span>
                        <i class="el-icon-view"></i>1212
                        <i class="el-icon-chat-line-round"></i>1212
                      </span>
                    </p>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never">
          <el-button type="primary" icon="el-icon-edit">发布新主题</el-button>
        </el-card>
        <el-card shadow="never" class="mt8">
          <el-button type="warning" class="sign">
            <div><i class="el-icon-edit"></i>签到</div>
            <div>
              <p><i class="el-icon-s-data"></i> 今日排名 第6名</p>
              <p><i class="el-icon-date"></i> 连续打卡 26天</p>
            </div>
          </el-button>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            此处应为该板块公告或者广告
          </div>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            此处应为该板块公告或者广告
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Breadcrumb from '@/components/Breadcrumb'
  export default {
    name: "index",
    components: {
      Breadcrumb
    },
    data() {
      return {
        labelId: ''
      }
    },
    created() {
      this.labelId = this.$route.query.labelId;
    },
    methods: {}
  }
</script>

<style scoped lang="scss">
  #post {
    .mt8 {
      margin-top: 8px;
    }

    .el-button--primary {
      border-radius: 0px;
      width: 100%;
    }
    .el-card {
      /deep/ .el-card__header {
        padding: 10px 20px;
      }
      /deep/ .el-card__body {
        padding: 12px;
      }
    }
    .post-divider {
      em {
        font-style: normal;
        color: #c01f2e;
      }
      .el-divider--vertical {
        margin: 8px;
      }
    }
    /*签到*/
    .sign {
      width: 100%;
      padding: 1px;
      border-radius: 0;
      & > /deep/ span {
        display: flex;
        align-items: center;
        justify-content: center;
        & > div {
          width: 50%;
          &:last-child {
            padding: 4px 0 4px 20px;
            background: #fff;
            box-sizing: border-box;
            p {
              color: #999;
              line-height: 1.5;
              text-align: left;
            }
          }
        }
      }
    }
    /*文章*/
    .post-list {
      li {
        margin-bottom: 10px;
        a {
          display: flex;
          &> img {
            width: 64px;
          }
          &> div {
            width: 100%;
            margin-left: 10px;
            display: flex;
            flex-direction: column;
            align-items: left;
            justify-content: space-around;
            overflow: hidden;
            border-bottom: 1px dashed #EBEEF5;
            h3 {
              font-weight: bold;
              color: #333;
            }
            p {
              color: #aaa;
            }
            span {
              float: right;
              margin-top: 2px;
            }
          }
        }
      }
    }
    .post-type {
      text-align: right;

    }
  }
</style>
