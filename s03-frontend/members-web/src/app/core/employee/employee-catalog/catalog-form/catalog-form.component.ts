import { Component, OnInit } from '@angular/core';
import { Catalog, Category } from '../../../../utils/apis/model/sample-data';
import { EmployeeCatalogService } from '../../../../utils/apis/services/employee-catalog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeCategoryService } from '../../../../utils/apis/services/employee-category.service';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-catalog-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormGroupComponent],
  templateUrl: './catalog-form.component.html'
})
export class CatalogFormComponent implements OnInit {

  categories: any[] = []
  catalog!: Catalog | undefined
  form: FormGroup

  constructor(private fb: FormBuilder, private employeeCatalogService: EmployeeCatalogService, private employeeCategoryService: EmployeeCategoryService, private router: Router, private route: ActivatedRoute) {
    this.form = fb.group({
      id: 0,
      catInput: '', // only use for presentation
      categories: fb.array([]),
      name: ['', Validators.required],
      weight: [0, Validators.min(1)],
      purity: [0, Validators.min(1)],
      undercount: [0, Validators.min(1)],
      remark: ''
    })
  }

  ngOnInit(): void {
    this.employeeCategoryService.search({name: ''}).subscribe(resp => this.categories = resp)

    this.route.queryParamMap.subscribe(param => {
      let id = + (param.get('id') as string)
      if(id) {
        this.employeeCatalogService.findById(id).subscribe(resp => {
          if(resp) {
            this.form.patchValue(resp)
            let arr: Category[] = resp.categories
            arr.map(name => this.categoriesControl.push(this.fb.control(name)))
          }
        })
      }
    })
  }

  get id() {
    return this.getFormControl('id')
  }

  get catInput() {
    return this.getFormControl('catInput')
  }

  get categoriesControl() {
    return this.form.get('categories') as FormArray
  }

  get name() {
    return this.getFormControl('name')
  }

  get weight() {
    return this.getFormControl('weight')
  }

  get purity() {
    return this.getFormControl('purity')
  }

  get undercount() {
    return this.getFormControl('undercount')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

  selectCategory(name: any) {
    let isSame = this.categories.filter(cat => name == cat.name).pop()
    let isExist = this.categoriesControl.controls.filter(ctrl => ctrl.value == name).pop()
    if(name && isSame && !isExist) {
      this.categoriesControl.push(this.fb.control(isSame))
    }
  }

  removeSelected(index: number) {
    this.categoriesControl.removeAt(index)
  }

  saveCatalog() {
    let result: any[] = []

    for (let i = 0; i < this.categoriesControl.controls.length; i++) {
      let value = this.categoriesControl.controls[i].value
      for (let j = 0; j < this.categories.length; j++) {
        if(this.categories[j].name == value) {
          result.push({
            id: this.categories[j].id,
            name: value
          })
        }
      }
    }

    this.form.patchValue({categories: result})
    let {catInput, ...data} = this.form.value

  }

}
