import type { User } from './types'
import {
  addData,
  putData,
  deleteData,
  batchDeleteData,
  getData,
  getDataList
} from '@/api/common/index'
import { getRoleList } from '@/api/role/index'
import { Page } from '../common/types'

const entity = 'user'
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getUser = (id?: number | string): Promise<IResponse> => {
  return getData(entity, id)
}
export const getUserList = async (payload: Partial<Page<User>>): Promise<IResponse> => {
  return getDataList(entity, payload)
}
/**
 * 增加
 * @param data
 * @returns
 */
export const addUser = async (data: Partial<User>): Promise<IResponse> => {
  return addData(entity, data)
}

/**
 * 修改
 * @param data
 * @returns
 */
export const putUser = async (data: Partial<User>): Promise<IResponse> => {
  return putData(entity, data)
}
/**
 * 删除
 * @param id
 * @returns
 * */
export const deleteUser = async (id?: number | string): Promise<IResponse> => {
  return deleteData(entity, id)
}
/**
 * 批量删除
 * @param ids
 * @returns
 * */
export const batchDeleteUser = async (ids: Array<string | number>): Promise<IResponse> => {
  return batchDeleteData(entity, ids)
}

/**
 * 查询所有角色信息
 * @returns
 */
export const getRoleAll = async (): Promise<IResponse> => {
  return getRoleList({})
}
