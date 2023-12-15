import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { SecurityService } from '../services/security.service';
import { AccessError } from '../model/access-error';

export const memberGuard: CanActivateFn = (route, state) => {
  let role = inject(SecurityService).activeUser?.role as string

  if(role == 'Member')
    return true;

  throw new AccessError
};
