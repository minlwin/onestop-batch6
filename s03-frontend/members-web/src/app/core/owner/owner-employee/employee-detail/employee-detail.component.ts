import { Component, OnInit } from '@angular/core';
import { OwnerEmployeeService } from '../../../../utils/apis/services/owner-employee.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-employee-detail',
  standalone: true,
  imports: [RouterModule, DatePipe],
  templateUrl: './employee-detail.component.html'
})
export class EmployeeDetailComponent implements OnInit {

  employee: any

  constructor(private ownerEmployeeService: OwnerEmployeeService,
    private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      let id = Number(param.get('id'))
      if(id) {
        this.ownerEmployeeService.findById(id).subscribe(resp => this.employee = resp.payload)
      }
    })
  }

}
