import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

const baseUrl = `${environment.url}/employee/sales`

@Injectable({
  providedIn: 'root'
})
export class EmployeeSaleService {

  constructor(private http: HttpClient) {}

  search(saleForm: any) {
    return this.http.get(baseUrl, {params: saleForm})
  }
}
