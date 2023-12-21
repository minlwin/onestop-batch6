import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ModalDialogComponent } from '../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormGroupComponent } from '../../utils/widgets/form-group/form-group.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PublicLoginService } from '../../utils/apis/services/public-login.service';
import { CommonModule } from '@angular/common';
import { SecurityService } from '../../utils/apis/services/security.service';
import { Catalog } from '../../utils/apis/model/sample-data';
import { SecondNavComponent } from '../../utils/widgets/second-nav/second-nav.component';
import { CartService } from '../../utils/apis/services/cart.service';
import { ApiResponse } from '../../utils/apis/model/api-dto';

@Component({
  selector: 'app-public',
  standalone: true,
  imports: [CommonModule, RouterModule, ModalDialogComponent, FormGroupComponent, ReactiveFormsModule, SecondNavComponent],
  templateUrl: './public.component.html'
})
export class PublicComponent implements OnInit {

  @ViewChild(ModalDialogComponent)
  dialog!: ModalDialogComponent
  cartItems: Catalog[] = []

  form: FormGroup
  user: any

  constructor(fb: FormBuilder,
    private publicLoginService: PublicLoginService,
    private cartService: CartService,
    private securityService: SecurityService,
    private router: Router) {
    this.form = fb.group({
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(5)]]
    })
  }

  ngOnInit(): void {
    this.cartItems = this.cartService.items
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
    if(this.form.valid) {
      this.publicLoginService.login(this.form.value).subscribe((resp: any) => {
        if(resp) {
          this.securityService.activeUser = resp.payload
          this.router.navigate(['/employee'])
        }
        this.dialog.hideDialog()
      })
    }
  }

  logout() {
    this.securityService.clearStorage()
    this.user = undefined
  }

  initForm() {
    this.form.patchValue({
      email: '',
      password: ''
    })
  }

}
