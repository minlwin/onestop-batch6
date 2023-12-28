import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-member',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './member.component.html'
})
export class MemberComponent implements OnInit {

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
