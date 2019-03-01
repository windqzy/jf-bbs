//  用于权限验证及路由进度展示
import router from './router';
import store from './store';

const whiteList = ['/login'];// 重定向白名单
console.log(window.localStorage['B-Token'])
router.beforeEach((to, from, next) => {
  if (window.localStorage['B-Token']) {
    console.log(store.getters.user.username)
    if (!store.getters.user.username) {
      store.dispatch('addUserInfo').then(() => {
        next()
      })
    } else {
      next()
    }
  } else {
    //  不存在用户姓名
    if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
      next()
    } else {
      next('/login')
    }
  }
});

router.afterEach((to) => {

});
