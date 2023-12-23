import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const API = `${environment.url}/employee/catalog`
const PUB_API = `${environment.url}/public/catalog`

@Injectable({
  providedIn: 'root'
})
export class EmployeeCatalogService {

  constructor(private http: HttpClient) {}

  save(catalog: any) {
    const {id, ...data} = catalog
    if(id) {
      return this.update(data, id)
    }

    return this.add(data)
  }

  private add(catalog: any) {
    return this.http.post<any>(API, catalog)
  }

  private update(data: any, id: number) {
    return this.http.put<any>(`${API}/${id}`, data)
  }

  search(params: any) {
    return this.http.get<any>(API, {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${PUB_API}/${id}`)
  }

  uploadImages(id: number, imageFiles: FileList) {
    var formData = new FormData

    for(let i = 0; i < imageFiles.length; i++) {
      formData.append('files', imageFiles[i], imageFiles[i].name)
    }

    return this.http.put<any>(`${API}/${id}/photos`, formData)
  }

}
