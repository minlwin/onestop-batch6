import { HttpErrorResponse, HttpInterceptorFn, HttpResponse } from '@angular/common/http';
import { tap } from 'rxjs';

export const appErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    tap(event => {
      if(event instanceof HttpErrorResponse) {
        let resp = event as HttpErrorResponse
        console.log(resp)
      }
    })
  );
};
