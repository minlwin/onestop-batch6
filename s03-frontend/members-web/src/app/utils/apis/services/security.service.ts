import { Injectable } from '@angular/core';
import { User } from '../model/sample-data';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  APP_USER_KEY: string = "com.jdc.user"
  private _activeUser: User | undefined

  constructor() {
    let user = localStorage.getItem(this.APP_USER_KEY)
    if(user) {
      this._activeUser = JSON.parse(user)
    }
  }

  set activeUser(user: User) {
    this._activeUser = user
    localStorage.setItem(this.APP_USER_KEY, JSON.stringify(user))
  }

  get activeUser(): User | undefined {
    return this._activeUser
  }

  getActiveUser() {
    return of(this._activeUser)
  }

  clearStorage() {
    localStorage.clear()
    this._activeUser = undefined
  }
}
