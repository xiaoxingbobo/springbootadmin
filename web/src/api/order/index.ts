import request from '@/config/axios'
import type { Order } from './types'
import type { Page } from '@/api/common/types'

/**
 * 分页查询
 * @param data
 * @returns
 */
export const pagedQueryOrder = (data: Partial<Page>): Promise<IResponse> => {
  return request.get({ url: `/order/paged/${data.current}/${data.size}` })
}
/**
 * 分页搜索
 * @param data
 * @returns
 */
export const pagedSearchOrder = (data: any): Promise<IResponse> => {
  return request.post({ url: '/order/paged/searches', data })
}
/**
 * 添加
 * @param data
 * @returns
 */
export const addOrder = (data: Partial<Order>): Promise<IResponse> => {
  return request.post({ url: '/order', data })
}
/**
 * 删除
 * @param id
 * @returns
 */
export const deleteOrder = (id: number): Promise<IResponse> => {
  return request.delete({ url: `/order/${id}` })
}
/**
 * 修改
 * @param data
 * @returns
 */
export const putOrder = (data: Partial<Order>): Promise<IResponse> => {
  return request.put({ url: '/order', data })
}
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getOrder = (id: number): Promise<IResponse> => {
  return request.get({ url: `/order/${id}` })
}
