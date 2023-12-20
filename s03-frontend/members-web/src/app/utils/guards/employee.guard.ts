import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityService } from '../apis/services/security.service';
import { AccessError } from '../apis/model/access-error';

export const employeeGuard: CanActivateFn = (route, state) => {
  let role = inject(SecurityService).activeUser?.role

  if(role == 'Employee' || role == 'Owner' || role == 'Admin')
    return true;

  throw new AccessError
};
