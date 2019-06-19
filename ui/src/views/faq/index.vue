<template>
    <div class="layui-container">
      <el-row :gutter="10">
        <el-col :lg="18" :xs="24">
          <el-card shadow="never">
            <div slot="header">
              <el-input placeholder="请输入产品或问题" autofocus>
                <el-button slot="append" type="primary" icon="el-icon-search"></el-button>
              </el-input>
            </div>
            <div class="faq_cont_list">
              <dl>
                <dd v-for="i in 5">
                  <a href="">选中一个单元格输入，输入括号（）以后，光标会飘到前面一个单元格，怎么办？</a>
                </dd>
              </dl>
            </div>
          </el-card>
        </el-col>
        <el-col :lg="6" :xs="24">
          <el-card shadow="never">
            <div slot="header">
              <span style="font-size: 18px">热门问题</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>

<script>
    export default {
      name: "index",
      created() {

      }
    }
</script>

<style scoped lang="scss">
  /deep/ .el-card {
    border-radius: 0px;
    .el-card__header {
      padding: 40px 40px;
    }
  }
  /deep/ .el-input__inner {
    border-radius: 0px;
  }
  /deep/ .el-input-group__append {
    border: 1px solid #009688;
    border-radius: 0px;
    background-color: #009688;
    color: #fff;
  }
  .faq_cont_list {
    margin: 30px;
    a {
      font-size: 15px;
      line-height: 30px;
      color: #333;
      &:hover {
        color: #009688;
      }
    }
  }
</style>
