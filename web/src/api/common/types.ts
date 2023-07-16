export type SearchType = {
  field?: string
  keyword?: string
}
export type Common = {
  id: number | string
  createTime?: string | null
  updateTime?: string | null
}
export type Page<T extends Common> = {
  current: number
  size: number
  condition?: T | null
  searches?: Array<SearchType> | null
}
