export interface ApiResponse<T> {
  status: string
  playload: T
}

export interface PagerResponse {
  content: any[]
  pager: Pager
}

interface Pager {
  totalPages: number
  totalElements: number
  size: number
  currentPage: number
}
