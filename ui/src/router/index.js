import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/Layer'
import Index from '@/views/index'
import Detail from '@/views/post/detail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect:'/home/index',
    },
    {
      path: '/home',
      component: Layer,
      children:[
        {
          path:'index',
          name:'Index',
          component: Index
        }
      ]
    },{
      path: '/post',
      component: Layer,
      children:[
        {
          path:'detail',
          component: Detail
        }
      ]
    },
  ]
})
