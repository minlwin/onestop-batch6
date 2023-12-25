import { Pipe, PipeTransform } from '@angular/core';
import { environment } from '../../../environments/environment';

@Pipe({
  name: 'apiImage',
  standalone: true
})
export class ApiImagePipe implements PipeTransform {

  transform(value: unknown): unknown {
    return `${environment.url}/resources/${value}`;
  }

}
