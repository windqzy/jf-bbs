<template>
  <div class="layui-container">
    <el-card shadow="never">
      <el-radio-group v-model="editType" size="small" @change="switchType">
        <el-radio-button label="0">富文本</el-radio-button>
        <!--<el-radio-button label="1">Markdown</el-radio-button>-->
      </el-radio-group>
      <el-tabs>
        <el-tab-pane label="发表新帖">
          <el-row :gutter="8">
            <el-col :span="12">
              <el-input size="small" v-model="post.title" placeholder="请输入文章标题"></el-input>
            </el-col>
            <el-col :span="6">
              <el-select v-model="post.labelId" placeholder="请选择板块" size="small" @change="getTagByLabelId">
                <el-option v-for="item in labelList" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
            <el-col :span="6">
              <el-select v-model="post.tagId" placeholder="请选择子版块" size="small">
                <el-option v-for="item in tagList" :key="item.id" :label="item.name" :value="item.id"></el-option>
              </el-select>
            </el-col>
          </el-row>
          <!-- 富文本 -->
          <div v-model="post.content" v-show="editType == 0" ref="editor" class="editor"></div>
          <markdown-editor v-show="editType == 1" v-model="post.content"/>
          <el-tabs type="border-card">
            <!--<el-tab-pane label="回帖奖励">-->
            <!--<div class="reward-box">-->
            <!--<span>每次回帖奖励</span>-->
            <!--<el-input placeholder="" size="small"></el-input>-->
            <!--<span>钻石，奖励</span>-->
            <!--<el-input placeholder="" size="small"></el-input>-->
            <!--<span>次</span>-->
            <!--<span class="grade-tip"> 回帖奖励总额: 0 钻石,  您有 钻石 20</span>-->
            <!--</div>-->
            <!--</el-tab-pane>-->
            <el-tab-pane label="添加附件">
              <el-upload
                class="upload-demo"
                :action="actionUrl"
                :on-change="handleChange"
                :on-success="handleSuccess"
                :file-list="fileList">
                <el-button size="small" type="primary">点击上传</el-button>
                <div slot="tip" class="el-upload__tip">
                  <el-checkbox v-model="post.replyDownload">回复可下载</el-checkbox>
                  只能上传zip文件，且不超过2GB
                </div>
              </el-upload>
            </el-tab-pane>
            <!--<el-tab-pane label="采纳奖励">-->
            <!--<el-input-number v-model="post.grade" :min="1" :max="10" label="描述文字" size="small"></el-input-number>-->
            <!--<span class="grade-tip">帖子采纳后将钻石发放到对方账户,当前剩余钻石总数2000</span>-->
            <!--</el-tab-pane>-->

          </el-tabs>
          <div class="publish-box">
            <el-button @click="publish" size="small" type="primary">发表帖子</el-button>
            <el-button @click="save" size="small">保存草稿</el-button>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script>
  import E from 'wangeditor'
  // https://hacpai.com/article/1549638745630?r=Vanessa
  import MarkdownEditor from '@/components/MarkdownEditor'
  import * as label from '@/api/label';
  import * as tag from '@/api/tag';
  import * as posts from '@/api/post';

  export default {
    name: "new",
    data() {
      return {
        editor: '',
        actionUrl: window.localStorage.baseUrl + '/upload/file',
        post: {
          id: '',
          title: '',
          labelId: '',
          tagId: '',
          content: '',
          grade: 0,
          isTemp: false,
          replyDownload: false
        },
        editType: '0',
        fileList: [],

        userInfo: '',
        labelList: [],
        tagList: [],
      }
    },
    components: {
      'markdown-editor': MarkdownEditor
    },
    created() {
      this.userInfo = this.$store.getters.user;
      this.getAllLabel();
    },
    mounted() {
      this.initEditor();
    },
    methods: {
      initEditor() {
        let _this = this;
        this.editor = new E(this.$refs.editor)
        this.editor.customConfig.onchange = (html) => {
          this.editorContent = html;
          _this.post.content = html;
        };
        this.editor.customConfig.zIndex = 1;
        this.editor.create();
      },
      getContent() {
        console.log(this.post.content)
      },
      switchType() {

      },
      getAllLabel() {
        label.getList().then(res => {
          let arr = [];
          res.data.forEach(e => arr = arr.concat(e.children));
          this.labelList = arr;
        });
      },
      getTagByLabelId() {
        tag.getList(this.post.labelId).then(res => {
          this.tagList = res.data;
        })
      },
      handleChange(file, fileList) {
        this.fileList = fileList.slice(-3);
      },
      handleSuccess(res, file, fileList) {
        let obj = {
          name: file.name,
          res: res.data.src
        };
        this.fileList.push(obj);
      },
      save() {
        this.post.isTemp = true;
        this.publish();
      },
      publish() {
        if (!this.post.title) {
          this.$message.warning('请填写文章标题');
          return;
        }
        if (!this.post.labelId || !this.post.tagId) {
          this.$message.warning('请选择模块');
          return;
        }
        if (!this.post.content) {
          this.$message.warning('请填写文章内容');
          return;
        }
        let bbsPosts = {
          id: this.post.id,
          labelId: this.post.labelId,
          title: this.post.title,
          rewardGrade: this.post.grade,
          content: this.post.content,
          // vote: this.isVote,
          tagId: this.post.tagId,
          isTemp: this.post.isTemp
        };
        posts.publish2(bbsPosts).then(res => {
          if (this.fileList.length > 0) {
            let bbsPostsFiles = [];
            this.fileList.forEach(e => {
              let suffix = e.name.split('.').pop();
              let item = {
                url: e.response.data.src,
                type: suffix,
                size: e.size,
                postsId: res.data.id,
                isReply: this.post.replyDownload
              };
              bbsPostsFiles.push(item);
            });
            // 更新文件路径
            posts.updateFile(bbsPostsFiles).then(res2 => {
              this.$message.success('发布成功');
              this.$router.push('/home/index')
            })
          };
        });
      }
    }
  }
</script>

<style scoped lang="scss">
  /deep/ .el-tabs {
    margin-top: 8px;
    box-shadow: none;
  }

  /deep/ .el-select {
    width: 100%;
  }

  /deep/ .el-input__inner {
    border-radius: 0px !important;
  }

  /deep/ .el-radio-group {
    position: absolute;
    right: 20px;
    z-index: 1;

    .el-radio-button__inner {
      border-radius: 0;
    }
  }

  .editor {
    text-align: left;
    margin-top: 10px;

    /deep/ .w-e-toolbar {
      background-color: #fff !important;
      border: 1px solid #DCDFE6 !important;
    }

    /deep/ .w-e-text {
      overflow-y: auto;

      p {
        margin: 10px 0;
      }
    }

    /deep/ .w-e-text-container {
      height: 500px !important;
      border: 1px solid #DCDFE6 !important;
    }

    /deep/ .w-e-toolbar .w-e-menu {
      padding: 8px 10px;
    }
  }

  .publish-box {
    margin-top: 10px;
  }

  .grade-tip {
    margin-left: 10px;
    color: #E6A23C;
  }

  .reward-box {
    /deep/ .el-input {
      width: 80px;
    }
  }

  .el-upload__tip {
    display: inline-block;
    margin-left: 10px;
    color: #E6A23C;

    /deep/ .el-checkbox {
      margin-right: 10px;
    }
  }
</style>
