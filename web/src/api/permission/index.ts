import request from '@/config/axios'
import type { TableData } from './types'

export const saveTableApi = (data: Partial<TableData>): Promise<IResponse> => {
  return request.post({ url: '/example/save', data })
}

// 权限列表
export const PermissionList = (): Promise<IResponse> => {
  return request.get({ url: '/authority' })
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
export const EditPermissions = (data: string[]): Promise<IResponse> => {
  return request.put({ url: '/authority', data })
}
