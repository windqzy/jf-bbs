// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store/index'
import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/base.scss'

import '@/icons' // icon
import './permission' // 权限

/*引入资源请求插件*/
import VueResource from 'vue-resource'

// import 'jquery'
import * as filters from './utils/filters' // 过滤器

/*使用VueResource插件*/
Vue.use(VueResource)

Vue.config.productionTip = false;
Vue.use(ElementUI);

// 全局过滤
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

// router.beforeEach((to, from, next) => {
//   if (store.state.user.username) {
//     next();
//   } else {
//     store.dispatch('addUserInfo').then(() => {
//       if (!store.state.user.username) {
//         router.push('/user/reg');
//       }
//     })
//   }
// })

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
