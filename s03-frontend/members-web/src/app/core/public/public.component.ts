import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ModalDialogComponent } from '../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormGroupComponent } from '../../utils/widgets/form-group/form-group.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PublicLoginService } from '../../utils/apis/services/public-login.service';
import { CommonModule } from '@angular/common';
import { SecurityService } from '../../utils/apis/services/security.service';

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

  constructor(fb: FormBuilder, private publicLoginService: PublicLoginService, private securityService: SecurityService, private router: Router) {
    this.form = fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(5)]]
    })
  }

  ngOnInit(): void {
    this.user = this.securityService.activeUser
  }

  get email(): FormControl {
    return this.form.get('email') as FormControl
  }

  get password(): FormControl {
    return this.form.get('password') as FormControl
  }

  showLoginForm() {
    this.initForm()
    this.dialog.openDialog()
  }

  login() {
    if(this.form.valid) {
      this.publicLoginService.login(this.form.value).subscribe(user => {
        if(user) {
          this.securityService.activeUser = user
          this.router.navigate([`/${user.role.toLocaleLowerCase()}`])
        }
        this.dialog.hideDialog()
      })
    }
  }

  logout() {
    this.securityService.clearStorage()
  }

  initForm() {
    this.form.patchValue({
      email: '',
      password: ''
    })
  }

}
