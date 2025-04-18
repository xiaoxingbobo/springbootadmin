import request from '@/config/axios'
import { Common, Page } from './types'

// 获取所有字典
export const getDictApi = (): Promise<IResponse> => {
  return request.get({ url: '/dict/list' })
}

// 模拟获取某个字典
export const getDictOneApi = async (): Promise<IResponse> => {
  return request.get({ url: '/dict/one' })
}

/**
 * 获取列表
 * @param entity
 * @param data
 * @returns
 */
export const getDataList = async <T extends Common>(
  entity,
  data: Partial<Page<T>>
): Promise<IResponse> => {
  return request.post({ url: `/${entity}/paged`, data: data })
}
/**
 * 获取一条
 * @param entity
 * @param data
 * @returns
 */
export const getData = async (entity, id?: number | string): Promise<IResponse> => {
  return request.get({ url: `/${entity}/${id}` })
}
/**
 * 删除
 * @param entity
 * @param id
 * @returns
 */
export const deleteData = async (entity, id?: string | number): Promise<IResponse> => {
  return request.delete({ url: `/${entity}/${id}` })
}
/**
 * 批量删除
 * @param entity
 * @param id
 * @returns
 */
export const batchDeleteData = async (
  entity,
  ids?: Array<number | string | null | undefined>
): Promise<IResponse> => {
  return request.post({ url: `/${entity}/delete`, data: ids })
}
/**
 * 添加
 * @param entity
 * @param data
 * @returns
 */
export const addData = async <T extends Common>(entity, data: Partial<T>): Promise<IResponse> => {
  return request.post({ url: `/${entity}`, data: data })
}

/**
 * 修改
 * @param entity
 * @param data
 * @returns
 */
export const putData = async <T extends Common>(entity, data: Partial<T>): Promise<IResponse> => {
  return request.put({ url: `/${entity}`, data: data })
}
