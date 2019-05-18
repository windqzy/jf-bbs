<template>
  <div class="layui-container">
    <el-row :gutter="10">
      <el-col :span="18">
        <!-- 文章内容 -->
        <el-card shadow="never">
          <div class="detail-box">
            <h1>{{postsInfo.title}}</h1>
            <div class="fly-detail-info">
              <!-- <span class="layui-badge">审核中</span> -->
              <!--<span class="layui-badge layui-bg-green fly-detail-column">动态</span>-->

              <!--<span class="layui-badge" style="background-color: #999;">未结</span>-->
              <!--&lt;!&ndash; <span class="layui-badge" style="background-color: #5FB878;">已结</span> &ndash;&gt;-->

              <!--<span class="layui-badge layui-bg-black">置顶</span>-->
              <!--<span class="layui-badge layui-bg-red">精帖</span>-->

              <!--<div class="fly-admin-box" data-id="123">-->
                <!--<span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>-->

                <!--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>-->
                <!--&lt;!&ndash; <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> &ndash;&gt;-->

                <!--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>-->
                <!--&lt;!&ndash; <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> &ndash;&gt;-->
              <!--</div>-->
              <!--<span class="fly-list-nums">-->
            <!--<a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> 66</a>-->
            <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
          <!--</span>-->
            </div>
            <div class="detail-about">
              <!--<router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-avatar">-->
              <!--<img :src="postInfo.icon == null ? defaultAvatar : postInfo.icon"-->
              <!--:alt="postInfo.author">-->
              <!--</router-link>-->
              <a class="fly-avatar">
                <img :src="postsInfo.icon == null ? defaultAvatar : postsInfo.icon"
                     :alt="postsInfo.author">
              </a>
              <div class="fly-detail-user">
                <!--<router-link :to="'/user/index?userId='+ postInfo.userId" class="fly-link">-->
                <!--<cite>{{postInfo.author}}</cite>-->
                <!--&lt;!&ndash;<i class="iconfont icon-renzheng" title="认证信息"></i>&ndash;&gt;-->
                <!--&lt;!&ndash;<i class="layui-badge fly-badge-vip">VIP3</i>&ndash;&gt;-->
                <!--</router-link>-->
                <a class="fly-link">
                  <cite>{{postsInfo.author}}</cite>
                </a>
                <span>{{postsInfo.initTime | dateStr}}</span>
              </div>
              <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                <span style="padding-right: 10px; color: #FF7200">悬赏：{{postsInfo.rewardGrade}}钻石</span>
                <span v-if="postsInfo.id == postsInfo.userId && !postsInfo.end && !postsInfo.vote"
                      class="layui-btn layui-btn-xs jie-admin"
                      type="edit">
                  <router-link :to="'/add/index?postId=' + postsInfo.id">编辑此贴</router-link>
                </span>
              </div>
            </div>
            <div class="detail-body photos" v-html="postsInfo.content" v-loading="loadDetail">

            </div>
          </div>
        </el-card>
        <!-- 附件区 -->
        <el-card shadow="never" class="attach">
          <el-divider><div class="title">附件下载</div></el-divider>
        </el-card>
        <!-- 评论 -->
        <el-card shadow="never">
          <div class="comment-box">
            <el-divider><div class="title">评论</div></el-divider>
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
  import * as post from '@/api/post'
  import * as timeUtils from '@/utils/time';
    export default {
      name: "detail",
      data() {
        return {
          actionBox: false,
          disable: true,
          comment: '',
          postsInfo: '',
          loadDetail: false,
          postId: '',
          userInfo: '',
          defaultAvatar: require('../../../static/images/avatar/4.jpg')
        }
      },
      created() {
        this.postId = this.$route.query.postId;
        this.getDetailById();
        this.userInfo = this.$store.getters.user;
      },
      methods: {
        commentChange() {
          if (this.comment) {
            this.disable = false;
          } else {
            this.disable = true;
          }
        },
        getDetailById() {
          this.loadDetail = true;
          post.getDetail(this.postId).then(res => {
            this.loadDetail = false;
            this.postsInfo = res.data;
            // this.getReplyList(postId);
            // this.$nextTick(() => {
            //   this.layui();
            // });
            // // 判断是否为投票贴
            // if (this.postInfo.vote) {
            //   this.getVoteList(this.postInfo.id)
            // }
          })
        },
      },
      filters: {
        dateStr(date) {
          return timeUtils.dateDiff(date);
        }
      }
    }
</script>

<style scoped lang="scss">
  .detail-box {
    padding: 0px;
  }
  .el-card {
    margin-bottom: 8px;
    /deep/ .el-card__header {
      padding: 10px 20px;
    }
  }

  .attach {
    .title {
      color: #8a9aa9;
      font-size: 16px;
      font-weight: 400;
      text-align: center;
    }
  }

  .comment-box {
    .title {
      color: #8a9aa9;
      font-size: 16px;
      font-weight: 400;
      text-align: center;
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
