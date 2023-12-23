import { Component, OnInit } from '@angular/core';
import { AccessError } from '../../apis/model/access-error';
import { Router } from '@angular/router';
import { SecurityService } from '../../apis/services/security.service';
import { CommonModule } from '@angular/common';

declare var bootstrap: any

@Component({
  selector: 'app-error-dialog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './error-dialog.component.html'
})
export class ErrorDialogComponent implements OnInit {

  dialog: any

  activeUser: any
  modalHeader = 'Error'
  messages: any[] = []

  constructor(private seurityService: SecurityService,
    private router: Router) {}

  ngOnInit(): void {
    this.activeUser = this.seurityService.activeUser
    this.dialog = new bootstrap.Modal('#errorDialogId')
  }

  openDialog(error: any) {
    this.messages = []
    if(error.error) {
      // Api Error
      this.modalHeader = this.apiExceptionModalHeader(error.error.status)

      if(error.error.status == 'SecurityError') {
        this.messages.push(error.error.payload)
      } else {
        this.messages = error.error.payload
      }
    } else {

      this.modalHeader = error.name
      this.messages.push(error.message)

      if(error instanceof AccessError) {
        this.router.navigate(['/'.concat(this.activeUser && this.activeUser.role ? this.activeUser.role.toLowerCase() : 'public')])
      }
      console.log(error)
    }

    this.dialog.show()
  }

  private apiExceptionModalHeader(header: string) {
    let result = 'Api Error'
    switch(header) {
      case 'ValidationError':
        result = 'Validation Error'
        break
      case 'BusinessError':
        result = 'Business Error'
        break
      case 'SecurityError':
        result = 'Security Error'
        break
      case 'PlatformError':
        result = 'Platform Error'
    }
    return result
  }

}
