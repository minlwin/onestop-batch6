import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { CommonModule } from '@angular/common';
import { EmployeeCatalogService } from '../../../utils/apis/services/employee-catalog.service';
import { EmployeeCategoryService } from '../../../utils/apis/services/employee-category.service';
import { GoldWeightPipe } from '../../../utils/pipe/gold-weight.pipe';
import { PaginationComponent } from '../../../utils/widgets/pagination/pagination.component';
import { ApiImagePipe } from '../../../utils/pipe/api-image.pipe';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CommonModule, RouterModule, NoDataComponent, PaginationComponent, GoldWeightPipe, ApiImagePipe],
  templateUrl: './employee-catalog.component.html'
})
export class EmployeeCatalogComponent implements OnInit {

  sizes = [8, 12, 16]
  params = {
    categoryId: 0,
    name: '',
    createFrom: '',
    priceFrom: 0,
    priceTo: 0,
    soldOut: false,
    page: 0,
    size: 8
  }

  categories: any[] = []
  catalogs: any[] = []

  constructor(private employeeCatalogService: EmployeeCatalogService, private employeeCategoryService: EmployeeCategoryService) {}

  ngOnInit(): void {
    this.employeeCategoryService.search().subscribe(resp => this.categories = resp.payload)
    this.search()
  }

  search() {
    this.employeeCatalogService.search(this.params).subscribe(resp => this.catalogs = resp.payload.content)
  }

  parseBool(value: string) {
    return value === 'true'
  }

}
