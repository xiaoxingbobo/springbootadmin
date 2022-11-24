import { Common } from '@/api/common/types'
export type Order = Common & {
  desc?: string | null
  as?: string | null
}
