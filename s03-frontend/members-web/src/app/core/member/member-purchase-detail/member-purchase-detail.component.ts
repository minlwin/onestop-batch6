import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { SaleIdPipe } from '../../../utils/pipe/sale-id.pipe';

@Component({
  selector: 'app-member-purchase-detail',
  standalone: true,
  imports: [CommonModule, SaleIdPipe],
  templateUrl: './member-purchase-detail.component.html'
})
export class MemberPurchaseDetailComponent implements OnInit {

  detail = {
    id: {
      saleDate: '2023-8-30',
      saleSeq: 2
    },
    issuedAt: '2023-8-30',
    total: 3300000,
    items: [
      {
        images: [],
        purity: 16,
        weight: 210,
        lostWeight: 65,
        baseInfo: {
          id: 1,
          name: 'Woman Hand Chain',
          categoryId: 1,
          categoryName: 'Hand Chain',
          description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate nobis illum aut veritatis, sunt consequatur voluptate incidunt odio necessitatibus reiciendis ab odit quidem consequuntur nesciunt quam ut, beatae facilis amet?',
          price: 3300000,
          coverImage: '',
          createAt: '2022-09-15',
          soldOut: true
        }
      },
      {
        images: [],
        purity: 16,
        weight: 197,
        lostWeight: 68,
        baseInfo: {
          id: 2,
          name: 'Shining Necklace for Lady',
          categoryId: 2,
          categoryName: 'Necklace',
          description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Cupiditate nobis illum aut veritatis, sunt consequatur voluptate incidunt odio necessitatibus reiciendis ab odit quidem consequuntur nesciunt quam ut, beatae facilis amet?',
          price: 4300000,
          coverImage: '',
          createAt: '2023-02-28',
          soldOut: true
        }
      },
      {
        images: [],
        purity: 16,
        weight: 100,
        lostWeight: 20,
        baseInfo: {
          id: 3,
          name: 'Weeding Ring',
          categoryId: 3,
          categoryName: 'Ring',
          description: '',
          price: 2300000,
          coverImage: '',
          createAt: '2023-04-04',
          soldOut: true
        }
      },
    ]
  }

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.data.subscribe(resp => console.log(resp['purchase']))
  }

}
