import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CartService } from '../../../../../utils/apis/services/cart.service';
import { Router, RouterModule } from '@angular/router';
import { EmployeeSaleService } from '../../../../../utils/apis/services/employee-sale.service';

@Component({
  selector: 'app-sale-checkout',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sale-checkout.component.html'
})
export class SaleCheckoutComponent implements OnInit {

  catalogs: any[] = []
  subtotal = 0
  discount = 0
  total = 0

  constructor(private cartService: CartService,
    private employeeSaleService: EmployeeSaleService,
    private router: Router) {}

  ngOnInit(): void {
    this.catalogs = this.cartService.items
    if(this.catalogs.length > 0) {
      this.subtotal = this.catalogs.map(i => i.salePrice).reduce((a, b) => a + b)
      this.discount = this.cartService.sale.discount
      this.total = this.subtotal + this.discount
    }
  }

  createSale() {
    let result = this.cartService.sale
    result.items = this.cartService.items.map(i => i.id)

    this.employeeSaleService.save(result).subscribe(resp => {
      if(resp) {
        this.cartService.clearCart()
        const id = resp.payload.id
        this.router.navigate(['/employee', 'sale', 'detail'], {queryParams: {id: id}})

      }
    })
  }
}
