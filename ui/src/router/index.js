import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/layer'
import Index from '@/views/index'
import Detail from '@/views/post/detail'
import Search from '@/views/post/search'
import NewPost from '@/views/post/new'
import Post from '@/views/post/index'
import Add from '@/views/add'
import Login from '@/views/loginpc'
import Login2 from '@/views/login'
import UserIndex from '@/views/user/home'
import UserIndex2 from '@/views/user/index'
import setIndex from '@/views/set/index'
import userReg from '@/views/user/reg'
import Case from '@/views/case/index'
import Games from '@/views/games/index'
import Gitter from '@/views/gitter/index'
import World from '@/views/world/index'
import WorldDetail from '@/views/world/detail'
import PostGuide from '@/views/guide/index'
import Faq from '@/views/faq/index'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home/index',
      meta: { title: 'Tinymce' }
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
        },
        {
          path: 'index',
          component: Post,
          meta: { title: 'Tinymce' }
        },
        {
          path: 'new',
          component: NewPost
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
        },{
          path: 'home',
          component: UserIndex2
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
    }, {
      path: '/world',
      component: Layer,
      children: [
        {
          path: 'index',
          component: World
        },
        {
          path: 'detail',
          component: WorldDetail
        }
      ]
    }, {
      path: '/guide',
      component: Layer,
      children: [
        {
          path: 'index',
          component: PostGuide
        }
      ]
    }, {
      path: '/faq',
      component: Layer,
      children: [
        {
          path: 'index',
          component: Faq
        }
      ]
    }
  ]
})
