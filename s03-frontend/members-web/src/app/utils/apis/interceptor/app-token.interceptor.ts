import { HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { SecurityService } from '../services/security.service';
import { tap } from 'rxjs';

export const appTokenInterceptor: HttpInterceptorFn = (req, next) => {
  let securityService = inject(SecurityService)
  let clone = req

  if(securityService.key) {
    clone = req.clone({headers: req.headers.append('Authorization', securityService.key)})
  }

  return next(clone).pipe(
    tap(event => {
      if(event instanceof HttpResponse) {
        let resp = event as HttpResponse<any>
        securityService.key = resp.headers.get('Authorization') as string
      }
    })
  )
};
