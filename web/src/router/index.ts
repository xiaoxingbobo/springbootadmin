import { createRouter, createWebHashHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'
import type { App } from 'vue'
import { Layout } from '@/utils/routerHelper'
import { useI18n } from '@/hooks/web/useI18n'

const { t } = useI18n()

// 静态路由
export const constantRouterMap: AppRouteRecordRaw[] = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard/analysis',
    name: 'Root',
    meta: {
      hidden: true
    }
  },
  {
    path: '/redirect',
    component: Layout,
    name: 'Redirect',
    children: [
      {
        path: '/redirect/:path(.*)',
        name: 'Redirect',
        component: () => import('@/views/Redirect/Redirect.vue'),
        meta: {}
      }
    ],
    meta: {
      hidden: true,
      noTagsView: true
    }
  },
  // 首页
  {
    path: '/dashboard',
    component: Layout,
    redirect: '/dashboard/analysis',
    name: 'Dashboard',
    meta: {
      title: t('router.dashboard'),
      icon: 'ant-design:dashboard-filled',
      alwaysShow: true
    },
    children: [
      {
        path: 'analysis',
        component: () => import('@/views/Dashboard/Analysis.vue'),
        name: 'Analysis',
        meta: {
          title: t('router.analysis'),
          noCache: true,
          affix: true
        }
      },
      {
        path: 'workplace',
        component: () => import('@/views/Dashboard/Workplace.vue'),
        name: 'Workplace',
        meta: {
          title: t('router.workplace'),
          noCache: true
        }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/Login/Login.vue'),
    name: 'Login',
    meta: {
      hidden: true,
      title: t('router.login'),
      noTagsView: true
    }
  },
  {
    path: '/404',
    component: () => import('@/views/Error/404.vue'),
    name: 'NoFind',
    meta: {
      hidden: true,
      title: '404',
      noTagsView: true
    }
  }
]

// export const myscreenRouters: AppRouteRecordRaw[] = wsCache.get('screenRouters')

// 动态路由列表
export const asyncRouterMap: AppRouteRecordRaw[] = [
  // 文档
  {
    path: '/external-link',
    component: Layout,
    meta: {},
    name: 'ExternalLink',
    children: [
      {
        path: 'https://element-plus-admin-doc.cn/',
        name: 'ExternalLink',
        meta: {
          title: t('router.document'),
          icon: 'clarity:document-solid',
          hidden: true // 菜单中不显示
        }
      }
    ]
  },
  // 代码生成
  {
    path: '/code',
    component: Layout,
    redirect: '/code/code',
    name: 'sys_code',
    meta: {
      // title: t('router.example'),
      title: '代码生成',
      icon: 'vaadin:code',
      alwaysShow: true
    },
    children: [
      {
        path: 'code',
        component: () => import('@/views/code/index.vue'),
        name: 'sys_code_select',
        meta: {
          // title: t('router.exampleDialog')
          title: '代码生成',
          hidden: true // 菜单中不显示
        }
      }
    ]
  },
  // 系统设置
  {
    path: '/system',
    component: Layout,
    redirect: '/system/permission',
    name: 'sys_system',
    meta: {
      // title: t('router.example'),
      title: '系统设置',
      icon: 'ep:setting',
      alwaysShow: true
    },
    children: [
      {
        path: 'permission',
        component: () => import('@/views/System/Permission/index.vue'),
        name: 'sys_authority_select',
        meta: {
          // title: t('router.exampleDialog')
          title: '权限管理',
          hidden: true, // 菜单中不显示
          icon: null
        }
      },
      {
        path: 'user',
        component: () => import('@/views/System/User/index.vue'),
        name: 'sys_user_select',
        meta: {
          // title: t('router.exampleDialog')
          title: '用户管理',
          hidden: true // 菜单中不显示
        }
      },
      {
        path: 'role',
        component: () => import('@/views/System/Role/index.vue'),
        name: 'sys_role_select',
        meta: {
          // title: t('router.exampleDialog')
          title: '角色管理',
          hidden: true // 菜单中不显示
        }
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    redirect: '/error/404',
    name: 'Error',
    meta: {
      title: t('router.errorPage'),
      icon: 'ci:error',
      hidden: true, // 菜单中不显示
      alwaysShow: true
    },
    children: [
      {
        path: '404-demo',
        component: () => import('@/views/Error/404.vue'),
        name: '404Demo',
        meta: {
          title: '404'
        }
      },
      {
        path: '403-demo',
        component: () => import('@/views/Error/403.vue'),
        name: '403Demo',
        meta: {
          title: '403'
        }
      },
      {
        path: '500-demo',
        component: () => import('@/views/Error/500.vue'),
        name: '500Demo',
        meta: {
          title: '500'
        }
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  strict: true,
  routes: constantRouterMap as RouteRecordRaw[],
  scrollBehavior: () => ({ left: 0, top: 0 })
})

export const resetRouter = (): void => {
  const resetWhiteNameList = ['Redirect', 'Login', 'NoFind', 'Root']
  router.getRoutes().forEach((route) => {
    const { name } = route
    if (name && !resetWhiteNameList.includes(name as string)) {
      router.hasRoute(name) && router.removeRoute(name)
    }
  })
}

export const setupRouter = (app: App<Element>) => {
  app.use(router)
}

export default router
