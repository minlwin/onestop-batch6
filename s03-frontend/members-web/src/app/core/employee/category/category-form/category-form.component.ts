import { Component, EventEmitter, Output, ViewChild } from '@angular/core';
import { ModalDialogComponent } from '../../../../utils/widgets/modal-dialog/modal-dialog.component';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { Category } from '../../../../utils/apis/model/sample-data';

@Component({
  selector: 'app-category-form',
  standalone: true,
  imports: [ModalDialogComponent, FormGroupComponent, ReactiveFormsModule],
  templateUrl: './category-form.component.html'
})
export class CategoryFormComponent {

  @ViewChild(ModalDialogComponent)
  dialog!: ModalDialogComponent

  form: FormGroup

  @Output()
  onSave = new EventEmitter

  categoryObject!: Category | undefined

  constructor(fb: FormBuilder) {
    this.form = fb.group({
      id: 0,
      name: ['', Validators.required]
    })
  }

  get id() {
    return this.form.get('id') as FormControl
  }

  get name() {
    return this.form.get('name') as FormControl
  }

  openForm(category: Category | undefined) {
    this.initForm()
    if(category) {
      this.categoryObject = category
      this.form.patchValue(category)
    }
    this.dialog.openDialog()
  }

  hideForm() {
    this.dialog.hideDialog()
  }

  saveCategory() {
    this.onSave.emit(this.form.value)
  }

  initForm() {
    this.form.patchValue({
      id: 0,
      name: ''
    })
  }

}
