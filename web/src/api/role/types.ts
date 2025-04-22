import { Common } from '@/api/common/types'
export type Role = Common & {
  createTime: Date | null
  id: number | null
  isDeleted: number | null
  name: string | null
  updateTime: Date | null
}

export type Authority = {
  id: number | null
  name: string | null
  parentId: number | null
  path: string | null
  type: number | null
}
export type RoleAuthority = {
  role: Role
  authority: Authority
}
