import request from '@/config/axios'
import type { TableData, userData, RoleAuthorityspaged } from './types'

// 分页查询
export const PaginationQuery = (data: Partial<TableData>): Promise<IResponse> => {
  return request.get({ url: `/Roles/paged/${data.current}/${data.size}` })
}

// 添加用户
export const addUser = (data: Partial<userData>): Promise<IResponse> => {
  return request.post({ url: '/Roles', data })
}

// 根据id删除用户
export const byIddeleteUser = (id: number): Promise<IResponse> => {
  return request.delete({ url: `/Roles/${id}` })
}
// 修改用户Roles
export const putUser = (data: Partial<userData>): Promise<IResponse> => {
  return request.put({ url: '/Roles', data })
}
// 根据id查询一个用户
export const byIdgetUser = (id: number): Promise<IResponse> => {
  return request.get({ url: `/Roles/${id}` })
}

// 查询所有角色信息
export const getRole = () => {
  return request.get({ url: '/Roles' })
}
// 查询所有权限
export const getAuthority = () => {
  return request.get({ url: '/authority' })
}

// 批量添加权限给角色，添加到RoleAuthoritys
export const batchRoleAuthoritys = (data: any): Promise<IResponse> => {
  return request.post({ url: '/RoleAuthoritys/batch', data })
}

// 分页筛选角色权限表
export const RoleAuthoritysPaged = (data: RoleAuthorityspaged): Promise<IResponse> => {
  return request.post({ url: '/RoleAuthoritys/paged', data })
}
