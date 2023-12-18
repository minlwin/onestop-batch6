import { Component, Input, OnInit } from '@angular/core';
import { Catalog } from '../../../apis/model/sample-data';
import { CommonModule } from '@angular/common';
import { CategoryNamePipe } from '../../../pipe/category-name.pipe';
import { CatalogDetailImagesComponent } from '../catalog-detail-images/catalog-detail-images.component';
import { CatalogDetailInfoComponent } from '../catalog-detail-info/catalog-detail-info.component';

@Component({
  selector: 'app-catalog-detail-for-member',
  standalone: true,
  imports: [CommonModule, CategoryNamePipe, CatalogDetailImagesComponent, CatalogDetailInfoComponent],
  templateUrl: './catalog-detail-for-member.component.html'
})
export class CatalogDetailForMemberComponent implements OnInit {

  @Input()
  catalog!: Catalog

  @Input()
  activeUser!: string

  images: any[] = []

  ngOnInit(): void {
    this.images = this.catalog?.images as string[]
  }

}
