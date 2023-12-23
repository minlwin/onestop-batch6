export interface ApiResponse<T> {
  status: string
  playload: T
}

export interface PageResult {
  list:any[]
  pager:Pager
}

export interface Pager {
  current: number
  size: number
  totalCount: number
  totalPage:number
}
