import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../apis/model/sample-data';

@Pipe({
  name: 'categoryName',
  standalone: true
})
export class CategoryNamePipe implements PipeTransform {

  transform(value: Category[]) {
    if(value.length > 1) {
      let result = ''
      for (let index = 0; index < value.length; index++) {
        result += value[index].name
        if(index != value.length - 1) {
          result += ", "
        }
      }

      return result
    }
    return value[0].name;
  }

}
