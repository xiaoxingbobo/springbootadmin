import { Common } from '@/api/common/types'
export type Authority = Common & {
  title?: string | null | undefined
  value?: string | null | undefined
  path?: string | null | undefined
  icon?: string | null | undefined
  parentId?: number | null | undefined
  authorityType?: string | undefined
  component?: string | null | undefined
}

export type AuthorityTree = Authority & {
  children?: AuthorityTree[] | null | undefined
}
