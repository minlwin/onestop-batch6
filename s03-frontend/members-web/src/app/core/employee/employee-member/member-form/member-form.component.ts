import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormGroupComponent } from '../../../../utils/widgets/form-group/form-group.component';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { EmployeeMemberService } from '../../../../utils/apis/services/employee-member.service';
import { LocationService } from '../../../../utils/apis/services/location.service';

@Component({
  selector: 'app-member-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormGroupComponent, RouterModule],
  templateUrl: './member-form.component.html'
})
export class MemberFormComponent implements OnInit {

  form: FormGroup
  states: any[] = []
  districts: any[] = []
  townships: any[] = []
  checkout = false

  constructor(fb: FormBuilder,
    private employeeMemberService: EmployeeMemberService,
    private locationService: LocationService,
    private router: Router,
    private route: ActivatedRoute) {
    this.form = fb.group({
      id: 0,
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('')]],
      dob: ['', [Validators.required, Validators.pattern('')]],
      gender: ['Male', Validators.required],
      state: 0,
      district: 0,
      township: [0, Validators.min(1)],
      address: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    this.locationService.getAllStates().subscribe(resp => this.states = resp)

    let id = 0
    this.route.queryParamMap.subscribe(param => {
      if(param.get('id')) {
        id = + (param.get('id') as string)
      }

      if(param.get('checkout')) {
        this.checkout = Boolean(param.get('checkout') as string)
      }
    })

    if(id) {
      this.employeeMemberService.findById(id).subscribe(resp => {
        if(resp)
          this.form.patchValue(resp.profile)
      })
    }
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

  get dob() {
    return this.getFormControl('dob')
  }

  get address() {
    return this.getFormControl('address')
  }

  get township() {
    return this.getFormControl('township')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

  saveMember() {
    if(this.form.valid) {
      this.employeeMemberService.save(this.form.value).subscribe(resp => {
        if(resp) {
          this.router.navigate(this.checkout ? ['/employee', 'sale', 'checkout'] : ['/employee', 'member', 'detail'], {queryParams: {id: resp.id}})
        }
      })
    }
  }

}
