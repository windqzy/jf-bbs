<template>
  <div class="layui-container">
    <!--<div class="fly-panel fly-panel-user" pad20>-->
    <!--<div class="layui-tab layui-tab-brief" lay-filter="user">-->
    <!--&lt;!&ndash;<ul class="layui-tab-title">&ndash;&gt;-->
    <!--&lt;!&ndash;&lt;!&ndash;<li><a href="login.html">登入</a></li>&ndash;&gt;&ndash;&gt;-->
    <!--&lt;!&ndash;<li class="layui-this">注册</li>&ndash;&gt;-->
    <!--&lt;!&ndash;</ul>&ndash;&gt;-->
    <!--<div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">-->
    <!--<div class="layui-tab-item layui-show">-->
    <!--<div class="layui-form layui-form-pane">-->
    <!--&lt;!&ndash;<form method="post">&ndash;&gt;-->
    <!--<div align="center">-->
    <!--<el-upload class="avatar-uploader"-->
    <!--:action="actionUrl"-->
    <!--:show-file-list="false"-->
    <!--:on-success="handleAvatarSuccess"-->
    <!--:before-upload="beforeAvatarUpload">-->
    <!--<img v-if="imageUrl" :src="imageUrl" class="avatar">-->
    <!--<i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
    <!--</el-upload>-->
    <!--</div>-->
    <!--<div class="layui-form-item"  align="center">-->
    <!--<div class="layui-inline">-->
    <!--&lt;!&ndash;<label for="L_username" class="layui-form-label">昵称</label>&ndash;&gt;-->
    <!--<div class="layui-input-inline">-->
    <!--<input type="text" id="L_username" name="username" required lay-verify="required" autocomplete="off"-->
    <!--class="layui-input" v-model="username">-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <!--<div class="layui-form-item" align="center">-->
    <!--<div class="layui-inline">-->
    <!--<div class="layui-input-inline">-->
    <!--<input type="radio" name="sex" v-model="selectSex" value="1" checked title="男">-->
    <!--<input type="radio" name="sex" v-model="selectSex" value="0" title="女">-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <!--&lt;!&ndash;<div class="layui-form-item">&ndash;&gt;-->
    <!--&lt;!&ndash;<label for="L_tel" class="layui-form-label">手机号</label>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-input-inline">&ndash;&gt;-->
    <!--&lt;!&ndash;<input type="text" id="L_tel" name="mobile" required lay-verify="required" autocomplete="off"&ndash;&gt;-->
    <!--&lt;!&ndash;class="layui-input" v-model="mobile">&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;&lt;!&ndash;<div class="layui-form-mid layui-word-aux">6到16个字符</div>&ndash;&gt;&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-form-item">&ndash;&gt;-->
    <!--&lt;!&ndash;<label for="L_repass" class="layui-form-label">确认密码</label>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-input-inline">&ndash;&gt;-->
    <!--&lt;!&ndash;<input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off"&ndash;&gt;-->
    <!--&lt;!&ndash;class="layui-input">&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-form-item">&ndash;&gt;-->
    <!--&lt;!&ndash;<label for="L_vercode" class="layui-form-label">人类验证</label>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-input-inline">&ndash;&gt;-->
    <!--&lt;!&ndash;<input type="text" id="L_vercode" name="vercode" required lay-verify="required"&ndash;&gt;-->
    <!--&lt;!&ndash;placeholder="请回答后面的问题" autocomplete="off" class="layui-input">&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;<div class="layui-form-mid">&ndash;&gt;-->
    <!--&lt;!&ndash;<span style="color: #c00;"></span>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--<div class="layui-form-item" align="center">-->
    <!--<button class="layui-btn" lay-filter="*" lay-submit @click="upDateUser"><i class="el-icon-right"></i></button>-->
    <!--</div>-->
    <!--&lt;!&ndash;<div class="layui-form-item fly-form-app">&ndash;&gt;-->
    <!--&lt;!&ndash;<span>或者直接使用社交账号快捷注册</span>&ndash;&gt;-->
    <!--&lt;!&ndash;<a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq"&ndash;&gt;-->
    <!--&lt;!&ndash;title="QQ登入"></a>&ndash;&gt;-->
    <!--&lt;!&ndash;<a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo"&ndash;&gt;-->
    <!--&lt;!&ndash;title="微博登入"></a>&ndash;&gt;-->
    <!--&lt;!&ndash;</div>&ndash;&gt;-->
    <!--&lt;!&ndash;</form>&ndash;&gt;-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <!--</div>-->
    <el-card shadow="never">
      <div class="reg-avatar" align="center">
        <el-upload  accept="image/png,image/jpg,image/jpeg"
                    class="avatar-uploader"
                   :action="actionUrl"
                   :show-file-list="false"
                   :on-success="handleAvatarSuccess"
                   :before-upload="beforeAvatarUpload">
          <img v-if="imageUrl" :src="imageUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </div>
      <div class="reg-sex" align="center">
        <!--  <input type="radio" name="sex" v-model="selectSex" value="1" checked >
          <input type="radio" name="sex" v-model="selectSex" value="0">
          <button></button>-->
        <el-radio-group v-model="selectSex">
          <el-radio-button label="0">
            <i class="el-icon-male"></i>
          </el-radio-button>
          <el-radio-button label="1">
            <i class="el-icon-female"></i>
          </el-radio-button>
        </el-radio-group>
      </div>
      <div class="reg-username" align="center">
        <div class="layui-input-inline">
          <input type="text" id="L_username" name="username" required lay-verify="required" autocomplete="off"
                 class="layui-input" v-model="username" placeholder="请输入您的昵称">
        </div>
      </div>
      <div class="reg-btn" align="center">
        <!--<button class="btn" lay-filter="*" lay-submit @click="upDateUser"><i class="el-icon-right"></i></button>-->
        <el-button type="primary" icon="el-icon-right" @click="upDateUser" circle></el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
  import * as user from "@/api/user"

  export default {
    name: "reg",
    data() {
      return {
        username: '',
        // mobile: '',
        actionUrl: window.localStorage.baseUrl + '/upload/file',
        imageUrl: '',
        selectSex: '1',
      }
    },
    mounted() {
      this.imageUrl = this.$store.getters.user.icon;
      // this.mobile = this.$store.getters.user.mobile;
      // layui.use(['form', 'layer'], function () {
      //   var form = layui.form;
      // })
      let _this = this;
      layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        form.on('radio', function (data) {
          _this.selectSex = data.value;
        });
        form.render();
      })
    },
    methods: {
      // 判断是否为空
      isnull(val) {
      var str = val.replace(/(^\s*)|(\s*$)/g, '');//去除空格;
      if (str == '' || str == undefined || str == null) {
       return true;
      // console.log('空')
      } else {
      return false;
      // console.log('非空');
      }
    },
      //上传头像
      handleAvatarSuccess(res) {
        this.imageUrl = res.data.src;
        console.log(this.imageUrl)
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isGIF = file.type === 'image/gif';
        const isPNG = file.type === 'image/png';
        const isLt2M = file.size / 1024 / 1024 < 0.2;

        if (!isJPG && !isGIF && !isPNG) {
          this.$message.error('上传头像图片只能是 JPG、PNG、GIF 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 200KB!');
        }
        return (isJPG || isGIF || isPNG) && isLt2M;
      },
      upDateUser() {
        if (this.imageUrl == null) {
          this.$message.error('请上传您的头像！')
        } else {
          if(this.isnull(this.username) == true){
            this.$message.error('请填写您的昵称！')
          }else {
            let UserForm = {
              username: this.username,
              // mobile: this.mobile,
              sex: this.selectSex,
              icon: this.imageUrl
            };
            user.upDateUser(UserForm).then(res => {
              // res.data.email = this.email;
              res.data.username = this.username;
              res.data.icon = this.imageUrl;
              res.data.selectSex = this.selectSex;
              // res.data.mobile = this.mobile;
              // window.localStorage.setItem('userInfo', JSON.stringify(res.data));
              // this.$router.push('/home/index');
              this.$store.dispatch('addUserInfo').then(() => {
                this.$router.push('/home/index');
              })
            })
          }
        }
        // let _this = this;
        // _this.$nextTick(() => {
        // layui.use(['form', 'layer'], function () {
        //   var form = layui.form;
        //   var layer = layui.layer;
        //   form.on('radio', function (data) {
        //     // _this.selectSex = 0;
        //     console.log(data.value)
        //     _this.selectSex = data.value;
        //     // if (data.elem[data.elem.selectedIndex].text == '提问') {
        //     //   _this.showGrade = true;
        //     // }
        //   });
        //   form.render();
        // });
        // });
        // let emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        // if (!this.username) {
        //   return false;
        // }
        // if (!emailReg.test(this.email)) {
        //   return layer.msg('邮箱格式不正确', {icon: 5});
        // }

      },
    }
  }
</script>

<style type="text/css" lang="scss">
  .reg-sex {
    margin: 20px;
    width: auto;
    height: 50px;
    line-height: 50px;

    .el-radio-group {
      /deep/ .el-radio-button__inner {
        padding: 8px 34px;
      }
    }

  }

  .reg-sex input {
    border: 0px;
    width: 50px;
    height: 30px;
    background-color: #333;
    font-size: 12px;
    color: #fff;
  }

  .el-icon-male {
    /*color: #1E9FFF;*/
    font-size: 20px;
  }

  .el-icon-female {
    /* color: pink;*/
    font-size: 20px;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

  .reg-avatar {
    margin-top: 40px;
  }
  .reg-btn {
    margin: 20px;
  }
</style>
