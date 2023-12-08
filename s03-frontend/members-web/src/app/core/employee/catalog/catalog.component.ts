import { Component } from '@angular/core';
import { CatalogSearch } from '../../../utils/apis/model/sample-data';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-catalog',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './catalog.component.html'
})
export class CatalogComponent {

  params: CatalogSearch = {
    id: 0,
    keyword: ''
  }

  search() {}

}
