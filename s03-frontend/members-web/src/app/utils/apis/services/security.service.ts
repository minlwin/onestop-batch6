import { Injectable } from '@angular/core';
import { User } from '../model/sample-data';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private APP_TOKEN_KEY: string = 'com.jdc.token'
  private APP_USER_KEY: string = 'com.jdc.user'
  private _activeUser: any
  private _key!: string

  constructor() {
    let user = localStorage.getItem(this.APP_USER_KEY)
    if(user) {
      this._activeUser = JSON.parse(user)
    }

    let tokenKey = localStorage.getItem(this.APP_TOKEN_KEY)
    if(tokenKey) {
      this._key = JSON.parse(tokenKey)
    }
  }

  set activeUser(user: any) {
    this._activeUser = user
    localStorage.setItem(this.APP_USER_KEY, JSON.stringify(user))
  }

  get activeUser() {
    return this._activeUser
  }

  getActiveUser() {
    return this._activeUser
  }

  get key() {
    return this._key
  }

  set key(key: string) {
    this._key = key
    localStorage.setItem(this.APP_TOKEN_KEY, JSON.stringify(key))
  }

  clearStorage() {
    localStorage.clear()
    this._key = ''
    this._activeUser = undefined
  }
}
