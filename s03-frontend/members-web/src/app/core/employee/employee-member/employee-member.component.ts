import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MemberFormComponent } from './member-form/member-form.component';
import { EmployeeMemberService } from '../../../utils/apis/services/employee-member.service';
import { MemberSearch } from '../../../utils/apis/model/sample-data';
import { CommonModule } from '@angular/common';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';

@Component({
  selector: 'app-employee-member',
  standalone: true,
  imports: [CommonModule, RouterModule, MemberFormComponent, NoDataComponent],
  templateUrl: './employee-member.component.html'
})
export class EmployeeMemberComponent implements OnInit {

  params: MemberSearch = { keyword: '' }
  members: any = []

  constructor(private employeeMemberService: EmployeeMemberService) {}

  ngOnInit(): void {
      this.search()
  }

  search() {
    this.employeeMemberService.search(this.params).subscribe(resp => this.members = resp)
  }

}
