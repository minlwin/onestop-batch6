import { inject } from "@angular/core";
import { ResolveFn } from "@angular/router";
import { SecurityService } from "../services/security.service";

export const checkoutTitle: ResolveFn<string> = (route, state) => {
  let role = inject(SecurityService).activeUser?.role
  let result = 'Public | Checkout'

  if(role == 'Owner')
    result = 'Owner | Checkout'
  else if(role == 'Employee')
    result = 'Employee | Checkout'
  else if(role == 'Member')
    result = 'Member | Checkout'

  return result
}
