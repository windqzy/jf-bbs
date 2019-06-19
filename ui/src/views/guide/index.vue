<template>
    <div class="layui-container">
      <el-row :gutter="8">
        <el-col :lg="18" :xs="24">
          <el-card shadow="never">
            <div slot="header" class="fly-filter">
              <a :class="{'layui-this': type == -1}" @click="changeSort(-1)">全部</a>
              <el-divider direction="vertical"></el-divider>
              <a :class="{'layui-this': type == 0}" @click="changeSort(1)">精华推荐</a>
              <el-divider direction="vertical"></el-divider>
              <a :class="{'layui-this': type == 2}" @click="changeSort(2)">最新发布</a>
              <el-divider direction="vertical"></el-divider>
              <a :class="{'layui-this': type == 3}" @click="changeSort(3)">最新回复</a>
            </div>
          </el-card>

        </el-col>
        <el-col :lg="6" :xs="24">
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
        changeSort(type) {

        }
      }
    }
</script>

<style scoped lang="scss">
  /deep/ .el-card__header {
    padding: 10px 20px;
  }
</style>
