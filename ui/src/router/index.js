import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/layer'
import Index from '@/views/index'
import Detail from '@/views/post/detail'
import Search from '@/views/post/search'
import Add from '@/views/add'
import Login from '@/views/loginpc'
import Login2 from '@/views/login'
import UserIndex from '@/views/user/home'
import setIndex from '@/views/set/index'
import userReg from '@/views/user/reg'
import Case from '@/views/case/index'
import Games from '@/views/games/index'
import Gitter from '@/views/gitter/index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home/index'
    }, {
      path: '/login',
      component: Login,
    }, {
      path: '/login2',
      component: Login2,
    }, {
      path: '/home',
      component: Layer,
      children: [
        {
          path: 'index',
          name: 'Index',
          component: Index
        }
      ]
    }, {
      path: '/post',
      component: Layer,
      children: [
        {
          path: 'detail',
          component: Detail
        },
        {
          path: 'search',
          component: Search
        }
      ]
    }, {
      path: '/add',
      component: Layer,
      children: [
        {
          path: 'index',
          component: Add
        }
      ]
    }, {
      path: '/user',
      component: Layer,
      children: [
        {
          path: 'index',
          component: UserIndex
        }
      ]
    }, {
      path: '/set',
      component: Layer,
      children: [
        {
          path: 'index',
          component: setIndex
        }
      ]
    }, {
      path: '/user',
      component: Layer,
      children: [
        {
          path: 'reg',
          component: userReg
        }
      ]
    }, {
      path: '/case',
      component: Layer,
      children: [
        {
          path: 'index',
          component: Case
        }
      ]
    }, {
      path: '/games',
      component: Layer,
      children: [
        {
          path: 'index',
          component: Games
        }
      ]
    }, {
      path: '/gitter',
      component: Layer,
      children: [
        {
          path: 'index',
          component: Gitter
        }
      ]
    }
  ]
})
