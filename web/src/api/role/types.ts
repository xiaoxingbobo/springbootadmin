import { Common } from '@/api/common/types'
export type Role = Common & {
  createTime: Date | null
  id: number | null
  isDeleted: number | null
  name: string | null
  updateTime: Date | null
}
