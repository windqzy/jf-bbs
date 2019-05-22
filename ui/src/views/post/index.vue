<template>
  <div id="post" class="layui-container">
    <el-row :gutter="10">
      <el-col :span="16">
        <breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>
        <el-card shadow="never">
          <div class="post-divider active-form">
            <span :class="{'active': activeTag == -1}" @click="selectTag(-1, '')">全部<em> (25)</em></span>
            <span v-for="(item, index) in tagList">
              <el-divider direction="vertical"></el-divider>
              <span :class="{'active': activeTag == index}" @click="selectTag(index, item.id)">{{item.name}}<em> (25)</em></span>
            </span>
          </div>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            <div class="post-type active-form">
              <span :class="{'active': activeSort == 0}" @click="selectSort(0)">最新</span>
              <el-divider direction="vertical" content-position="right"></el-divider>
              <span :class="{'active': activeSort == 1}" @click="selectSort(1)">热门</span>
              <el-divider direction="vertical"></el-divider>
              <span :class="{'active': activeSort == 2}" @click="selectSort(2)">精华</span>
            </div>
          </div>
          <div class="post-list">
            <ul class="fly-list">
              <li v-for="post in postsList">
                <!--<router-link :to="'/user/index?userId='+ post.userId" class="fly-avatar">-->
                <!--<img :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author">-->
                <!--</router-link>-->
                <a class="fly-avatar">
                  <el-image :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author"></el-image>
                </a>
                <h2>
                  <a v-if="post.tagName != null" class="layui-badge">{{post.tagName}}</a>
                  <router-link :to="'/post/detail?postId=' + post.id">{{post.title}}
                  </router-link>
                </h2>
                <div class="fly-list-info">
                  <!--<router-link :to="'/user/index?userId='+ post.userId">-->
                  <!--<cite>{{post.author}}</cite>-->
                  <!--&lt;!&ndash;-->
                  <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                  <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                  <!--&ndash;&gt;-->
                  <!--</router-link>-->
                  <a>
                    <cite>{{post.author}}</cite>
                  </a>
                  <span> {{post.initTime | dateStr}} 发表  最后回复 {{post.lastReply | dateStr}}</span>
                  <!--<span class="fly-list-kiss layui-hide-xs" title="悬赏钻石" v-show="post.rewardGrade != 0">-->
                    <!--<i class="layui-icon layui-icon-diamond"></i> {{post.rewardGrade}}</span>-->
                  <span v-if="post.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                    <i class="iconfont" title="人气">&#xe60b;</i> 99999
                    <i class="iconfont icon-pinglun1" title="回答"></i>{{post.replyCount}}

                  </span>
                </div>
                <div class="fly-list-badge">
                  <span v-if="post.good" class="layui-badge layui-bg-red">精帖</span>
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="never">
          <el-button type="primary" icon="el-icon-edit">发布新主题</el-button>
        </el-card>
        <el-card shadow="never" class="mt8">
          <el-button type="warning" class="sign">
            <div><i class="el-icon-edit"></i>签到</div>
            <div>
              <p><i class="el-icon-s-data"></i> 今日排名 第6名</p>
              <p><i class="el-icon-date"></i> 连续打卡 26天</p>
            </div>
          </el-button>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            此处应为该板块公告或者广告
          </div>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            此处应为该板块公告或者广告
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Breadcrumb from '@/components/Breadcrumb'
  import * as tag from '@/api/tag';
  import * as post from '@/api/post';
  import * as timeUtils from '@/utils/time';


  export default {
    name: "index",
    components: {
      Breadcrumb
    },
    data() {
      return {
        labelId: '',
        tagList: [],
        activeTag: -1,
        activeSort: 0,
        postsForm: {
          type: 0,
          pageIndex: 1,
          pageSize: 20,
          tagId: ''
        },
        postsList: [],
        defaultAvatar: require('../../../static/images/avatar/4.jpg')
      }
    },
    created() {
      this.labelId = this.$route.query.labelId;
      this.getTagByLabelId();
      this.getPostsList();
    },
    methods: {
      getTagByLabelId() {
        tag.getList(this.labelId).then(res => {
          this.tagList = res.data;
        })
      },
      selectTag(index, tagId) {
        this.postsForm.tagId = tagId;
        this.activeTag = index;
        this.getPostsList();
      },
      selectSort(index) {
        this.activeSort = index;
      },
      getPostsList() {
        post.getPostsList(this.postsForm).then(res => {
          this.postsList = res.data;
        })
      }
    },
    filters: {
      dateStr(date) {
        return timeUtils.dateDiff(date);
      },
      formatTime(dateStr) {
        let date = new Date(dateStr);
        return timeUtils.dateFormat('YYYY-MM-dd hh:mm', date);
      }
    },
    destroyed() {
      console.log("died")
    }
  }
</script>

<style scoped lang="scss">
  #post {
    .mt8 {
      margin-top: 8px;
    }

    .el-button--primary {
      border-radius: 0px;
      width: 100%;
    }

    .el-card {
      /deep/ .el-card__header {
        padding: 10px 20px;
      }

      /deep/ .el-card__body {
        padding: 12px;
      }
    }

    .post-divider {
      em {
        font-style: normal;
        color: #c01f2e;
      }

      .el-divider--vertical {
        margin: 8px;
      }
    }

    /*签到*/
    .sign {
      width: 100%;
      padding: 1px;
      border-radius: 0;

      & > /deep/ span {
        display: flex;
        align-items: center;
        justify-content: center;

        & > div {
          width: 50%;

          &:last-child {
            padding: 4px 0 4px 20px;
            background: #fff;
            box-sizing: border-box;

            p {
              color: #999;
              line-height: 1.5;
              text-align: left;
            }
          }
        }
      }
    }

    /*文章*/
    .post-list {

    }

    .post-type {
      text-align: right;

    }
  }
</style>
