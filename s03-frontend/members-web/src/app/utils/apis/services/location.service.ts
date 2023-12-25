import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

const API = `${environment.url}/employee/location`

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(private http: HttpClient) {}

  uploadLocations(file: any) {
    var formData = new FormData
    formData.append('file', file, file.name)
    return this.http.post<any>(`${API}/upload`, formData)
  }

  getState() {
    return this.http.get<any>(`${API}/state`)
  }

  getDistrict(params: any) {
    return this.http.get<any>(`${API}/district`, {params: params})
  }

  getTownship(params: any) {
    return this.http.get<any>(`${API}/township`, {params: params})
  }

}
