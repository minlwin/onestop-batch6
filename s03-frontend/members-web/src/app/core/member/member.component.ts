import { Component } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';

@Component({
  selector: 'app-member',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './member.component.html'
})
export class MemberComponent {

  constructor(private securityService: SecurityService, private router: Router) {}

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/public'])
  }

}
