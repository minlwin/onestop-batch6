import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

const API = `${environment.url}/employee/sales`

@Injectable({
  providedIn: 'root'
})
export class EmployeeSaleService {

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

  private update(id: string, data: any) {
    return this.http.put<any>(`${API}/${id}`, data)
  }

  findById(id: string) {
    return this.http.get<any>(`${API}/${id}`)
  }

  search(saleForm: any) {
    return this.http.get<any>(API, {params: saleForm})
  }
}
