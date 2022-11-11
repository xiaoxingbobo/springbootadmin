import { defineStore } from 'pinia'
import { ref } from 'vue'
import { asyncRouterMap, constantRouterMap } from '@/router'
import { generateRoutesFn1, generateRoutesFn2, flatMultiLevelRoutes } from '@/utils/routerHelper'
import { store } from '../index'
import { cloneDeep } from 'lodash-es'
import { useCache } from '@/hooks/web/useCache'

const { wsCache } = useCache('localStorage')

export interface PermissionState {
  routers: AppRouteRecordRaw[]
  addRouters: AppRouteRecordRaw[]
  isAddRouters: boolean
  menuTabRouters: AppRouteRecordRaw[]
  // _userInfo: any[]
  _roleAuthority: any[]
}

export const usePermissionStore = defineStore({
  id: 'permission',
  state: (): PermissionState => ({
    routers: [],
    addRouters: [],
    isAddRouters: false, // true 页面空白
    menuTabRouters: [],
    // _userInfo: wsCache.get('userInfo'), // 拿到存到本地的用户信息
    _roleAuthority: wsCache.get('roleAuthority') // 拿到存到本地的用户权限列表
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
    generateRoutes(_myasyncRouterMap?: AppCustomRouteRecordRaw[] | string[]): Promise<unknown> {
      return new Promise<void>((resolve) => {
        let routerMap: AppRouteRecordRaw[] = []

        const asyncRouterMap2 = ref([]) // 定义筛选后的路由列表
        // 用全部的路由与用户权限做对比，筛选出应有的路由
        asyncRouterMap.forEach((e1) => {
          // e1是全部路由
          this._roleAuthority.forEach((e2) => {
            // e2是全部权限列表
            // 一级菜单路由，如果权限名与路由name对应
            if (e1.name === e2.authorityValue) {
              // asyncRouterMap2.value.push(e1)
              // 如果该路由有子路由
              if (e1.children) {
                // 遍历子路由
                e1.children.forEach((e3) => {
                  // 子路由
                  // if (e3.name !== e2.authorityValue) {
                  //   e3.meta.hidden = true
                  // }
                  this._roleAuthority.forEach((e4) => {
                    // 如果子路由有权限,让他显示,默认隐藏了的
                    if (e3.name == e4.authorityValue) {
                      e3.meta.hidden = false // 让他不隐藏
                    }
                    // 判断不等，不准确！
                    // if (e3.name !== e4.authorityValue) {
                    //   console.log(e3.name)
                    // }
                  })
                })
              }
              asyncRouterMap2.value.push(e1)
            }
          })
        })
        // routerMap = cloneDeep(asyncRouterMap)
        routerMap = cloneDeep(asyncRouterMap2.value)

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
