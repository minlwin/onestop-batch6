import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-catalog-detail-images',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalog-detail-images.component.html'
})
export class CatalogDetailImagesComponent implements OnInit {

  @Input()
  images: any[] = []

  activeCarousel!: string

  ngOnInit(): void {
    if(this.images?.length)
      this.activeCarousel = this.images[0]
  }

}
