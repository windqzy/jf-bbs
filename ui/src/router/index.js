import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/layer'
import Index from '@/views/index'
import Detail from '@/views/post/detail'
import Add from '@/views/add'
import Login from '@/views/loginpc'
import UserIndex from '@/views/user/home'
import setIndex from '@/views/set/index'
import userReg from '@/views/user/reg'
import Case from '@/views/case/index'

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
      path: '/user',
      component: Layer,
      children:[
        {
          path:'index',
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
    },{
      path: '/user',
      component: Layer,
      children:[
        {
          path:'reg',
          component: userReg
        }
      ]
    },{
      path: '/case',
      component: Layer,
      children:[
        {
          path:'index',
          component: Case
        }
      ]
    },
  ]
})
