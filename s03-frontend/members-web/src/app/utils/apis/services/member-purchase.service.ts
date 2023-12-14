import { Injectable } from '@angular/core';
import { PURCHASE_DATA, PurchaseSearch } from '../model/sample-data';
import { of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MemberPurchaseService {

  constructor() { }

  search(params: PurchaseSearch) {
    return of(PURCHASE_DATA)
  }
}
