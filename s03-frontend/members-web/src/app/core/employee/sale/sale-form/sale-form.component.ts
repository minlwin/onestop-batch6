import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterEvent, RouterModule } from '@angular/router';

@Component({
  selector: 'app-sale-form',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './sale-form.component.html'
})
export class SaleFormComponent implements OnInit {

  url = 'customer'
  width = 0

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(e => {
      if(e instanceof NavigationEnd) {
        if(e.urlAfterRedirects == '/employee/sale/form/customer') {
          this.url = 'customer'
          this.width = 0
        } else if(e.urlAfterRedirects == '/employee/sale/form/discount') {
          this.url = 'discount'
          this.width = 50
        } else {
          this.url = 'items'
          this.width = 100
        }
      }
    })
  }

}
