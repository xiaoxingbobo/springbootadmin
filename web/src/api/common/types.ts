export type Page = {
  current: number
  size: number
}
export type CommonEntity = {
  id?: number | null
  createTime?: string | null
  updateTime?: string | null
}
export type SearchField = Array<{
  field?: string
  keyword?: string | null
}>
