import { Component, OnInit } from '@angular/core';
import { Purchase, PurchaseSearch } from '../../../utils/apis/model/sample-data';
import { MemberPurchaseService } from '../../../utils/apis/services/member-purchase.service';
import { CommonModule } from '@angular/common';
import { SaleIdPipe } from '../../../utils/pipe/sale-id.pipe';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-member-purchase-history',
  standalone: true,
  imports: [CommonModule, SaleIdPipe, RouterModule],
  templateUrl: './member-purchase-history.component.html'
})
export class MemberPurchaseHistoryComponent implements OnInit {

  params: PurchaseSearch = {
    dateFrom: '',
    dateTo: ''
  }

  purchases: Purchase[] = []

  constructor(private memberPurchaseService: MemberPurchaseService) {}

  ngOnInit(): void {
    this.search()
  }

  addDateFromValue(value: string) {
    this.params.dateFrom = value
    this.search()
  }

  addDateToValue(value: string) {
    this.params.dateTo = value
    this.search()
  }

  search() {
    this.memberPurchaseService.search(this.params).subscribe(resp => this.purchases = resp)
  }

}
