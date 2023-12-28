import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CartService } from '../../../../../utils/apis/services/cart.service';
import { EmployeeCatalogService } from '../../../../../utils/apis/services/employee-catalog.service';
import { GoldWeightPipe } from '../../../../../utils/pipe/gold-weight.pipe';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-sale-items',
  standalone: true,
  imports: [CommonModule, RouterModule, GoldWeightPipe],
  templateUrl: './sale-items.component.html'
})
export class SaleItemsComponent implements OnInit {

  name = ''
  catalogs: any[] = []

  constructor(private cartService: CartService,
    private employeeCatalogService: EmployeeCatalogService) {}

  ngOnInit(): void {
    this.searchCatalog()
  }

  searchCatalog() {
    this.employeeCatalogService.search({categoryId: 0, name: this.name, createFrom: '', priceForm: 0, priceTo: 0, soldOut: false}).subscribe(resp => {
      this.catalogs = resp.payload.content
    })
  }

  get cartItems() {
    return this.cartService.sale.items
  }

  addToCart(item: any) {
    this.cartService.addItemOrRemove(item)
  }

  isExist(catalog: any) {
    return this.cartService.isExist(catalog)
  }

}
