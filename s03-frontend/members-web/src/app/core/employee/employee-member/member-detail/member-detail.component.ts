import { Component, OnInit } from '@angular/core';
import { EmployeeMemberService } from '../../../../utils/apis/services/employee-member.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MemberDto } from '../../../../utils/apis/model/sample-data';
import { NoDataComponent } from '../../../../utils/widgets/no-data/no-data.component';

@Component({
  selector: 'app-member-detail',
  standalone: true,
  imports: [CommonModule, RouterModule, NoDataComponent],
  templateUrl: './member-detail.component.html'
})
export class MemberDetailComponent implements OnInit {

  purchases: any[] = []
  member: any

  constructor(private employeeMemberService: EmployeeMemberService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      if(param.get('id')) {
        this.employeeMemberService.findById(+(param.get('id') as string)).subscribe(resp => {
          if(resp) {
            this.member = resp.profile
            this.purchases = resp.purchases
          }
        })
      }
    })
  }

}
