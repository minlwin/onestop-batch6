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

  disctrictParams = {
    state: 0,
    name: ''
  }

  townshipParams = {
    district: 0,
    name: ''
  }

  form: FormGroup
  states: any[] = []
  districts: any[] = []
  townships: any[] = []
  checkout : any
  update : any

  constructor(fb: FormBuilder,
    private employeeMemberService: EmployeeMemberService,
    private locationService: LocationService,
    private router: Router,
    private route: ActivatedRoute) {
    this.form = fb.group({
      id: 0,
      loginId: ['', Validators.required],
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', Validators.required],
      dob: ['', Validators.required],
      gender: ['Male', Validators.required],
      townshipId: [0, Validators.min(1)],
      address: ['', Validators.required],
    })
  }

  ngOnInit(): void {
    let id = 0
    this.route.queryParamMap.subscribe(param => {
      if(param.get('id')) {
        id = Number(param.get('id'))
      }

      if(param.get('checkout')) {
        this.checkout = param.get('checkout')
      }

      if(param.get('update')) {
        this.update = param.get('update')
      }
    })

    if(id) {
      this.employeeMemberService.findById(id).subscribe(resp => {
        if(resp)
          this.form.patchValue(resp.payload.profile)
      })
    }
    this.searchState()
  }

  searchState() {
    this.locationService.getState().subscribe(resp => this.states = resp.payload)
  }

  searchDistrict() {
    this.locationService.getDistrict(this.disctrictParams).subscribe(resp => this.districts = resp.payload)
  }

  searchTownship() {
    this.locationService.getTownship(this.townshipParams).subscribe(resp => this.townships = resp.payload)
  }

  selectDistrict(state: any) {
    if(state > 0) {
      this.disctrictParams.state = state;
    } else {
      this.disctrictParams.state = state;
      this.selectTownship(0)
    }
    this.searchDistrict()
  }

  selectTownship(district: any) {
    if(district > 0) {
      this.townshipParams.district = district;
    } else {
      this.townshipParams.district = district;
      this.form.patchValue({townshipId: 0})
    }
    this.searchTownship()
  }

  saveMember() {
    this.employeeMemberService.save(this.form.value).subscribe(resp => {
      if(resp) {
        const url = this.checkout ? ['/employee', 'sale', 'form', 'customer'] : ['/employee', 'member', 'detail']
        this.router.navigate(url, {queryParams: {id: resp.payload.id}})
      }
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

  get townshipId() {
    return this.getFormControl('townshipId')
  }

  private getFormControl(formControlName: string) {
    return this.form.get(formControlName) as FormControl
  }

}
