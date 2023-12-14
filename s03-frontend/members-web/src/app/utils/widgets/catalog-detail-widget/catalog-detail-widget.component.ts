import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Catalog } from '../../apis/model/sample-data';
import { CategoryNamePipe } from '../../pipe/category-name.pipe';
import { RouterModule } from '@angular/router';
import { CartService } from '../../apis/services/cart.service';

@Component({
  selector: 'app-catalog-detail-widget',
  standalone: true,
  imports: [CommonModule, CategoryNamePipe, RouterModule],
  templateUrl: './catalog-detail-widget.component.html'
})
export class CatalogDetailWidgetComponent implements OnInit {

  @Input()
  catalog!: Catalog

  isExist = false
  activeCarousel!: string

  constructor(private cartService: CartService) {}

  ngOnInit(): void {
    this.isExist = this.cartService.isExist(this.catalog)

    if(this.images?.length)
      this.activeCarousel = this.images[0]
  }

  get images() {
    return this.catalog?.images as string[]
  }

  addToCart() {
    this.cartService.addItems(this.catalog)
    this.isExist = !this.isExist
  }
}
