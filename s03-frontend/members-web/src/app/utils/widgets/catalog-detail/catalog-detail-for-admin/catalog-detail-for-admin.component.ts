import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { CatalogDetailImagesComponent } from '../catalog-detail-images/catalog-detail-images.component';
import { CatalogDetailInfoComponent } from '../catalog-detail-info/catalog-detail-info.component';
import { RouterModule } from '@angular/router';
import { EmployeeCatalogService } from '../../../apis/services/employee-catalog.service';

@Component({
  selector: 'app-catalog-detail-for-admin',
  standalone: true,
  imports: [CommonModule, CatalogDetailImagesComponent, CatalogDetailInfoComponent, RouterModule],
  templateUrl: './catalog-detail-for-admin.component.html'
})
export class CatalogDetailForAdminComponent implements OnInit, OnDestroy {

  uploadDialog: any

  @Input()
  catalog: any

  @Input()
  activeUser: any

  images: any[] = []
  imageFiles!: FileList

  uploadStatus = false

  constructor(private employeeCatalogService: EmployeeCatalogService) {}

  ngOnInit(): void {
    this.loadImages()
  }

  loadImages() {
    this.images = this.catalog.images as string[]
  }

  showImage(event: any) {
    let fileList: FileList = event.target.files
    this.imageFiles = fileList
    const length = fileList.length > 5 ? 5 : fileList.length

    for(let i = 0; i < length; i++) {
      this.images.push(URL.createObjectURL(event.target.files[i]))
    }
    this.uploadStatus = !this.uploadStatus

  }

  uploadImages() {
    this.employeeCatalogService.uploadImages(this.catalog.id, this.imageFiles).subscribe(resp => {
      if(resp) {
        this.uploadStatus = !this.uploadStatus
        this.catalog = resp
        this.loadImages()
      }
    })
  }

  ngOnDestroy(): void {
    if(this.images.length && this.uploadStatus) {
      this.images = []
      this.catalog.images = []
    }
  }

}
