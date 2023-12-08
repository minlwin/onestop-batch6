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

export interface CatalogSearch {
  id: number
  keyword: string
}

export interface Catalog {
  id: number
  name: string
  category: Category
  weight: number
  purity: number
  undercount: number
  coverImage: string
  images: string[]
  remark: string
}

export interface MemberSearch {
  keyword: string
}

export interface Member {
  id?: number
  name: string
  email: string
  phone: string
  nrc: string
  gender: 'Male' | 'Female' | 'Other'
  address: string
  remark?: string
}

export const EMPLOYEE_MEMBERS: Member[] = [
  {
    id: 1,
    name: 'U Min Khant',
    email: 'uminkhanthu@gmail.com',
    phone: '09256257252',
    nrc: '9/PaThaKa(N)100200',
    gender: 'Male',
    address: '28(B), Yadanar Myaing Rd, Kamayut',
    remark: 'FA Man'
  },
  {
    id: 2,
    name: 'Theint Theint Thu',
    email: 'ttt@gmail.com',
    phone: '09100020002',
    nrc: '8/PaKhaKa(N)300400',
    gender: 'Female',
    address: '20, Yadanar Myaing Rd, Kamayut'
  }
]

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
