import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const API = `${environment.url}/employee/category`

@Injectable({
  providedIn: 'root'
})
export class EmployeeCategoryService {

  constructor(private http: HttpClient) {}

  save(category: any) {
    const {id, ...form} = category
    if(id == 0)
      return this.add(form)
    return this.update(id, form)
  }

  private add(category: any) {
    return this.http.post<any>(API, category)
  }

  private update(id: number, category: any) {
    return this.http.put<any>(`${API}/${id}`, category)
  }

  search() {
    return this.http.get<any>(API)
  }
}
