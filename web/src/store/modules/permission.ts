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
    generateRoutes(_myasyncRouterMap?: AppCustomRouteRecordRaw[] | string[]): Promise<unknown> {
      return new Promise<void>((resolve) => {
        let routerMap: AppRouteRecordRaw[] = []
        const asyncRouterMap1 = asyncRouterMap // 复制的全部路由
        const asyncRouterMap2 = ref([]) // 定义筛选后的路由列表

        // 让子路由显示的函数，isAll为当要显示所有组路由时候传true
        const _modifychildren = (fun, isAll?) => {
          if (isAll) {
            // 传入isAll这个参数，代表显示所有子路由
            fun.forEach((echildren) => {
              echildren.meta.hidden = false // 显示当前子路由
              // 如果有子路由
              if (echildren.children) {
                echildren.children.forEach((echildren2) => {
                  echildren2.meta.hidden = false
                  console.log('修改了子路由')
                  // _modifychildren(echildren)
                  _modifychildren(echildren.children)
                })
              }
            })
          } else {
            // 否则只显示有权限的子路由
            // 遍历当前路由
            fun.forEach((echildren) => {
              // 遍历权限列表
              this._roleAuthority.forEach((enoAll) => {
                if (echildren.name === enoAll.authorityValue) {
                  // 有权限
                  echildren.meta.hidden = false // 显示当前子路由
                  if (echildren.children) {
                    // 如果有子路由
                    _modifychildren(echildren) // 递归
                  }
                }
              })
            })
          }
        }

        // 用全部的路由与用户权限做对比，筛选出应有的路由
        // 遍历全部路由
        asyncRouterMap1.forEach((e1) => {
          // e1是全部路由
          this._roleAuthority.forEach((e2) => {
            // e2是全部权限列表
            // 一级菜单路由，如果权限名与路由name对应
            if (e1.name === e2.authorityValue) {
              // asyncRouterMap2.value.push(e1)
              // 如果该路由有子路由
              if (e1.children) {
                _modifychildren(e1.children)
              }
              asyncRouterMap2.value.push(e1)
            }
          })
        })
        // 判断是否有全部权限(超级管理)
        console.log(wsCache.get('userInfo').roleId)
        const isAllSys = ref(false) // true 为有全部权限
        if (wsCache.get('userInfo').roleId === 1) {
          // 角色id为1，就有所有权限
          isAllSys.value = true // 让是否有全部权限为true
        } else {
          // console.log('有权限')
          isAllSys.value = true // 让是否有全部权限为true
        }

        if (isAllSys.value) {
          // 有全部权限
          _modifychildren(asyncRouterMap, true) // 调用函数让子路由显示
          routerMap = cloneDeep(asyncRouterMap) // 直接给全部路由
        } else {
          routerMap = cloneDeep(asyncRouterMap2.value)
        }
        // console.log(this._roleAuthority) // 当前角色权限列表
        // routerMap = cloneDeep(asyncRouterMap)
        // routerMap = cloneDeep(asyncRouterMap2.value)

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
