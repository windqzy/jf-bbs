<template>
  <div class="layui-container">
    <el-card shadow="never">
      <div class="divider">
        <i class="el-icon-s-data"></i>
        <span>今日: 303</span>
        <el-divider direction="vertical"></el-divider>
        <span>昨日: 56</span>
        <el-divider direction="vertical"></el-divider>
        <span>帖子: 342326</span>
      </div>
    </el-card>
    <div class="brief">
      <el-row type="flex">
        <el-col :span="8">
          <div class="brief-header">
            <p>
              <i class="el-icon-news"></i>
              精华推荐
            </p>
            <a>更多>></a>
          </div>
          <ul class="brief-content">
            <li v-for="item in briefList.recommend">
              <a href="">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
        <el-col :span="8">
          <div class="brief-header">
            <p>
              <i class="el-icon-news"></i>
              最新发布
            </p>
            <a>更多>></a>
          </div>
          <ul class="brief-content">
            <li v-for="item in briefList.publish">
              <a href="">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
        <el-col :span="8">
          <div class="brief-header">
            <p>
              <i class="el-icon-news"></i>
              最新回复
            </p>
            <a>更多>></a>
          </div>
          <ul class="brief-content">
            <li v-for="item in briefList.reply">
              <a href="">{{item.title}}</a>
            </li>
          </ul>
        </el-col>
      </el-row>
    </div>
    <div class="label">
      <el-card v-for="label in labelList" shadow="never" :key="label.id">
        <div slot="header" class="label-header">{{label.name}}</div>
        <el-row :gutter="10">
          <el-col :span="8" v-for="tag in label.child" :key="tag.id" class="label-col"
                  :class="{'one': label.child.length % 3 == 1,'two': label.child.length % 3 == 2,'zero': label.child.length % 3 == 0}">
            <el-row :gutter="10" type="flex">
              <el-col :span="6">
                <img :src="tag.cover" alt="">
              </el-col>
              <el-col :span="18" class="label-content-col">
                <div>
                  <router-link :to="'/post/index?labelId='+label.id" class="label-title">
                    {{tag.name}}
                    <em>(20)</em>
                  </router-link>
                </div>
                <div>
                  <span class="label-reply">
                    最后回复:昨天 17:03
                  </span>
                </div>
                <div>
                  <a class="label-good">本版精华</a>
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

  import * as post from '@/api/post';
  import * as reply from '@/api/reply';
  import * as time from '@/utils/time';
  import * as sign from '@/api/sign';
  import * as grade from '@/api/grade';
  import * as label from '@/api/label';
  import * as tag from '@/api/tag';
  import * as log from '@/api/log';
  // import * as labelArr from '@/mock/label.json';

  export default {
    name: "index",

    data() {
      return {
        labelList: require('../mock/label.json'),
        briefList: require('../mock/brief.json'),
      }
    },
    created() {
      // this.getAllLabel();
    },
    mounted() {

    },
    methods: {
      getAllLabel() {
        label.getList().then(res => {
          this.labelList = res.data.list;
        })
      }
    }
  }
</script>
<style scoped lang="scss">
  .brief {
    margin: 5px 0;
    border: 1px solid #EBEEF5;
    height: 200px;
    padding: 10px;
    background-color: #fff;
    .el-row {
      height: 100%;
    }
    .el-col {
      padding: 0 10px;
      border-right: 1px solid #EBEEF5;
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
      font-size: 14px;
    }
  }

  .el-card {
    margin: 5px 0;
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
</style>
