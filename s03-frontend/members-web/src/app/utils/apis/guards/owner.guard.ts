import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityService } from '../services/security.service';
import { AccessError } from '../model/access-error';

export const ownerGuard: CanActivateFn = (route, state) => {
  let role = inject(SecurityService).activeUser?.role
  if(role == 'Owner')
    return true;
  throw new AccessError
};
