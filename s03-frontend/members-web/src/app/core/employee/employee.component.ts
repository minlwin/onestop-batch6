import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './employee.component.html'
})
export class EmployeeComponent {

  constructor(private securityService: SecurityService, private router: Router) {}

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/public'])
  }

}
