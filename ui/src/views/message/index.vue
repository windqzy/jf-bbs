<template>
  <div class="layui-container">
    <!-- TABS -->
    <el-tabs v-model="activeMsg" @tab-click="changeTab">
      <el-tab-pane label="用户消息" name="0"></el-tab-pane>
      <el-tab-pane label="系统消息" name="1"></el-tab-pane>
    </el-tabs>
    <!-- MSG -->
    <el-card shadow="never" class="follow">
      <p>1个新的关注者</p>
      <div class="follow-card">
        <a>
          <img src="http://placehold.it/64x64" alt="">
          <div>
            <p>111</p>
            <p>11111111</p>
          </div>
        </a>
        <button class="follow-btn">关注</button>
      </div>
    </el-card>
    <el-card v-for="msg in 5" shadow="never">
      <div class="msg-img">
        <img src="http://placehold.it/45x45" alt="">
        <div>
          <h2>回复人姓名</h2>
          <p>回复人标签 · 10月前</p>
        </div>
      </div>
      <div class="msg-content">
        <p>回复内容</p>
        <div class="msg-comment">
          <span>
            <svg-icon icon-class="head"></svg-icon>
            <svg-icon icon-class="message"></svg-icon>
             回复了你的评论：
          </span>
          <a>评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容评论内容</a>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        activeMsg: '0'
      }
    },
    methods: {
      /* 切换标签页 */
      changeTab() {

      }
    }
  }
</script>

<style scoped lang="scss">
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
          }
        }
        button {
          font-size: 14px;
          font-weight: 600;
          width: 100px;
          height: 34px;
          color: #37c701;
          border-radius: 3px;
          background-color: #fff;
          border: 1px solid #37c701;
        }
      }
    }
  }
</style>
