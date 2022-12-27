import { Common } from '@/api/common/types'
export type User = Common & {
  age?: number | null
  id?: number | null
  name?: string | null
  nickname?: string | null
  password?: string | null
  roleId?: number | 0
  sex?: number | 0
  username?: string | null
  email?: string | null
}
