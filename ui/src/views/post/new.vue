<template>
    <div class="layui-container">
      <el-card shadow="never">
        <el-radio-group v-model="editType" size="small" @change="switchType">
          <el-radio-button label="0">富文本</el-radio-button>
          <el-radio-button label="1">Markdown</el-radio-button>
        </el-radio-group>
        <el-tabs>
          <el-tab-pane label="发表新帖">
            <el-row :gutter="8">
              <el-col :span="12">
                <el-input size="small" v-model="form.title" placeholder="请输入文章标题"></el-input>
              </el-col>
              <el-col :span="6">
                <el-select v-model="form.labelId" placeholder="请选择板块" size="small"></el-select>
              </el-col>
              <el-col :span="6">
                <el-select v-model="form.tagId" placeholder="请选择子版块" size="small"></el-select>
              </el-col>
            </el-row>
            <!-- 富文本 -->
            <div v-model="form.content" v-show="editType == 0" ref="editor" class="editor"></div>
            <markdown-editor v-show="editType == 1" v-model="form.content" />
            <el-tabs type="border-card">
              <el-tab-pane label="回帖奖励">
                <div class="reward-box">
                  <span>每次回帖奖励</span>
                  <el-input placeholder="" size="small"></el-input>
                  <span>钻石，奖励</span>
                  <el-input placeholder="" size="small"></el-input>
                  <span>次</span>
                  <span class="grade-tip"> 回帖奖励总额: 0 钻石,  您有 钻石 20</span>
                </div>
              </el-tab-pane>
              <el-tab-pane label="采纳奖励">
                <el-input-number v-model="form.grade" :min="1" :max="10" label="描述文字" size="small"></el-input-number>
                <span class="grade-tip">帖子采纳后将钻石发放到对方账户,当前剩余钻石总数2000</span>
              </el-tab-pane>
              <el-tab-pane label="添加附件">
                <el-upload
                  class="upload-demo"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :on-change="handleChange"
                  :file-list="fileList">
                  <el-button size="small" type="primary">点击上传</el-button>
                  <div slot="tip" class="el-upload__tip">
                    <el-checkbox v-model="replyDownload">回复可下载</el-checkbox>
                    只能上传zip文件，且不超过2GB
                  </div>
                </el-upload>
              </el-tab-pane>
            </el-tabs>
            <div class="publish-box">
              <el-button @click="getContent" size="small" type="primary">测试</el-button>
              <el-button @click="getContent" size="small" type="primary">发表帖子</el-button>
              <el-button @click="getContent" size="small">保存草稿</el-button>
            </div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
</template>

<script>
  import E from 'wangeditor'
  import MarkdownEditor from '@/components/MarkdownEditor'
  export default {
      name: "new",
      data() {
        return {
          editor: '',
          form: {
            title: '',
            labelId: '',
            tagId: '',
            content: '',
            grade: 0
          },
          editType: '0',
          fileList: [
            {
            name: 'food.jpeg',
            url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
            }
          ],
          replyDownload: false
        }
      },
      components: {
        'markdown-editor' : MarkdownEditor
      },
      created() {
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
            _this.form.content = html;
          };
          this.editor.customConfig.zIndex = 1;
          this.editor.create();
        },
        getContent() {
          console.log(this.form.content)
        },
        switchType() {
          this.$confirm('此操作将清空当前编辑器内容, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.form.content = '';
            this.$message({
              type: 'success',
              message: '已切换为' + val == '0' ? '富文本编辑器' : 'Markdown编辑器'
            });
          }).catch(() => {

          });
        },
        handleChange(file, fileList) {
          this.fileList = fileList.slice(-3);
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
    text-align:left;
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
