<template>
  <div class="layui-container">
    <el-row :gutter="10">
      <el-col :lg="18" :xs="24">
        <!-- 文章内容 -->
        <el-card shadow="never">
          <div class="detail-box">
            <el-page-header @back="goBack" :content="postsInfo.title"></el-page-header>
            <!--<h1>{{postsInfo.title}}</h1>-->
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
                <a class="fly-link author">
                  <cite>{{postsInfo.author == null ? '匿名' : postsInfo.author}}</cite>
                </a>

              </div>
              <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                <span>{{postsInfo.initTime | dateStr}}</span>
                <span>阅读数 {{postsInfo.readCount}}</span>
                <!--<span style="padding-right: 10px; color: #FF7200">悬赏：{{postsInfo.rewardGrade}}钻石</span>-->
                <!--<span v-if="postsInfo.id == postsInfo.userId && !postsInfo.end && !postsInfo.vote"-->
                <!--class="layui-btn layui-btn-xs jie-admin"-->
                <!--type="edit">-->
                <!--<router-link :to="'/add/index?postId=' + postsInfo.id">编辑此贴</router-link>-->
                <!--</span>-->
              </div>
              <div class="star">
                <!--<el-button size="small" type="success">关注</el-button>-->
                <div id="collection">
                  <el-button size="small" type="success" @click="changeCollection">收藏</el-button>
                </div>
                <div id="noCollection" style="display: none">
                  <el-button size="small" type="success" plain @click="changeCollection">取消收藏</el-button>
                </div>
              </div>
            </div>
            <div class="detail-body photos" v-html="postsInfo.content" v-loading="loadDetail">

            </div>
          </div>
        </el-card>
        <!-- 附件区 -->
        <el-card shadow="never" class="attach" v-if="fileList.length > 0">
          <el-divider>
            <div class="title">附件下载</div>
          </el-divider>
          <el-row :gutter="10">
            <el-col :lg="8" :xs="24" v-for="item in fileList" :key="item.id">
              <div class="pointer">
                <el-card shadow="hover">
                  <el-row>
                    <el-col :span="6">
                      <div class="file-type">{{item.type}}</div>
                    </el-col>
                    <el-col :span="18" class="attach-info">
                      <div :title="item.name">{{item.name}}</div>
                      <div>{{item.size | formatSize}}</div>
                    </el-col>
                  </el-row>
                </el-card>
                <div class="attach-mask">
                  <div class="attach-mask-filter"></div>
                  <el-button type="primary" size="mini" @click="preview(item)">预览</el-button>
                  <el-button type="primary" size="mini" @click="download(item)">下载</el-button>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
        <!-- 评论 -->
        <el-card shadow="never">
          <div class="comment-box">
            <el-divider>
              <div class="title">评论</div>
            </el-divider>
            <div class="comment-form">
              <el-row type="flex">
                <el-col :span="2" :xs="4">
                  <img :src="userInfo.icon">
                </el-col>
                <el-col :span="22" :xs="20">
                  <!--<el-input @focus="replyId = -1"
                            placeholder="输入评论..."
                            @input="commentChange"
                            size="small"
                            v-model="comment">
                  </el-input>-->
                  <edit-div v-model="comment" @focus="replyId = -1" @input="commentChange" placeholder="输入评论..."
                            class="rich-input"></edit-div>
                  <el-row class="action-box" type="flex" justify="space-between" align="middle" v-show="replyId == -1">
                    <el-col :span="4" :xs="6">
                      <el-popover width="300">
                        <ul class="face-list">
                          <li v-for="item in face" @click="appendComment(item)">
                            <img :src="item.src" :alt="item.alt">
                          </li>
                        </ul>
                        <el-button slot="reference" type="text" icon="el-icon-warning-outline">表情</el-button>
                      </el-popover>
                    </el-col>
                    <el-col :span="4" :xs="12" class="tr">
                      <el-button type="primary" size="small" :disabled="disable" @click="postComment({},0)">评论
                      </el-button>
                    </el-col>
                  </el-row>
                </el-col>
              </el-row>
            </div>
            <div class="comment-list">
              <div class="comment-item" v-for="reply in replyList" :key="reply.id">
                <img :src="reply.replyUser.icon" alt="">
                <div class="comment-content">
                  <div class="author">
                    {{reply.replyUser.username}}
                  </div>
                  <div class="content" v-html="reply.content"></div>
                  <div class="stat">
                    <el-row type="flex" justify="space-between">
                      <el-col :span="4" :xs="12">
                        <span>{{reply.initTime | dateStr}}</span>
                        <span v-if="reply.replyUser.id == userInfo.id">· 删除</span>
                      </el-col>
                      <el-col :span="4" :xs="12" class="action tr">
                        <div>
                          <i class="el-icon-goblet-square"></i>
                          <span>{{reply.upCount}}</span>
                        </div>
                        <div>
                          <i class="el-icon-chat-round"></i>
                          <label @click="replyComment(reply)">回复</label>
                        </div>
                      </el-col>
                    </el-row>
                  </div>
                  <el-card shadow="never" v-if="replyId == reply.id">
                    <!--<el-input autofocus :id="replyId"
                              :placeholder="'回复' + reply.replyUser.username+ '...'"
                              size="small"
                              v-model="commentText">-->
                    <edit-div v-model="commentText" @input="commentChange"
                              :placeholder="'回复' + reply.replyUser.username+ '...'"
                              class="rich-input" :id="replyId"></edit-div>
                    <!--</el-input>-->
                    <el-row class="action-box" type="flex" justify="space-between" align="middle">
                      <el-col :span="2" :xs="12">
                        <el-popover width="300">
                          <ul class="face-list">
                            <li v-for="item in face" @click="appendComment(item,reply)">
                              <img :src="item.src" :alt="item.alt">
                            </li>
                          </ul>
                          <el-button slot="reference" type="text" icon="el-icon-warning-outline">表情</el-button>
                        </el-popover>
                      </el-col>
                      <el-col :span="2" :xs="12" class="tr">
                        <el-button type="primary" size="small" :disabled="!commentText" @click="postComment(reply,1)">
                          评论
                        </el-button>
                      </el-col>
                    </el-row>
                  </el-card>
                  <div class="sub-comment-list">
                    <div v-for="item in reply.replayVoList" :key="item.id" style="display: flex">
                      <img :src="item.replyUser.icon" alt="">
                      <div class="item">
                        <div class="author">
                          {{item.replyUser.username}}
                        </div>
                        <div class="content">
                          <span class="">回复 <a class="layui-this">{{item.replyToUser.username}}</a>：</span>
                          <span v-html="item.content"></span>
                        </div>
                        <div class="stat">
                          <el-row type="flex" justify="space-between">
                            <el-col :span="6" :xs="10">
                              <span>{{item.initTime | dateStr}}</span>
                              <span v-if="item.userId == userInfo.id">·删除</span>
                            </el-col>
                            <el-col :span="6" :xs="14" class="action tr">
                              <div @click="upCount">
                                <i class="el-icon-goblet-square"></i>
                                <span>{{item.upCount}}</span>
                              </div>
                              <div>
                                <i class="el-icon-chat-round"></i>
                                <label @click="replyComment(item)">回复</label>
                              </div>
                            </el-col>
                          </el-row>
                        </div>
                        <el-card shadow="never" type="flex" v-if="replyId == item.id">
                          <!--<el-input autofocus :id="replyId"
                                    :placeholder="'回复' + item.replyUser.username+ '...'"
                                    size="small"
                                    v-model="commentText">
                          </el-input>-->
                          <edit-div v-model="commentText" :placeholder="'回复' + reply.replyUser.username+ '...'"
                                    class="rich-input" :id="replyId"></edit-div>
                          <el-row class="action-box" type="flex" justify="space-between" align="middle">
                            <el-col :span="2">
                              <el-popover width="300">
                                <ul class="face-list">
                                  <li v-for="face in face" @click="appendComment(face,item)">
                                    <img :src="face.src" :alt="face.alt">
                                  </li>
                                </ul>
                                <el-button slot="reference" type="text" icon="el-icon-warning-outline">表情</el-button>
                              </el-popover>
                            </el-col>
                            <el-col :span="4" class="tr">
                              <el-button type="primary" size="small" :disabled="!commentText"
                                         @click="postComment(item,2)">评论
                              </el-button>
                            </el-col>
                          </el-row>
                        </el-card>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </el-card>
        <!-- TODO 移动端bar -->
      </el-col>
      <!-- 右侧 -->
      <el-col :lg="6" :xs="24">
        <el-card shadow="never">
          <div slot="header">
            关于作者
          </div>
          <div class="author-about">
            <el-row>
              <el-col :span="6" :xs="4">
                <el-image :src="userInfo.icon == null? defaultAvatar : userInfo.icon"
                          :alt="userInfo.username"></el-image>
              </el-col>
              <el-col :span="18" :xs="20">
                <div class="info-box">
                  <a class="username">{{userInfo.username}}</a>
                  <div class="position">暂无成就</div>
                </div>
              </el-col>
            </el-row>
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
  import * as reply from '@/api/reply'
  import * as timeUtils from '@/utils/time';
  import * as face from '@/assets/face.json';
  import editDiv from '@/components/EditDiv';
  import * as collection from '@/api/collection';

  export default {
    name: "detail",
    components: {editDiv},
    data() {
      return {
        actionBox: false,
        disable: true,
        comment: '', // 文章回复
        commentText: '', // 评论回复
        postsInfo: '',
        loadDetail: false,
        postId: '',
        userInfo: '',
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
        fileList: [],
        replyList: [],// 评论列表
        replyId: '',
        msg: '',
        coll: '',
        face: require('@/assets/face.json')
      }
    },
    created() {
      this.postId = this.$route.query.postId;
      this.getDetailById();
      this.userInfo = this.$store.getters.user;
      this.getFileList();
      this.isColl();
      this.getReplyList();
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      changeCollection() {
        let div1 = document.getElementById("collection");
        let div2 = document.getElementById("noCollection");
        if (div1.style.display == "none") {
          div1.style.display = "";
          div2.style.display = "none";
          this.addCollection();
        } else {
          div1.style.display = "none";
          div2.style.display = "";
          this.addCollection();
        }
      },
      addCollection() {
        let id = this.postId;
        collection.addCollection(id).then(res => {
          this.msg = res.data;
          this.$message({
            message: this.msg,
            type: 'success'
          });
        })
      },
      //判断是否是收藏状态
      isColl() {
        let id = this.postId;
        collection.isColl(id).then(res => {
          this.coll = res.isColl;
          let div1 = document.getElementById("collection");
          let div2 = document.getElementById("noCollection");
          if (this.coll == "true") {
            div1.style.display = "none";
            div2.style.display = "";
          } else {
            div1.style.display = "";
            div2.style.display = "none";
          }
        })
      },
      getReplyList() {
        reply.getReplyList(this.postId).then(res => {
          this.replyList = res.data;
        })
      },
      commentChange() {
        if (this.comment) {
          this.disable = false;
        } else {
          this.disable = true;
        }
      },
      /**
       * 评论添加表情
       **/
      appendComment(face, item = {}) {
        let imgStr = `<img src="${face.src}" alt="${face.alt}"/>`;
        if (!item.id) {
          this.comment += imgStr;
        } else {
          this.commentText += imgStr;
        }
        this.commentChange();
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
      getFileList() {
        post.getFileList(this.postId).then(res => {
          this.fileList = res.data;
        })
      },
      /* 预览 */
      preview(item) {
        let url = encodeURIComponent(item.url); //要预览文件的访问地址
        let winHeight = window.document.documentElement.clientHeight - 10;
        window.open('http://127.0.0.1:8012/onlinePreview?url=' + url, '_blank', 'height=' + winHeight + ',top=80,left=80,toolbar=no,menubar=no,scrollbars=yes,resizable=yes');
      },
      /* 下载附件 */
      download(item) {
        console.log(item);
        let a = document.createElement('a');
        a.setAttribute('download', item.name);
        a.setAttribute('href', item.url);
        a.click();
      },
      /* 评论点赞 */
      upCount() {

      },
      /* 回复评论 */
      replyComment(item) {
        if (this.replyId == item.id) {
          this.replyId = '';
        } else {
          this.replyId = item.id
        }
        this.commentText = '';
        /* 获得焦点 */
        this.$nextTick(() => {
          if (this.replyId) {
            document.getElementById(this.replyId).focus();
          }
        })
      },
      /**
       * 发布评论
       * @param item 评论对象
       * @param status 0 一级回复 1 二级回复 2 三级回复
       */
      postComment(item, status) {
        this.replyId = status == 0 ? '-1' : item.id;
        let data = {
          content: status == 0 ? this.comment : this.commentText,
          postsId: +this.postId,
          userId: this.userInfo.id,
          parentId: status == 2 ? item.parentId : status == 0 ? '' : item.id,
          replyTo: !item.replyUser ? '' : item.replyUser.id
        }
        reply.add(data).then(res => {
          this.replyId = '';
          this.comment = '';
          this.$message({type: 'success', message: res.msg, duration: 1000});
          this.getReplyList();
        })
      },
    },
    filters: {
      dateStr(date) {
        return timeUtils.dateDiff(date);
      },
      formatSize(size) {
        if (size < 1000000) {
          return (size / 1000).toFixed(2) + 'KB'
        } else {
          return (size / 1000000).toFixed(2) + 'MB';
        }
      }
    }
  }
</script>

<style scoped lang="scss">
  .tr {
    text-align: right;
  }

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
    &-info {
      div {
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        line-height: 22px;
      }
    }
    .pointer {
      position: relative;
      &:hover {
        .attach-mask {
          display: flex;
        }
      }
      .attach-mask {
        display: none;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        justify-content: center;
        align-items: center;
        background-color: rgba(200, 200, 200, 0.8);
        &-filter {
          position: absolute;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          filter: blur(20px);
        }
      }
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
      padding: 10px 8px;
      background-color: #fafbfc;
      border-radius: 3px;

      img {
        width: 2.667rem;
        height: 2.667rem;
        border-radius: 50%;
      }

      .el-input {
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
            div {
              display: inline-block;
              margin-left: 18px;
              cursor: pointer;
            }
          }
        }
        .sub-comment-list {
          display: flex;
          flex-direction: column;
          margin: 6px 0;
          background-color: #fafbfc;
          border-radius: 3px;
          .item {
            margin: 14px 0;
            width: 100%;
            padding: 0 2px;
            border-bottom: 1px solid #f1f1f1;
            .content {
              a {
                color: #406599;
                cursor: pointer;
              }
            }
          }
        }
      }
    }
  }

  .author-about {
    /deep/ .el-image {
      img {
        width: 48px;
        height: 48px;
        border-radius: 50%;
      }
    }

    .info-box {
      flex: 1 1 auto;
      min-width: 0;

      .username {
        font-size: 16px;
        font-weight: 600;
        color: #000;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: pre-wrap;
      }

      .position {
        margin-top: 5px;
        font-size: 16px;
        color: #72777b;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }

  .detail-about {
    .star {
      position: absolute;
      top: 50%;
      right: 30px;
      transform: translateY(-50%);
    }

    .author {
      display: inline-block;
      max-width: 100%;
      font-size: 1.3rem;
      font-weight: 500;
      color: #333;
    }
  }

  .attach {
    /deep/ .el-card {
      .el-card__body {
        padding: 15px
      }
    }

    .file-type {
      width: 42px;
      height: 42px;
      line-height: 42px;
      /*border-radius: 50%;*/
      background-color: #33aba0;
      color: #fff;
      text-align: center;
    }
  }

  .face-list {
    display: flex;
    flex-wrap: wrap;
    height: 200px;
    overflow: auto;
  }

  @media only screen and (max-width: 768px) {
    .comment-box {
      .comment-form {

      }
      .comment-list {
        margin: 20px 0px 0 0px;
      }
    }
  }
</style>
