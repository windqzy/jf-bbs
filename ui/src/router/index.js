import Vue from 'vue'
import Router from 'vue-router'
import Layer from '@/views/layer'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/home/index',
      meta: {title: 'Tinymce'}
    }, {
      path: '/login',
      component: () => import('@/views/loginpc'),
    }, {
      path: '/login2',
      component: () => import('@/views/login'),
    }, {
      path: '/home',
      component: Layer,
      children: [
        {
          path: 'index',
          name: 'Index',
          component: () => import('@/views/index')
        }
      ]
    }, {
      path: '/post',
      component: Layer,
      children: [
        {
          path: 'detail',
          component: () => import('@/views/post/detail')
        },
        {
          path: 'search',
          component: () => import('@/views/post/search')
        },
        {
          path: 'index',
          component: () => import('@/views/post/index'),
          meta: {title: 'Tinymce'}
        },
        {
          path: 'new',
          component: () => import('@/views/post/new')
        }
      ]
    }, {
      path: '/add',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/add')
        }
      ]
    }, {
      path: '/user',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/user/home')
        }, {
          path: 'home',
          component: () => import('@/views/user/index')
        }
      ]
    }, {
      path: '/set',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/set/index')
        }
      ]
    }, {
      path: '/user',
      component: Layer,
      children: [
        {
          path: 'reg',
          component: () => import('@/views/user/reg')
        }
      ]
    }, {
      path: '/case',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/case/index')
        }
      ]
    }, {
      path: '/games',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/games/index')
        }
      ]
    }, {
      path: '/gitter',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/gitter/index')
        }
      ]
    }, {
      path: '/world',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/world/index')
        },
        {
          path: 'detail',
          component: () => import('@/views/world/detail')
        }
      ]
    }, {
      path: '/guide',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/guide/index')
        }
      ]
    }, {
      path: '/faq',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/faq/index')
        }
      ]
    }, {
      path: '/msg',
      component: Layer,
      children: [
        {
          path: 'index',
          component: () => import('@/views/message/index')
        }
      ]
    }
  ]
})
