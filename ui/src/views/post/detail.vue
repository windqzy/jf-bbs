<template>
  <div class="layui-container">
    <el-row :gutter="10">
      <el-col :span="18">
        <el-card shadow="never">
          <div v-html="content">
          </div>
          <div class="comment-box">
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
            <div class="comment-list">
              <div class="comment-item" v-for="i in 5">
                <img src="http://iph.href.lu/72x72" alt="">
                <div class="comment-content">
                  <div class="author">
                    王者之钻
                  </div>
                  <div class="content">
                    没关系的兄弟，生活多一些磨难挺好，这种垃圾公司，不值得生气，肯定会亏死，垮掉，公司老板肯定会家庭，事业各种不顺，
                    因为亏心事做的太多，小鬼都不会放过他们
                  </div>
                  <div class="stat">
                    <el-row type="flex" justify="space-between">
                      <el-col :span="4">
                        <span>两小时前</span>
                        <span>·删除</span>
                      </el-col>
                      <el-col :span="16"></el-col>
                      <el-col :span="4">
                        <div class="action">
                          <i class="el-icon-goblet-square"></i>
                          <span>2</span>
                          <i class="el-icon-chat-round"></i>
                          <span>回复</span>
                        </div>
                      </el-col>
                    </el-row>
                  </div>
                  <div class="sub-comment-list">
                    <div v-for="item in 3" style="display: flex">
                      <img src="http://iph.href.lu/72x72" alt="">
                      <div class="item">
                        <div class="author">
                          王者之钻
                        </div>
                        <div class="content">
                          <span class="">回复 <a class="layui-this">张三</a>：</span>
                          没关系的兄弟，生活多一些磨难挺好，这种垃圾公司，不值得生气，肯定会亏死，垮掉，公司老板肯定会家庭，事业各种不顺，
                          因为亏心事做的太多，小鬼都不会放过他们
                        </div>
                        <div class="stat">
                          <el-row type="flex" justify="space-between">
                            <el-col :span="6">
                              <span>两小时前</span>
                              <span>·删除</span>
                            </el-col>
                            <el-col :span="14"></el-col>
                            <el-col :span="6">
                              <div class="action">
                                <i class="el-icon-goblet-square"></i>
                                <span>2</span>
                                <i class="el-icon-chat-round"></i>
                                <span>回复</span>
                              </div>
                            </el-col>
                          </el-row>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
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
  .comment-box {
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

    .comment-list {
      margin: 20px 25px 0 24px;
      .comment-item {
        margin: 8px 0;
        display: flex;
        img {
          width: 40px;
          height: 40px;
          border-radius: 50%;
          margin: 8px;
        }
        .comment-content {
          width: 100%;
          border-bottom: 1px solid #f1f1f1;
          .author {

          }
          .content {
            margin-top: 8px;
            line-height: 22px;
            color: #505050;
          }
        }
        .stat {
          font-size: 14px;
          color: #8a9aa9;
          cursor: default;
          margin: 7px;
          font-weight: 400;
          .action {
            span {
              margin-right: 18px;
            }
          }
        }
        .sub-comment-list {
          display: flex;
          flex-direction: column;
          margin: 12px 0;
          background-color: #fafbfc;
          border-radius: 3px;
          .item {
            margin: 14px 0;
            width: 100%;
            padding: 0 2px;
            border-bottom: 1px solid #f1f1f1;
          }
        }
      }
    }
  }
</style>
