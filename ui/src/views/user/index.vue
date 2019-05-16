<template>
    <div class="layui-container">
      <el-row :gutter="8">
        <el-col :span="18">
          <el-card shadow="never">
            <div class="info">
              <el-row>
                <el-col :span="6">
                  <el-upload
                    class="avatar-uploader"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :before-upload="beforeAvatarUpload">
                    <img v-if="avatarUrl" :src="avatarUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
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
      name: "index",
      data() {
        return {
          avatarUrl: ''
        }
      },
      created() {

      },
      methods: {
        handleAvatarSuccess(res, file) {
          this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
          const isJPG = file.type === 'image/jpeg';
          const isLt2M = file.size / 1024 / 1024 < 2;

          if (!isJPG) {
            this.$message.error('上传头像图片只能是 JPG 格式!');
          }
          if (!isLt2M) {
            this.$message.error('上传头像图片大小不能超过 2MB!');
          }
          return isJPG && isLt2M;
        }
      }
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
    /deep/ .avatar-uploader .el-upload {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
      border-color: #409EFF;
    }
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 150px;
      height: 150px;
      line-height: 150px;
      text-align: center;
    }
    .avatar {
      width: 150px;
      height: 150px;
      display: block;
    }
  }
</style>
