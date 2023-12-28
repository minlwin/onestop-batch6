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
  style = {'width': '0%'}

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.router.events.subscribe(e => {
      if(e instanceof NavigationEnd) {
        if(e.urlAfterRedirects == '/employee/sale/form/customer') {
          this.url = 'customer'
          this.style = {'width': '0%'}
        } else if(e.urlAfterRedirects == '/employee/sale/form/discount') {
          this.url = 'discount'
          this.style = {'width': '33.333%'}
        } else if(e.urlAfterRedirects == '/employee/sale/form/items') {
          this.url = 'items'
          this.style = {'width': '66.666%'}
        } else {
          this.url = 'checkout'
          this.style = {'width': '100%'}
        }
      }
    })
  }

}
