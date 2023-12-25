import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';

const API = `${environment.url}/employee/member`

@Injectable({
  providedIn: 'root'
})
export class EmployeeMemberService {

  constructor(private http: HttpClient) {}

  save(member: any) {
    const {id, ...data} = member
    if(id == 0)
      return this.add(data)

    return this.update(data, id)
  }

  add(member: any) {
    return this.http.post<any>(API, member)
  }

  update(member: any, id: number) {
    return this.http.put<any>(`${API}/${id}`, member)
  }

  search(params: any) {
    return this.http.get<any>(API, {params: params})
  }

  findById(id: number) {
    return this.http.get<any>(`${API}/${id}`)
  }

  uploadPhoto(id: number, file: any) {
    var formData = new FormData
    formData.append('file', file, file.name)
    return this.http.put<any>(`${API}/${id}/photo`, formData)
  }

}
