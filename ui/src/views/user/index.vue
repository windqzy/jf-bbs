<template>
    <div class="layui-container">
      <el-row :gutter="8">
        <el-col :span="18">
          <el-card shadow="never">
            <div class="info">
              <el-row>
                <el-col :span="6">
                  <img src="http://placehold.it/128x128" alt="">
                </el-col>
                <el-col :span="18">
                  个人信息
                  <el-button type="primary" size="small">修改</el-button>
                </el-col>
              </el-row>
            </div>
            <div class="post">
              <el-tabs>
                <el-tab-pane label="我的消息">我的消息</el-tab-pane>
                <el-tab-pane label="我的帖子">我的帖子</el-tab-pane>
                <el-tab-pane label="我的回复">我的回复</el-tab-pane>
                <el-tab-pane label="我的收藏">我的收藏</el-tab-pane>
              </el-tabs>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="never">
            <div slot="header">
              统计信息
            </div>
          </el-card>
          <el-card shadow="never">
            <div slot="header">
              活跃
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
        name: "index"
    }
</script>

<style scoped lang="scss">
  .el-card {
    margin-bottom: 8px;
    /deep/ .el-card__header {
      padding: 10px 20px;
    }
  }
  .post {
    margin-top: 8px;
  }
  .info {
    .el-button {
      float: right;
    }
  }
</style>
