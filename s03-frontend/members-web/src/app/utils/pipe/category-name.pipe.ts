import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../apis/model/sample-data';
import { EmployeeCategoryService } from '../apis/services/employee-category.service';

@Pipe({
  name: 'categoryName',
  standalone: true
})
export class CategoryNamePipe implements PipeTransform {

  categories: Category[] = []

  constructor(employeeCategoryService: EmployeeCategoryService) {
    employeeCategoryService.search().subscribe(resp => this.categories = resp.payload)
  }

  transform(value: number) {
    return this.categories.filter(category => category.id == value).pop()?.name
  }

}
