import { Component, OnInit } from '@angular/core';
import { EmployeeMemberService } from '../../../../../utils/apis/services/employee-member.service';
import { CommonModule, DatePipe } from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sale-customer',
  standalone: true,
  imports: [CommonModule, DatePipe, RouterModule],
  templateUrl: './sale-customer.component.html'
})
export class SaleCustomerComponent implements OnInit {

  id = '0'
  member: any

  constructor(private employeeMemberService: EmployeeMemberService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      let id = Number(param.get('id'))
      if(id)
        this.findCustomerById(id)
    })
  }

  searchCustomer() {
    let result = Number(this.id)
    if(result > 0)
      this.findCustomerById(result)
  }

  findCustomerById(id: number) {
    this.employeeMemberService.findById(id).subscribe(resp => {
      this.member = resp.payload.profile
    })
  }

}
