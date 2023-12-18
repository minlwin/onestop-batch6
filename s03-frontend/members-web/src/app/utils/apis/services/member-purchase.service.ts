import { Injectable } from '@angular/core';
import { PURCHASE_DATA, Purchase, PurchaseSearch } from '../model/sample-data';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MemberPurchaseService {

  private _purchases: Purchase[] = PURCHASE_DATA

  constructor() { }

  search(params: PurchaseSearch) {
    return of(this._purchases)
  }

  findById(id: any) {
    return of(this._purchases.filter(purchase => purchase.id.saleDate == id.saleDate && purchase.id.saleSeq == id.saleSeq).pop() as Purchase)
  }
}
