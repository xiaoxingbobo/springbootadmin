export type Page = {
  current: number
  size: number
}
export type SearchField = Array<{
  field?: string
  keyword?: string | null
}>
