<template>
  <div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <!--<li><a href="login.html">登入</a></li>-->
          <li class="layui-this">注册</li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane">
              <!--<form method="post">-->
              <div class="layui-form-item">
                <label for="L_username" class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" required lay-verify="required" autocomplete="off"
                         class="layui-input" v-model="username">
                </div>
                <div class="layui-inline">
                  <div class="layui-input-inline">
                    <input type="radio" name="sex" v-model="selectSex" value="1" checked title="男">
                    <input type="radio" name="sex" v-model="selectSex" value="0" title="女">
                  </div>
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_email" name="email" required lay-verify="email" autocomplete="off"
                         class="layui-input" v-model="email">
                </div>
                <!--<div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>-->
              </div>
              <div class="layui-form-item">
                <label for="L_tel" class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_tel" name="mobile" required lay-verify="required" autocomplete="off"
                         class="layui-input" v-model="mobile">
                </div>
                <!--<div class="layui-form-mid layui-word-aux">6到16个字符</div>-->
              </div>
              <!--<div class="layui-form-item">-->
              <!--<label for="L_repass" class="layui-form-label">确认密码</label>-->
              <!--<div class="layui-input-inline">-->
              <!--<input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off"-->
              <!--class="layui-input">-->
              <!--</div>-->
              <!--</div>-->
              <!--<div class="layui-form-item">-->
              <!--<label for="L_vercode" class="layui-form-label">人类验证</label>-->
              <!--<div class="layui-input-inline">-->
              <!--<input type="text" id="L_vercode" name="vercode" required lay-verify="required"-->
              <!--placeholder="请回答后面的问题" autocomplete="off" class="layui-input">-->
              <!--</div>-->
              <!--<div class="layui-form-mid">-->
              <!--<span style="color: #c00;"></span>-->
              <!--</div>-->
              <!--</div>-->
              <div class="layui-form-item">
                <button class="layui-btn" @click="upDateUser">立即注册</button>
              </div>
              <!--<div class="layui-form-item fly-form-app">-->
              <!--<span>或者直接使用社交账号快捷注册</span>-->
              <!--<a href="" onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-qq"-->
              <!--title="QQ登入"></a>-->
              <!--<a href="" onclick="layer.msg('正在通过微博登入', {icon:16, shade: 0.1, time:0})" class="iconfont icon-weibo"-->
              <!--title="微博登入"></a>-->
              <!--</div>-->
              <!--</form>-->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as user from "@/api/user"

  export default {
    name: "reg",
    data() {
      return {
        username: '',
        mobile: '',
        email: '',
        selectSex: 1
      }
    },
    mounted() {
      this.username = this.$store.getters.user.username;
      this.mobile = this.$store.getters.user.mobile;
      this.email = this.$store.getters.user.email;
      layui.use(['form', 'layer'], function () {
        var form = layui.form;
      })
    },
    methods: {
      upDateUser() {
        let _this = this;
        _this.$nextTick(() => {
          layui.use(['form', 'layer'], function () {
            var form = layui.form;
            var layer = layui.layer;
            form.on('radio', function (data) {
              // _this.selectSex = 0;
              console.log(data.value)
              _this.selectSex = data.value;
              // if (data.elem[data.elem.selectedIndex].text == '提问') {
              //   _this.showGrade = true;
              // }
            });
            form.render();
          });
        });
        let UserForm = {
          username: this.username,
          email: this.email,
          mobile: this.mobile,
          sex: this.selectSex
        };
        user.upDateUser(UserForm).then(res => {

          // res.data.email = this.email;
          // res.data.username = this.username;
          // res.data.mobile = this.mobile;
          //
          // window.localStorage.setItem('userInfo', JSON.stringify(res.data));
          // this.$router.push('/home/index');
          this.$store.dispatch('addUserInfo').then(() => {
            this.$router.push('/home/index');
          })
        })
      },
    }
  }
</script>

<style scoped>

</style>
