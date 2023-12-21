import { Component, OnInit } from '@angular/core';
import { CatalogSearch } from '../../../utils/apis/model/sample-data';
import { RouterModule } from '@angular/router';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { CommonModule } from '@angular/common';
import { EmployeeCatalogService } from '../../../utils/apis/services/employee-catalog.service';
import { EmployeeCategoryService } from '../../../utils/apis/services/employee-category.service';
import { CategoryNamePipe } from '../../../utils/pipe/category-name.pipe';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [CommonModule, RouterModule, NoDataComponent, CategoryNamePipe],
  templateUrl: './employee-catalog.component.html'
})
export class EmployeeCatalogComponent implements OnInit {

  params = {
    id: 0,
    name: '',
    createFrom: '',
    priceFrom: 0,
    priceTo: 0,
    page: 0,
    size: 10
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

}
