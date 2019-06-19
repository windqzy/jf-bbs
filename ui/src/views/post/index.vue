<template>
  <div id="post" class="layui-container">
    <el-row :gutter="10">
      <el-col :lg="16" :sm="24">
        <!--<breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>-->
        <el-card shadow="never">
          <div class="post-divider active-form">
            <span :class="{'active': activeTag == -1}" @click="selectTag(-1, '')">全部<em> (25)</em></span>
            <span v-for="(item, index) in tagList">
              <el-divider direction="vertical"></el-divider>
              <span :class="{'active': activeTag == index}"
                    @click="selectTag(index, item.id)">{{item.name}}<em> (25)</em></span>
            </span>
          </div>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            <div class="post-type active-form">
              <span :class="{'active': postsForm.sortType == 0}" @click="selectSort(0)">最新</span>
              <el-divider direction="vertical" content-position="right"></el-divider>
              <span :class="{'active': postsForm.sortType == 1}" @click="selectSort(1)">热门</span>
              <el-divider direction="vertical"></el-divider>
              <span :class="{'active': postsForm.sortType == 2}" @click="selectSort(2)">精华</span>
            </div>
          </div>
          <div class="post-list">
            <div v-if="postsList.length == 0" class="post-empty">
              <svg-icon icon-class="empty"></svg-icon>
              <p>暂无数据</p>
            </div>
            <ul class="fly-list" v-else>
              <li v-for="post in postsList">
                <!--<router-link :to="'/user/index?userId='+ post.userId" class="fly-avatar">-->
                <!--<img :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author">-->
                <!--</router-link>-->
                <a class="fly-avatar">
                  <el-image :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author"></el-image>
                </a>
                <h2>
                  <a v-if="post.tagName != null" class="layui-badge">{{post.tagName}}</a>
                  <a @click="toPostDetail(post.id)">{{post.title}}
                  </a>
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
                    <cite>{{post.author == null ? '匿名' : post.author}}</cite>
                  </a>
                  <span> {{post.initTime | dateStr}}</span>
                  <!--<span> {{post.initTime | dateStr}} 发表  最后回复 {{post.lastReply | dateStr}}</span>-->
                  <!--<span class="fly-list-kiss layui-hide-xs" title="悬赏钻石" v-show="post.rewardGrade != 0">-->
                  <!--<i class="layui-icon layui-icon-diamond"></i> {{post.rewardGrade}}</span>-->
                  <span v-if="post.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                    <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
                    <i class="iconfont icon-pinglun1" title="回答"></i>{{post.replyCount}}
                  </span>
                </div>
                <div class="fly-list-badge">
                  <span v-if="post.good" class="layui-badge layui-bg-red">精帖</span>
                </div>
              </li>
            </ul>

            <div style="text-align: center;cursor: pointer" v-if="postsList.length != 0 && isMore">
              <div class="laypage-main">
                <a @click="getMorePost" class="laypage-next">更多求解</a>
              </div>
            </div>
            <el-divider v-if="!isMore">我是有底线的</el-divider>
          </div>
        </el-card>
      </el-col>
      <el-col :lg="8" :sm="24">
        <!--<el-card shadow="never">
          <el-button type="primary" icon="el-icon-edit">发布新主题</el-button>
        </el-card>-->
        <el-card shadow="never">
          <el-button type="warning" class="sign" :disabled="isSign" @click="sign">
            <div><i class="el-icon-edit"></i>打卡</div>
            <div>
              <p><i class="el-icon-s-data"></i> 今日排名 第{{signTop}}名</p>
              <p><i class="el-icon-date"></i> 连续打卡 {{signCount}}天</p>
            </div>
          </el-button>
        </el-card>
        <el-card shadow="never" class="mt8">
          <div slot="header">
            此处应为该板块公告或者广告
          </div>
          <div>
            <p v-html="labelInfo.details"></p>
          </div>
        </el-card>
        <!--<el-card shadow="never" class="mt8">-->
        <!--<div slot="header">-->
        <!--此处应为该板块公告或者广告-->
        <!--</div>-->
        <!--</el-card>-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import Breadcrumb from '@/components/Breadcrumb'
  import * as tag from '@/api/tag';
  import * as post from '@/api/post';
  import * as sign from '@/api/sign';
  import * as label from '@/api/label';
  import * as timeUtils from '@/utils/time';


  export default {
    name: "index",
    components: {
      Breadcrumb
    },
    data() {
      return {
        labelId: '',
        labelInfo: '',
        tagList: [],
        activeTag: -1,
        postsForm: {
          pageIndex: 1,
          pageSize: 20,
          tagId: '',
          sortType: 0
        },
        postsList: [],
        defaultAvatar: require('../../../static/images/avatar/4.jpg'),
        isSign: false,
        isMore: true, // 是否有更多帖子
        signCount: 0,
        signTop: 0
      }
    },
    created() {
      this.labelId = this.$route.query.labelId;
      this.getTagByLabelId();
      this.getPostsList();
      this.getIsSignFlag();
      this.getSignCount();
      this.getSignRank();
      this.getLabelList();
    },
    methods: {
      // 先增加次数，然后跳转页面
      toPostDetail(postId){
        post.addRead(postId).then(res => {
          this.$router.push('/post/detail?postId=' + postId);
        });
      },
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
        this.postsForm.sortType = index;
        this.getPostsList();
      },
      getPostsList() {
        post.getPostsList(this.postsForm).then(res => {
          this.postsList = res.data;
        })
      },
      /* 查看更多帖子 */
      getMorePost() {
        this.postsForm.pageIndex++;
        post.getPostsList(this.postsForm).then(res => {
          if (res.data.length == 0) {
            this.isMore = false;
          } else {
            this.postsList = this.postsList.concat(res.data);
          }
        })
      },
      getIsSignFlag() {
        sign.isSign().then(res => {
          this.isSign = res.data.isSign;
        })
      },
      sign() {
        sign.save().then(res => {
          this.$message.success(res.msg);
          this.getIsSignFlag();
          this.getSignCount();
          this.getSignRank();
        })
      },
      getSignCount() {
        sign.getSignCount().then(res => {
          this.signCount = res.data.signCount;
        })
      },
      getSignRank() {
        const type = 2;
        sign.list(type).then(res => {
          res.data.forEach((e, index) => {
            if (e.userId == this.$store.getters.user.id) {
              this.signTop = index + 1;
            }
          })
        })
      },
      getLabelList() {
        label.getList().then(res => {
          res.data.forEach(e => {
            e.children.forEach(f => {
              if (this.labelId == f.id) {
                this.labelInfo = f;
              }
            })
          })
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
      text-align: left;
    }
    .post-empty {
      text-align: center;
      color: #b2bac2;
      padding: 48px 0;
      .svg-icon {
        font-size: 64px;
      }
      p {
        font-size: 14px;
      }
    }
  }
</style>
