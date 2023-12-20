import { CommonModule } from '@angular/common';
import { Component, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-catalog-detail-images',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalog-detail-images.component.html'
})
export class CatalogDetailImagesComponent implements OnInit, OnChanges {

  @Input()
  images: any[] = []

  activeCarousel!: string

  ngOnInit(): void {
    this.setActiveCarousel()
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.setActiveCarousel()
  }

  private setActiveCarousel() {
    if(this.images?.length)
      this.activeCarousel = this.images[0]
  }

}
