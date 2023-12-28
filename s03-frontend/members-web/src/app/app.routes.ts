import { Route, Routes } from '@angular/router';
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
import { MemberPurchaseHistoryComponent } from './core/member/member-purchase-history/member-purchase-history.component';
import { purchaseHistoryResolver } from './utils/resolver/purchase-history.resolver';
import { memberGuard } from './utils/guards/member.guard';
import { employeeGuard } from './utils/guards/employee.guard';
import { SaleFormComponent } from './core/employee/sale/sale-form/sale-form.component';
import { SaleDetailComponent } from './core/employee/sale/sale-detail/sale-detail.component';
import { GoldPriceSettingComponent } from './core/owner/gold-price-setting/gold-price-setting.component';
import { OwnerEmployeeComponent } from './core/owner/owner-employee/owner-employee.component';
import { EmployeeDetailComponent } from './core/owner/owner-employee/employee-detail/employee-detail.component';
import { ownerGuard } from './utils/guards/owner.guard';
import { SaleCustomerComponent } from './core/employee/sale/sale-form/sale-customer/sale-customer.component';
import { SaleDiscountComponent } from './core/employee/sale/sale-form/sale-discount/sale-discount.component';
import { SaleItemsComponent } from './core/employee/sale/sale-form/sale-items/sale-items.component';
import { SaleCheckoutComponent } from './core/employee/sale/sale-form/sale-checkout/sale-checkout.component';

const publicRoute: Route = { path: 'public', component: PublicComponent, children: [
  { path: 'home', component: PublicHomeComponent, title: 'Public | Home' },
  { path: 'catalog', component: PublicCatalogComponent, title: 'Public | Catalog' },
  { path: '', redirectTo: '/public/home', pathMatch: 'full' }
]}

const memberRoute: Route = { path: 'member', component: MemberComponent, children: [
  { path: 'home', component: MemberHomeComponent, title: 'Member | Home' },
  { path: 'purchase', children: [
    { path: 'history', component: MemberPurchaseHistoryComponent, title: 'Member | Purchase Histroy' },
    { path: 'detail', component: MemberPurchaseDetailComponent, title: 'Member | Purchase Detail', resolve: { purchase: purchaseHistoryResolver } },
    { path: '', redirectTo: '/member/purchase/history', pathMatch: 'full' }
  ]},
  { path: '', redirectTo: '/member/home', pathMatch: 'full' }
], canActivate: [memberGuard]}

const employeeRoute: Route = { path: 'employee', component: EmployeeComponent, children: [
  { path: 'sale', children: [
    { path: 'management', component: SaleComponent, title: 'Employee | Sale' },
    { path: 'form', component: SaleFormComponent, title: 'Employee | Sale Form', children: [
      { path: 'customer', component: SaleCustomerComponent },
      { path: 'discount', component: SaleDiscountComponent },
      { path: 'items', component: SaleItemsComponent },
      { path: 'checkout', component: SaleCheckoutComponent },
      { path: '', redirectTo: '/employee/sale/form/customer', pathMatch: 'full' }
    ]},
    { path: 'detail', component: SaleDetailComponent, title: 'Employee | Sale Detail' },
    { path: '', redirectTo: '/employee/sale/management', pathMatch: 'full' }
  ]},
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
], canActivate: [employeeGuard]}

const ownerRoute: Route = { path: 'owner', component: OwnerComponent, children: [
  { path: 'gold-price', component: GoldPriceSettingComponent, title: 'Owner | Gold Price Setting' },
  { path: 'employee', children: [
    { path: 'management', component: OwnerEmployeeComponent, title: 'Owner | Employee Management' },
    { path: 'detail', component: EmployeeDetailComponent, title: 'Owner | Employee Detail' },
    { path: '', redirectTo: '/owner/employee/management', pathMatch: 'full' }
  ]},
  { path: '', redirectTo: '/owner/gold-price', pathMatch: 'full' }
], canActivate: [ownerGuard]}

export const routes: Routes = [
  publicRoute,
  memberRoute,
  employeeRoute,
  ownerRoute,
  { path: '', redirectTo: '/public', pathMatch: 'full' }
];
