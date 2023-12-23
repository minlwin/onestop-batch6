import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';

@Component({
  selector: 'app-owner',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './owner.component.html'
})
export class OwnerComponent {

  constructor(private securityService: SecurityService, private router: Router) {}

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/'])
  }

}
