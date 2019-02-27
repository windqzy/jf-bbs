<template>
  <div class="container demo-1">
    <div class="content">
      <div id="large-header" class="large-header">
        <a style="cursor: pointer; color: white; float: right; margin: 5px" @click="testLogin">开发者登录</a>
        <canvas id="demo-canvas"></canvas>
        <div class="logo_box">
          <h3>欢迎登录</h3>
          <div id="login_container"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
  import * as login from '@/api/login'

  let width, height, largeHeader, canvas, ctx, points, target, animateHeader = true;
  export default {
    name: "loginpc",
    data() {
      return {
        loginTmpCode: '',
        redirectState: '',
        accessToken: '',
        companyToken: '',
        unionId: '',
      }
    },
    created() {
      if (this.$route.query.code) {
        this.redirectCode = this.$route.query.code;
        this.redirectState = this.$route.query.state;
        this.getAccessToken();
        this.getCompanyToken();
      }
    },
    mounted() {
      this.initHeader();
      this.initAnimation();
      this.addListeners();
      // 钉钉登录开始
      !function (window, document) {
        function d(a) {
          var e, c = document.createElement("iframe"),
            d = a.qrcodeUrl;
          d += a.style ? "&style=" + a.style : "",
            d += a.href ? "&href=" + a.href : "",
            c.src = d,
            c.frameBorder = "0",
            c.allowTransparency = "true",
            c.scrolling = "no",
            c.width = "330px",
            c.height = "400px",
            e = document.getElementById(a.id),
            e.innerHTML = "",
            e.appendChild(c)
        }

        window.DDLogin = d
      }(window, document);

      var hanndleMessage = function (event) {
        var data = event.data;
        var origin = event.origin;
        if (origin == "https://login.dingtalk.com" || origin == "https://pre-login.dingtalk.com") {
          window.location.href = getUrlParam("goto", qrcodeUrl) + "&loginTmpCode=" + data;
        }
      };
      if (typeof window.addEventListener != 'undefined') {
        window.addEventListener('message', hanndleMessage, false);
      } else if (typeof window.attachEvent != 'undefined') {
        //for ie
        window.attachEvent('onmessage', hanndleMessage);
      }

      var goto = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid="+window.localStorage.DD_APPID+"&response_type=code&scope=snsapi_login&state=STATE" +
        "&redirect_uri=" + encodeURIComponent('http://10.0.2.63:8080/#/');
      var qrcodeUrl = "https://login.dingtalk.com/login/qrcode.htm?goto=" + encodeURIComponent(goto);
      var obj = DDLogin({
        id: "login_container",
        qrcodeUrl: qrcodeUrl,
        style: "",
        href: "",
        width: "300px",
        height: "300px",

      });

      function getUrlParam(name, url) {
        // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空
        if (url.indexOf("?") == -1 || url.indexOf(name + '=') == -1) {
          return '';
        }
        // 获取链接中参数部分
        var queryString = url.substring(url.indexOf("?") + 1);
        if (queryString.indexOf('#') > -1) {
          queryString = queryString.substring(0, queryString.indexOf('#'));
        }
        ;
        // 分离参数对 ?key=value&key2=value2
        var parameters = queryString.split("&");
        var pos, paraName, paraValue;
        for (var i = 0; i < parameters.length; i++) {
          // 获取等号位置
          pos = parameters[i].indexOf('=');
          if (pos == -1) {
            continue;
          }
          // 获取name 和 value
          paraName = parameters[i].substring(0, pos);
          paraValue = parameters[i].substring(pos + 1);
          // 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
          if (paraName == name) {
            return decodeURIComponent(paraValue.replace("+", " "));
          }
        }
        return '';
      };
      // 钉钉登录结束
    },
    methods: {
      testLogin() {
        window.localStorage['B-Token'] = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbklkIjoiZ0RKdjR4UVQyaWl' +
          '2TWZTYVRvYmVORkFpYWFhIiwiZXhwIjoxNTUyNjU3NjY3LCJ1c2VySWQiOiI3NyJ9._Fj9u6QJD2sM5Jg0gqA8pbjrqxRfQm7U61Kgyb3FbRI';
        let userInfo = {
          id: 77
        }
        window.localStorage.setItem('userInfo', JSON.stringify(userInfo));
        this.$router.push('/home/index');
      },
      getAccessToken() {
        login.getAccessToken().then(res => {
          console.log('accessToken：' + res.data.access_token)
          // console.log(res.data)
          this.accessToken = res.data.access_token;
          this.getUnionId();
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
          console.log('getUserId:' + res.data);
          if (res.data.errcode == 60121) {
            // 非法用户
          } else {
            this.userId = res.data.userid;
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
          console.log('bbb');
          console.log(res.data);
          let token = res.token;
          window.localStorage['B-Token'] = token;
          window.localStorage.setItem('userInfo', JSON.stringify(res.data));
          this.$router.push('/home/index');
        })
      },
      initHeader() {
        width = window.innerWidth;
        height = window.innerHeight;
        target = {x: width / 2, y: height / 2};

        largeHeader = document.getElementById('large-header');
        console.log(largeHeader)
        largeHeader.style.height = height + 'px';

        canvas = document.getElementById('demo-canvas');
        canvas.width = width;
        canvas.height = height;
        ctx = canvas.getContext('2d');

        // create points
        points = [];
        for (var x = 0; x < width; x = x + width / 20) {
          for (var y = 0; y < height; y = y + height / 20) {
            var px = x + Math.random() * width / 20;
            var py = y + Math.random() * height / 20;
            var p = {x: px, originX: px, y: py, originY: py};
            points.push(p);
          }
        }

        // for each point find the 5 closest points
        for (var i = 0; i < points.length; i++) {
          var closest = [];
          var p1 = points[i];
          for (var j = 0; j < points.length; j++) {
            var p2 = points[j]
            if (!(p1 == p2)) {
              var placed = false;
              for (var k = 0; k < 5; k++) {
                if (!placed) {
                  if (closest[k] == undefined) {
                    closest[k] = p2;
                    placed = true;
                  }
                }
              }

              for (var k = 0; k < 5; k++) {
                if (!placed) {
                  if (this.getDistance(p1, p2) < this.getDistance(p1, closest[k])) {
                    closest[k] = p2;
                    placed = true;
                  }
                }
              }
            }
          }
          p1.closest = closest;
        }

        // assign a circle to each point
        for (var i in points) {
          var c = new this.Circle(points[i], 2 + Math.random() * 2, 'rgba(255,255,255,0.3)');
          points[i].circle = c;
        }
      },
      // Event handling
      addListeners() {
        if (!('ontouchstart' in window)) {
          window.addEventListener('mousemove', this.mouseMove);
        }
        window.addEventListener('scroll', this.scrollCheck);
        window.addEventListener('resize', this.resize);
      },
      mouseMove(e) {
        var posx = 0, posy = 0;
        if (e.pageX || e.pageY) {
          posx = e.pageX;
          posy = e.pageY;
        } else if (e.clientX || e.clientY) {
          posx = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
          posy = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
        }
        target.x = posx;
        target.y = posy;
      },
      scrollCheck() {
        if (document.body.scrollTop > height) animateHeader = false;
        else animateHeader = true;
      },
      resize() {
        width = window.innerWidth;
        height = window.innerHeight;
        largeHeader.style.height = height + 'px';
        canvas.width = width;
        canvas.height = height;
      },
      // animation
      initAnimation() {
        this.animate();
        for (var i in points) {
          this.shiftPoint(points[i]);
        }
      },
      animate() {
        if (animateHeader) {
          ctx.clearRect(0, 0, width, height);
          for (var i in points) {
            // detect points in range
            if (Math.abs(this.getDistance(target, points[i])) < 4000) {
              points[i].active = 0.3;
              points[i].circle.active = 0.6;
            } else if (Math.abs(this.getDistance(target, points[i])) < 20000) {
              points[i].active = 0.1;
              points[i].circle.active = 0.3;
            } else if (Math.abs(this.getDistance(target, points[i])) < 40000) {
              points[i].active = 0.02;
              points[i].circle.active = 0.1;
            } else {
              points[i].active = 0;
              points[i].circle.active = 0;
            }

            this.drawLines(points[i]);
            points[i].circle.draw();
          }
        }
        requestAnimationFrame(this.animate);
      },
      shiftPoint(p) {
        var _this = this;
        TweenLite.to(p, 1 + 1 * Math.random(), {
          x: p.originX - 50 + Math.random() * 100,
          y: p.originY - 50 + Math.random() * 100, ease: Circ.easeInOut,
          onComplete: function () {
            _this.shiftPoint(p);
          }
        });
      },
      // Canvas manipulation
      drawLines(p) {
        if (!p.active) return;
        for (var i in p.closest) {
          ctx.beginPath();
          ctx.moveTo(p.x, p.y);
          ctx.lineTo(p.closest[i].x, p.closest[i].y);
          ctx.strokeStyle = 'rgba(156,217,249,' + p.active + ')';
          ctx.stroke();
        }
      },
      Circle(pos, rad, color) {
        var _this = this;

        // constructor
        (function () {
          _this.pos = pos || null;
          _this.radius = rad || null;
          _this.color = color || null;
        })();

        this.draw = function () {
          if (!_this.active) return;
          ctx.beginPath();
          ctx.arc(_this.pos.x, _this.pos.y, _this.radius, 0, 2 * Math.PI, false);
          ctx.fillStyle = 'rgba(156,217,249,' + _this.active + ')';
          ctx.fill();
        };
      },
      // Util
      getDistance(p1, p2) {
        return Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2);
      },
      getUrlParam(name, url) {
        // 如果链接没有参数，或者链接中不存在我们要获取的参数，直接返回空
        if (url.indexOf("?") == -1 || url.indexOf(name + '=') == -1) {
          return '';
        }
        // 获取链接中参数部分
        var queryString = url.substring(url.indexOf("?") + 1);
        if (queryString.indexOf('#') > -1) {
          queryString = queryString.substring(0, queryString.indexOf('#'));
        }
        ;
        // 分离参数对 ?key=value&key2=value2
        var parameters = queryString.split("&");
        var pos, paraName, paraValue;
        for (var i = 0; i < parameters.length; i++) {
          // 获取等号位置
          pos = parameters[i].indexOf('=');
          if (pos == -1) {
            continue;
          }
          // 获取name 和 value
          paraName = parameters[i].substring(0, pos);
          paraValue = parameters[i].substring(pos + 1);
          // 如果查询的name等于当前name，就返回当前值，同时，将链接中的+号还原成空格
          if (paraName == name) {
            return decodeURIComponent(paraValue.replace("+", " "));
          }
        }
        return '';
      }
    },
  }
</script>

<style scoped>
  *, *:after, *:before {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
  }

  .clearfix:before, .clearfix:after {
    content: '';
    display: table;
  }

  .clearfix:after {
    clear: both;
  }

  a, button {
    outline: none;
  }

  a {
    color: #566473;
    text-decoration: none;
  }

  a:hover, a:focus {
    color: #34495e;
  }

  section {
    padding: 1em;
    text-align: center;
  }

  p.ref {
    text-align: center;
    padding: 2em 1em;
  }

  .container {
    position: fixed;
    top: 0;
  }

  /* Header */
  .large-header {
    position: relative;
    width: 100%;
    background: #333;
    overflow: hidden;
    background-size: cover;
    background-position: center center;
    z-index: 1;
  }

  .demo-1 .large-header {
    background-image: url('../../static/images/demo-1-bg.jpg');
  }

  .logo_box {
    width: 400px;
    height: 500px;
    padding: 35px;
    color: #EEE;
    position: absolute;
    left: 50%;
    top: 50%;
    margin-left: -200px;
    margin-top: -250px;
  }

  .logo_box h1, h2, h3 {
    text-align: center;
    height: 20px;
    font: 20px "microsoft yahei", Helvetica, Tahoma, Arial, "Microsoft jhengHei", sans-serif;
    color: #FFFFFF;
    height: 20px;
    line-height: 20px;
    padding: 0 0 35px 0;
  }

  .forms {
    width: 280px;
    height: 485px;
  }

  .logon_inof {
    width: 100%;
    min-height: 450px;
    padding-top: 35px;
    position: relative;
  }

  .input_outer {
    height: 46px;
    padding: 0 5px;
    margin-bottom: 30px;
    border-radius: 50px;
    position: relative;
    border: rgba(255, 255, 255, 0.2) 2px solid !important;
  }

  .u_user {
    width: 25px;
    height: 25px;
    /*background: url(../img/login_ico.png);*/
    background-position: -125px 0;
    position: absolute;
    margin: 10px 13px;
  }

  .us_uer {
    width: 25px;
    height: 25px;
    /*background-image: url(../img/login_ico.png);*/
    background-position: -125px -34px;
    position: absolute;
    margin: 10px 13px;
  }

  .l-login {
    position: absolute;
    z-index: 1;
    left: 50px;
    top: 0;
    height: 46px;
    font: 14px "microsoft yahei", Helvetica, Tahoma, Arial, "Microsoft jhengHei";
    line-height: 46px;
  }

  .text {
    width: 220px;
    height: 46px;
    outline: none;
    display: inline-block;
    font: 14px "microsoft yahei", Helvetica, Tahoma, Arial, "Microsoft jhengHei";
    margin-left: 50px;
    border: none;
    background: none;
    line-height: 46px;
  }

  /*///*/
  .mb2 {
    margin-bottom: 20px
  }

  .mb2 a {
    text-decoration: none;
    outline: none;
  }

  .submit {
    padding: 15px;
    margin-top: 20px;
    display: block;
  }

  .act-but {
    line-height: 20px;
    text-align: center;
    font-size: 20px;
    border-radius: 50px;
    background: #0096e6;
  }
</style>
