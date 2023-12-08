import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { CATEGORIES, Category, CategorySearch } from '../../../utils/apis/model/sample-data';
import { CategoryFormComponent } from './category-form/category-form.component';
import { EmployeeCategoryService } from '../../../utils/apis/services/employee-category.service';

@Component({
  selector: 'app-category',
  standalone: true,
  imports: [CommonModule, CategoryFormComponent],
  templateUrl: './category.component.html'
})
export class CategoryComponent implements OnInit {

  categories: any = []
  params: CategorySearch = { name: '' }

  @ViewChild(CategoryFormComponent)
  form!: CategoryFormComponent

  constructor(private employeeCategoryService: EmployeeCategoryService) {}

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.employeeCategoryService.search(this.params)
      .subscribe(resp => {
        console.log(resp)
        this.categories = resp
      })
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
