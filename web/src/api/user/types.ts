import { Common } from '@/api/common/types'
export type User = Common & {
  age?: number | null | undefined
  id?: number | null | undefined
  name?: string | null | undefined
  nickname?: string | null | undefined
  password?: string | null | undefined
  roleId?: number | 0 | undefined
  sex?: number | 0 | undefined
  username?: string | null | undefined
  email?: string | null | undefined
  updateTime?: Date | null | undefined
  createTime?: Date | null | undefined
}
