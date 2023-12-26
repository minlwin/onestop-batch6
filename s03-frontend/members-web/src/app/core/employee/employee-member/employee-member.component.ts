import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterModule } from '@angular/router';
import { MemberFormComponent } from './member-form/member-form.component';
import { EmployeeMemberService } from '../../../utils/apis/services/employee-member.service';
import { CommonModule } from '@angular/common';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { SuccessDialogComponent } from '../../../utils/widgets/success-dialog/success-dialog.component';
import { LocationService } from '../../../utils/apis/services/location.service';

@Component({
  selector: 'app-employee-member',
  standalone: true,
  imports: [CommonModule, RouterModule, MemberFormComponent, NoDataComponent, SuccessDialogComponent],
  templateUrl: './employee-member.component.html'
})
export class EmployeeMemberComponent implements OnInit {

  @ViewChild(SuccessDialogComponent)
  infoDialog!: SuccessDialogComponent
  infoHeader: any
  infoMessage: any

  params = {
    name: '',
    phone: '',
    from: '',
    to: ''
  }
  members: any[] = []

  constructor(private employeeMemberService: EmployeeMemberService, private locationService: LocationService) {}

  ngOnInit(): void {
      this.search()
  }

  search() {
    this.employeeMemberService.search(this.params).subscribe(resp => this.members = resp.payload.content)
  }

  uploadLocations(fileList: FileList) {
    let file: File = fileList[0];
    if(!file.name.endsWith('xls') && !file.name.endsWith('xlsx')) {
      this.infoHeader = 'Wrong File Format'
      this.infoMessage = 'Please select correct excel file!'
      this.infoDialog.openDialog()
    } else {
      this.locationService.uploadLocations(file).subscribe(resp => {
        if(resp) {
          this.infoHeader = 'Upload Complete'
          this.infoMessage = resp.payload.message
          this.infoDialog.openDialog()
        }
      })
    }
  }

}
