import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'saleId',
  standalone: true
})
export class SaleIdPipe implements PipeTransform {

  transform(value: {saleDate: string, saleSeq: number}): string {
    let arr = value.saleDate.split('-')
    return `#${arr[0]}${arr[1]}${arr[2]}-${value.saleSeq}`
  }

}
