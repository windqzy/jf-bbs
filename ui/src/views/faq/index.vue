<template>
  <div class="layui-container">
    <!--<el-row :gutter="10">-->
    <!--<el-col :lg="18" :xs="24">-->
    <!--<el-card shadow="never">-->
    <!--<div slot="header">-->
    <!--<el-input placeholder="请输入产品或问题" autofocus>-->
    <!--<el-button slot="append" type="primary" icon="el-icon-search"></el-button>-->
    <!--</el-input>-->
    <!--</div>-->
    <!--<div class="faq_cont_list">-->
    <!--<dl>-->
    <!--<dd v-for="i in 5">-->
    <!--<a href="">选中一个单元格输入，输入括号（）以后，光标会飘到前面一个单元格，怎么办？</a>-->
    <!--</dd>-->
    <!--</dl>-->
    <!--</div>-->
    <!--</el-card>-->
    <!--</el-col>-->
    <!--<el-col :lg="6" :xs="24">-->
    <!--<el-card shadow="never">-->
    <!--<div slot="header">-->
    <!--<span style="font-size: 18px">热门问题</span>-->
    <!--</div>-->
    <!--</el-card>-->
    <!--</el-col>-->
    <!--</el-row>-->
    <el-row :gutter="10">
      <el-col :lg="6" :xs="24" class="faq-tree">
        <el-button size="mini" class="extra" type="primary">新增</el-button>
        <el-tabs type="border-card" class="md8">
          <el-tab-pane label="FAQ分类">
            <el-tree
              default-expand-all
              :data="data"
              :props="defaultProps"
              @node-click="handleNodeClick">
            </el-tree>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :lg="18" :xs="24" v-show="listBox" class="faq-tabs">
        <el-input placeholder="请输入关键字" size="mini"></el-input>
        <el-tabs type="border-card">
          <el-tab-pane label="FAQ列表">

          </el-tab-pane>
          <el-tab-pane label="热门问题">

          </el-tab-pane>
          <ul>
            <li v-for="(item, index) in 15">
              <el-link type="primary" @click="faqDetail(item)">
                <span>{{index + 1}}</span>. 这是一个问题
              </el-link>
            </li>
          </ul>
        </el-tabs>
      </el-col>
      <el-col :lg="18" :xs="24" v-show="detailBox">
        <el-card shadow="never">
          <div slot="header">
            <el-page-header @back="goBack" content="详情页面"></el-page-header>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        listBox: true,
        detailBox: false,
        data: [{
          label: '一级 1',
          children: [{
            label: '二级 1-1',
            children: [{
              label: '三级 1-1-1'
            }]
          }]
        }, {
          label: '一级 2',
          children: [{
            label: '二级 2-1',
            children: [{
              label: '三级 2-1-1'
            }]
          }, {
            label: '二级 2-2',
            children: [{
              label: '三级 2-2-1'
            }]
          }]
        }, {
          label: '一级 3',
          children: [{
            label: '二级 3-1',
            children: [{
              label: '三级 3-1-1'
            }]
          }, {
            label: '二级 3-2',
            children: [{
              label: '三级 3-2-1'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        },
      }
    },
    created() {

    },
    methods: {
      handleNodeClick(data) {
        console.log(data);
      },
      faqDetail() {
        this.listBox = false;
        this.detailBox = true;
      },
      goBack() {
        this.listBox = true;
        this.detailBox = false;
      }
    }
  }
</script>

<style scoped lang="scss">
  .layui-container {

  }

  /deep/ .el-tabs--border-card {
    box-shadow: none;
  }

  .md8 {
    margin-bottom: 8px;
  }

  .faq-tree {
    position: relative;
    .extra {
      position: absolute;
      top: 6px;
      right: 12px;
      z-index: 1;
    }
  }

  .faq-tabs {
    position: relative;
    & > .el-input {
      position: absolute;
      right: 12px;
      top: 6px;
      width: 160px;
      z-index: 1;
      /deep/ input {
        border-radius: 0;
      }
    }
    .el-link {
      margin: 4px 0;
      span {
        text-align: center;
        display: inline-block;
        width: 24px;
      }
    }
  }
</style>
