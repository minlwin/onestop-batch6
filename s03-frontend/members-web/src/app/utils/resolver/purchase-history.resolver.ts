import { inject } from '@angular/core';
import { ResolveFn } from '@angular/router';
import { MemberPurchaseService } from '../apis/services/member-purchase.service';
import { Purchase } from '../apis/model/sample-data';

export const purchaseHistoryResolver: ResolveFn<Purchase> = (route, state) => {
  let memberPurchaseService = inject(MemberPurchaseService)

  let saleDate = route.queryParamMap.get('saleDate') as string
  let saleSeq = Number(route.queryParamMap.get('saleSeq'))

  return memberPurchaseService.findById({saleDate: saleDate, saleSeq: saleSeq});
};
