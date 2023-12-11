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
  categories: Category[]
  weight: number
  purity: number
  undercount: number
  size: number
  stock: number
  price?: number
  coverImage?: string
  images?: string[]
  remark?: string
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
  profileImage?: string
  remark?: string
}

export const EMPLOYEE_CATALOGS: Catalog[] = [
  {
    id: 1,
    name: 'Diamond Earring',
    categories: [
      {
        id: 1,
        name: 'Earring'
      },
      {
        id: 8,
        name: 'Gem'
      }
    ],
    weight: 54,
    purity: 16,
    undercount: 10,
    size: 10,
    stock: 2,
    price: 400000,
    coverImage: '/assets/diamond-earring/cover.jpg',
    images: [
      '/assets/diamond-earring/1.jpg',
      '/assets/diamond-earring/2.jpg',
      '/assets/diamond-earring/3.jpg',
      '/assets/diamond-earring/4.jpg'
    ],
    remark: '1 Carat Diamond Studs, Dainty Stud Earrings, Delicate Wedding Anniversary Round Beauty Stud Earrings Prongs Setting'
  },
  {
    id: 2,
    name: '18k Gold Necklace with Heart Shape',
    categories: [
      {
        id: 2,
        name: 'Necklace'
      },
      {
        id: 3,
        name: 'Locket'
      }
    ],
    weight: 250,
    purity: 16,
    undercount: 78,
    size: 86,
    stock: 1,
    price: 1800000,
    coverImage: '/assets/gold-necklace/cover.jpg',
    images: [
      '/assets/gold-necklace/1.jpg',
      '/assets/gold-necklace/2.jpg',
      '/assets/gold-necklace/3.jpg',
      '/assets/gold-necklace/4.jpg'
    ],
    remark: 'Custom Name Necklace, 18K Gold Plated Name Necklace, Personalized Name Necklace, Birthday Gift for Her, 2023 Christmas Gift, Gift for Mom'
  }
]

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
  },
  {
    id: 8,
    name: 'Gem'
  }
]
