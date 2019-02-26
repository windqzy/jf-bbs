import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/layer'
import Index from '@/views/index'
import Detail from '@/views/post/detail'
import Add from '@/views/add'
import Login from '@/views/loginpc'
import UserIndex from '@/views/user/home'
import setIndex from '@/views/set/index'

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
    },{
      path: '/user/index',
      component: Layer,
      children:[
        {
          path:'/user/index',
          component: UserIndex
        }
      ]
    },{
      path: '/set',
      component: Layer,
      children:[
        {
          path:'index',
          component: setIndex
        }
      ]
    },
  ]
})
