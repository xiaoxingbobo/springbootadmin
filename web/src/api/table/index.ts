import request from '@/config/axios'
import type { TableData } from './types'

export const getTableListApi = (params: any): Promise<IResponse> => {
  return request.get({ url: '/example/list', params })
}

export const saveTableApi = (data: Partial<TableData>): Promise<IResponse> => {
  return request.post({ url: '/example/save', data })
}

export const getTableDetApi = (id: string): Promise<IResponse<TableData>> => {
  return request.get({ url: '/example/detail', params: { id } })
}

export const delTableListApi = (ids: string[] | number[]): Promise<IResponse> => {
  return request.post({ url: '/example/delete', data: { ids } })
}

// 代码生成接口
// 可生成实体列表
export const Cangenerateentitylist = (ids: string[] | number[]): Promise<IResponse> => {
  return request.get({ url: '/code/classes', data: { ids } })
}

// 实体生成记录
export const Entitygenerationrecord = (ids: string[] | number[]): Promise<IResponse> => {
  return request.get({ url: '/code/list', data: { ids } })
}

// 单个实体生成
export const Singleentitygeneration = (data: any): Promise<IResponse> => {
  return request.post({ url: '/code/generate', data })
}

// 撤销实体生成
export const RevokeEntity = (ids: number[]): Promise<IResponse> => {
  return request.get({ url: `/code/undo/${ids}` })
}

// 添加权限-带详情参数
export const ParameterPermission = (data: string[]): Promise<IResponse> => {
  return request.post({ url: '/code/generate', data })
}
