import { ApplicationConfig, ErrorHandler } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { AppErrorHandler } from './utils/apis/model/error-handler';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    { provide: ErrorHandler, useClass: AppErrorHandler }
  ],
};
