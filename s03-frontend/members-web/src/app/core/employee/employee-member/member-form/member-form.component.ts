import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-member-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormGroupComponent],
  templateUrl: './member-form.component.html'
})
export class MemberFormComponent {

  form: FormGroup

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      id: 0,
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('')]],
      nrc: ['', [Validators.required, Validators.pattern('')]],
      gender: ['Male', Validators.required],
      address: ['', Validators.required],
      remark: ''
    })
  }

  get id() {
    return this.getFormControl('id')
  }

  get name() {
    return this.getFormControl('name')
  }

  get email() {
    return this.getFormControl('email')
  }

  get phone() {
    return this.getFormControl('phone')
  }

  get nrc() {
    return this.getFormControl('nrc')
  }

  get address() {
    return this.getFormControl('address')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

}
