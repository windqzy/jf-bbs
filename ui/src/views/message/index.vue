<template>
  <div class="layui-container">
    <!-- TABS -->
    <el-tabs v-model="activeMsg" @tab-click="changeTab">
      <el-tab-pane label="用户消息" name="0"></el-tab-pane>
      <el-tab-pane label="系统消息" name="1"></el-tab-pane>
    </el-tabs>
    <!-- MSG -->
    <el-card shadow="never" v-if="msgList.length == 0">
      <p class="tc f16">无新的通知</p>
    </el-card>
    <!-- TODO：关注 -->
    <!--<el-card shadow="never" class="follow">
      <p>1个新的关注者</p>
      <div class="follow-card">
        <a>
          <img src="http://placehold.it/64x64" alt="">
          <div>
            <p>姓名</p>
            <p>这里可以考虑放置个性签名</p>
          </div>
        </a>
        <button class="follow-btn">关注</button>
      </div>
    </el-card>-->
    <el-card v-for="msg in msgList" shadow="never" v-else>
      <div class="msg-img">
        <img :src="msg.sponsor.icon" alt="">
        <div>
          <h2>{{msg.sponsor.username}}</h2>
          <p>回复人标签 · {{msg.messageEntity.createTime | dateStr}}</p>
        </div>
      </div>
      <div class="msg-content">
        <p v-html="msg.messageEntity.content"></p>
        <div class="msg-comment">
          <span>
            <!--<svg-icon icon-class="head"></svg-icon>-->
            <svg-icon icon-class="message"></svg-icon>
            {{msg.messageEntity.type == '0'?'评论了：':'回复了你的评论：'}}
          </span>
          <router-link :to="'/post/detail?postId=' + msg.postsEntity.id ">
            {{!msg.replyMessageEntity?msg.postsEntity.title :msg.replyMessageEntity.content}}
          </router-link>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  import * as reply from '@/api/reply'

  export default {
    name: "index",
    mounted() {
      this.getMsgList();
    },
    data() {
      return {
        activeMsg: '0',
        msgList: [], // 信息列表
      }
    },
    methods: {
      /* 切换标签页 */
      changeTab(e) {
        console.log(e.name);
      },
      getMsgList() {
        reply.msgList().then(res => {
          this.msgList = res.data;
        })
      }
    }
  }
</script>

<style scoped lang="scss">
  .tc {
    text-align: center
  }

  .f16 {
    font-size: 16px
  }

  .layui-container {
    padding: 0 10px;
    .el-card {
      margin-bottom: 16px;
      .msg-img {
        display: flex;
        cursor: pointer;
        img {
          width: 45px;
          height: 45px;
          margin-right: 20px;
          border-radius: 100%;
        }
        h2 {
          color: #2e3135;
          font-size: 15px;
          font-weight: 600;
        }
        p {
          color: #8a9aa9;
          font-size: 14px;
          padding: 6px 0;
          display: flex;
        }
      }
      .msg-content {
        padding-left: 65px;
        p {
          font-size: 15px;
          color: #17181a;
          padding: 0 0 8px;
        }
        .msg-comment {
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
          border-radius: 2px;
          background-color: #f8f8f8;
          height: 39px;
          padding: 0 12px;
          display: flex;
          justify-content: flex-start;
          align-items: center;
          span {
            color: #a1a9b3;
          }
          a {
            cursor: pointer;
            width: 100%;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
          }
        }
      }
    }
    /* 关注者 */
    .follow {
      p {
        font-size: 16px;
      }
      &-card {
        padding: 12px;
        display: flex;
        flex-direction: row;
        border-radius: 2px;
        border: 1px solid #ebebeb;
        justify-content: space-between;
        align-items: center;
        margin-top: 10px;
        a {
          display: flex;
          flex-direction: row;
          img {
            width: 50px;
            height: 50px;
            border-radius: 100%;
          }
          div {
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 0 12px;
            p {
              font-size: 16px;
              font-weight: 600;
              color: #2e3135;
              &:last-child {
                padding: 6px 0;
                font-size: 14px;
                color: #8a9aa9;
                font-weight: 400;
              }
            }
          }
        }
        .follow-btn {
          font-size: 14px;
          font-weight: 600;
          width: 100px;
          height: 34px;
          color: #37c701;
          border-radius: 3px;
          cursor: pointer;
          background-color: #fff;
          border: 1px solid #37c701;
        }
      }
    }
  }
</style>
