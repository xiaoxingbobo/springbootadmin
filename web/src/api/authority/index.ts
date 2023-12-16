import request from '@/config/axios'

// 全部权限列表
export const PermissionList = (): Promise<IResponse> => {
  return request.post({ url: '/authority/paged', data: { current: 1, size: 1000 } })
}

// 添加权限
export const addPermission = (data: string[]): Promise<IResponse> => {
  return request.post({ url: '/authority', data })
}

// 删除权限
export const DeletePermissions = (id: number[]): Promise<IResponse> => {
  return request.delete({ url: `/authority/${id}` })
}

// 编辑权限
export const EditPermissions = (data: any[]): Promise<IResponse> => {
  return request.put({ url: '/authority', data })
}

// 查询一条
export const GetPermissionById = (id: string[]): Promise<IResponse> => {
  return request.get({ url: `/authority/${id}` })
}

// 菜单列表
export const menuList = (): Promise<IResponse> => {
  return request.get({ url: '/authority/menu' })
}
