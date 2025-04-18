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
export const getAuthorityList = async (payload: Partial<Page<Authority>>): Promise<IResponse> => {
  return getDataList(entity, payload)
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
