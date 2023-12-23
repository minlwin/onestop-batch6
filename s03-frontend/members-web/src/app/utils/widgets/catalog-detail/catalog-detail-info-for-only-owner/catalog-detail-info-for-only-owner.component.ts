import { CommonModule, DatePipe } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-catalog-detail-info-for-only-owner',
  standalone: true,
  imports: [CommonModule, DatePipe],
  templateUrl: './catalog-detail-info-for-only-owner.component.html'
})
export class CatalogDetailInfoForOnlyOwnerComponent {

  @Input()
  catalog: any

}
