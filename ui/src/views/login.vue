<template>
  <div class="layui-container">
    <div class="layui-row layui-row-space10">
      <div class="layui-col-xs2 layui-col-md4">
        .
      </div>
      <div class="layui-col-xs8 layui-col-md4">
        <div class="layui-form" id="login-form">
          <form class="layui-form" action="">
            <div class="layui-form-item logo-title">
              <h1>用户登录</h1>
            </div>
            <!-- 二维码区域 -->
            <div id="qrcode_container">
              <button class="layui-btn" @click="ddLogin">钉钉登录</button>
              <!--<a href="https://oapi.dingtalk.com/connect/qrconnect?appid=dingoagzpnxuezq1lukowt&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=http%3a%2f%2f10.0.2.157%3a8080%2f%23%2f">钉钉登录</a>-->
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import * as login from '@/api/login'

  export default {
    name: "login",
    data() {
      return {
        go: 'https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=dingoagzpnxuezq1lukowt&response_type=code' +
        '&scope=snsapi_login&state=STATE&redirect_uri=http://10.0.2.157:8080/',
        loginTmpCode: '',
        accessToken: '',
        companyToken: '',
        redirectCode: '',
        redirectState: '',
        unionId: '',
        userId: '',
        user: [],
      }
    },
    created() {
      // debugger
      // 生成二维码
      console.log(this.$route);
      if (this.$route.query.code) {
        this.redirectCode = this.$route.query.code;
        this.redirectState = this.$route.query.state;
      }
    },
    mounted() {
      // this.createQrCode();
      this.getAccessToken();
      this.getCompanyToken();
    },
    methods: {
      ddLogin() {
        window.location.href = 'https://oapi.dingtalk.com/connect/qrconnect?appid=' + window.localStorage.DD_APPID +
          '&response_type=code&scope=snsapi_login&state=STATE&redirect_uri=' + encodeURIComponent(window.localStorage.DD_REDIRECT);
        window.event.returnValue = false;
      },
      // getList() {
      //   login.getList().then(res => {
      //     console.log(res.data)
      //   })
      // }
      createQrCode() {
        var obj = DDLogin({
          id: "qrcode_container",
          goto: encodeURIComponent(this.go),
          style: "border:none;background-color:#FFFFFF;",
          width: "365",
          height: "400"
        });
        var hanndleMessage = function (event) {
          var origin = event.origin;
          console.log("origin", event.origin);
          if (origin == "https://login.dingtalk.com") { //判断是否来自ddLogin扫码事件。
            this.loginTmpCode = event.data; //拿到loginTmpCode后就可以在这里构造跳转链接进行跳转了
            console.log('loginTmpCode：' + this.loginTmpCode)
          }
        };
        if (typeof window.addEventListener != 'undefined') {
          window.addEventListener('message', hanndleMessage, false);
        } else if (typeof window.attachEvent != 'undefined') {
          window.attachEvent('onmessage', hanndleMessage);
        }

      },
      getAccessToken() {
        login.getAccessToken().then(res => {
          console.log('accessToken：' + res.data.access_token)
          // console.log(res.data)
          this.accessToken = res.data.access_token;
          if (!!this.accessToken) {
            this.getUnionId();
          }
        })
      },
      getCompanyToken() {
        login.getCompanyToken().then(res => {
          console.log('companyToken:' + res.data.access_token)
          this.companyToken = res.data.access_token;
        })
      },
      getUnionId() {
        let json = {
          "tmp_auth_code": this.redirectCode
        }
        login.getUnionId(this.accessToken, json).then(res => {
          console.log(res.data);
          this.unionId = res.data.unionid;
          if (!!this.unionId) {
            this.getUserId();
          }
        })
      },
      getUserId() {
        login.getUserId(this.companyToken, this.unionId).then(res => {
          console.log(res.data);
          this.userId = res.data.userid;
          if (!!this.userId) {
            this.getUser();
          }
        })
      },
      getUser() {
        login.getUser(this.companyToken, this.userId).then(res => {
          this.user = res.data;
          console.log(this.user);
          if (!!this.user) {
            this.login();
          }
        })
      },
      login() {
        let loginForm = {
          unionId: this.unionId,
          name: this.user.name,
          mobile: this.user.mobile,
          position: this.user.position
        }

        login.addUser(loginForm).then(res => {
          console.log(res)
          let token = res.token
          window.localStorage['B-Token'] = token;
          // 'B-Token'
        })
      }
    },

  }
</script>

<style scoped>
</style>
