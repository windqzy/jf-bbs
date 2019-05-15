<template>
    <div class="layui-container">
      <el-card shadow="never">
        <el-radio-group v-model="editType" size="small">
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
            <div v-show="editType == 0" ref="editor" class="editor"></div>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </div>
</template>

<script>
  import E from 'wangeditor'
  export default {
      name: "new",
      data() {
        return {
          form: {
            title: '',
            labelId: '',
            tagId: '',
            content: ''
          },
          editType: '0'
        }
      },
      components: {
      },
      created() {
      },
      mounted() {
        let editor = new E(this.$refs.editor)
        editor.customConfig.onchange = (html) => {
          this.editorContent = html;
        };
        editor.customConfig.zIndex = 1;
        editor.create();
      },
      methods: {
        getContent() {
          alert(this.form.content)
        }
      }
    }
</script>

<style scoped lang="scss">
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
      height: 490px !important;
    }
  }
</style>
