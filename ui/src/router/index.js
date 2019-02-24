import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/views/index'
import Detail from '@/views/post/detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'index',
      component: Index
    },{
      path: '/post/detail',
      name: 'detail',
      component: Detail
    },
  ]
})
