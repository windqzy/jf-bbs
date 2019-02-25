import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/Layer'
import Index from '@/views/index'
import Detail from '@/views/Post/detail'
import Add from '@/views/Add'
import Login from '@/views/login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      component: Login,
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
    },{
      path: '/add',
      component: Layer,
      children:[
        {
          path:'index',
          component: Add
        }
      ]
    },
  ]
})
