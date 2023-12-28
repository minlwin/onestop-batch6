import { Component, OnInit } from '@angular/core';
import { EmployeeCatalogService } from '../../../../utils/apis/services/employee-catalog.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeCategoryService } from '../../../../utils/apis/services/employee-category.service';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { CommonModule } from '@angular/common';
import { PURITY } from '../../../../utils/apis/model/api-constant';

@Component({
  selector: 'app-catalog-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormGroupComponent],
  templateUrl: './catalog-form.component.html'
})
export class CatalogFormComponent implements OnInit {

  purityConst = PURITY
  categories: any[] = []
  catalog: any
  form: FormGroup

  constructor(fb: FormBuilder, private employeeCatalogService: EmployeeCatalogService, private employeeCategoryService: EmployeeCategoryService, private router: Router, private route: ActivatedRoute) {
    this.form = fb.group({
      id: 0,
      categoryId: [0, Validators.min(1)],
      name: ['', Validators.required],
      weight: [0, Validators.min(1)],
      purity: ['', Validators.required],
      lostWeight: [0, Validators.min(1)],
      basedPrice: [0, Validators.min(1)],
      goldSmithFees: [0, Validators.min(1)],
      description: '',
      soldOut: false
    })
  }

  ngOnInit(): void {
    this.employeeCategoryService.search().subscribe(resp => this.categories = resp.payload)

    this.route.queryParamMap.subscribe(param => {
      let id = Number(param.get('id'))
      if(id) {
        this.employeeCatalogService.findById(id).subscribe(resp => {
          if(resp) {
            this.form.patchValue(resp.payload.baseInfo)
          }
        })
      }
    })
  }

  saveCatalog() {

    this.employeeCatalogService.save(this.form.value).subscribe(resp => {
      if(resp) {
        this.router.navigate(['/employee', 'catalog', 'detail'], {queryParams: {id: resp.payload.id}})
      }
    })

  }

  get id() {
    return this.getFormControl('id')
  }

  get categoryId() {
    return this.getFormControl('categoryId')
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

  get lostWeight() {
    return this.getFormControl('lostWeight')
  }

  get basedPrice() {
    return this.getFormControl('basedPrice')
  }

  get goldSmithFees() {
    return this.getFormControl('goldSmithFees')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

}
