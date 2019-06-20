<template>
  <div class="container demo-1">
    <div class="content" unselectable="on" onselectstart="return false;"
         oncontextmenu="return false"
         onmousedown="return false;">
      <div id="large-header" class="large-header">
        <a style="cursor: pointer; color: blue; float: right; margin: 5px" @click="testLogin" v-if="true">开发者登录</a>
        <canvas></canvas>
        <div class="logo_box">
          <div id="login_container"></div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>

  import * as login from '@/api/login'
  import * as user from '@/api/user'

  // let width, height, largeHeader, canvas, ctx, points, target, animateHeader = true;
  export default {
    name: "loginpc",
    data() {
      return {
        loginTmpCode: '',
        redirectState: '',
        accessToken: '',
        companyToken: '',
        unionId: '',
        token: '',
        freeCode: ''
      }
    },
    created() {
      if (this.$route.query.token) {
        // console.log("aaaa")
        // this.redirectCode = this.$route.query.code;
        // this.redirectState = this.$route.query.state;
        // this.getAccessToken();
        // // this.getCompanyToken();
        this.token = this.$route.query.token;
        window.localStorage['J-Token'] = this.token;
        user.getUser().then(res => {
          // console.log(res.data)
          this.$store.dispatch('addUserInfo').then(() => {
            if (!res.data.username) {
              this.$router.push('/user/reg');
            } else {
              window.localStorage.setItem('userInfo', JSON.stringify(res.data));
              this.$router.push('/home/index');
            }
          });
        })
      }
    },
    mounted() {
      document.addEventListener('touchmove', function (e) {
        e.preventDefault()
      })
      var c = document.getElementsByTagName('canvas')[0],
        x = c.getContext('2d'),
        pr = window.devicePixelRatio || 1,
        w = window.innerWidth,
        // w = document.body.clientWidth,
        // h = document.body.clientHeight,
        h = window.innerHeight,
        f = 90,
        q,
        m = Math,
        r = 0,
        u = m.PI * 2,
        v = m.cos,
        z = m.random
      c.width = w * pr
      c.height = h * pr
      x.scale(pr, pr)
      x.globalAlpha = 0.6

      function i() {
        x.clearRect(0, 0, w, h)
        q = [{x: 0, y: h * .7 + f}, {x: 0, y: h * .7 - f}]
        while (q[1].x < w + f) d(q[0], q[1])
      }

      function d(i, j) {
        x.beginPath()
        x.moveTo(i.x, i.y)
        x.lineTo(j.x, j.y)
        var k = j.x + (z() * 2 - 0.25) * f,
          n = y(j.y)
        x.lineTo(k, n)
        x.closePath()
        r -= u / -50
        x.fillStyle = '#' + (v(r) * 127 + 128 << 16 | v(r + u / 3) * 127 + 128 << 8 | v(r + u / 3 * 2) * 127 + 128).toString(16)
        x.fill()
        q[0] = q[1]
        q[1] = {x: k, y: n}
      }

      function y(p) {
        var t = p + (z() * 2 - 1.1) * f
        return (t > h || t < 0) ? y(p) : t
      }

      document.onclick = i
      document.ontouchstart = i
      i()
      // this.initHeader();
      // this.initAnimation();
      // this.addListeners();
      // console.log(DingTalkPC.ua.isWeb) //引入钉钉桌面端JSAPI后可直接获取
      this.dingLogin();
      // if (DingTalkPC.ua.isInDingTalk) {
      //   this.dingLogin();
      // } else {
      //   this.freeLogin()
      // }
      /*
      {
          isDesktop: true, //是否在桌面端
          isInDingTalk: true, //页面是否在钉钉网页端，windows端，mac端 的工作模块容器内显示
          isWeb: false, //是否是网页端
          isWin: true, //是否是windows客户端
          isMac: true, //是否是mac客户端
          hostVersion : '3.1.0' //当前客户端版本
          language : 'zh_CN' //当前客户端的语言设置，当前值可能是："zh_CN","zh_TW","en_US"（注意：需具备jsapi 2.6.0版本以上，客户端版本3.3.0以上才能检测出到此字段， 当客户端版本低于3.3.0，而jsAPI使用了2.6.0,则此字段值为 “*” ）
      }
      */

      // if (dd.ios || dd.android) {
      //   console.log("钉钉客户端")
      //   this.freeLogin()
      // } else {
      //   console.log("不是钉钉客户端")
      //   this.dingLogin();
      // }

    },
    methods: {
      testLogin() {
        window.localStorage['J-Token'] = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbklkIjoiaWlnRFNuRFdnVHRpU2lpRjVDOGQ2SkxkZ2lFaUUiLCJleHAiOjE1NjE5NzY5MDAsInVzZXJJZCI6Ijc5In0.BFrcIyk0WVnLAKQ7BMPEdKBkr45zfWVau3dj1qA9Dac';
        let userInfo = {
          id: 78
        }
        window.localStorage.setItem('userInfo', JSON.stringify(userInfo));
        this.$router.push('/home/index');
      },
      getAccessToken() {
        // login.getAccessToken().then(res => {
        //   console.log('accessToken：' + res.data.access_token)
        //   // console.log(res.data)
        //   this.accessToken = res.data.access_token;
        //   this.getUnionId();
        // })
        login.getAccessToken().then(res => {
          // console.log(res.data)
        })
      },
      freeLogin() {
        login.freeLogin().then(res => {
          // console.log(res)
        })
      },
      // getCompanyToken() {
      //   login.getCompanyToken().then(res => {
      //     console.log('companyToken:' + res.data.access_token)
      //     this.companyToken = res.data.access_token;
      //   })
      // },
      // getUnionId() {
      //   let json = {
      //     "tmp_auth_code": this.redirectCode
      //   }
      //   login.getUnionId(this.accessToken, json).then(res => {
      //     console.log(res.data);
      //     this.unionId = res.data.unionid;
      //     if (!!this.unionId) {
      //       this.getUserId();
      //     }
      //   })
      // },
      // getUserId() {
      //   login.getUserId(this.companyToken, this.unionId).then(res => {
      //     console.log('getUserId:' + res.data);
      //     if (res.data.errcode == 60121) {
      //       // 非法用户
      //     } else {
      //       this.userId = res.data.userid;
      //       this.getUser();
      //     }
      //   })
      // },
      // getUser() {
      //   login.getUser(this.companyToken, this.userId).then(res => {
      //     this.user = res.data;
      //     console.log(this.user);
      //     if (!!this.user) {
      //       this.login();
      //     }
      //   })
      // },
      // login() {
      //   let loginForm = {
      //     unionId: this.unionId,
      //     name: this.user.name,
      //     mobile: this.user.mobile,
      //     position: this.user.position
      //   };
      //
      //   login.addUser(loginForm).then(res => {
      //     console.log(res.data);
      //     let token = res.token;
      //     window.localStorage['J-Token'] = token;
      //     this.$store.dispatch('addUserInfo').then(() => {
      //       if (!res.data.username) {
      //         this.$router.push('/user/reg');
      //       } else {
      //         window.localStorage.setItem('userInfo', JSON.stringify(res.data));
      //         this.$router.push('/home/index');
      //       }
      //     });
      //
      //   })
      // },
      dingLogin() {
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
              c.width = "300px",
              c.height = "300px",
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

        var goto = "https://oapi.dingtalk.com/connect/oauth2/sns_authorize?appid=" + window.localStorage.DD_APPID +
          "&response_type=code&scope=snsapi_login&state=STATE" +
          "&redirect_uri=" + encodeURIComponent(window.localStorage.DD_REDIRECT);
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
      initHeader() {
        width = window.innerWidth;
        height = window.innerHeight;
        target = {x: width / 2, y: height / 2};

        largeHeader = document.getElementById('large-header');
        // console.log(largeHeader)
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
      },

    },
  }
</script>

<style scoped>
  .container {
    position: fixed;
    top: 0;
  }
  .logo_box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>
