import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { ModalDialogComponent } from '../../../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ROLE } from '../../../../utils/apis/model/api-constant';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [CommonModule, ModalDialogComponent, ReactiveFormsModule, FormGroupComponent],
  templateUrl: './employee-form.component.html'
})
export class EmployeeFormComponent {

  @ViewChild(ModalDialogComponent)
  dialog!: ModalDialogComponent

  @Input()
  updateStatus = false

  roles = ROLE
  form: FormGroup

  @Output()
  onSave = new EventEmitter

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      id: 0,
      loginId: ['', Validators.required],
      name: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', Validators.email],
      role: ['', Validators.required],
      assignAt: ['', Validators.required],
      retiredAt: ''
    })
  }

  saveEmployee() {
    this.onSave.emit(this.form.value)
  }

  openEmployeeForm(employee: any) {
    this.initForm()
    if(employee) {
      this.form.patchValue(employee)
    }
    this.dialog.openDialog()
  }

  closeEmployeeForm() {
    this.dialog.hideDialog()
  }

  initForm() {
    this.form.patchValue({
      id: 0,
      loginId: '',
      name: '',
      phone: '',
      email: '',
      assignAt: '',
      retiredAt: ''
    })
  }

  get id() {
    return this.getFormControl('id')
  }

  get loginId() {
    return this.getFormControl('loginId')
  }

  get name() {
    return this.getFormControl('name')
  }

  get phone() {
    return this.getFormControl('phone')
  }

  get email() {
    return this.getFormControl('email')
  }

  get role() {
    return this.getFormControl('role')
  }

  get assignAt() {
    return this.getFormControl('assignAt')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }
}
