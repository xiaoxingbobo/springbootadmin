import request from '@/config/axios'
import type { TableData, userData } from './types'

export const getTableListApi = (params: any): Promise<IResponse> => {
  return request.get({ url: '/example/list', params })
}

export const saveTableApi = (data: Partial<TableData>): Promise<IResponse> => {
  return request.post({ url: '/example/save', data })
}

// 分页查询
export const PaginationQuery = (data: Partial<TableData>): Promise<IResponse> => {
  return request.get({ url: `/user/paged/${data.current}/${data.size}` })
}

// 添加用户
export const addUser = (data: Partial<userData>): Promise<IResponse> => {
  return request.post({ url: '/user', data })
}
