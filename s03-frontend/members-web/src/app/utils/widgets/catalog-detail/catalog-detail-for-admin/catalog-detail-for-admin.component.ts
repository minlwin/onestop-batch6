import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { CatalogDetailImagesComponent } from '../catalog-detail-images/catalog-detail-images.component';
import { CatalogDetailInfoComponent } from '../catalog-detail-info/catalog-detail-info.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-catalog-detail-for-admin',
  standalone: true,
  imports: [CommonModule, CatalogDetailImagesComponent, CatalogDetailInfoComponent, RouterModule],
  templateUrl: './catalog-detail-for-admin.component.html'
})
export class CatalogDetailForAdminComponent implements OnInit {

  @Input()
  catalog: any

  @Input()
  activeUser: any

  images: any[] = []

  ngOnInit(): void {
    this.images = this.catalog.images as string[]
  }

  showImage(files: FileList) {
    var fr = FileReader
    this.images.push(files)
    // for(let index = 0; index < files.length; index++) {
    //   console.log(files.item(index))
    //   this.images.push(files.item(index))
    // }
  }
}
