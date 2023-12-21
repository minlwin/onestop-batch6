import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

export const appErrorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(
    catchError(error => throwError(() => error))
  )
};
