import { Component, OnInit, ViewChild } from '@angular/core';
import { EmployeeMemberService } from '../../../../utils/apis/services/employee-member.service';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NoDataComponent } from '../../../../utils/widgets/no-data/no-data.component';
import { SuccessDialogComponent } from '../../../../utils/widgets/success-dialog/success-dialog.component';
import { ApiImagePipe } from '../../../../utils/pipe/api-image.pipe';

@Component({
  selector: 'app-member-detail',
  standalone: true,
  imports: [CommonModule, RouterModule, NoDataComponent, SuccessDialogComponent, ApiImagePipe],
  templateUrl: './member-detail.component.html'
})
export class MemberDetailComponent implements OnInit {

  @ViewChild(SuccessDialogComponent)
  successDialog!: SuccessDialogComponent
  successHeader: any
  successMessage: any

  purchases: any[] = []
  member: any

  constructor(private employeeMemberService: EmployeeMemberService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      if(param.get('id')) {
        this.findMemberById(Number(param.get('id')))
      }
    })
  }

  findMemberById(id: number) {
    this.employeeMemberService.findById(id).subscribe(resp => {
      if(resp) {
        this.member = resp.payload.profile
        this.purchases = resp.payload.purchases
      }
    })
  }

  uploadProfileImage(fileList: FileList) {
    this.employeeMemberService.uploadPhoto(this.member.id, fileList[0]).subscribe(resp => {
      if(resp) {
        this.findMemberById(this.member.id)
        this.successHeader = 'Upload Complete'
        this.successMessage = resp.payload.message
        this.successDialog.openDialog()
      }
    })
  }

}
