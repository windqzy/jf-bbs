<template>
  <div class="layui-container">
    <el-row :gutter="10">
      <el-col :span="18">
        <el-card shadow="never">
          <div v-html="content">
          </div>
          <div class="comment-list">
            <div class="title">评论</div>
            <div class="comment-form">
              <el-row type="flex" align="middle">
                <el-col :span="2">
                  <img src="http://iph.href.lu/72x72">
                </el-col>
                <el-col :span="22">
                  <el-input @focus="actionBox = true"
                            placeholder="输入评论..."
                            @input="commentChange"
                            size="small"
                            v-model="comment">
                  </el-input>
                  <el-row class="action-box" type="flex" justify="space-between" align="middle" v-show="actionBox">
                    <el-col :span="2">
                      <el-button type="text" icon="el-icon-warning-outline">表情</el-button>
                    </el-col>
                    <el-col :span="2">
                      <el-button type="primary" size="small" :disabled="disable">评论</el-button>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="never">
          <div slot="header">
            关于作者
          </div>
        </el-card>
        <el-card shadow="never">
          <div slot="header">
            打赏榜
          </div>
        </el-card>
        <el-card shadow="never">
          <div slot="header">
            目录
          </div>
        </el-card>
        <el-card shadow="never">
          <div slot="header">
            相关文章
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    export default {
      name: "detail",
      data() {
        return {
          content: '文章区',
          actionBox: false,
          disable: true,
          comment: ''
        }
      },
      created() {
      },
      methods: {
        commentChange() {
          if (this.comment) {
            this.disable = false;
          } else {
            this.disable = true;
          }
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
  .comment-list {
    .title {
      color: #8a9aa9;
      font-size: 16px;
      font-weight: 400;
      text-align: center;
      padding: 14px 0 5px;
    }
    .comment-form {
      margin: 10px 0;
      /*display: flex;*/
      /*position: relative;*/
      padding: 10px;
      background-color: #fafbfc;
      border-radius: 3px;
      img {
        width: 2.667rem;
        height: 2.667rem;
        border-radius: 50%;
      }
      .el-input{
        border-radius: 0px;
        width: 100%;
      }
      .action-box {
        margin-top: 4px;
      }
    }
  }
</style>
