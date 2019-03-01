<template>
  <div>
    <!-- <v-header @change="changeLabel"></v-header>-->
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
          <!-- 标签区 -->
          <div class="fly-panel" v-if="labelId != 0">
            <div class="fly-panel-title fly-filter">
              <a>标签</a>
              <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right"
                 style="color: #FF5722;">去签到</a>
            </div>
            <div class="layui-row fly-panel-main" style="padding: 15px;">
              <div class="layui-clear fly-list-quick">
                <div class="layui-col-xs2">
                  <div><a href="/extend/" target="_blank"> 组件平台 </a></div>
                </div>
                <div class="layui-col-xs2">
                  <div><a href="/case/2019/" target="_blank"> 年度案例 </a></div>
                </div>
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/30227/" target="_blank"> 精贴集锦 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/4281/" target="_blank"> Git 仓库 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="/store/" target="_blank"> layui 模板 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/13435/" target="_blank"> 关于飞吻 </a></div>-->
                <!--</div>-->
                <a name="signin"> </a></div>
            </div>
          </div>
          <div class="fly-panel layui-hide-xs" v-if="labelId == 0">
            <div class="layui-row fly-panel-main" style="padding: 15px;">
              <div class="layui-carousel fly-topline" id="FLY_topline" lay-anim="fade"
                   style="width: 100%; height: 141px;">
                <div carousel-item="">
                  <div class="layui-this">
                    <a href="https://fly.layui.com/jump/yundashi/" target="_blank">
                      <img src="http://cdn.layui.com/upload/2019_1/168_1546503131842_22821.jpg" alt="云大使" style="max-width: 100%;">
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- 置顶区 -->
          <div class="fly-panel" v-if="labelId == 0">
            <div class="fly-panel-title fly-filter">
              <a>置顶</a>
              <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                 style="color: #FF5722;">去签到</a>
            </div>
            <ul class="fly-list">
              <li v-for="top in topPostList">
                <a href="user/home.html" class="fly-avatar">
                  <img
                    :src="top.icon == null? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : top.icon"
                    :alt="top.author">
                </a>
                <h2>
                  <a class="layui-badge">{{top.labelName}}</a>
                  <router-link :to="'/post/detail?postId=' + top.id + '&labelId=' + top.labelId">{{top.title}}
                  </router-link>
                </h2>
                <div class="fly-list-info">
                  <a href="" link>
                    <cite>{{top.author}}</cite>
                    <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                    <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                  </a>
                  <span>{{top.initTime}}</span>
                  <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> {{top.rewardGrade}}</span>
                  <span v-if="top.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> {{top.replyCount}}
              </span>
                </div>
                <div class="fly-list-badge">

                  <span v-if="top.top" class="layui-badge layui-bg-black">置顶</span>
                  <span v-if="top.good" class="layui-badge layui-bg-red">精帖</span>

                </div>
              </li>
            </ul>
          </div>
          <!-- 文章区 -->
          <div class="fly-panel" style="margin-bottom: 0;">
            <!-- TAB -->
            <div class="fly-panel-title fly-filter">
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 0}" @click="setPostType(0)">综合</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 1}" @click="setPostType(1)">未结</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 2}" @click="setPostType(2)">已结</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 3}" @click="setPostType(3)">精华</a>
              <!-- sort -->
              <span class="fly-filter-right layui-hide-xs">
                <a style="cursor: pointer" :class="{'layui-this': sortTypeActive == 0}" @click="setSortType(0)">按最新</a>
                <span class="fly-mid"></span>
                <a style="cursor: pointer" :class="{'layui-this': sortTypeActive == 1}" @click="setSortType(1)">按热议</a>
              </span>
            </div>
            <!-- LIST -->
            <ul class="fly-list">
              <li v-for="post in postList">
                <a href="user/home.html" class="fly-avatar">
                  <img
                    :src="post.icon == null? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : post.icon"
                    :alt="post.author">
                </a>
                <h2>
                  <a class="layui-badge">{{post.labelName}}</a>
                  <router-link :to="'/post/detail?postId=' + post.id + '&labelId=' + post.labelId">{{post.title}}
                  </router-link>
                </h2>
                <div class="fly-list-info">
                  <a href="user/home.html" link>
                    <cite>{{post.author}}</cite>
                    <!--
                    <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                    <i class="layui-badge fly-badge-vip">VIP3</i>
                    -->
                  </a>
                  <span>{{post.initTime}}</span>
                  <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> {{post.rewardGrade}}</span>
                  <span v-if="post.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                    <i class="iconfont icon-pinglun1" title="回答"></i>{{post.replyCount}}
                  </span>
                </div>
                <div class="fly-list-badge">
                  <span v-if="post.good" class="layui-badge layui-bg-red">精帖</span>
                </div>
              </li>
            </ul>
            <div style="text-align: center">
              <div class="laypage-main">
                <a style="cursor: pointer" @click="nextPage" class="laypage-next">更多求解</a>
              </div>
            </div>
          </div>
        </div>
        <!-- 右侧卡片 -->
        <div class="layui-col-md4">
          <!-- 温馨通道 -->
          <div class="fly-panel" v-if="labelId == 0">
            <h3 class="fly-panel-title">今日热点</h3>
            <ul class="fly-panel-main fly-list-static">
              <li>
                <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
              </li>
              <li>
                <a href="http://fly.layui.com/jie/5366/" target="_blank">
                  layui 常见问题的处理和实用干货集锦
                </a>
              </li>
              <li>
                <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
              </li>
              <li>
                <a href="http://fly.layui.com/jie/5366/" target="_blank">
                  layui 常见问题的处理和实用干货集锦
                </a>
              </li>
              <li>
                <a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>
              </li>
            </ul>
          </div>
          <!-- 签到 -->
          <div class="fly-panel fly-signin" v-if="labelId == 0">
            <div class="fly-panel-title">
              签到
              <i class="fly-mid"></i>
              <a style="cursor: pointer" @click="signDoc" class="fly-link" id="LAY_signinHelp">说明</a>
              <i class="fly-mid"></i>
              <a style="cursor: pointer" class="fly-link" id="LAY_signinTop" @click="signTop">活跃榜<span
                class="layui-badge-dot"></span></a>
              <span class="fly-signin-days">已连续签到<cite><span v-text="signCount"></span></cite>天</span>
            </div>
            <div class="fly-panel-main fly-signin-main">
              <button v-if="isSign" class="layui-btn layui-btn-disabled">今日已签到</button>
              <button v-else class="layui-btn layui-btn-danger" @click="saveSign">今日签到</button>
              <span>可获得<cite v-text="currGrade"></cite>飞吻</span>

              <!-- 已签到状态 -->
              <!--
              <button class="layui-btn layui-btn-disabled">今日已签到</button>
              <span>获得了<cite>20</cite>飞吻</span>
              -->
            </div>
          </div>
          <!-- 回帖周榜 -->
          <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank" v-if="labelId == 0">
            <h3 class="fly-panel-title">回贴周榜</h3>
            <dl>
              <!--<i class="layui-icon fly-loading">&#xe63d;</i>-->
              <dd v-for="replyTop in replyTopList">
                <a href="user/home.html">
                  <img
                    :src="replyTop.icon == null? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : replyTop.icon">
                  <cite>{{replyTop.author}}</cite><i>{{replyTop.replyCount}}<span>次回答</span></i>
                </a>
              </dd>
            </dl>
          </div>
          <!-- 本周热议 -->
          <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">本周热议</dt>
            <dd v-for="hot in hotList">
              <router-link :to="'/post/detail?postId=' + hot.id + '&labelId=' + hot.labelId">{{hot.title}}</router-link>
              <span><i class="iconfont icon-pinglun1"></i>{{hot.replyCount}}</span>
            </dd>
            <!-- 无数据时 -->
            <!--
            <div class="fly-none">没有相关数据</div>
            -->
          </dl>
          <!-- 广告区域 -->
          <div class="fly-panel">
            <div class="fly-panel-title">
              这里可作为广告区域
            </div>
            <div class="fly-panel-main">
              <a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"
                 time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>
            </div>
          </div>
          <!-- 友情链接 -->
          <div class="fly-panel fly-link">
            <h3 class="fly-panel-title">友情链接</h3>
            <dl class="fly-panel-main">
              <dd><a href="http://www.layui.com/" target="_blank">layui</a></dd>
              <dd><a href="http://layim.layui.com/" target="_blank">WebIM</a></dd>
              <dd><a href="http://layer.layui.com/" target="_blank">layer</a></dd>
              <dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a></dd>
              <dd><a
                href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE"
                class="fly-link">申请友链</a></dd>
            </dl>
          </div>
        </div>
      </div>
    </div>

    <!-- 签到规则 -->
    <div class="layui-text" style="padding: 20px; display: none" id="signDoc">
      <blockquote class="layui-elem-quote">“签到”可获得社区飞吻，规则如下</blockquote>
      <table class="layui-table">
        <thead>
        <tr>
          <th>连续签到天数</th>
          <th>每天可获飞吻</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>＜5</td>
          <td>5</td>
        </tr>
        <tr>
          <td>≥5</td>
          <td>10</td>
        </tr>
        <tr>
          <td>≥15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>≥30</td>
          <td>20</td>
        </tr>
        </tbody>
      </table>
      <ul>
        <li>中间若有间隔，则连续天数重新计算</li>
        <li style="color: #FF5722;">不可利用程序自动签到，否则飞吻清零</li>
      </ul>
    </div>

    <!-- 签到榜 -->
    <div class="layui-tab layui-tab-brief" style="margin: 5px 0 0; display: none" id="signTop">
      <ul class="layui-tab-title">
        <li class="layui-this" @click="getListSign(0)">最新签到</li>
        <li @click="getListSign(1)">今日最快</li>
        <li @click="getListSign(2)">总签到榜</li>
      </ul>
      <div class="layui-tab-content fly-signin-list" id="LAY_signin_list">
        <ul class="layui-tab-item layui-show">
          <li v-for="sign in noticeList">
            <a href="" target="_blank">
              <img :src="sign.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : sign.icon">
              <cite class="fly-link">{{sign.username}}</cite>
            </a>
            <span class="fly-grey">签到于 {{sign.initTime}}</span>
          </li>
        </ul>
        <ul class="layui-tab-item">
          <li v-for="sign in noticeList">
            <a href="" target="_blank">
              <img :src="sign.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : sign.icon">
              <cite class="fly-link">{{sign.username}}</cite>
            </a>
            <span class="fly-grey">签到于 {{sign.initTime}}</span>
          </li>
        </ul>
        <ul class="layui-tab-item">
          <li v-for="sign in noticeList">
            <a href="" target="_blank">
              <img :src="sign.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : sign.icon">
              <cite class="fly-link">{{sign.username}}</cite>
            </a>
            <span class="fly-grey">已连续签到 <i>{{sign.signCount}} </i>天</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script>
  /*import Header from '@/components/Header';*/
  import * as post from '@/api/post';
  import * as reply from '@/api/reply';
  import * as time from '@/utils/time';
  import * as sign from '@/api/sign';
  import * as grade from '@/api/grade';

  export default {
    name: "index",
    /* components: {
       'v-header': Header
     },*/
    data() {
      return {
        postList: [],
        topPostList: [],
        replyTopList: [],
        hotList: [],
        noticeList: [],
        currPage: 1,
        pageSize: 10,
        sortType: 0,         // 排序类型：0 时间， 1 热度
        postType: 0,         // 文章类型：0 所有， 1 未结， 2 已结， 3 精华
        labelId: '',
        sortTypeActive: 0,
        postTypeActive: 0,
        signCount: 0, //连续签到次数
        isSign: false,
        currGrade: 0, //今天签到应得的分数
        layer: null,
        $: null
      }
    },
    created() {
      // layui.cache.user = {
      //   username: '游客'
      //   ,uid: -1
      //   ,avatar: '../../../static/images/avatar/00.jpg'
      //   ,experience: 83
      //   ,sex: '男'
      // };
      // layui.config({
      //   version: "3.0.0"
      //   ,base: '../../../static/mods/'
      // }).extend({
      //   fly: 'index'
      // }).use('fly');
      let id = this.$route.query.id ? this.$route.query.id : '';
      this.changeLabel(id);
      this.getTopPostList();
      this.getReplyTop();
      this.getWeekHot();
      // this.init_layui();
      // this.getCount();
      this.getBool();
      this.getCurrGrade();
    },
    mounted() {
      let _this = this;
      layui.use(['layer'], function () {
        _this.layer = layui.layer;
        _this.$ = layui.jquery
      });
    },
    watch: {
      '$route.query.id'(val) {
        this.changeLabel(val);
      }
    },
    methods: {
      //获取该用户今日签到与否
      getBool() {
        sign.getBool().then(res => {
          console.log(res.data);
          this.isSign = res.data.isSign;
          this.signCount = res.data.count;
        })
      },
      saveSign() {
        sign.saveSign().then(res => {
          this.getBool();
          this.layer.msg('签到成功，飞吻+' + this.grade);
        })
      },
      signDoc() {
        this.layer.open({
          type: 1
          , title: '签到说明'
          , area: '300px'
          , shade: 0.8
          , shadeClose: true
          , content: this.$('#signDoc')
        });
      },
      signTop() {
        this.layer.open({
          type: 1
          , title: '签到活跃榜 TOP-20'
          , area: '300px'
          , shade: 0.8
          , shadeClose: true
          , content: this.$('#signTop')
        });
        this.getListSign(0);
      },

      //TODO userId
      // init_layui() {
      //   layui.cache.user = {
      //     username: '游客'
      //     , uid: -1
      //     , avatar: '../../../static/images/avatar/00.jpg'
      //     , experience: 83
      //     , sex: '男'
      //   };
      //   layui.config({
      //     version: "3.0.0"
      //     , base: '../../../static/mods/'
      //   }).extend({
      //     a: 'index'
      //   }).use('a');
      // },
      changeLabel(e) {
        console.log(e);
        this.labelId = e;
        this.getPostList();
      },
      // 查询文章列表
      getPostList() {
        let obj = {
          currPage: this.currPage,
          pageSize: this.pageSize,
          sortType: this.sortType,
          postType: this.postType,
          labelId: this.labelId,
          beginTime: '',
          endTime: ''
        };
        post.getList(obj).then(res => {
          console.log(res.data)
          this.postList = res.data;
        })
      },
      // 查询置顶文章
      getTopPostList() {
        post.getTop().then(res => {
          console.log(res.data)
          this.topPostList = res.data;
        })
      },
      // 设置排序类型
      setSortType(type) {
        this.sortTypeActive = type;
        this.sortType = type;
        this.getPostList();
      },
      setPostType(type) {
        this.postTypeActive = type;
        this.postType = type;
        this.getPostList();
      },
      // 查询回复周榜
      getReplyTop() {
        let obj = {
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate()
        }
        reply.getTop(obj).then(res => {
          this.replyTopList = res.data;
        })
      },
      // 获取本周热议
      getWeekHot() {
        console.log(this.labelId)
        let obj = {
          currPage: 1,
          pageSize: 10,
          sortType: 0,
          postType: 0,
          labelId: this.labelId,
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate(),
        };
        // console.log(obj)
        post.getList(obj).then(res => {
          this.hotList = res.data;
        })
      },
      nextPage() {
        this.currPage += 1;
        console.log(this.currPage)
        let obj = {
          currPage: this.currPage,
          pageSize: this.pageSize,
          sortType: this.sortType,
          postType: this.postType,
          labelId: this.labelId,
          beginTime: '',
          endTime: ''
        };
        post.getList(obj).then(res => {
          res.data.map(item => {
            this.postList.push(item);
          })
        })
      },
      getCurrGrade() {
        grade.getCurr().then(res => {
          this.currGrade = res.data;
        })
      },
      getListSign(listType) {
        sign.listSign(listType).then(res => {
          console.log(res.data)
          this.noticeList = res.data;
        })
      }
    }
  }
</script>
<style scoped>

</style>
