import { Injectable } from '@angular/core';
import { User } from '../model/sample-data';
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

const USERS_DATA: User[] = [
  {
    email: 'member@gmail.com',
    password: 'member',
    role: 'Member'
  },
  {
    email: 'employee@gmail.com',
    password: 'employee',
    role: 'Employee'
  },
  {
    email: 'owner@gmail.com',
    password: 'owner',
    role: 'Owner'
  }
]
