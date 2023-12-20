export interface User {
  username: string
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
  name: string
  createFrom: string
  priceFrom: number
  priceTo: number
}

export interface Catalog {
  id: number
  name: string
  categoryId: number
  weight: number
  purity: number
  lostWeight: number
  goldSmithFees: number
  basedPrice: number
  price?: number
  coverImage?: string
  images?: string[]
  soldOut?: boolean
  description?: string
}

export interface MemberSearch {
  keyword: string
}

export interface Member {
  id?: number
  name: string
  email: string
  phone: string
  dob: string
  gender: 'Male' | 'Female'
  township: string
  address: string
  registrationDate: string
  profileImage?: string
}

export interface MemberDto {
  profile: Member
  purchases: Purchase[]
}

export interface Purchase {
  id: {
    saleDate: string
    saleSeq: number
  }
  issuedAt: string
  itemCount: number
  total: number
}

export interface PurchaseSearch {
  dateFrom: string
  dateTo: string
}

export const EMPLOYEE_CATALOGS: Catalog[] = [
  {
    id: 1,
    name: 'Diamond Earring',
    categoryId: 1,
    weight: 54,
    purity: 16,
    lostWeight: 10,
    goldSmithFees: 50000,
    basedPrice: 350000,
    price: 400000,
    soldOut: false,
    coverImage: '/assets/diamond-earring/cover.jpg',
    images: [
      '/assets/diamond-earring/1.jpg',
      '/assets/diamond-earring/2.jpg',
      '/assets/diamond-earring/3.jpg',
      '/assets/diamond-earring/4.jpg'
    ],
    description: '1 Carat Diamond Studs, Dainty Stud Earrings, Delicate Wedding Anniversary Round Beauty Stud Earrings Prongs Setting'
  },
  {
    id: 2,
    name: '18k Gold Necklace with Heart Shape',
    categoryId: 2,
    weight: 250,
    purity: 16,
    lostWeight: 78,
    goldSmithFees: 43000,
    basedPrice: 1500000,
    price: 1800000,
    soldOut: false,
    coverImage: '/assets/gold-necklace/cover.jpg',
    images: [
      '/assets/gold-necklace/1.jpg',
      '/assets/gold-necklace/2.jpg',
      '/assets/gold-necklace/3.jpg',
      '/assets/gold-necklace/4.jpg'
    ],
    description: 'Custom Name Necklace, 18K Gold Plated Name Necklace, Personalized Name Necklace, Birthday Gift for Her, 2023 Christmas Gift, Gift for Mom'
  },
  {
    id: 3,
    name: 'Wedding Ring',
    categoryId: 6,
    weight: 210,
    purity: 16,
    lostWeight: 35,
    goldSmithFees: 80000,
    basedPrice: 3100000,
    price: 3500000,
    soldOut: false,
    coverImage: '/assets/gold-necklace/cover.jpg',
    images: [],
    description: 'Custom Name Necklace, 18K Gold Plated Name Necklace, Personalized Name Necklace, Birthday Gift for Her, 2023 Christmas Gift, Gift for Mom'
  }
]

export const EMPLOYEE_MEMBERS: Member[] = [
  {
    id: 1,
    name: 'U Min Khant',
    email: 'uminkhanthu@gmail.com',
    phone: '09256257252',
    dob: '1986-01-06',
    gender: 'Male',
    address: '28(B), Yadanar Myaing Rd, Kamayut',
    township: 'Ayeyarwady Region',
    registrationDate: '2023-01-20'
  },
  {
    id: 2,
    name: 'Theint Theint Thu',
    email: 'ttt@gmail.com',
    phone: '09100020002',
    dob: '2000-02-10',
    gender: 'Female',
    address: '20, Yadanar Myaing Rd, Kamayut',
    township: 'Bago Region',
    registrationDate: '2023-04-22'
  }
]

export const PURCHASE_DATA: Purchase[] = [
  {
    id: {
      saleDate: '2023-6-10',
      saleSeq: 1
    },
    issuedAt: '2023-6-10',
    itemCount: 2,
    total: 2500000
  },
  {
    id: {
      saleDate: '2023-8-30',
      saleSeq: 2
    },
    issuedAt: '2023-8-30',
    itemCount: 3,
    total: 3300000
  },
  {
    id: {
      saleDate: '2023-7-04',
      saleSeq: 1
    },
    issuedAt: '2023-7-04',
    itemCount: 1,
    total: 4200000
  }
]

export const EMPLOYEE_MEMBER_DTO: MemberDto[] = [
  {
    profile: {
      id: 1,
      name: 'U Min Khant',
      email: 'uminkhanthu@gmail.com',
      phone: '09256257252',
      dob: '1986-01-06',
      gender: 'Male',
      address: '28(B), Yadanar Myaing Rd, Kamayut',
      township: 'Ayeyarwady Region',
      registrationDate: '2023-01-20'
    },
    purchases: [
      {
        id: {
          saleDate: '2023-6-10',
          saleSeq: 1
        },
        issuedAt: '2023-6-10',
        itemCount: 2,
        total: 2500000
      },
      {
        id: {
          saleDate: '2023-8-30',
          saleSeq: 2
        },
        issuedAt: '2023-8-30',
        itemCount: 3,
        total: 3300000
      }
    ]
  },
  {
    profile: {
      id: 2,
      name: 'Theint Theint Thu',
      email: 'ttt@gmail.com',
      phone: '09100020002',
      dob: '2000-02-10',
      gender: 'Female',
      address: '20, Yadanar Myaing Rd, Kamayut',
      township: 'Bago Region',
      registrationDate: '2023-04-22'
    },
    purchases: [{
      id: {
        saleDate: '2023-7-04',
        saleSeq: 1
      },
      issuedAt: '2023-7-04',
      itemCount: 1,
      total: 4200000
    }]
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

export const STATES_DATA = [
	{
		id: 1,
		name: "Ayeyarwady Region",
	},
	{
		id: 2,
		name: "Bago Region",
	},
	{
		id: 3,
		name: "Chin State",
	},
	{
		id: 4,
		name: "Kachin State",
	},
	{
		id: 5,
		name: "Kayah State",
	},
	{
		id: 6,
		name: "Kayin State",
	},
	{
		id: 7,
		name: "Magway Region",
	},
	{
		id: 8,
		name: "Mandalay Region",
	},
	{
		id: 9,
		name: "Mon State",
	},
	{
		id: 10,
		name: "Naypyidaw Union",
	},
	{
		id: 11,
		name: "Rakhine State",
	},
	{
		id: 12,
		name: "Sagaing Region",
	},
	{
		id: 13,
		name: "Shan State",
	},
	{
		id: 14,
		name: "Tanintharyi Region",
	},
	{
		id: 15,
		name: "Yangon Region",
	},
]

export const USERS_DATA: User[] = [
  {
    username: 'member',
    password: 'member',
    role: 'Member'
  },
  {
    username: 'employee',
    password: 'employee',
    role: 'Employee'
  },
  {
    username: 'owner',
    password: 'owner',
    role: 'Owner'
  }
]
