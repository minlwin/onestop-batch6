import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { CartService } from '../../../apis/services/cart.service';
import { GoldWeightPipe } from '../../../pipe/gold-weight.pipe';

@Component({
  selector: 'app-catalog-detail-info',
  standalone: true,
  imports: [CommonModule, GoldWeightPipe],
  templateUrl: './catalog-detail-info.component.html'
})
export class CatalogDetailInfoComponent implements OnInit {

  @Input()
  catalog: any

  @Input()
  activeUser: any

  constructor(private cartService: CartService) {}

  isExist = false

  ngOnInit() {
    this.isExist = this.cartService.isExist(this.catalog)
  }

  addToCart() {
    this.cartService.addItemOrRemove(this.catalog)
    this.isExist = this.cartService.isExist(this.catalog)
  }

}
