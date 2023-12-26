import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { Catalog } from '../../utils/apis/model/sample-data';
import { CartService } from '../../utils/apis/services/cart.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './employee.component.html'
})
export class EmployeeComponent implements OnInit {

  cartItems: Catalog[] = []
  activeUser: any

  constructor(private securityService: SecurityService,
    private cartService: CartService,
    private router: Router) {}

  ngOnInit(): void {
    this.cartItems = this.cartService.items
    this.activeUser = this.securityService.activeUser
  }

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/public'])
  }

}
