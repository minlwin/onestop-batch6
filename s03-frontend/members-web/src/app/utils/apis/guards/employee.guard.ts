import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityService } from '../services/security.service';
import { AccessError } from '../model/access-error';

export const employeeGuard: CanActivateFn = (route, state) => {
  let role = inject(SecurityService).activeUser?.role

  if(role == 'Employee' || role == 'Owner')
    return true;

  throw new AccessError
};
