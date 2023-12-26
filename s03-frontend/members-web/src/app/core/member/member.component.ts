import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { CartService } from '../../utils/apis/services/cart.service';
import { Catalog } from '../../utils/apis/model/sample-data';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-member',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './member.component.html'
})
export class MemberComponent implements OnInit {

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
