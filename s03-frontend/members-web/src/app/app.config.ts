import { ApplicationConfig, ErrorHandler } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { AppErrorHandler } from './utils/apis/model/error-handler';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { appTokenInterceptor } from './utils/apis/interceptor/app-token.interceptor';
import { appErrorInterceptor } from './utils/apis/interceptor/app-error.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([appTokenInterceptor, appErrorInterceptor])),
    { provide: ErrorHandler, useClass: AppErrorHandler },
  ],
};
