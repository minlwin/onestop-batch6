import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-owner',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './owner.component.html'
})
export class OwnerComponent implements OnInit {

  activeUser: any

  constructor(private securityService: SecurityService, private router: Router) {}

  ngOnInit(): void {
    this.activeUser = this.securityService.activeUser
  }

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/'])
  }

}
