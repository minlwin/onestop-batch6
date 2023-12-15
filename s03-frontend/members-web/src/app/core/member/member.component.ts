import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { SecurityService } from '../../utils/apis/services/security.service';
import { SecondNavComponent } from '../../utils/widgets/second-nav/second-nav.component';
import { CartService } from '../../utils/apis/services/cart.service';
import { Catalog } from '../../utils/apis/model/sample-data';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-member',
  standalone: true,
  imports: [CommonModule, RouterModule, SecondNavComponent],
  templateUrl: './member.component.html'
})
export class MemberComponent implements OnInit {

  cartItems: Catalog[] = []
  currentUsername!: string

  constructor(private securityService: SecurityService,
    private cartService: CartService,
    private router: Router) {}

  ngOnInit(): void {
    this.cartItems = this.cartService.items
    this.currentUsername = this.securityService.activeUser?.role as string
  }

  logout() {
    this.securityService.clearStorage()
    this.router.navigate(['/public'])
  }

}
