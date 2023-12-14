import { Injectable } from '@angular/core';
import { Catalog } from '../model/sample-data';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private _cartItems: Catalog[] = []

  constructor() {}

  addItems(item: Catalog) {
    this._cartItems.push(item)
  }

  isExist(item: Catalog) {
    return this._cartItems.filter(cat => cat.id == item.id).pop() ? true : false
  }

  get items() {
    return this._cartItems
  }
}
