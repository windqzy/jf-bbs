<template>
  <div class="layui-container">
    <el-row :gutter="8">
      <el-col :lg="18" :xs="24">
        <el-card shadow="never" class="post">
          <div slot="header" class="fly-filter">
            <a :class="{'layui-this': type == 0}" @click="changeSort(0)" v-if="isSearch">全部</a>
            <!--<el-divider direction="vertical" v-if="!isSearch"></el-divider>-->
            <a :class="{'layui-this': type == 1}" @click="changeSort(1)" v-if="!isSearch">精华推荐</a>
            <el-divider direction="vertical" v-if="!isSearch"></el-divider>
            <a :class="{'layui-this': type == 2}" @click="changeSort(2)" v-if="!isSearch">最新发布</a>
            <el-divider direction="vertical" v-if="!isSearch"></el-divider>
            <a :class="{'layui-this': type == 3}" @click="changeSort(3)" v-if="!isSearch">最新回复</a>
          </div>
          <empty-data v-if="data.length == 0" class="fly-list"></empty-data>
          <ul class="fly-list" v-else>
            <li v-for="top in data">
              <a class="fly-avatar">
                <el-image :src="top.icon">
                  <div slot="error" class="img-error">
                    <svg-icon icon-class="img-error"></svg-icon>
                  </div>
                </el-image>
              </a>
              <h2>
                <a @click="toPostDetail(top.id)">{{top.title}}</a>
              </h2>
              <div class="fly-list-info">
                <span>{{top.initTime}}</span>
                <span class="fly-list-nums">
              </span>
              </div>
              <div class="fly-list-badge">
              </div>
            </li>
          </ul>
          <div style="text-align: center;cursor: pointer" v-if="!isSearch && data.length != 0">
            <div class="laypage-main">
              <a @click="getMorePost" class="laypage-next">更多求解</a>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :lg="6" :xs="24">
        <el-card shadow="never">
          <empty-data></empty-data>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import * as post from '@/api/post';
  import emptyData from '@/components/emptyData';

  export default {
    name: "index",
    components: {emptyData},
    data() {
      return {
        type: 0,
        isSearch: true, // 是否为搜索
        query: {
          currPage: 1,
          limit: 10
        },
        data: [],
      }
    },
    created() {
      this.type = this.$route.query.type;
      if (this.$route.query.search === undefined) {
        this.isSearch = false;
        this.getNewPosts(this.type);
      } else {
        this.isSearch = true;
      }
    },
    watch: {
      '$route.query.search'(newVal) {
        this.isSearch = true;
        if (!newVal) {
          this.data = [];
        } else {
          this.searchPosts(newVal)
        }
      }
    },
    methods: {
      /* 切换分类标签页 */
      changeSort(type) {
        this.type = type;
        this.getNewPosts(type);
      },
      /* 通过首页跳转查询 */
      getNewPosts(type = this.type) {
        let params = {
          currPage: this.query.currPage,
          limit: this.query.limit
        }
        post.getNewPosts(params).then(res => {
          if (type == 1) {
            this.data = res.data.goodList;
          } else if (type == 2) {
            this.data = res.data.publishList;
          } else if (type == 3) {
            this.data = res.data.replyList;
          }
        })
      },
      /* 查询更多 */
      getMorePost() {
        this.query.currPage++;
        this.getNewPosts();
      },
      /**
       * 搜索内容
       * @param keyword 关键词
       */
      searchPosts(keyword) {
        this.type = 0;
        keyword = encodeURIComponent(keyword);
        post.search(keyword).then(res => {
          this.data = res.data;
        }).catch(() => {
          this.data = [];
        })
      },
      toPostDetail(postId) {
        post.addRead(postId).then(res => {
          this.$router.push('/post/detail?postId=' + postId);
        });
      }
    }
  }
</script>

<style scoped lang="scss">
  .layui-container {
    .post {
      /deep/ .el-card__body {
        padding: 0;
        .el-image {
          width: 45px;
          height: 45px;
        }
      }
    }
    /deep/ .el-card__header {
      padding: 10px 20px;
    }
    .fly-filter {
      a {
        cursor: pointer;
      }
    }
  }

</style>
