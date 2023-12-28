import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { EmployeeSaleService } from '../../../utils/apis/services/employee-sale.service';
import { SecurityService } from '../../../utils/apis/services/security.service';

@Component({
  selector: 'app-sale',
  standalone: true,
  imports: [CommonModule, RouterModule, NoDataComponent],
  templateUrl: './sale.component.html'
})
export class SaleComponent implements OnInit {

  params = {
    customerName: '',
    customerPhone: '',
    from: '',
    to: ''
  }

  activeUser: any
  sales: any[] = []

  constructor(private securityService: SecurityService, private employeeSaleService: EmployeeSaleService) {}

  ngOnInit(): void {
    this.search()
    this.activeUser = this.securityService.activeUser
  }

  search() {
    this.employeeSaleService.search(this.params).subscribe(resp => {
      this.sales = resp.payload.content
    })
  }

  edit(id: string) {
    
  }

}
