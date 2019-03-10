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
                  <!--<div class="layui-col-md3">-->
                  <!--<label class="layui-form-label">所在专栏</label>-->
                  <!--<div class="layui-input-block">-->
                  <!--<select lay-verify="required" name="class" v-model="post.labelId"-->
                  <!--@change="selectLabel">-->
                  <!--<option v-for="label in labelList" :value="label.id">{{label.name}}</option>-->
                  <!--&lt;!&ndash;<option value="0">提问</option>&ndash;&gt;-->
                  <!--&lt;!&ndash;<option value="99">分享</option>&ndash;&gt;-->
                  <!--&lt;!&ndash;<option value="100">讨论</option>&ndash;&gt;-->
                  <!--&lt;!&ndash;<option value="101">建议</option>&ndash;&gt;-->
                  <!--&lt;!&ndash;<option value="168">公告</option>&ndash;&gt;-->
                  <!--&lt;!&ndash;<option value="169">动态</option>&ndash;&gt;-->
                  <!--</select>-->
                  <!--</div>-->
                  <!--</div>-->
                  <div class="layui-col-md6">
                    <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off"
                           v-model="post.title"
                           placeholder="标题"
                           class="layui-input">
                  </div>
                  <div class="layui-col-md3">
                    <!--<select lay-verify="required" name="label" lay-filter="label" v-model="post.labelId"-->
                    <!--@change="selectLabel">-->
                    <!--<option value="">选择板块</option>-->
                    <!--<option v-for="label in labelList" :value="label.id">{{label.name}}</option>-->
                    <!--</select>-->
                    <el-select v-model="post.labelId" placeholder="选择板块" @change="selectLabel">
                      <el-option
                        v-for="item in labelList"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                      </el-option>
                    </el-select>
                  </div>
                  <div class="layui-col-md3">
                    <!--<select lay-verify="required" name="tag" lay-filter="tag" v-model="post.tagId"-->
                    <!--@change="selectTag">-->
                    <!--<option value="">选择模块</option>-->
                    <!--<option v-for="tag in tagList" :value="tag.id">{{tag.name}}</option>-->
                    <!--</select>-->
                    <el-select v-model="post.tagId" placeholder="选择模块" @change="selectTag">
                      <el-option
                        v-for="item in tagList"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id">
                      </el-option>
                    </el-select>
                  </div>
                </div>
                <div class="layui-row layui-col-space15 layui-form-item layui-hide" id="LAY_quiz">
                  <!--<div class="layui-col-md3">-->
                  <!--<label class="layui-form-label">所属产品</label>-->
                  <!--<div class="layui-input-block">-->
                  <!--<select name="project">-->
                  <!--<option></option>-->
                  <!--<option value="layui">layui</option>-->
                  <!--<option value="独立版layer">独立版layer</option>-->
                  <!--<option value="独立版layDate">独立版layDate</option>-->
                  <!--<option value="LayIM">LayIM</option>-->
                  <!--<option value="Fly社区模板">Fly社区模板</option>-->
                  <!--</select>-->
                  <!--</div>-->
                  <!--</div>-->
                  <!--<div class="layui-col-md3">-->
                  <!--<label class="layui-form-label" for="L_version">版本号</label>-->
                  <!--<div class="layui-input-block">-->
                  <!--<input type="text" id="L_version" value="" name="version" autocomplete="off"-->
                  <!--class="layui-input">-->
                  <!--</div>-->
                  <!--</div>-->
                  <!--<div class="layui-col-md6">-->
                  <!--<label class="layui-form-label" for="L_browser">浏览器</label>-->
                  <!--<div class="layui-input-block">-->
                  <!--<input type="text" id="L_browser" value="" name="browser" placeholder="浏览器名称及版本，如：IE 11"-->
                  <!--autocomplete="off" class="layui-input">-->
                  <!--</div>-->
                  <!--</div>-->
                </div>
                <div class="layui-form-item layui-form-text">
                  <div class="layui-input-block">
                      <textarea id="L_content" name="content" required placeholder="详细描述"
                                class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                  </div>
                </div>
                <!-- 分类 -->
                <!--<div class="layui-row layui-form-item">-->
                <!--<div class="layui-col-md12 label-width">-->
                <!--<label class="layui-form-label" style="border: none; background-color: white">分类</label>-->
                <!--<div class="layui-input-block">-->
                <!--<button class="layui-btn layui-btn-sm" :class="{'layui-btn-primary': activeBtn !== index}"-->
                <!--v-for="(label,index) in labelList"-->
                <!--style="top: 5px;position: relative;" @click="changeBtn(index, label)">{{label.name}}-->
                <!--</button>-->
                <!--</div>-->
                <!--</div>-->
                <!--</div>-->
                <!-- 标签 -->
                <!--<div class="layui-row layui-form-item">-->
                <!--<div class="layui-col-md12">-->
                <!--<label class="layui-form-label" style="border: none; background-color: white">标签</label>-->
                <!--<div class="layui-input-block">-->
                <!--<input type="text" id="L_tag" name="title" required lay-verify="required" autocomplete="off"-->
                <!--class="layui-input">-->
                <!--</div>-->
                <!--</div>-->
                <!--</div>-->
                <div class="layui-form-item" v-if="isGrade">
                  <div class="layui-inline label-width">
                    <label class="layui-form-label" style="border: none; background-color: white">悬赏</label>
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
                <!-- 投票 -->
                <div class="layui-row" v-show="isVote">
                  <div class="layui-col-md12 label-width layui-form-item">
                    <label class="layui-form-label" style="border: none; background-color: white">选项</label>
                    <div class="layui-input-block">
                      <div v-for="(item,index) in inputCount" class="vote">
                        <input type="text" class="layui-input" v-model="inputCount[index]">
                        <i class="layui-icon layui-icon-close" @click="inputCount.splice(index,1)"></i>
                      </div>
                    </div>
                  </div>
                  <div class="layui-col-md11 label-width layui-form-item">
                    <label class="layui-form-label" style="border: none; background-color: white">结束时间</label>
                    <div class="layui-input-inline">
                      <input type="text" class="layui-input" id="endTime" v-model="endTime">
                    </div>
                    <input type="number" name="" placeholder="至少选择几项" lay-skin="primary"
                           class="layui-input layui-input-inline" v-model="optionMin">
                    <input type="number" name="" placeholder="至多选择几项" lay-skin="primary"
                           class="layui-input layui-input-inline" v-model="optionMax">
                    <input type="checkbox" name="" title="投票前可见" class="layui-input layui-input-inline"
                           lay-skin="primary" v-model="isVisible">
                  </div>
                  <div class="layui-col-md1" style="padding-left: 0px;">
                    <button class="layui-btn" @click="inputCount.push('')">新增选项</button>
                  </div>
                </div>
              </div>

              <div class="layui-form-item">
                <button class="layui-btn" lay-filter="addForm" lay-submit @click="publish">立即发布</button>
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
  import * as vote from '@/api/vote';
  import * as tag from '@/api/tag';

  export default {
    name: "index",
    // components: {
    //   'v-tinymce': Tinymce
    // },
    data() {
      return {
        editIndex: null,
        layedit: null,
        laydate: null,
        layform: null,
        $: null,
        labelList: [],
        post: {
          labelId: '',
          title: '',
          grade: 0,
          content: '',
          tagId: ''
        },
        currGrade: 0,
        layer: null,
        postId: null,
        title: '',
        showGrade: false,
        userInfo: '',
        required: false,
        activeBtn: 0,
        isGrade: false,
        isVote: false, // 投票
        inputCount: [''],
        optionMin: '',
        optionMax: '',
        endTime: '',
        isVisible: true,
        tagList: [],
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
          this.labelList = res.data.list;
        })
      },
      selectLabel(labelId) {
        this.getTagListByLabelId(labelId);
        this.labelList.forEach(e => {
          if (labelId == e.id) {
            this.isVote = e.name === '投票' ? true : false;
          }
        })
      },
      selectTag(tag) {
        // console.log(tag)
        // this.post.tagId = tagId;
      },
      initLayUI() {
        let _this = this;
        this.layui = layui.use(['layedit', 'layer', 'jquery', 'laydate'], function () {
          _this.$ = layui.jquery;
          _this.layer = layui.layer;
          _this.layedit = layui.layedit;
          _this.laydate = layui.laydate;
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
              , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                // console.log(obj);
                layer.load(); //上传loading
              }
              , choose: function (obj) {
                // console.log(obj);
              }
              , done: function (data) {
                // console.log(data);
              }
            },
            uploadVideo: {
              url: window.localStorage.baseUrl + '/upload/file',
              accept: 'video',
              acceptMime: 'video/*',
              exts: 'mp4|flv|avi|rm|rmvb',
              size: 1024 * 10 * 200,
              done: function (data) {
                // console.log(data);
              }
            }
            , uploadFiles: {
              url: window.localStorage.baseUrl + '/upload/file',
              accept: 'file',
              acceptMime: 'file/*',
              size: 1024 * 10 * 200,
              autoInsert: true, //自动插入编辑器设置
              done: function (data) {
                // console.log(data);
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
                // console.log(data);
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
            , height: '400px'
          });
          _this.editIndex = _this.layedit.build('L_content');
          _this.laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime',
            done: function (value, date) { //监听日期被切换
              _this.endTime = value;
            }
          });
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
        } else if (this.post.labelId == '') {
          this.layer.msg('请选择所属专栏');
        } else if (this.post.tagId == '') {
          this.layer.msg('请选择所属模块');
        } else {
          if (!this.post.title || !this.post.labelId) {
            return false;
          }
          this.post.content = this.layedit.getContent(this.editIndex);

          let bbsPosts = {
            id: this.postId,
            labelId: this.post.labelId,
            title: this.post.title,
            rewardGrade: this.post.grade,
            content: this.post.content,
            vote: this.isVote,
            tagId: this.post.tagId
          };
          post.publish(bbsPosts).then(res => {
            // console.log(res.data);
            this.postId = res.data.id;
            this.updateGrade();

            if (this.isVote) {
              let bbsVote = {
                postId: this.postId,
                voteVisible: this.isVisible,
                voteStatus: true,
                endTime: this.endTime,
                maxSel: this.optionMax,
                minSel: this.optionMin,
                optionList: []
              };
              this.inputCount.forEach(item => {
                let voteOption = {
                  content: item
                }
                bbsVote.optionList.push(voteOption);
              });
              // console.log("bbsVoteopl1" + bbsVote.optionList)
              vote.addVote(bbsVote).then(res => {
                // console.log("bbsVote2" + res.data)
              })
            }
            this.layer.msg('发布成功');
          })

        }
      },
      //修改积分
      updateGrade() {
        let newGrade = this.currGrade - this.post.grade;
        // console.log(newGrade);
        grade.update(newGrade).then(res => {
          // console.log(res.data);
          this.$router.push('/home/index');
        });
      },
      getTagListByLabelId(labelId) {
        tag.getList(labelId).then(res => {
          this.post.tagId = '';
          this.tagList = res.data;
          if (this.tagList.length == 1) {
            this.post.tagId = res.data[0].id;
          }
        })

      },
      // 查询文章详情
      getPostDetail() {
        post.getDetail(this.postId).then(res => {
          // console.log(res.data)
          this.post.tagId = res.data.tagId;
          this.post.labelId = res.data.labelId;
          this.post.title = res.data.title;
          this.post.grade = res.data.rewardGrade;
          // this.post.content = res.data.content;
          this.layedit.setContent(this.editIndex, res.data.content);
          // 假增加当前积分
          this.currGrade = this.currGrade + this.post.grade;
          this.getTagListByLabelId(this.post.labelId);

          // let _this = this;
          // layui.use('form', function () {
          //   _this.layform.on('select(tag)', function (data) {
          //     tag.getList(this.post.labelId).then(res => {
          //       _this.tagList = res.data;
          //       _this.$("select[name=tag]").html('<option value="">选择模块</option>');
          //       _this.$.each(_this.tagList, function (key, val) {
          //         let option = _this.$("<option>").val(val.id).text(val.name);
          //         _this.$("select[name=tag]").append(option);
          //         _this.layform.render('select');
          //       });
          //       // let option = _this.$("<option>").val(res.data[0].id).text((res.data[0].name));
          //       // _this.$("select[name=tag]").html(option);
          //       // _this.layform.render('select');
          //     })
          //   })
          // })


        })
      },
      changeBtn(index, label) {
        this.activeBtn = index;
        this.post.labelId = label.id;
        this.isGrade = label.name === '提问' ? true : false;
        this.isVote = label.name === '投票' ? true : false;
      },
      // test() {
      //   console.log(this.inputCount, this.optionMin, this.optionMax, this.isVisible, this.endTime);
      // }
    }
  }
</script>

<style scoped lang="scss">
  .layui-layedit-tool .layui-colorpicker-xs {
    border: 0;
  }

  .layui-layedit-tool .layui-colorpicker-trigger-span i {
    display: none !important;
  }

  .vote {
    display: flex;
    align-items: center;
    margin-bottom: 8px;

    .layui-icon {
      margin-left: 10px;
      cursor: pointer;
    }
  }

  .label-width {
    .layui-form-label {
      width: 80px;
      padding: 8px 0;
      text-align: left;
    }

    .layui-input-block {
      margin-left: 80px;
    }
  }

  /deep/ .el-input__inner {
    border-radius: 0px !important;
    height: 38px !important;
  }

  .el-select {
    width: 100%;
  }
</style>
