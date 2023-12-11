import { Component, OnInit } from '@angular/core';
import { CatalogDetailWidgetComponent } from '../../../../utils/widgets/catalog-detail-widget/catalog-detail-widget.component';
import { ActivatedRoute } from '@angular/router';
import { EmployeeCatalogService } from '../../../../utils/apis/services/employee-catalog.service';
import { Catalog } from '../../../../utils/apis/model/sample-data';

@Component({
  selector: 'app-catalog-detail',
  standalone: true,
  imports: [CatalogDetailWidgetComponent],
  templateUrl: './catalog-detail.component.html'
})
export class CatalogDetailComponent implements OnInit {

  catalog!: Catalog

  constructor(private route: ActivatedRoute, private employeeCatalogService: EmployeeCatalogService) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(param => {
      let id = + (param.get('id') as string)
      if(id) {
        this.employeeCatalogService.findById(id).subscribe(resp => this.catalog = resp as Catalog)
      }
    })
  }

}
