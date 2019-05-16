<template>
    <div class="layui-container">
      <el-row :gutter="8">
        <el-col :span="18">
          <el-card shadow="never">
            <div slot="header" class="fly-filter">
              <a class="layui-this">最新热门</a>
              <el-divider direction="vertical"></el-divider>
              <a>最新精华</a>
              <el-divider direction="vertical"></el-divider>
              <a>最新回复</a>
              <el-divider direction="vertical"></el-divider>
              <a>最新发表</a>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card shadow="never">

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
          type: 0
        }
      },
      created() {
        this.type = this.$route.query.type;
      },
      methods: {

      }
    }
</script>

<style scoped lang="scss">
  /deep/ .el-card__header {
    padding: 10px 20px;
  }
</style>
