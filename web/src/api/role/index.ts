import type { Role } from './types'
import {
  addData,
  putData,
  deleteData,
  batchDeleteData,
  getData,
  getDataList
} from '@/api/common/index'
import { Page } from '../common/types'

//实体名称
const entity = 'role'
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getRole = (id?: number | string): Promise<IResponse> => {
  return getData(entity, id)
}
export const getRoleList = async (payload: Partial<Page<Role>>): Promise<IResponse> => {
  return getDataList(entity, payload)
}
/**
 * 增加
 * @param data
 * @returns
 */
export const addRole = async (data: Partial<Role>): Promise<IResponse> => {
  return addData(entity, data)
}

/**
 * 修改
 * @param data
 * @returns
 */
export const putRole = async (data: Partial<Role>): Promise<IResponse> => {
  return putData(entity, data)
}
/**
 * 删除
 * @param id
 * @returns
 * */
export const deleteRole = async (id?: number | string): Promise<IResponse> => {
  return deleteData(entity, id)
}
/**
 * 批量删除
 * @param ids
 * @returns
 * */
export const batchDeleteRole = async (ids: Array<string | number>): Promise<IResponse> => {
  return batchDeleteData(entity, ids)
}

// /**
//  * 查询所有角色信息
//  * @returns
//  */
// export const getRole = (): Promise<IResponse> => {
//   return request.get({ url: '/Roles' })
// }
