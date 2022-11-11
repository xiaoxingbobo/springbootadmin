import { defineStore } from 'pinia'
import { asyncRouterMap, constantRouterMap } from '@/router'
import { generateRoutesFn1, generateRoutesFn2, flatMultiLevelRoutes } from '@/utils/routerHelper'
import { store } from '../index'
import { cloneDeep } from 'lodash-es'

export interface PermissionState {
  routers: AppRouteRecordRaw[]
  addRouters: AppRouteRecordRaw[]
  isAddRouters: boolean
  menuTabRouters: AppRouteRecordRaw[]
  _myasyncRouterMap: AppRouteRecordRaw[]
}

export const usePermissionStore = defineStore({
  id: 'permission',
  state: (): PermissionState => ({
    routers: [],
    addRouters: [],
    isAddRouters: false, // true 页面空白
    menuTabRouters: [],
    _myasyncRouterMap: [] // 自己定义用来存修改后的动态路由表
  }),
  persist: {
    enabled: true
  },
  getters: {
    getRouters(): AppRouteRecordRaw[] {
      return this.routers
    },
    getAddRouters(): AppRouteRecordRaw[] {
      return flatMultiLevelRoutes(cloneDeep(this.addRouters))
    },
    getIsAddRouters(): boolean {
      return this.isAddRouters
    },
    getMenuTabRouters(): AppRouteRecordRaw[] {
      return this.menuTabRouters
    },
    // 修改  自己定义用来存修改后的动态路由表
    getmyasyncRouterMap(): AppRouteRecordRaw[] {
      return this._myasyncRouterMap
    }
  },
  actions: {
    // generateRoutes(
    //   type: 'admin' | 'test' | 'none',
    //   routers?: AppCustomRouteRecordRaw[] | string[]
    // ): Promise<unknown> {
    //   return new Promise<void>((resolve) => {
    //     let routerMap: AppRouteRecordRaw[] = []
    //     if (type === 'admin') {
    //       // 模拟后端过滤菜单
    //       routerMap = generateRoutesFn2(routers as AppCustomRouteRecordRaw[])
    //     } else if (type === 'test') {
    //       // 模拟前端过滤菜单
    //       routerMap = generateRoutesFn1(cloneDeep(asyncRouterMap), routers as string[])
    //     } else {
    //       // 直接读取静态路由表
    //       routerMap = cloneDeep(asyncRouterMap)
    //     }
    //     // 动态路由，404一定要放到最后面
    //     this.addRouters = routerMap.concat([
    //       {
    //         path: '/:path(.*)*',
    //         redirect: '/404',
    //         name: '404Page',
    //         meta: {
    //           hidden: true,
    //           breadcrumb: false
    //         }
    //       }
    //     ])
    //     // 渲染菜单的所有路由
    //     this.routers = cloneDeep(constantRouterMap).concat(routerMap)
    //     resolve()
    //   })
    // },

    // 根据角色拥有的权限，筛选权限对应的路由
    filterRoutes(_myasyncRouterMap: AppRouteRecordRaw[]): void {
      this._myasyncRouterMap = asyncRouterMap
      console.log(this._myasyncRouterMap)
    },
    generateRoutes(_myasyncRouterMap?: AppCustomRouteRecordRaw[] | string[]): Promise<unknown> {
      return new Promise<void>((resolve) => {
        let routerMap: AppRouteRecordRaw[] = []
        // 直接读取静态路由表
        routerMap = cloneDeep(asyncRouterMap)

        // 动态路由，404一定要放到最后面
        this.addRouters = routerMap.concat([
          {
            path: '/:path(.*)*',
            redirect: '/404',
            name: '404Page',
            meta: {
              hidden: true,
              breadcrumb: false
            }
          }
        ])
        // 渲染菜单的所有路由 constantRouterMap基础静态路由
        this.routers = cloneDeep(constantRouterMap).concat(routerMap)
        resolve()
      })
    },
    setIsAddRouters(state: boolean): void {
      this.isAddRouters = state
    },
    setMenuTabRouters(routers: AppRouteRecordRaw[]): void {
      this.menuTabRouters = routers
    }
  }
})

export const usePermissionStoreWithOut = () => {
  return usePermissionStore(store)
}
