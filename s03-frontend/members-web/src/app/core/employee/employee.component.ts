import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './employee.component.html'
})
export class EmployeeComponent implements OnInit {

  activeUser: any

  constructor(private securityService: SecurityService,
    private router: Router) {}

  ngOnInit(): void {
    this.activeUser = this.securityService.activeUser
  }

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/public'])
  }

}
