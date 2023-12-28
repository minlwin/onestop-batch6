import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ModalDialogComponent } from '../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormGroupComponent } from '../../utils/widgets/form-group/form-group.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PublicLoginService } from '../../utils/apis/services/public-login.service';
import { CommonModule } from '@angular/common';
import { SecurityService } from '../../utils/apis/services/security.service';
import { Observer } from 'rxjs';

@Component({
  selector: 'app-public',
  standalone: true,
  imports: [CommonModule, RouterModule, ModalDialogComponent, FormGroupComponent, ReactiveFormsModule],
  templateUrl: './public.component.html'
})
export class PublicComponent implements OnInit {

  @ViewChild(ModalDialogComponent)
  dialog!: ModalDialogComponent

  form: FormGroup
  user: any

  constructor(fb: FormBuilder,
    private publicLoginService: PublicLoginService,
    private securityService: SecurityService,
    private router: Router) {
    this.form = fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]]
    })
  }

  ngOnInit(): void {
    this.setActiveUser()
  }

  setActiveUser() {
    this.user = this.securityService.activeUser
  }

  get email(): FormControl {
    return this.form.get('username') as FormControl
  }

  get password(): FormControl {
    return this.form.get('password') as FormControl
  }

  showLoginForm() {
    this.initForm()
    this.dialog.openDialog()
  }

  login() {
    const part: Partial<Observer<any>> = {
      next: (resp: any) => {
        if(resp) {
          this.securityService.activeUser = resp.payload
          this.setActiveUser()
          this.router.navigate([`/${this.user.role == 'Admin' ? 'employee' : this.user.role.toLowerCase()}`])
        }
        this.dialog.hideDialog()
      },
      error: err => {
        this.dialog.hideDialog()
        throw err
      },
      complete: () => {
        console.log('Complete')
      }
    }
    this.publicLoginService.login(this.form.value).subscribe(part)
  }

  logout() {
    this.securityService.clearStorage()
    this.user = undefined
  }

  initForm() {
    this.form.patchValue({
      username: '',
      password: ''
    })
  }

}
