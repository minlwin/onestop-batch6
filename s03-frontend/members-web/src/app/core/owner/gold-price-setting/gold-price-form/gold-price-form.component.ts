import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ModalDialogComponent } from '../../../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';

@Component({
  selector: 'app-gold-price-form',
  standalone: true,
  imports: [ModalDialogComponent, ReactiveFormsModule, FormGroupComponent],
  templateUrl: './gold-price-form.component.html'
})
export class GoldPriceFormComponent {

  @ViewChild(ModalDialogComponent)
  dialog!: ModalDialogComponent

  form: FormGroup

  @Output()
  onSave = new EventEmitter

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      id: 0,
      businessTime: ['', Validators.required],
      basePrice: [0, Validators.min(1)],
      diffFor16P: [0, Validators.min(1)],
      diffFor15P: [0, Validators.min(1)]
    })
  }

  openPriceForm(dto: any) {
    this.initForm()
    if(dto) {
      this.form.patchValue(dto)
    }
    this.dialog.openDialog()
  }

  closePriceForm() {
    this.dialog.hideDialog()
  }

  savePrice() {
    this.onSave.emit(this.form.value)
  }

  initForm() {
    this.form.patchValue({
      id: 0,
      businessTime: '',
      basePrice: 0,
      diffFor16P: 0,
      diffFor15P: 0
    })
  }

  get id() {
    return this.getFormControl('id')
  }

  get businessTime() {
    return this.getFormControl('businessTime')
  }

  get basePrice() {
    return this.getFormControl('basePrice')
  }

  get diffFor16P() {
    return this.getFormControl('diffFor16P')
  }

  get diffFor15P() {

    return this.getFormControl('diffFor15P')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

}
