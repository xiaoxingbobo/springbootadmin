import request from '@/config/axios'
import { Authority } from './types'
import { Page } from '../common/types'
import {
  addData,
  putData,
  deleteData,
  batchDeleteData,
  getData,
  getDataList
} from '@/api/common/index'
//实体名称
const entity = 'authority'
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getAuthority = (id?: number | string): Promise<IResponse> => {
  return getData(entity, id)
}

/**
 * 获取权限列表
 * @param payload
 * @returns
 */
export const getAuthorityList = async (payload: Partial<Page<Authority>>): Promise<IResponse> => {
  return getDataList(entity, payload)
}

/**
 * 获取所有权限
 * @returns
 */
export const getAllAuthority = async (): Promise<IResponse> => {
  return getDataList(entity, { current: 1, size: 10000 })
}

/**
 * 增加
 * @param data
 * @returns
 */
export const addAuthority = async (data: Partial<Authority>): Promise<IResponse> => {
  return addData(entity, data)
}

/**
 * 修改
 * @param data
 * @returns
 */
export const putAuthority = async (data: Partial<Authority>): Promise<IResponse> => {
  return putData(entity, data)
}
/**
 * 删除
 * @param id
 * @returns
 * */
export const deleteAuthority = async (id?: number | string): Promise<IResponse> => {
  return deleteData(entity, id)
}
/**
 * 批量删除
 * @param ids
 * @returns
 * */
export const batchDeleteAuthority = async (ids: Array<string | number>): Promise<IResponse> => {
  return batchDeleteData(entity, ids)
}
/**
 * 获取当前登录用户权限列表
 * @returns
 */
export const menuList = (): Promise<IResponse> => {
  return request.get({ url: '/authority/menu' })
}
