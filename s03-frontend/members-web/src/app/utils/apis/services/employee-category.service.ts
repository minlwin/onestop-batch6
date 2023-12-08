import { Injectable } from '@angular/core';
import { CATEGORIES, Category } from '../model/sample-data';
import { of } from 'rxjs';
import { generate } from '../model/id-generator';

@Injectable({
  providedIn: 'root'
})
export class EmployeeCategoryService {

  private _categories: Category[] = []

  constructor() {
    this._categories = CATEGORIES
  }

  save(category: any) {
    if(category.id == 0)
      return this.add(category)
    return this.update(category)
  }

  add(category: Category) {
    let id = generate(this._categories) + 1
    category.id = id
    this._categories.push(category)
    return of(this._categories[this._categories.length - 1])
  }

  update(category: Category) {
    let index = this._categories.findIndex(cat => cat.id == category.id)
    this._categories[index] = category
    return of(this._categories[index])
  }

  search(params: any) {
    if(params.name)
      return of(this._categories.filter(cat => cat.name.startsWith(params.name)))
    return of(this._categories)
  }
}
