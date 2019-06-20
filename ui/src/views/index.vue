<template>
  <div class="layui-container">
    <el-card shadow="never">
      <div class="divider">
        <i class="el-icon-s-data"></i>
        <span>今日: {{updateCount.todayCount}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>昨日: {{updateCount.yesterdayCount}}</span>
        <el-divider direction="vertical"></el-divider>
        <span>帖子: {{updateCount.count}}</span>
      </div>
    </el-card>
    <div class="brief">
      <el-row>
        <el-col :lg="8" :xs="24">
          <div class="brief-header">
            <p>
              <i class="el-icon-news" style="margin-right: 6px"></i>
              精华推荐
            </p>
            <router-link to="/guide/index?type=1">更多>></router-link>
          </div>
          <div v-if="newPost.goodList.length == 0" class="post-empty brief-content">
            <svg-icon icon-class="empty"></svg-icon>
            <p>暂无数据</p>
          </div>
          <ul class="brief-content" v-else>
            <li v-for="item in newPost.goodList">
              <span class="brief-item-time">{{item.initTime | formatTime}}</span>
              <a @click="toPostDetail(item.id)">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
        <el-col :lg="8" :xs="24">
          <div class="brief-header">
            <p>
              <i class="el-icon-news" style="margin-right: 6px"></i>
              最新发布
            </p>
            <router-link to="/guide/index?type=2">更多>></router-link>
          </div>
          <div v-if="newPost.publishList.length == 0" class="post-empty brief-content">
            <svg-icon icon-class="empty"></svg-icon>
            <p>暂无数据</p>
          </div>
          <ul class="brief-content" v-else>
            <li v-for="item in newPost.publishList">
              <span class="brief-item-time">{{item.initTime | formatTime}}</span>
              <a @click="toPostDetail(item.id)">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
        <el-col :lg="8" :xs="24">
          <div class="brief-header">
            <p>
              <i class="el-icon-news" style="margin-right: 6px"></i>
              最新回复
            </p>
            <router-link to="/guide/index?type=3">更多>></router-link>
          </div>
          <div v-if="newPost.replyList.length == 0" class="post-empty brief-content">
            <svg-icon icon-class="empty"></svg-icon>
            <p>暂无数据</p>
          </div>
          <ul class="brief-content" v-else>
            <li v-for="item in newPost.replyList">
              <span class="brief-item-time">{{item.initTime | formatTime}}</span>
              <a @click="toPostDetail(item.id)">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
      </el-row>
    </div>
    <div class="label">
      <el-card v-for="label in labelList" shadow="never" :key="label.id">
        <div slot="header" class="label-header">{{label.name}}</div>
        <el-row :gutter="10">
          <el-col :lg="8" :xs="24" v-for="tag in label.children" :key="tag.id" class="label-col"
                  :class="{'one': label.children.length % 3 == 1,'two': label.children.length % 3 == 2,'zero': label.children.length % 3 == 0}">
            <el-row :gutter="10" type="flex">
              <el-col :span="6">
                <el-image :src="tag.icon" :alt="tag.name" class="label-col-img">
                  <div slot="error" class="img-error">{{tag.name}}</div>
                </el-image>
              </el-col>
              <el-col :span="18" class="label-content-col">
                <div>
                  <router-link :to="'/post/index?labelId='+tag.id + '&type=0'" class="label-title">
                    {{tag.name}}
                    <em>({{tag.postsCount}})</em>
                  </router-link>
                </div>
                <div>
                  <span class="label-reply">
                    最后回复:昨天 17:03
                  </span>
                </div>
                <div>
                  <router-link :to="'/post/index?labelId='+tag.id + '&type=1'" class="label-good">本版精华</router-link>
                </div>
              </el-col>
            </el-row>
          </el-col>
        </el-row>
      </el-card>
    </div>
  </div>
</template>
<script>

  import * as label from '@/api/label';
  import * as posts from '@/api/post';
  import * as timeUtils from '@/utils/time';

  export default {
    name: "index",

    data() {
      return {
        labelList: [],
        briefList: require('../mock/brief.json'),
        updateCount: {
          todayCount: 0,
          yesterdayCount: 0,
          count: 0
        },
        newPost: {
          goodList:[],
          publishList: [],
          replyList:[]
        }
      }
    },
    created() {
      this.getAllLabel();
      this.getUpdateCount();
      this.getNewPosts();
    },
    mounted() {

    },
    methods: {
      toPostDetail(postId){
        posts.addRead(postId).then(res => {
          this.$router.push('/post/detail?postId=' + postId);
        });
      },
      getAllLabel() {
        label.getList().then(res => {
          this.labelList = res.data;
        })
      },
      getUpdateCount() {
        posts.getUpdateCount().then(res => {
          this.updateCount = res.data;
        })
      },
      getNewPosts() {
        let params = {
          currPage: 1,
          limit: 10
        };
        posts.getNewPosts(params).then(res => {
          this.newPost = res.data;
        })
      },
    },
    filters: {
      formatTime(dateStr) {
        let date = new Date(dateStr);
        return timeUtils.dateFormat('hh:mm', date);
      }
    }
  }
</script>
<style scoped lang="scss">
  .brief {
    margin: 10px 0;
    border: 1px solid #EBEEF5;
    padding: 10px;
    background-color: #fff;
    .el-row {
      height: 100%;
    }
    .el-col {
      padding: 0 10px;
      border-right: 1px solid #EBEEF5;
      &:first-child {
        li {
          /*list-style: square;*/
          color: #666;
        }
      }
      &:nth-child(2) {

      }
      &:last-child {
        border-right: none;
      }
    }
    &-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      margin-bottom: 10px;
      font-weight: bold;
      font-size: 16px;
      a {
        font-size: 12px;
        font-weight: 400;
      }
    }
    &-content {
      padding-left: 15px;
      margin-bottom: 8px;
      li {
        a {
          &:hover {
            color: #009688;
          }
        }
      }
    }
    .brief-content {
      .brief-item-time {
        margin-right: 10px;
        color: #999;
        font-size: 13px;
      }
      li {
        margin: 5px 2px;
      }
    }
  }

  .el-card {
    margin: 0 0 10px;
    border-radius: 0px;
    /deep/ .el-card__body {
      padding: 10px;
    }
    /deep/ .el-card__header {
      padding: 12px 20px;
      border-left: 2px solid #009688;
    }
  }

  .label {
    /deep/ .el-card__body {
      padding: 0 10px;
    }

    .label-header {
      font-weight: 600;
    }

    .label-title {
      font-size: 16px;
      font-weight: normal;
      cursor: pointer;
      em {
        color: #c01f2e;
        font-size: 12px;
        font-style: normal;
      }
    }
    .label-reply {
      color: #999;
      font-size: 12px;
    }
    .label-good {
      font-size: 12px;
      color: #1e63b0;
    }
    .label-col {
      padding: 16px 0;
      border-bottom: 1px solid #EBEEF5;
      &-img {
        width: 72px;
        height: 72px;
        /deep/ .img-error {
          background-color: #00bdac;
        }
      }
      .label-content-col {
        display: flex;
        flex-direction: column;
        justify-content: space-around;
      }
      &.zero {
        &:nth-last-child(1), &:nth-last-child(2), &:nth-last-child(3) {
          border-bottom: none;
        }
      }
      &.one {
        &:nth-last-child(1) {
          border-bottom: none;
        }
      }
      &.two {
        &:nth-last-child(1), &:nth-last-child(2) {
          border-bottom: none;
        }
      }
    }
  }

  @media only screen and (max-width: 767px) {
    .brief {
      .el-col {
        margin-bottom: 15px;
        border-right: none;
        border-bottom: 1px solid #EBEEF5;
      }
    }
  }
</style>
