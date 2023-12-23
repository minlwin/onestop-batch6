import { CommonModule, formatDate } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NoDataComponent } from '../../../utils/widgets/no-data/no-data.component';
import { GoldPriceFormComponent } from './gold-price-form/gold-price-form.component';
import { OwnerGoldPriceService } from '../../../utils/apis/services/owner-gold-price.service';
import { PRICE_STATUS } from '../../../utils/apis/model/api-constant';

@Component({
  selector: 'app-gold-price-setting',
  standalone: true,
  imports: [CommonModule, NoDataComponent, GoldPriceFormComponent],
  templateUrl: './gold-price-setting.component.html'
})
export class GoldPriceSettingComponent implements OnInit {

  params = {
    from: '',
    to: '',
    status: ''
  }

  status = PRICE_STATUS
  goldPriceDtos: any[] = []

  @ViewChild(GoldPriceFormComponent)
  priceForm!: GoldPriceFormComponent

  constructor(private ownerGoldPriceService: OwnerGoldPriceService) {}

  ngOnInit(): void {
    this.search()
  }

  search() {
    this.ownerGoldPriceService.search(this.params).subscribe(resp => this.goldPriceDtos = resp.payload.content)
  }

  openPriceForm() {
    this.priceForm.openPriceForm()
  }

  save(data: any) {
    data.businessTime = formatDate(data.businessTime, 'yyyy-MM-dd HH:mm:ss', 'en_US')
    this.ownerGoldPriceService.save(data).subscribe(resp => {
      if(resp)
        this.priceForm.closePriceForm()
    })
  }

}
