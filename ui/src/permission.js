//  用于权限验证及路由进度展示
import router from './router';
import store from './store';

const whiteList = ['/user/reg', '/'];// 重定向白名单

router.beforeEach((to, from, next) => {
  if (window.localStorage['B-Token']) {
    if (!store.getters.user.username) {
      store.dispatch('addUserInfo').then((res) => {
        if (!store.getters.user.username) {
          next('/user/reg');
        } else {
          next()
        }
      })
    } else {
      next()
    }
  } else {
    next('/');
    //  不存在用户姓名
    console.log(to.path);
    // if (whiteList.indexOf(to.path) !== -1) { // 在免登录白名单，直接进入
    //   next()
    // }

    // store.dispatch('addUserInfo').then(() => {
    //   console.log('刷新', store.getters.user.username)
    //   if (!store.getters.user.username) {
    //     next('/user/reg');
    //   } else {
    //     next()
    //   }
    // })
  }
});

router.afterEach((to) => {

});
