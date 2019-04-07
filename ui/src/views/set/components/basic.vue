<!--基本设置-->
<template>
  <div class="layui-tab layui-tab-brief" lay-filter="user">
    <ul class="layui-tab-title" id="LAY_mine">
      <li class="layui-this" lay-id="info">我的资料</li>
      <li lay-id="avatar">头像</li>
      <!--<li lay-id="pass">密码</li>-->
      <!--<li lay-id="bind">帐号绑定</li>-->
    </ul>
    <div class="layui-tab-content" style="padding: 20px 0;">
      <div class="layui-form layui-form-pane layui-tab-item layui-show">
        <!--<form method="post" action="basic.vue">-->
        <!--<div class="layui-form-item">-->
        <!--<label for="L_email" class="layui-form-label">邮箱</label>-->
        <!--<div class="layui-input-inline">-->
        <!--<input type="text" id="L_email" name="email" required lay-verify="email" autocomplete="off" v-model="email"-->
        <!--class="layui-input">-->
        <!--</div>-->
        <!--<div class="layui-form-mid layui-word-aux">如果您在邮箱已激活的情况下，变更了邮箱，需<a href="javascript:;"-->
        <!--style="font-size: 12px; color: #4f99cf;">重新验证邮箱</a>。-->
        <!--</div>-->
        <!--</div>-->
        <div class="layui-form-item">
          <label for="L_username" class="layui-form-label">昵称</label>
          <div class="layui-input-inline">
            <input type="text" id="L_username" required lay-verify="required" autocomplete="off" name="username"
                   class="layui-input" maxLength="20"
                   v-model="username">
            <!--<el-select v-model="username" placeholder="请选择">-->
            <!--<el-option-->
            <!--v-for="item in vestList"-->
            <!--:key="item.id"-->
            <!--:label="item.vest"-->
            <!--:value="item.vest">-->
            <!--</el-option>-->
            <!--</el-select>-->
          </div>
          <div class="layui-inline">
            <div class="layui-input-inline">
              <el-button type="primary" icon="el-icon-plus" @click="addAccountBox = true"></el-button>
              <el-button type="primary" @click="getAccount">切换账号</el-button>
            </div>
          </div>
        </div>
        <div class="layui-form-item">
          <label for="L_city" class="layui-form-label">城市</label>
          <div class="layui-input-inline">
            <input type="text" id="L_city" name="city" required lay-verify="required" autocomplete="off" v-model="city"
                   class="layui-input">
          </div>
          <div class="layui-inline">
            <div class="layui-input-inline">
              <input type="radio" name="sex" v-model="selectSex" value="1" checked title="男">
              <input type="radio" name="sex" v-model="selectSex" value="0" title="女">
            </div>
          </div>
        </div>
        <!--<div class="layui-form-item">-->
        <!--<label for="L_mobile" class="layui-form-label">电话号码</label>-->
        <!--<div class="layui-input-inline">-->
        <!--<input type="text" id="L_mobile" name="mobile" required autocomplete="off"-->
        <!--v-model="mobile" class="layui-input">-->
        <!--</div>-->
        <!--</div>-->
        <div class="layui-form-item layui-form-text">
          <label for="L_sign" class="layui-form-label">签名</label>
          <div class="layui-input-block">
              <textarea placeholder="随便写些什么刷下存在感" id="L_sign" name="sign" autocomplete="off" class="layui-textarea"
                        style="height: 80px;" v-model="signature"></textarea>
          </div>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn" lay-filter="*" lay-submit @click="upDateUser">确认修改</button>
        </div>
        <!--</form>-->
      </div>

      <div class="layui-form layui-form-pane layui-tab-item">
        <div class="layui-form-item">
          <div class="avatar-add">
            <p>建议尺寸168*168，支持jpg、png、gif</p>
            <input type="file" id="file" name="myfile" style="display: none" ref="file" @change="finishFile"/>
            <button type="button" class="layui-btn upload-img" @click="UpladFile()">
              <i class="layui-icon">&#xe67c;</i>上传头像
            </button>

            <img :src='imgUrl'/>
            <!--<img-->
            <!--:src="userInfo.icon == null ? 'https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg' : userInfo.icon">-->
            <span class="loading"></span>
          </div>
        </div>
      </div>


      <!--<div class="layui-form layui-form-pane layui-tab-item">-->
      <!--<form action="/user/repass" method="post">-->
      <!--<div class="layui-form-item">-->
      <!--<label for="L_nowpass" class="layui-form-label">当前密码</label>-->
      <!--<div class="layui-input-inline">-->
      <!--<input type="password" id="L_nowpass" name="nowpass" required lay-verify="required" autocomplete="off"-->
      <!--class="layui-input">-->
      <!--</div>-->
      <!--</div>-->
      <!--<div class="layui-form-item">-->
      <!--<label for="L_pass" class="layui-form-label">新密码</label>-->
      <!--<div class="layui-input-inline">-->
      <!--<input type="password" id="L_pass" name="pass" required lay-verify="required" autocomplete="off"-->
      <!--class="layui-input">-->
      <!--</div>-->
      <!--<div class="layui-form-mid layui-word-aux">6到16个字符</div>-->
      <!--</div>-->
      <!--<div class="layui-form-item">-->
      <!--<label for="L_repass" class="layui-form-label">确认密码</label>-->
      <!--<div class="layui-input-inline">-->
      <!--<input type="password" id="L_repass" name="repass" required lay-verify="required" autocomplete="off"-->
      <!--class="layui-input">-->
      <!--</div>-->
      <!--</div>-->
      <!--<div class="layui-form-item">-->
      <!--<button class="layui-btn" key="set-mine" lay-filter="*" lay-submit>确认修改</button>-->
      <!--</div>-->
      <!--</form>-->
      <!--</div>-->

      <!--<div class="layui-form layui-form-pane layui-tab-item">-->
      <!--<ul class="app-bind">-->
      <!--<li class="fly-msg app-havebind">-->
      <!--<i class="iconfont icon-qq"></i>-->
      <!--<span>已成功绑定，您可以使用QQ帐号直接登录Fly社区，当然，您也可以</span>-->
      <!--<a href="javascript:;" class="acc-unbind" type="qq_id">解除绑定</a>-->

      <!--&lt;!&ndash; <a href="" onclick="layer.msg('正在绑定微博QQ', {icon:16, shade: 0.1, time:0})" class="acc-bind" type="qq_id">立即绑定</a>-->
      <!--<span>，即可使用QQ帐号登录Fly社区</span> &ndash;&gt;-->
      <!--</li>-->
      <!--<li class="fly-msg">-->
      <!--<i class="iconfont icon-weibo"></i>-->
      <!--&lt;!&ndash; <span>已成功绑定，您可以使用微博直接登录Fly社区，当然，您也可以</span>-->
      <!--<a href="javascript:;" class="acc-unbind" type="weibo_id">解除绑定</a> &ndash;&gt;-->

      <!--<a href="" class="acc-weibo" type="weibo_id" onclick="layer.msg('正在绑定微博', {icon:16, shade: 0.1, time:0})">立即绑定</a>-->
      <!--<span>，即可使用微博帐号登录Fly社区</span>-->
      <!--</li>-->
      <!--</ul>-->
      <!--</div>-->
    </div>

    <!-- 购买马甲 -->
    <el-dialog title="购买马甲" :visible.sync="vestBox" width="320px">
      <el-form ref="form" :model="vest" label-width="80px" label-position="top">
        <el-form-item label="">
          <span style="color: red">说明：每个人最多可以拥有五个马甲，不可修改，不可删除，第二个起，每个马甲200钻石</span>
        </el-form-item>
        <el-form-item label="">
          <el-input v-model="vest.name" placeholder="请输入马甲名称"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="vestBox = false">取 消</el-button>
        <el-button type="primary" @click="addVest">确 定</el-button>
      </span>
    </el-dialog>

    <!-- account -->
    <el-dialog title="账号列表" :visible.sync="accountBox" width="320px">
      <div class="fly-panel fly-rank fly-rank-reply">
        <dl>
          <dd v-for="account in accountList">
            <a @click="turnAccount(account.id)">
              <img :src="account.icon == null? defaultAvatar : account.icon">
              <cite>{{account.username}}</cite>
            </a>
          </dd>
        </dl>
      </div>
    </el-dialog>

    <!-- add account -->
    <el-dialog title="添加账号" :visible.sync="addAccountBox" width="320px">
      <el-form :model="accountForm" label-width="40px">
        <el-form-item label="说明">
          <span style="color: red">说明：每个人最多可以拥有账号，第二个起，每个马甲200钻石</span>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="accountForm.username" placeholder="昵称"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="accountForm.sex" label="1">男</el-radio>
          <el-radio v-model="accountForm.sex" label="2">女</el-radio>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addAccountBox = false">取 消</el-button>
        <el-button type="primary" @click="addAccount">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import * as user from '@/api/user'
  import * as vest from '@/api/vest'
  import axios from 'axios'

  export default {
    name: "base",
    data() {
      return {
        icon: '',
        email: '',
        username: '',
        city: '',
        signature: '',
        mobile: '',
        userInfo: '',
        selectSex: '',
        vestBox: false,
        vestList: [],
        vest: {
          id: '',
          name: ''
        },
        accountBox: false,
        accountList: [],
        addAccountBox: false,
        accountForm: {
          username: '',
          sex: '',
          unionId: ''
        },

      }
    },
    computed: {
      imgUrl() {
        if (!this.$store.getters.user.icon) {
          return require('../../../../static/images/avatar/4.jpg');
        } else {
          return this.$store.getters.user.icon
        }
      }
    },
    created() {
      this.userInfo = this.$store.getters.user;
      this.getUser();
      this.getVestList();
    },
    mounted() {
      layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
      });
    },
    methods: {
      //获取用户信息
      getUser() {
        user.getUser().then(res => {
          let _this = this;
          console.log(res.data);
          _this.email = res.data.email;
          _this.username = res.data.username;
          _this.city = res.data.city;
          _this.signature = res.data.signature;
          _this.mobile = res.data.mobile;
          _this.selectSex = res.data.sex;
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
        })
      },
      UpladFile() {
        this.$refs.file.click();
      },
      finishFile(e) {

        let file = e.target.files[0];
        let param = new FormData(); //创建form对象
        param.append('file', file);//通过append向form对象添加数据
        console.log(param.get('file')); //FormData私有类对象，访问不到，可以通过get判断值是否传进去
        let config = {
          headers: {
            'Content-Type': 'multipart/form-data',
            'B-Token': window.localStorage['B-Token']
          }
        }; //添加请求头
        axios.post(window.localStorage.baseUrl + '/upload/file', param, config)
          .then(res => {
            this.updateUserIcon(res.data.data.src);
          })
      },
      //修改用户头像
      updateUserIcon(imgUrl) {
        let url = encodeURIComponent(imgUrl);
        this.$store.state.user.icon = decodeURIComponent(url);
        user.updateUserIcon(url).then(res => {
          layer.msg('修改成功');
        })

      },
      upDateUser() {
        let emailReg = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if (!emailReg.test(this.email)) {
          return layer.msg('邮箱格式不正确', {icon: 5});
        }
        if (!this.username || !this.city) {
          return false;
        }

        let UserForm = {
          email: this.email,
          username: this.username,
          city: this.city,
          signature: this.signature,
          mobile: this.mobile,
          sex: this.selectSex
        };
        user.upDateUser(UserForm).then(res => {
          this.email = res.data.email;
          this.username = res.data.username;
          this.city = res.data.city;
          this.signature = res.data.signature;
          this.mobile = res.data.mobile;
          this.selectSex = res.data.sex;
          layer.msg('修改成功');
          this.$store.dispatch('addUserInfo');// 回填基本信息
        })
      },
      // 购买马甲
      showBuyVestBox() {
        this.vestBox = true;
      },
      addVest() {
        vest.add(this.vest.name).then(res => {
          this.vestBox = false;
          layer.msg(res.msg);
          this.getVestList();
        })
      },
      getVestList() {
        vest.getList().then(res => {
          this.vestList = res.data;
          this.vestName = this.username;
        })
      },
      // 获得上好信息
      getAccount() {
        this.accountBox = true;
        user.getAccount().then(res => {
          this.accountList = res.data;
        })
      },
      // 切换账号
      turnAccount(userId) {
        window.location.href = window.localStorage.baseUrl + '/turnAccount?userId=' + userId;
      },
      addAccount() {
        this.accountForm.unionId = this.userInfo.unionId;
        user.addUser(this.accountForm).then(res => {
          this.addAccountBox = false;
          layer.msg('添加成功');
        })
      }
    },
    filters: {
      subString(str, n) {
        if (!str) {
          return '';
        }
        let sign = str.length > n ? '...' : '';
        return str.substring(0, n) + sign;
      }
    }
  }
</script>

<style scoped>
  /deep/ .el-input__inner {
    border-radius: 0px !important;
    height: 38px !important;
  }

  .el-select {
    width: 100%;
  }

  .el-button {
    border-radius: 0px;
  }
</style>
