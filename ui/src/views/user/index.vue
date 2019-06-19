<template>
  <div class="layui-container">
    <el-card shadow="never" class="info">
      <div class="info-cover">
        <img :src="background" alt="">
        <el-upload
          class="info-cover-upload"
          :show-file-list="false"
          :action="actionUrl"
          :on-success="handleBackgroundSuccess"
          :before-upload="beforeBackgroundUpload">
          <el-button size="small" type="primary" icon="el-icon-camera-solid">编辑封面图片</el-button>
        </el-upload>
      </div>
      <div class="info-user">
        <el-upload
          accept="image/png,image/jpg,image/jpeg"
          class="avatar-uploader"
          :action="actionUrl"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
          <img v-if="avatarUrl" :src="avatarUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          <div class="avatar-hover">
            <i class="el-icon-camera-solid"></i>
            <p>修改我的头像</p>
          </div>
        </el-upload>

        <div class="info-user-content">
          <h1>
            <span class="name">{{userInfo.username}}<i class="hidden-sm-and-up el-icon-edit-outline"></i></span>
            <span>标签</span>
          </h1>
          <p>{{userInfo.signature}}</p>
          <el-button type="primary" size="small" class="hidden-sm-and-down">编辑个人资料</el-button>
        </div>
      </div>
    </el-card>
    <el-row :gutter="8" class="post-content">
      <el-col :span="18" :xs="24">
        <el-card shadow="never" class="post" @on-click="getCollection()">
          <el-tabs stretch v-model="tabName" @tab-click="changeTab">
            <el-tab-pane label="帖子" name="0">
              <el-row type="flex" justify="space-between" class="post-sort">
                <p>我的帖子</p>
                <div class="fly-filter">
                  <a @click="changeOrder('0')" :class="{'layui-this' : order == '0'}">按时间排序</a>
                  <el-divider direction="vertical" content-position="right"></el-divider>
                  <a @click="changeOrder('1')" :class="{'layui-this' : order == '1'}">按回复数排序</a>
                </div>
              </el-row>
            </el-tab-pane>
            <!-- <el-tab-pane label="回复"></el-tab-pane>-->
            <el-tab-pane label="收藏" name="1">
              <el-row type="flex" justify="space-between" class="post-sort">
                <p>我的收藏</p>
                <div class="post-sort fly-filter">
                  <a @click="changeOrder('0')" :class="{'layui-this' : order == '0'}">按时间排序</a>
                  <el-divider direction="vertical" content-position="right"></el-divider>
                  <a @click="changeOrder('1')" :class="{'layui-this' : order == '1'}">按回复数排序</a>
                </div>
              </el-row>
            </el-tab-pane>
            <el-tab-pane label="草稿" name="2">
              <el-row type="flex" justify="space-between">
                <p>我的草稿</p>
              </el-row>
            </el-tab-pane>
          </el-tabs>
          <el-card v-for="post in postList" :key="post.id" shadow="never" class="fly-list">
            <a class="fly-avatar hidden-sm-and-down">
              <el-image :src="post.icon" alt=""></el-image>
            </a>
            <h2>
              <a v-if="post.tagName != null" class="layui-badge">{{post.tagName}}</a>
              <router-link :to="'/post/detail?postId=' + post.id">{{post.title}}</router-link>
            </h2>
            <div class="fly-list-info">
              <a>
                <cite>{{post.author}}</cite>
              </a>
              <span> {{post.initTime | dateStr}}</span>
              <span v-if="post.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
              <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i>{{post.replyCount}}
                <span v-if="tabName != '1'">编辑</span>
                <span @click="deletePost(post)" v-if="tabName != '1'">删除</span>
              </span>
            </div>
            <div class="fly-list-badge">
              <span v-if="post.good" class="layui-badge layui-bg-red">精帖</span>
            </div>
          </el-card>
          <div v-if="postList.length == 0" class="post-empty">
            <svg-icon icon-class="empty"></svg-icon>
            <p>暂无数据</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6" :xs="24">
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
  import * as grade from '@/api/grade'

  import * as post from '@/api/post'

  import * as user from '@/api/user'

  import * as timeUtils from '@/utils/time'

  export default {
    name: "index",
    data() {
      return {
        actionUrl: window.localStorage.baseUrl + '/upload/file',
        userInfo: '',
        postList: [],
        userId: '',
        avatarUrl: '',
        loginUserId: '',
        order: '0',
        background: require('@/assets/img/cover-default.jpg'),
        tabName: '0'
      }
    },
    created() {
      this.userInfo = this.$store.getters.user;
      this.avatarUrl = this.userInfo.icon;
      this.background = this.userInfo.background || this.background;
      this.loginUserId = this.userInfo.id;
      this.userId = this.$route.query.userId;
      if (!this.userId) {
        this.userId = this.userInfo.id;
      } else {
        this.getOther();
      }
      this.getGrade();
      this.getList();
    },
    methods: {
      handleAvatarSuccess(res) {
        this.avatarUrl = res.data.src;
        this.upDateUser();
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 0.2;

        if (!isJPG && !isGIF && !isPNG) {
          this.$message.error('上传头像图片只能是 JPG、PNG、GIF 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 200KB!');
        }
        return (isJPG || isGIF || isPNG) && isLt2M;
      },
      handleBackgroundSuccess(res) {
        this.background = res.data.src;
        console.log(this.background);
        this.upDateUser();
      },
      beforeBackgroundUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      upDateUser() {
        let UserForm = {
          background: this.background,
          icon: this.avatarUrl
        };
        user.upDateUser(UserForm).then(res => {
          res.data.icon = this.avatarUrl;
          res.data.background = this.background;
          this.$router.push('/user/home');
        })
      },


      //获取他人主页用户信息
      getOther() {
        user.getOther(this.userId).then(res => {
          // console.log(res.data);
          this.userInfo = res.data;
        })
      },
      //获取积分
      getGrade() {
        let id = this.userId == undefined ? null : this.userId;
        grade.getGrade(id).then(res => {
          this.grade = res.data.grade;
        })
      },
      // 获取用户帖子列表
      getList() {
        let id = this.userId == undefined ? null : this.userId;
        let type = this.tabName;
        let order = this.order;
        post.getPersonList(id, type, order).then(res => {
          this.postList = res.data;
        })
      },
      // 改变所选帖子类型
      changeTab() {
        this.getList();
      },
      // 改变排序
      changeOrder(order) {
        this.order = order;
        this.getList();
      },
      /**
       * 删除帖子
       * @param id 帖子ID
       */
      deletePost(item) {
        this.$confirm(`即将删除标题为【${item.title}】的帖子，是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          post.del(item.id).then(res => {
            this.getList();
            this.$message({
              type: 'success',
              message: res.msg
            });
          })

        }).catch(() => {

        });
      }
    },
    filters: {
      formatTime(dateStr) {
        let date = new Date(dateStr);
        return timeUtils.dateFormat('YYYY-MM-dd hh:mm', date);
      }
    }
  }
</script>

<style scoped lang="scss">
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

  .el-card {
    margin-bottom: 8px;
    /deep/ .el-card__header {
      padding: 10px 20px;
    }
  }

  .post {
    .post-sort {
      p {
        font-weight: 600;
      }
      a {
        cursor: pointer;
      }
    }
    /deep/ .el-card__body {
      padding: 8px 20px;
    }
  }

  .info {
    /deep/ .el-card__body {
      padding: 0;
    }
  }

  .info-cover {
    position: relative;
    height: 240px;
    overflow: hidden;
    background: #f6f6f6;
    border-top-right-radius: 1px;
    border-top-left-radius: 1px;
    -webkit-transition: height .3s;
    transition: height .3s;
    img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    &-upload {
      position: absolute;
      top: 24px;
      right: 24px;
      z-index: 3;
      overflow: hidden;
      button {
        display: inline-block;
        padding: 0 16px;
        font-size: 14px;
        line-height: 32px;
        color: #8590a6;
        text-align: center;
        cursor: pointer;
        background: none;
        border: 1px solid;
        border-radius: 3px;
        color: rgba(26, 26, 26, .6);
        border-color: rgba(26, 26, 26, .12);
      }
    }
  }

  .info-user {
    position: relative;
    margin: 0 20px 24px;
    .avatar-uploader {
      position: absolute;
      top: -60px;
      background-color: #fff;
      /deep/ .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 0px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        &:hover {
          border-color: #009688;
          .avatar-uploader-icon {
            color: #009688;
          }
          .avatar-hover {
            display: flex;
          }
        }
        .avatar-uploader-icon {
          font-size: 28px;
          color: #8c939d;
          width: 150px;
          height: 150px;
          line-height: 150px;
          text-align: center;
        }
      }
      .avatar-hover {
        display: none;
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        flex-direction: column;
        justify-content: center;
        line-height: 150px;
        text-align: center;
        color: #fff;
        background-color: rgba(0, 0, 0, 0.18);
        i, p {
          font-size: 34px;
          line-height: 50px;
        }
        p {
          font-size: 16px;
          line-height: 30px;
        }
      }
    }
    .avatar {
      width: 150px;
      height: 150px;
      display: block;
    }
    &-content {
      padding-top: 16px;
      padding-left: 32px;
      border-left: 164px solid transparent;
      h1 {
        margin-bottom: 16px;
        span {
          font-size: 18px;
          &.name {
            font-size: 26px;
            font-weight: 500;
            i {
              font-size: 18px;
            }
          }
        }
      }
      p {
        padding-top: 8px;
      }
      button {
        position: absolute;
        right: 0;
        bottom: 0;
        display: inline-block;
        padding: 0 16px;
        font-size: 14px;
        line-height: 32px;
        text-align: center;
        cursor: pointer;
        background: none;
        border: 1px solid #009688;
        border-radius: 3px;
        color: #009688;
      }
    }
  }

  .post-content {
    .fly-list {
      position: relative;
      min-height: 45px;
      line-height: 22px;
      padding: 15px 15px 15px 75px;
      border-bottom: 1px dotted #e2e2e2;
      margin-top: 10px;
      /deep/ .el-card__body {
        padding: 0;
      }
      h2 {
        min-height: 26px;
        font-size: 18px;
      }
    }
  }
.fly-list-nums {
  display: flex;
  align-items: baseline;
  span {
    cursor: pointer;
    margin-left: 6px;
  }
}
  /* 手机端兼容 */
  @media only screen and (max-width: 767px) {
    .info-user {
      .avatar-uploader {
        left: 50%;
        transform: translateX(-50%);
      }
      &-content {
        padding: 100px 0 0;
        border: none;
        text-align: center;
        h1 {
          margin-top: 4px;
          span {
            display: block;
            padding-top: 8px;
          }
        }
      }
    }
    .post-content {
      padding: 0 8px;
      .fly-list {
        padding-left: 15px;
      }
    }
  }
</style>
