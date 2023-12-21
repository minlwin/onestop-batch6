import { inject } from "@angular/core"
import { EmployeeCatalogService } from "../services/employee-catalog.service"
import { of } from "rxjs"

export function generate(array: Array<any>) {
  if(array.length) {
    let arr = array.sort((a, b) => a.id - b.id)
    return arr[arr.length - 1].id
  }

  return 1
}

export function findCatalogById(id: number) {
  let employeeCatalogService = inject(EmployeeCatalogService)
  let catalogs: any[] = []
  employeeCatalogService.search(undefined).subscribe(resp => catalogs = resp.payload)
  return of(catalogs.filter(catalog => catalog.id == id).pop())
}
