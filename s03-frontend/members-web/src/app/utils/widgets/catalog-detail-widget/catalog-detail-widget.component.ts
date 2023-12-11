import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Catalog } from '../../apis/model/sample-data';

@Component({
  selector: 'app-catalog-detail-widget',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './catalog-detail-widget.component.html'
})
export class CatalogDetailWidgetComponent implements OnInit {

  @Input()
  catalog!: Catalog

  activeCarousel!: string

  ngOnInit(): void {
    if(this.images?.length)
      this.activeCarousel = this.images[0]
  }

  get images() {
    return this.catalog?.images as string[]
  }

}
