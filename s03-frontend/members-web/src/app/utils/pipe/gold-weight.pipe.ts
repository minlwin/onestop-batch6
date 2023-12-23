import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'goldWeight',
  standalone: true
})
export class GoldWeightPipe implements PipeTransform {

  transform(value: number): any {
    const kyat = (value / 128) | 0
    const pae = ((value % 128) / 8) | 0
    const ywe = ((value % 128) % 8) | 0
    const kyatText = kyat ? `${kyat}K` : ''
    const paeText = pae ? `${pae}P` : ''
    const yweText = ywe ? `${ywe}Y` : ''
    return `${kyatText}${this.getSpace(kyatText)}${paeText}${this.getSpace(paeText)}${yweText}`;
  }

  private getSpace(value: any) {
    return value ? ' ' : ''
  }

}
