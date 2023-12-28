import { Component, OnInit } from '@angular/core';
import { EmployeeMemberService } from '../../../../../utils/apis/services/employee-member.service';
import { CommonModule, DatePipe } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CartService } from '../../../../../utils/apis/services/cart.service';

@Component({
  selector: 'app-sale-customer',
  standalone: true,
  imports: [CommonModule, DatePipe, RouterModule],
  templateUrl: './sale-customer.component.html'
})
export class SaleCustomerComponent implements OnInit {

  id = 0
  member: any

  constructor(private employeeMemberService: EmployeeMemberService,
    private cartService: CartService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      let id = Number(param.get('id'))
      this.id = id > 0 ? id : this.cartService.sale.customerId
      this.searchCustomer()
    })
  }

  searchCustomer() {
    if(this.id > 0)
      this.employeeMemberService.findById(this.id).subscribe(resp => {
        this.member = resp.payload.profile
      })
    else
      this.member = undefined
  }

  saveCustomer() {
    this.cartService.sale.customerId = this.member.id
  }

}
