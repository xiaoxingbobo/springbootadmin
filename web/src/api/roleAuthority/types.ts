import { Common } from '@/api/common/types'
export type RoleAuthority = Common & {
  authorityId: number | string
  roleId: number | string
}
