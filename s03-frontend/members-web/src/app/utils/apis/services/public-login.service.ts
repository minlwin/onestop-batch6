import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

const API = `${environment.url}/public/login`

@Injectable({
  providedIn: 'root'
})
export class PublicLoginService {

  constructor(private http: HttpClient) {}

  login(loginForm: any) {
    return this.http.post<any>(API, loginForm)
  }

}
