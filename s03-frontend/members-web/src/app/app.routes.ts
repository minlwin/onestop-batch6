import { Routes } from '@angular/router';
import { PublicComponent } from './core/public/public.component';
import { MemberComponent } from './core/member/member.component';
import { EmployeeComponent } from './core/employee/employee.component';
import { OwnerComponent } from './core/owner/owner.component';
import { PublicHomeComponent } from './core/public/public-home/public-home.component';
import { PublicCatalogComponent } from './core/public/public-catalog/public-catalog.component';
import { MemberHomeComponent } from './core/member/member-home/member-home.component';
import { MemberPurchaseDetailComponent } from './core/member/member-purchase-detail/member-purchase-detail.component';
import { SaleComponent } from './core/employee/sale/sale.component';
import { CategoryComponent } from './core/employee/category/category.component';
import { EmployeeCatalogComponent } from './core/employee/employee-catalog/employee-catalog.component';
import { EmployeeMemberComponent } from './core/employee/employee-member/employee-member.component';
import { MemberFormComponent } from './core/employee/employee-member/member-form/member-form.component';
import { MemberDetailComponent } from './core/employee/employee-member/member-detail/member-detail.component';
import { CatalogFormComponent } from './core/employee/employee-catalog/catalog-form/catalog-form.component';
import { CatalogDetailComponent } from './core/employee/employee-catalog/catalog-detail/catalog-detail.component';

export const routes: Routes = [
  { path: 'public', component: PublicComponent, children: [
    { path: 'home', component: PublicHomeComponent, title: 'Public | Home' },
    { path: 'catalog', component: PublicCatalogComponent, title: 'Public | Catalog' },
    { path: '', redirectTo: '/public/home', pathMatch: 'full' }
  ]},
  { path: 'member', component: MemberComponent, children: [
    { path: 'home', component: MemberHomeComponent, title: 'Member | Home' },
    { path: 'purchase-detail', component: MemberPurchaseDetailComponent, title: 'Member | Purchase Detial' },
    { path: '', redirectTo: '/member/home', pathMatch: 'full' }
  ]},
  { path: 'employee', component: EmployeeComponent, children: [
    { path: 'sale', component: SaleComponent, title: 'Employee | Sale' },
    { path: 'member', children: [
      { path: 'management', component: EmployeeMemberComponent, title: 'Employee | Member' },
      { path: 'form', component: MemberFormComponent, title: 'Employee | Member Form' },
      { path: 'detail', component: MemberDetailComponent, title: 'Employee | Member Detail' },
      { path: '', redirectTo: '/employee/member/management', pathMatch: 'full' }
    ]},
    { path: 'category', component: CategoryComponent, title: 'Employee | Category' },
    { path: 'catalog', children: [
      { path: 'management', component: EmployeeCatalogComponent, title: 'Employee | Catalog' },
      { path: 'form', component: CatalogFormComponent, title: 'Employee | Catalog Form'},
      { path: 'detail', component: CatalogDetailComponent, title: 'Employee | Catalog Detail'},
      { path: '', redirectTo: '/employee/catalog/management', pathMatch: 'full' }
    ]},
    { path: '', redirectTo: '/employee/sale', pathMatch: 'full' }
  ]},
  { path: 'owner', component: OwnerComponent },
  { path: '', redirectTo: '/public', pathMatch: 'full' }
];
