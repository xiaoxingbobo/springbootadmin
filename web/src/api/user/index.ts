import request from '@/config/axios'
import type { User } from './types'
import type { Page } from '@/api/common/types'

/**
 * 分页查询
 * @param data
 * @returns
 */
export const pagedQueryUser = (data: Partial<Page>): Promise<IResponse> => {
  return request.get({ url: `/user/paged/${data.current}/${data.size}` })
}
/**
 * 分页搜索
 * @param data
 * @returns
 */
export const pagedSearchUser = (data: any): Promise<IResponse> => {
  return request.post({ url: '/user/paged/searches', data })
}
/**
 * 添加
 * @param data
 * @returns
 */
export const addUser = (data: Partial<User>): Promise<IResponse> => {
  return request.post({ url: '/user', data })
}
/**
 * 删除
 * @param id
 * @returns
 */
export const deleteUser = (id: number): Promise<IResponse> => {
  return request.delete({ url: `/user/${id}` })
}
/**
 * 修改
 * @param data
 * @returns
 */
export const putUser = (data: Partial<User>): Promise<IResponse> => {
  return request.put({ url: '/user', data })
}
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getUser = (id: number): Promise<IResponse> => {
  return request.get({ url: `/user/${id}` })
}

/**
 * 查询所有角色信息
 * @returns
 */
export const getRole = (): Promise<IResponse> => {
  return request.get({ url: '/Roles' })
}
