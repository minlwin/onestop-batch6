import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeCatalogService } from '../../../../utils/apis/services/employee-catalog.service';
import { Catalog } from '../../../../utils/apis/model/sample-data';
import { CatalogDetailForAdminComponent } from '../../../../utils/widgets/catalog-detail/catalog-detail-for-admin/catalog-detail-for-admin.component';
import { SecurityService } from '../../../../utils/apis/services/security.service';

@Component({
  selector: 'app-catalog-detail',
  standalone: true,
  imports: [CatalogDetailForAdminComponent],
  templateUrl: './catalog-detail.component.html'
})
export class CatalogDetailComponent implements OnInit {

  catalog!: Catalog
  activeUser: any

  constructor(private route: ActivatedRoute,
    private employeeCatalogService: EmployeeCatalogService,
    private securityService: SecurityService) {}

  ngOnInit(): void {
    this.activeUser = this.securityService.activeUser

    this.route.queryParamMap.subscribe(param => {
      let id = + (param.get('id') as string)
      if(id) {
        this.employeeCatalogService.findById(id).subscribe(resp => this.catalog = resp as Catalog)
      }
    })
  }

}
