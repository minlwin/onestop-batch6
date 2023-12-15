import { Component, OnInit } from '@angular/core';
import { AccessError } from '../../apis/model/access-error';
import { Router } from '@angular/router';
import { SecurityService } from '../../apis/services/security.service';

declare var bootstrap: any

@Component({
  selector: 'app-error-dialog',
  standalone: true,
  imports: [],
  templateUrl: './error-dialog.component.html'
})
export class ErrorDialogComponent implements OnInit {

  dialog: any

  activeUser: any
  modalHeader = 'Error'
  errorMessage = 'Error Occured!'

  constructor(private seurityService: SecurityService,
    private router: Router) {}

  ngOnInit(): void {
    this.activeUser = this.seurityService.activeUser
    this.dialog = new bootstrap.Modal('#errorDialogId')
  }

  openDialog(error: Error) {
    this.errorMessage = error.message
    this.modalHeader = error.name

    if(error instanceof AccessError) {
      this.router.navigate(['/'.concat(this.activeUser && this.activeUser.role ? this.activeUser.role.toLowerCase() : 'public')])
      this.dialog.show()
    }
  }

}
