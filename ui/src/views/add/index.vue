<template>
  <div>
    <div class="layui-container fly-marginTop">
      <div class="fly-panel" pad20 style="padding-top: 5px;">
        <!--<div class="fly-none">没有权限</div>-->
        <div class="layui-form layui-form-pane">
          <div class="layui-tab layui-tab-brief" lay-filter="user">
            <ul class="layui-tab-title">
              <li class="layui-this">{{title}}<!-- 编辑帖子 --></li>
            </ul>
            <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
              <div class="layui-tab-item layui-show">

                <div class="layui-row layui-col-space15 layui-form-item">
                  <div class="layui-col-md3">
                    <label class="layui-form-label">所在专栏</label>
                    <div class="layui-input-block">
                      <select lay-verify="required" name="class" v-model="post.labelId"
                              @change="selectLabel">
                        <option v-for="label in labelList" :value="label.id">{{label.name}}</option>
                        <!--<option value="0">提问</option>-->
                        <!--<option value="99">分享</option>-->
                        <!--<option value="100">讨论</option>-->
                        <!--<option value="101">建议</option>-->
                        <!--<option value="168">公告</option>-->
                        <!--<option value="169">动态</option>-->
                      </select>
                    </div>
                  </div>
                  <div class="layui-col-md9">
                    <label for="L_title" class="layui-form-label">标题</label>
                    <div class="layui-input-block">
                      <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off"
                             v-model="post.title"
                             class="layui-input">
                      <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                    </div>
                  </div>
                </div>
                <div class="layui-row layui-col-space15 layui-form-item layui-hide" id="LAY_quiz">
                  <div class="layui-col-md3">
                    <label class="layui-form-label">所属产品</label>
                    <div class="layui-input-block">
                      <select name="project">
                        <option></option>
                        <option value="layui">layui</option>
                        <option value="独立版layer">独立版layer</option>
                        <option value="独立版layDate">独立版layDate</option>
                        <option value="LayIM">LayIM</option>
                        <option value="Fly社区模板">Fly社区模板</option>
                      </select>
                    </div>
                  </div>
                  <div class="layui-col-md3">
                    <label class="layui-form-label" for="L_version">版本号</label>
                    <div class="layui-input-block">
                      <input type="text" id="L_version" value="" name="version" autocomplete="off"
                             class="layui-input">
                    </div>
                  </div>
                  <div class="layui-col-md6">
                    <label class="layui-form-label" for="L_browser">浏览器</label>
                    <div class="layui-input-block">
                      <input type="text" id="L_browser" value="" name="browser" placeholder="浏览器名称及版本，如：IE 11"
                             autocomplete="off" class="layui-input">
                    </div>
                  </div>
                </div>
                <div class="layui-form-item layui-form-text">
                  <div class="layui-input-block">
                      <textarea id="L_content" name="content" required lay-verify="required" placeholder="详细描述"
                                class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                  </div>
                </div>
                <div class="layui-form-item">
                  <div class="layui-inline">
                    <label class="layui-form-label">悬赏钻石</label>
                    <div class="layui-input-inline">
                      <input type="number" name="experience" required lay-verify="required" autocomplete="off"
                             :max="post.grade" v-model="post.grade"
                             class="layui-input">
                    </div>
                    <!--<div class="layui-input-inline" style="width: 190px;">-->
                    <!--<select name="experience">-->
                    <!--<option value="20">20</option>-->
                    <!--<option value="30">30</option>-->
                    <!--<option value="50">50</option>-->
                    <!--<option value="60">60</option>-->
                    <!--<option value="80">80</option>-->
                    <!--</select>-->
                    <!--</div>-->
                    <div class="layui-form-mid layui-word-aux" v-model="currGrade">当前钻石数 {{currGrade}}</div>
                  </div>
                </div>
                <!--<div class="layui-form-item">-->
                <!--<label for="L_vercode" class="layui-form-label">人类验证</label>-->
                <!--<div class="layui-input-inline">-->
                <!--<input type="text" id="L_vercode" name="vercode" required lay-verify="required"-->
                <!--placeholder="请回答后面的问题" autocomplete="off" class="layui-input">-->
                <!--</div>-->
                <!--<div class="layui-form-mid">-->
                <!--<span style="color: #c00;">1+1=?</span>-->
                <!--</div>-->
                <!--</div>-->
                <div class="layui-form-item">
                  <button class="layui-btn" lay-filter="*" lay-submit @click="publish">立即发布</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  // import Tinymce from '@/components/Tinymce';
  import * as label from '@/api/label';
  import * as grade from '@/api/grade';
  import * as post from '@/api/post';

  export default {
    name: "index",
    // components: {
    //   'v-tinymce': Tinymce
    // },
    data() {
      return {
        editIndex: null,
        layedit: null,
        labelList: [],
        post: {
          labelId: 0,
          title: '',
          grade: 0,
          content: ''
        },
        currGrade: 0,
        layer: null,
        postId: null,
        title: '',
        showGrade: false,
        userInfo: ''
      }
    },
    created() {
      this.userInfo = this.$store.getters.user;
      this.postId = this.$route.query.postId;
      this.getCurrGrade();

      if (!this.postId) {
        this.title = '发表新帖';
      } else {
        this.title = '编辑此贴';
        this.getPostDetail();
      }
    },
    mounted() {
      this.initLayUI();
      this.getLabelList();
    },
    methods: {
      getLabelList() {
        label.getList().then(res => {
          console.log(this.label);
          this.labelList = res.data.list;
          this.$nextTick(() => {
            let _this = this;
            layui.use(['layer', 'form'], function () {
              var form = layui.form;
              form.on('select', function (data) {
                _this.selectLabel(data.value);
                if (data.elem[data.elem.selectedIndex].text == '提问') {
                  _this.showGrade = true;
                }
              });
              form.render();
            })
          })
        })
      },
      selectLabel(labelId) {
        this.post.labelId = labelId;
      },
      initLayUI() {
        let _this = this;
        layui.use(['layedit', 'layer', 'jquery'], function () {
          let $ = layui.jquery;
          _this.layer = layui.layer;
          _this.layedit = layui.layedit;
          _this.layedit.set({
            //暴露layupload参数设置接口 --详细查看layupload参数说明
            uploadImage: {
              url: window.localStorage.baseUrl + '/upload/file',
              accept: 'image',
              acceptMime: 'image/*',
              exts: 'jpg|png|gif|bmp|jpeg',
              size: 1024 * 10,
              data: {
                name: "测试参数",
                age: 99
              }
              ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                console.log(obj);
                layer.load(); //上传loading
              }
              ,choose: function (obj) {
                console.log(obj);
              }
              , done: function (data) {
                console.log(data);
              }
            },
            uploadVideo: {
              url: window.localStorage.baseUrl + '/upload/file',
              accept: 'video',
              acceptMime: 'video/*',
              exts: 'mp4|flv|avi|rm|rmvb',
              size: 1024 * 10 * 2,
              done: function (data) {
                console.log(data);
              }
            }
            , uploadFiles: {
              url: window.localStorage.baseUrl + '/upload/file',
              accept: 'file',
              acceptMime: 'file/*',
              size: '20480',
              autoInsert: true, //自动插入编辑器设置
              done: function (data) {
                console.log(data);
              }
            }
            //右键删除图片/视频时的回调参数，post到后台删除服务器文件等操作，
            //传递参数：
            //图片： imgpath --图片路径
            //视频： filepath --视频路径 imgpath --封面路径
            //附件： filepath --附件路径
            , calldel: {
              url: window.localStorage.baseUrl + '/upload/del',
              done: function (data) {
                console.log(data);
              }
            }
            , rightBtn: {
              type: "layBtn",//default|layBtn|custom  浏览器默认/layedit右键面板/自定义菜单 default和layBtn无需配置customEvent
              customEvent: function (targetName, event) {
                //根据tagName分类型设置
                switch (targetName) {
                  case "img":
                    alert("this is img");
                    break;
                  default:
                    alert("hello world");
                    break;
                }
                ;
                //或者直接统一设定
                //alert("all in one");
              }
            }
            //测试参数
            , backDelImg: true
            //开发者模式 --默认为false
            , devmode: true
            //是否自动同步到textarea
            , autoSync: true
            //内容改变监听事件
            , onchange: function (content) {
              // console.log(content);
              // _this.post.content = content;
            }
            //插入代码设置 --hide:false 等同于不配置codeConfig
            , codeConfig: {
              hide: false,  //是否隐藏编码语言选择框
              default: 'javascript', //hide为true时的默认语言格式
              encode: true //是否转义
              , class: 'layui-code' //默认样式
            }
            //新增iframe外置样式和js
            , quote: {
              style: ['Content/css.css'],
              //js: ['/Content/Layui-KnifeZ/lay/modules/jquery.js']
            }
            //自定义样式-暂只支持video添加
            //, customTheme: {
            //    video: {
            //        title: ['原版', 'custom_1', 'custom_2']
            //        , content: ['', 'theme1', 'theme2']
            //        , preview: ['', '/images/prive.jpg', '/images/prive2.jpg']
            //    }
            //}
            //插入自定义链接
            , customlink: {
              title: '插入layui官网'
              , href: 'https://www.bjjfsoft.com'
              , onmouseup: ''
            }
            , facePath: 'http://knifez.gitee.io/kz.layedit/Content/Layui-KnifeZ/'
            , devmode: true
            , videoAttr: ' preload="none" '
            //预览样式设置，等同layer的offset和area规则，暂时只支持offset ,area两个参数
            //默认为 offset:['r'],area:['50%','100%']
            //, previewAttr: {
            //    offset: 'r'
            //    ,area:['50%','100%']
            //}
            , tool: [
              'html', 'code', 'strong', 'italic', 'underline', 'del', 'addhr', '|', 'removeformat', 'fontFomatt', 'fontfamily',
              'fontSize', 'fontBackColor', 'colorpicker', 'face'
              , '|', 'left', 'center', 'right', '|', 'link', 'unlink', 'images', 'image_alt', 'video', 'attachment', 'anchors'
              , '|'
              , 'table', 'customlink'
              , 'fullScreen', 'preview'
            ]
            , height: '500px'
          });
          _this.editIndex = _this.layedit.build('L_content');
          //设置编辑器内容
          //layedit.setContent(ieditor, "<h1>hello layedit</h1><p>对layui.layedit的拓展，基于layui v2.4.3.增加了HTML源码模式、插入table、批量上传图片、图片插入、超链接插入功能优化、视频插入功能、全屏功能、段落、字体颜色、背景色设置、锚点设置等功能。</p><br><br><div>by KnifeZ </div>", false);
          // $("#openlayer").click(function () {
          //   layer.open({
          //     type: 2,
          //     area: ['700px', '500px'],
          //     fix: false, //不固定
          //     maxmin: true,
          //     shadeClose: true,
          //     shade: 0.5,
          //     title: "title",
          //     content: 'add.html'
          //   });
          // })
          // console.log(this.layedit.getContent(this.editIndex))
        })
      },
      // 查询当前分数
      getCurrGrade() {
        grade.getGrade(this.userInfo.id).then(res => {
          this.currGrade = res.data.grade;
        })
      },
      // 发布文章
      publish() {
        if (this.post.grade > this.currGrade) {
          this.layer.msg('钻石不够你咋发布？!');
        } else {
          this.post.content = this.layedit.getContent(this.editIndex);
          let bbsPosts = {
            id: this.postId,
            labelId: this.post.labelId,
            title: this.post.title,
            rewardGrade: this.post.grade,
            content: this.post.content
          };
          post.publish(bbsPosts).then(res => {
            this.layer.msg('发布成功');
            console.log(res.data);
            this.updateGrade();
          })
        }
      },
      //修改积分
      updateGrade() {
        let newGrade = this.currGrade - this.post.grade;
        console.log(newGrade);
        grade.update(newGrade).then(res => {
          console.log(res.data);
          this.$router.push('/home/index');
        });
      },
      // 查询文章详情
      getPostDetail() {
        post.getDetail(this.postId).then(res => {
          console.log(res.data)
          this.post.labelId = res.data.labelId;
          this.post.title = res.data.title;
          this.post.grade = res.data.rewardGrade;
          // this.post.content = res.data.content;
          this.layedit.setContent(this.editIndex, res.data.content);
        })
      }
    }
  }
</script>

<style scoped>
  .layui-layedit-tool .layui-colorpicker-xs {
    border: 0;
  }

  .layui-layedit-tool .layui-colorpicker-trigger-span i {
    display: none !important;
  }
</style>
