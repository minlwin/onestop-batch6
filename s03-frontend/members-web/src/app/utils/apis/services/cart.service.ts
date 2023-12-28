import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private _sale: {id: string, customerId: number, discount?: number, items: any[]} = {
    id: '',
    customerId: 0,
    discount: 0,
    items: []
  }

  set sale(sale: any) {
    this._sale = sale
  }

  get sale() {
    return this._sale
  }

  get items(): any[] {
    return this._sale.items
  }

  addItemOrRemove(item: any) {
    if(this.isExist(item)) {
      let index = this._sale.items.findIndex(i => i.id == item.id)
      this._sale.items.splice(index, 1)
    } else {
      this._sale.items.push(item)
    }
  }

  isExist(item: any) {
    return this._sale.items.filter(it => it.id == item.id).pop() ? true : false
  }

  clearCart() {
    this._sale = {
      id: '',
      customerId: 0,
      discount: 0,
      items: []
    }
  }

}
