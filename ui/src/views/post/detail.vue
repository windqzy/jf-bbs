<template>
  <div class="layui-container">
    <el-row>
      <el-col :span="18">
        <div v-html="content">

        </div>
      </el-col>
      <el-col :span="6">
        目录
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "detail",
      data() {
        return {
          content: ''
        }
      },
      created() {
      },
      methods: {

      }
    }
</script>

<style scoped>

</style>
