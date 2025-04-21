import type { RoleAuthority } from './types'
import {
  addData,
  putData,
  deleteData,
  batchDeleteData,
  getData,
  getDataList
} from '@/api/common/index'
import { Page } from '@/api/common/types'
import request from '@/config/axios'
//实体名称
const entity = 'roleAuthority'
/**
 * 根据id查询
 * @param id
 * @returns
 */
export const getRoleAuthority = (id?: number | string): Promise<IResponse> => {
  return getData(entity, id)
}
export const getRoleAuthorityList = async (
  payload: Partial<Page<RoleAuthority>>
): Promise<IResponse> => {
  return getDataList(entity, payload)
}

export const getRoleAuthorityByRoleId = async (roleId: number): Promise<IResponse> => {
  return request.post({ url: `/${entity}/listJoin`, data: { roleId } })
}

/**
 * 增加
 * @param data
 * @returns
 */
export const addRoleAuthority = async (data: Partial<RoleAuthority>): Promise<IResponse> => {
  return addData(entity, data)
}

/**
 * 修改
 * @param data
 * @returns
 */
export const putRoleAuthority = async (data: Partial<RoleAuthority>): Promise<IResponse> => {
  return putData(entity, data)
}

/**
 * 批量修改
 * @param data
 * @returns
 */
export const batchRoleAuthority = async (data: Partial<RoleAuthority>[]): Promise<IResponse> => {
  return request.put({ url: `/${entity}/batch`, data: data })
}

/**
 * 删除
 * @param id
 * @returns
 * */
export const deleteRoleAuthority = async (id?: number | string): Promise<IResponse> => {
  return deleteData(entity, id)
}
/**
 * 批量删除
 * @param ids
 * @returns
 * */
export const batchDeleteRoleAuthority = async (ids: Array<string | number>): Promise<IResponse> => {
  return batchDeleteData(entity, ids)
}
