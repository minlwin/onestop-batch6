import { Component, OnInit, ViewChild } from '@angular/core';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { RouterModule } from '@angular/router';
import { OwnerEmployeeService } from '../../../utils/apis/services/owner-employee.service';
import { CommonModule } from '@angular/common';
import { EmployeeFormComponent } from './employee-form/employee-form.component';

@Component({
  selector: 'app-owner-employee',
  standalone: true,
  imports: [CommonModule, EmployeeFormComponent, NoDataComponent, RouterModule],
  templateUrl: './owner-employee.component.html'
})
export class OwnerEmployeeComponent implements OnInit {

  params = {
    name: '',
    phone: '',
    from: '',
    to: ''
  }

  @ViewChild(EmployeeFormComponent)
  employeeForm!: EmployeeFormComponent

  employees: any[] = []

  constructor(private ownerEmployeeService: OwnerEmployeeService) {}

  ngOnInit(): void {
    this.search()
  }

  openForm(employee: any) {
    this.employeeForm.openEmployeeForm(employee)
  }

  save(employee: any) {
    this.ownerEmployeeService.save(employee).subscribe(resp => {
      if(resp) {
        this.employeeForm.closeEmployeeForm()
        this.search()
      }
    })
  }

  search() {
    this.ownerEmployeeService.search(this.params).subscribe(resp => this.employees = resp.payload.content)
  }

}
