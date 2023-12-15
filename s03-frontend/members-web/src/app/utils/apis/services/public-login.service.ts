import { Injectable } from '@angular/core';
import { USERS_DATA, User } from '../model/sample-data';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PublicLoginService {

  constructor() {}

  login(loginForm: any): Observable<User | undefined> {
    return of(
      USERS_DATA.filter(user => user.email == loginForm.email && user.password == loginForm.password).pop()
    )
  }

}
