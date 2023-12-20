import { Injectable } from '@angular/core';
import { Catalog, CatalogSearch, EMPLOYEE_CATALOGS } from '../model/sample-data';
import { generate } from '../model/id-generator';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeCatalogService {

  private _catalogs: Catalog[] = EMPLOYEE_CATALOGS

  constructor() {}

  save(catalog: any) {
    const {id, ...data} = catalog
    if(id) {
      return this.update(data, id)
    }

    return this.add(catalog)
  }

  add(catalog: any) {
    catalog.id = generate(this._catalogs) + 1
    this._catalogs.push(catalog)
    return of(this._catalogs[this._catalogs.length - 1])
  }

  update(data: any, id: number) {
    let index = this._catalogs.findIndex(catalog => catalog.id == id)
    data.id = id
    this._catalogs[index] = data
    return of(this._catalogs[index])
  }

  search(params: CatalogSearch) {
    // if(params.id || params.keyword) {
    //   return of(this._catalogs.filter(catalog => {
    //     let categoryResult = false
    //     let catalogResult = false

    //     if(params.id) {
    //       let result = catalog.categories.filter(category => category.id == params.id).pop()
    //       categoryResult = result ? true : false
    //     }

    //     if(params.keyword)
    //       catalogResult = catalog.name.startsWith(params.keyword) || catalog.purity == + params.keyword || catalog.price ? catalog.price! >= + params.keyword : false
    //     return categoryResult || catalogResult
    //   }))
    // }
    return of(this._catalogs)
  }

  findById(id: number) {
    return of(this._catalogs.filter(catalog => catalog.id == id).pop())
  }

  uploadImages(id: number, imageFiles: FileList) {
    const index = this._catalogs.findIndex(catalog => catalog.id == id)
    this._catalogs[index].images = []
    const length = imageFiles.length > 5 ? 5 : imageFiles.length
    for(let i = 0; i < length; i++) {
      this._catalogs[index].images?.push(URL.createObjectURL(imageFiles.item(i) as Blob))
    }
    return of(this._catalogs[index])
  }

}
