<template>
  <div id="case">
    <div class="fly-case-header">
      <p class="fly-case-year">2019</p>
      <a href="/">
        <img class="fly-case-banner" src="../../../static/images/case.png" alt="发现 金风 年度最佳案例">
      </a>
      <div class="fly-case-btn">
        <a class="layui-btn layui-btn-big fly-case-active" data-type="push" @click="openCaseBox">提交案例</a>
        <a href="" class="layui-btn layui-btn-primary layui-btn-big">我的案例</a>

        <a href="http://fly.layui.com/jie/11996/" target="_blank" style="padding: 0 15px; text-decoration: underline">案例要求</a>
      </div>
    </div>

    <div class="fly-main" style="overflow: hidden;">

      <div class="fly-tab-border fly-case-tab">
    <span>
      <a href="" class="tab-this">2019年度</a>
      <!--<a href="">2016年度</a>-->
    </span>
      </div>
      <div class="layui-tab layui-tab-brief">
        <ul class="layui-tab-title">
          <li class="layui-this"><a href="javascript:;">按提交时间</a></li>
          <li><a href="javascript:;">按点赞排行</a></li>
        </ul>
      </div>

      <ul class="fly-case-list">
        <li data-id="123">
          <a class="fly-case-img" href="http://fly.layui.com/" target="_blank">
            <img src="../../../static/images/fly.jpg" alt="Fly社区">
            <cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>
          </a>
          <h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>
          <p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>
          <div class="fly-case-info">
            <a href="../user/home.html" class="fly-case-user" target="_blank"><img
              src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>
            <p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>
            <p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"
                    style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>
            <button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>
            <!-- <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> -->
          </div>
        </li>
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn  fly-case-active" data-type="praise">已赞</button>-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
        <!--<li data-id="123">-->
        <!--<a class="fly-case-img" href="http://fly.layui.com/" target="_blank">-->
        <!--<img src="../../../static/images/fly.jpg" alt="Fly社区">-->
        <!--<cite class="layui-btn layui-btn-primary layui-btn-small">去围观</cite>-->
        <!--</a>-->
        <!--<h2><a href="http://fly.layui.com/" target="_blank">Fly社区</a></h2>-->
        <!--<p class="fly-case-desc">Fly 社区是 layui 的官方社区，全站的前端层面基于 Layui 风格编写，轻量而简洁，并且模版已经开源，可用于极简社区模板。</p>-->
        <!--<div class="fly-case-info">-->
        <!--<a href="../user/home.html" class="fly-case-user" target="_blank"><img-->
        <!--src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg"></a>-->
        <!--<p class="layui-elip" style="font-size: 12px;"><span style="color: #666;">贤心</span> 2017-11-30</p>-->
        <!--<p>获得<a class="fly-case-nums fly-case-active" href="javascript:;" data-type="showPraise"-->
        <!--style=" padding:0 5px; color: #01AAED;">666</a>个赞</p>-->
        <!--<button class="layui-btn layui-btn-primary fly-case-active" data-type="praise">点赞</button>-->
        <!--&lt;!&ndash; <button class="layui-btn  fly-case-active" data-type="praise">已赞</button> &ndash;&gt;-->
        <!--</div>-->
        <!--</li>-->
      </ul>

      <!-- <blockquote class="layui-elem-quote layui-quote-nm">暂无数据</blockquote> -->
      <div style="text-align: center;">
        <div class="laypage-main">
          <span class="laypage-curr">1</span>
          <a href="">2</a><a href="">3</a>
          <a href="">4</a>
          <a href="">5</a>
          <span>…</span>
          <a href="" class="laypage-last" title="尾页">尾页</a>
          <a href="" class="laypage-next">下一页</a>
        </div>
      </div>
    </div>
    <!-- 提交读书分享 -->
    <div id="case-box" style="display: none">
      <ul class="layui-form" style="margin: 20px;">
        <li class="layui-form-item">
          <label class="layui-form-label">分享名称</label>
          <div class="layui-input-block">
            <input required name="title" lay-verify="required" placeholder="一般为读书分享名称" v-model="caseInfo.title"
                   class="layui-input">
          </div>
        </li>
        <li class="layui-form-item">
          <label class="layui-form-label">分享附件</label>
          <div class="layui-input-inline" style="width:auto;">
            <div class="">
              <el-upload
                class="upload-demo"
                :action="uploadUrl"
                :on-success="uploadFile"
                :show-file-list="false"
                auto-upload
                multiple
              >
                <button type="button" class="layui-btn layui-btn-primary">
                  <i class="layui-icon">&#xe67c;</i>上传附件
                </button>
                <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
              </el-upload>
            </div>
          </div>
          <div class="layui-form-mid layui-word-aux">{{uploadFileTip}}</div>
        </li>
        <li class="layui-form-item">
          <label class="layui-form-label">分享封面</label>
          <div class="layui-input-inline" style="width:auto;">
            <!--<input type="hidden" name="cover" lay-verify="required" class="layui-input fly-case-image">-->
            <!--<button type="button" class="layui-btn layui-btn-primary" id="caseUpload">-->
            <!--<i class="layui-icon">&#xe67c;</i>上传图片-->
            <!--</button>-->
            <div class="">
              <el-upload
                class="upload-demo"
                :action="uploadUrl"
                :on-success="uploadImage"
                :show-file-list="false"
                multiple
              >
                <button type="button" class="layui-btn layui-btn-primary">
                  <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
                <!--<div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
              </el-upload>
            </div>
          </div>
          <div class="layui-form-mid layui-word-aux" id="preview">{{uploadImgTip}}</div>
        </li>
        <li class="layui-form-item layui-form-text">
          <label class="layui-form-label">分享描述</label>
          <div class="layui-input-block layui-form-text">
            <textarea required name="desc" lay-verify="required" autocomplete="off"
                      v-model="caseInfo.desc" class="layui-textarea"></textarea>
          </div>
        </li>
        <li class="layui-form-item">
          <label class="layui-form-label"> </label>
          <div class="layui-input-block">
            <input type="checkbox" name="agree" id="agree" title="我同意（如果你进行了刷赞行为，你的案例将被立马剔除）" lay-skin="primary">
          </div>
        </li>
        <li class="layui-form-item">
          <div class="layui-input-block">
            <button type="button" lay-submit lay-filter="pushCase" class="layui-btn" @click="submitCase">提交分享</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        layer: null,
        util: null,
        laytpl: null,
        form: null,
        laypage: null,
        upload: null,
        fly: null,
        device: null,
        $: null,
        uploadUrl: window.localStorage.baseUrl + '/upload/file',
        caseInfo: {
          title: '',
          file: '',
          img: '',
          desc: ''
        },
        uploadFileTip: '推荐尺寸：478*300，大小不能超过 30kb',
        uploadImgTip: '推荐尺寸：478*300，大小不能超过 30kb'
      }
    },
    mounted() {
      let _this = this;
      layui.use(['layedit', 'layer', 'jquery'], function () {
        _this.$ = layui.jquery;
        _this.layer = layui.layer;
        _this.util = layui.util;
        _this.laytpl = layui.laytpl;
        _this.form = layui.form;
        _this.laypage = layui.laypage;
        _this.upload = layui.upload;
        _this.fly = layui.fly;
        _this.device = layui.device();
      })
    },
    methods: {
      openCaseBox() {
        let _this = this;
        this.layer.open({
          type: 1
          , id: 'LAY_pushcase'
          , title: '提交分享'
          , area: (_this.device.ios || _this.device.android) ? ($(window).width() + 'px') : '660px'
          , content: _this.$('#case-box')
          , success: function (layero, index) {
            // var image = layero.find('.fly-case-image')
            //   , preview = _this.$('#preview');
            //
            // _this.upload.render({
            //   url: '/api/upload/case/'
            //   , elem: '#caseUpload'
            //   , size: 30
            //   , done: function (res) {
            //     if (res.status == 0) {
            //       image.val(res.url);
            //       preview.html('<a href="' + res.url + '" target="_blank" style="color: #5FB878;">封面已上传，点击可预览</a>');
            //     } else {
            //       _this.layer.msg(res.msg, {icon: 5});
            //     }
            //   }
            // });
            //
            // _this.form.render('checkbox').on('submit(pushCase)', function (data) {
            //   if (!data.field.agree) {
            //     return layer.tips('你需要同意才能提交', _this.$('#agree').next(), {tips: 1});
            //   }
            //
            //   _this.fly.json('/case/push/', data.field, function (res) {
            //     layer.close(index);
            //     layer.alert(res.msg, {
            //       icon: 1
            //     })
            //   });
            // });
          }
        });
      },
      uploadFile(res, file) {
        // console.log(res)
        this.caseInfo.file = res.data.src;
        this.uploadFileTip = '上传成功';
      },
      uploadImage(res, file) {
        this.caseInfo.img = res.data.src;
        this.uploadImgTip = '上传成功';
      },
      submitCase() {
        // console.log(this.caseInfo)
      }
    }
  }
</script>

<style scoped>

</style>
