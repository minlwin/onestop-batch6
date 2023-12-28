import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CartService } from '../../../../../utils/apis/services/cart.service';

@Component({
  selector: 'app-sale-discount',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sale-discount.component.html'
})
export class SaleDiscountComponent implements OnInit {

  discount = 0

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    if(this.cartService.sale.discount)
      this.discount = this.cartService.sale.discount
  }

  saveDiscount() {
    if(this.discount > 0)
      this.cartService.sale.discount = this.discount
  }

}
