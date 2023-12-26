import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, ViewChild } from '@angular/core';
import { CatalogDetailImagesComponent } from '../catalog-detail-images/catalog-detail-images.component';
import { CatalogDetailInfoComponent } from '../catalog-detail-info/catalog-detail-info.component';
import { RouterModule } from '@angular/router';
import { EmployeeCatalogService } from '../../../apis/services/employee-catalog.service';
import { CatalogDetailInfoForOnlyOwnerComponent } from '../catalog-detail-info-for-only-owner/catalog-detail-info-for-only-owner.component';
import { SuccessDialogComponent } from '../../success-dialog/success-dialog.component';

@Component({
  selector: 'app-catalog-detail-for-admin',
  standalone: true,
  imports: [CommonModule, CatalogDetailImagesComponent, CatalogDetailInfoComponent, CatalogDetailInfoForOnlyOwnerComponent, SuccessDialogComponent, RouterModule],
  templateUrl: './catalog-detail-for-admin.component.html'
})
export class CatalogDetailForAdminComponent implements OnDestroy {

  @ViewChild(SuccessDialogComponent)
  successDialog!: SuccessDialogComponent

  @Input()
  catalog: any

  @Input()
  activeUser: any

  @Input()
  images: any[] = []

  imageFiles!: FileList

  uploadStatus = false

  constructor(private employeeCatalogService: EmployeeCatalogService) {}

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
        this.employeeCatalogService.findById(this.catalog.id).subscribe(resp => {
          this.images = resp.payload.images
        })
        this.uploadStatus = !this.uploadStatus
        this.successDialog.openDialog()
      }
    })
  }

  ngOnDestroy(): void {
    if(this.images.length && this.uploadStatus) {
      this.images = []
    }
  }

}
