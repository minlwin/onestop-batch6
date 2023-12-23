import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';

const API = `${environment.url}/owner/employee`

@Injectable({
  providedIn: 'root'
})
export class OwnerEmployeeService {

  constructor(private http: HttpClient) {}

  save(form: any) {
    const {id, ...data} = form
    if(id) {
      return this.update(id, data)
    }
    return this.create(data)
  }

  private create(data: any) {
    return this.http.post<any>(API, data)
  }

  private update(id: number, data: any) {
    return this.http.put<any>(`${API}/${id}`, data)
  }

  search(params: any) {
    return this.http.get<any>(API, {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${API}/${id}`)
  }
}
