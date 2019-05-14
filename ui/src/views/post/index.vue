<template>
  <div id="post" class="layui-container">
    <el-row :gutter="10">
      <el-col :span="16">
        <el-card shadow="never">
          <div slot="header" class="post-divider">
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
          <div class="post-list">

          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never">
          <el-button type="primary" icon="el-icon-edit" >发布新主题</el-button>
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
    export default {
      name: "index",
      data() {
        return {
          labelId: ''
        }
      },
      created() {
        this.labelId = this.$route.query.labelId;
      },
      methods: {

      }
    }
</script>

<style scoped lang="scss">
  #post{
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
  }
</style>
