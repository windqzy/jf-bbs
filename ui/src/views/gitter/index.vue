<template>
  <div class="layui-container">
    <el-row :gutter="16" style="padding: 0 16px 16px">
      <el-col :xs="24" :md="16">
        <!--<el-card shadow="never">-->
          <!--<img src="../../../static/images/music.jpg" alt="金风音乐会" style="max-width: 100%;">-->
        <!--</el-card>-->
        <el-card shadow="never" class="sider mt8">
          <!--<div slot="header">前端</div>-->
          <div class="type-box">
            <span>前端</span>
            <div class="fly-mid"></div>
            <a @click="searchLanguage('')">All</a>
            <a @click="searchLanguage('javascript')">Javascript</a>
            <a @click="searchLanguage('css')">CSS</a>
            <a @click="searchLanguage('vue')">Vue</a>
            <a @click="searchLanguage('angular')">Angular</a>
            <a @click="searchLanguage('react')">React</a>
          </div>
          <div class="type-box">
            <span>后端</span>
            <div class="fly-mid"></div>
            <a @click="searchLanguage('')">All</a>
            <a @click="searchLanguage('c')">C</a>
            <a @click="searchLanguage('c++')">C++</a>
            <a @click="searchLanguage('c#')">C#</a>
            <a @click="searchLanguage('java')">Java</a>
            <a @click="searchLanguage('python')">Python</a>
            <a @click="searchLanguage('go')">Go</a>
            <a @click="searchLanguage('pascal')">Delphi</a>
          </div>
        </el-card>

        <el-card shadow="never" class="mt8 repo" v-loading="loading">
          <el-row slot="header" type="flex" justify="space-between">
            <a>开源库推荐<span v-if="language"> / {{decodeURIComponent(decodeURIComponent(language))}}</span> </a>
            <div class="layuiadmin-card-link fly-filter">
              <a :class='{"layui-this":activeTag == 0}' @click="searchTime(0, 'daily')">今日最热</a>
              <span class="fly-mid"></span>
              <a :class='{"layui-this":activeTag == 1}' @click="searchTime(1, 'weekly')">本周最热</a>
              <span class="fly-mid"></span>
              <a :class='{"layui-this":activeTag == 2}' @click="searchTime(2, 'monthly')">本月最热</a>
            </div>
          </el-row>
          <div class="card-box" v-for="item in trendingList">
            <a :href="item.builtBy.length == 0?'javascript:;':item.builtBy[0].href" target="_blank">
              <img src="../../assets/img/github.png" alt="" v-if="item.builtBy.length == 0">
              <img :src="item.builtBy[0].avatar" :alt="item.builtBy.length == 0?'':item.builtBy[0].username" v-else>
            </a>
            <a :href="item.url" target="_blank">
              <div class="info-box">
                <h2>{{item.name + ' / '}}{{item.builtBy.length == 0?'':item.builtBy[0].username}}</h2>
                <p>{{item.description}}</p>
                <el-row type="flex" align="middle">
                  <svg-icon icon-class="star"></svg-icon>
                  <!--<img src="@/assets/img/star.png" alt="" class="icon">-->
                  <span>{{item.stars | toQfw}}</span>
                  <svg-icon icon-class="fork"></svg-icon>
                  <!--<img src="@/assets/img/forkrepo.png" alt="" class="icon">-->
                  <span>{{item.forks | toQfw}}</span>
                  <svg-icon v-if="item.language" icon-class="circle" class="svg-circle"
                            :style="{color: item.languageColor}"></svg-icon>
                  <!--<img src="@/assets/img/circle.png" alt="" class="icon">-->
                  <span v-if="item.language">{{item.language}}</span>
                  <!--<span class="time">12小时前</span>-->
                </el-row>
              </div>
            </a>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :md="8">
        <!--<el-card shadow="never" class="sider mt8">-->
          <!--<div slot="header">前端</div>-->
          <!--<div class="type-box">-->
            <!--<a @click="searchLanguage('')">All</a>-->
            <!--<a @click="searchLanguage('javascript')">Javascript</a>-->
            <!--<a @click="searchLanguage('css')">CSS</a>-->
            <!--<a @click="searchLanguage('vue')">Vue</a>-->
            <!--<a @click="searchLanguage('angular')">Angular</a>-->
            <!--<a @click="searchLanguage('react')">React</a>-->
          <!--</div>-->
        <!--</el-card>-->
        <!--<el-card shadow="never" class="sider mt8">-->
          <!--<div slot="header">后端</div>-->
          <!--<div class="type-box">-->
            <!--<a @click="searchLanguage('')">All</a>-->
            <!--<a @click="searchLanguage('c')">C</a>-->
            <!--<a @click="searchLanguage('c++')">C++</a>-->
            <!--<a @click="searchLanguage('c#')">C#</a>-->
            <!--<a @click="searchLanguage('java')">Java</a>-->
            <!--<a @click="searchLanguage('python')">Python</a>-->
            <!--<a @click="searchLanguage('go')">Go</a>-->
            <!--<a @click="searchLanguage('pascal')">Delphi</a>-->
          <!--</div>-->
        <!--</el-card>-->
        <!--<div class="fly-panel mt8">-->
          <!--&lt;!&ndash;<div class="fly-panel-title">前端日报</div>&ndash;&gt;-->
          <!--&lt;!&ndash;<div class="fly-panel-main fly-list-static">&ndash;&gt;-->
            <!--&lt;!&ndash;<el-collapse v-model="activeName" accordion @change="getCoderDailyInfo">&ndash;&gt;-->
              <!--&lt;!&ndash;<el-collapse-item :title="item.title" :name="item.id" v-for="(item, index) in dailyDateList">&ndash;&gt;-->
                <!--&lt;!&ndash;<ul class="fly-panel-main fly-list-static">&ndash;&gt;-->
                  <!--&lt;!&ndash;<li v-for="(info, index) in dailyInfoList" :key="index">&ndash;&gt;-->
                    <!--&lt;!&ndash;<a :href="info.url" target="_blank">{{info.title}}</a>&ndash;&gt;-->
                  <!--&lt;!&ndash;</li>&ndash;&gt;-->
                <!--&lt;!&ndash;</ul>&ndash;&gt;-->
              <!--&lt;!&ndash;</el-collapse-item>&ndash;&gt;-->
            <!--&lt;!&ndash;</el-collapse>&ndash;&gt;-->
          <!--&lt;!&ndash;</div>&ndash;&gt;-->

        <!--</div>-->
        <el-collapse v-model="activeName" accordion @change="getCoderDailyInfo" class="mt8">
          <el-collapse-item :title="item.title" :name="item.id" v-for="(item, index) in dailyDateList" :key="index">
            <ul class="fly-panel-main fly-list-static">
              <li v-for="(info, index) in dailyInfoList" :key="index">
                <a :href="info.url" target="_blank">{{info.title}}</a>
              </li>
            </ul>
          </el-collapse-item>
        </el-collapse>
        <div class="fly-panel mt8" style="padding: 20px 0; text-align: center;">
          <img src="../../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
          <p style="position: relative; color: #666;">微信扫码关注 金风推特 公众号</p>
        </div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
  import * as gitter from '@/api/gitter'
  import {
    fetchAllLanguages,
    fetchRepositories,
    fetchDevelopers,
  } from '@huchenme/github-trending';

  export default {
    name: "index",
    data() {
      return {
        trendingList: [],
        loading: true,
        language: '',
        since: 'daily',    //weekly monthly
        activeTag: 0,
        activeName: '',
        dailyDateList: [],
        dailyInfoList: []
      }
    },
    created() {
      this.getReposTrendingList();
      this.getCoderDailyDate();
    },
    methods: {
      getReposTrendingList() {
        this.loading = true;
        let type = 'repo';
        fetchRepositories({language: this.language, since: this.since}).then(res => {
          this.loading = false;
          console.log(res)
          this.trendingList = res;
        });

        // gitter.getReposTrendingList(type, this.language, this.since).then(res => {
        //   console.log(res.data);
        //   this.loading = false;
        //   this.trendingList = res.data;
        // }).catch(() => {
        //   layer.msg('服务器超时，近期会解决，还请见谅')
        //   this.loading = false;
        // })
      },
      searchTime(index, since) {
        this.activeTag = index;
        this.since = since;
        this.getReposTrendingList();
      },
      searchLanguage(language) {
        this.language = encodeURIComponent(language);
        this.getReposTrendingList();
      },
      getCoderDailyDate() {
        gitter.getCoderDailyDate().then(res => {
          this.dailyDateList = res.data;
          this.activeName = res.data[0].id;
          this.getCoderDailyInfo(this.activeName);
        })
      },
      getCoderDailyInfo(date) {
        gitter.getCoderDailyInfo(date).then(res => {
          this.dailyInfoList = res.data.links;
        })
      }

    },
    filters: {
      formatTime(dateTime) {
        var timestamp = Date.parse(dateTime);
        // 补全为13位
        var arrTimestamp = (timestamp + '').split('');
        for (var start = 0; start < 13; start++) {
          if (!arrTimestamp[start]) {
            arrTimestamp[start] = '0';
          }
        }
        timestamp = arrTimestamp.join('') * 1;
        var minute = 1000 * 60;
        var hour = minute * 60;
        var day = hour * 24;
        var halfamonth = day * 15;
        var month = day * 30;
        var now = new Date().getTime();
        var diffValue = now - timestamp;
        // 如果本地时间反而小于变量时间
        if (diffValue < 0) {
          return '不久前';
        }
        // 计算差异时间的量级
        var monthC = diffValue / month;
        var weekC = diffValue / (7 * day);
        var dayC = diffValue / day;
        var hourC = diffValue / hour;
        var minC = diffValue / minute;
        // 数值补0方法
        var zero = function (value) {
          if (value < 10) {
            return '0' + value;
          }
          return value;
        };
        // 使用
        if (monthC > 12) {
          // 超过1年，直接显示年月日
          return (function () {
            var date = new Date(timestamp);
            return date.getFullYear() + '年' + zero(date.getMonth() + 1) + '月' + zero(date.getDate()) + '日';
          })();
        } else if (monthC >= 1) {
          return parseInt(monthC) + "月前";
        } else if (weekC >= 1) {
          return parseInt(weekC) + "周前";
        } else if (dayC >= 1) {
          return parseInt(dayC) + "天前";
        } else if (hourC >= 1) {
          return parseInt(hourC) + "小时前";
        } else if (minC >= 1) {
          return parseInt(minC) + "分钟前";
        }
        return '刚刚';
      },
      toQfw(str) {
        return (str || 0).toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
      }
    }
  }
</script>

<style scoped lang="scss">
  /deep/ .el-card__body {
    padding: 10px;
  }

  .code {
    width: 100%;
    min-height: 100%;
    padding: 8px;;
    background: #edeff1;
  }

  .mt8 {
    margin-top: 10px;
  }

  .header {
    /deep/ .el-card__body {
      padding: 4px 8px;
    }

    .el-col {
      display: flex;
      align-items: center;

      .el-select {
        margin-left: 8px;
      }
    }
  }

  .repo {
    /deep/ .el-card__header {
      padding: 14px 20px;
    }

    a {
      cursor: pointer;
    }
  }

  .card-box {
    display: flex;
    cursor: pointer;
    padding: 18px 24px;
    border-bottom: 1px solid rgba(178, 186, 194, .25);

    &:hover {
      background-color: #fbfbfb;
    }

    &:last-child {
      border-bottom: none;
    }

    img {
      display: inline-block;
      width: 48px;
      height: 48px;

      &.icon {
        width: 16px;
        height: 16px;
      }
    }

    .info-box {
      margin-left: 16px;

      h2 {
        color: #0366d6;
        font-weight: 600;
        font-size: 16px;
        margin-bottom: 8px;
        overflow: hidden;
        text-overflow: ellipsis;
        /*white-space: nowrap;*/
        &:hover {
          text-decoration: underline;
        }
      }

      & > p {
        font-size: 14px;
        color: #333;
        line-height: 20px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
      }

      & > div {
        color: #666666;
        margin-top: 16px;

        .svg-circle {
          color: #b17319;
        }

        span {
          font-size: 14px;
          color: #666;
          margin-right: 16px;
          margin-left: 4px;
          font-weight: bold;

          &.time {
            font-weight: 400;
            color: #ccc;
          }
        }
      }
    }
  }

  /* 分类 */
  .sider {
    /deep/ .el-card__header {
      padding: 12px 10px;
    }

    .type-box {
      a {
        display: inline-block;
        margin-top: 8px;
        margin-right: 24px;
        font-size: 14px;
        line-height: 1.2;
        color: #01AAED;
        cursor: pointer;

        &:hover {
          color: #1b56a5;
        }
      }
    }
  }
  .el-collapse {
    /deep/ &-item__header {
      padding: 0 16px;
    }
  }

</style>
