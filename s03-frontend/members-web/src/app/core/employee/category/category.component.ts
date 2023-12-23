import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Category, CategorySearch } from '../../../utils/apis/model/sample-data';
import { EmployeeCategoryService } from '../../../utils/apis/services/employee-category.service';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { CategoryFormComponent } from './category-form/category-form.component';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, CategoryFormComponent, NoDataComponent],
  templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit {

  categories: any = []

  @ViewChild(CategoryFormComponent)
  form!: CategoryFormComponent

  constructor(private employeeCategoryService: EmployeeCategoryService) {}

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.employeeCategoryService.search()
      .subscribe(resp => this.categories = resp.payload)
  }

  openCategoryForm() {
    this.form.openForm(undefined)
  }

  saveCategory(category: any) {
    this.employeeCategoryService.save(category).subscribe(resp => {
      if(resp) {
        this.search()
        this.form.hideForm()
      }
    })
  }

  editCategory(category: Category) {
    this.form.openForm(category)
  }

}
