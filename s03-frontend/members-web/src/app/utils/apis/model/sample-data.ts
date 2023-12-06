export interface User {
  email: string
  password: string
  role: 'Member' | 'Employee' | 'Owner'
}

export interface CategorySearch {
  name: string
}

export interface Category {
  id: number
  name: string
}

export interface MemberSearch {
  keyword: string
}

export interface Member {
  id: number
  name: string
  email: string
  phone: string
  nrc: string
  gender: 'Male' | 'Female' | 'Other'
  address: string
  remark: string
}

export const CATEGORIES = [
  {
    id: 1,
    name: 'Earring'
  },
  {
    id: 2,
    name: 'Necklace'
  },
  {
    id: 3,
    name: 'Locket'
  },
  {
    id: 4,
    name: 'Hand Chain'
  },
  {
    id: 5,
    name: 'Foot Chain'
  },
  {
    id: 6,
    name: 'Ring'
  },
  {
    id: 7,
    name: 'Brace'
  }
]
