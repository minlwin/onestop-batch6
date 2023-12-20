import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { EmployeeSaleService } from '../../../utils/apis/services/employee-sale.service';

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

  sales: any[] = []

  constructor(private employeeSaleService: EmployeeSaleService) {}

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.employeeSaleService.search(this.params).subscribe((resp: any) => {
      this.sales = resp.payload.content
    })
  }

}
