import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EmployeeCatalogService } from '../../../../utils/apis/services/employee-catalog.service';
import { CatalogDetailForAdminComponent } from '../../../../utils/widgets/catalog-detail/catalog-detail-for-admin/catalog-detail-for-admin.component';
import { SecurityService } from '../../../../utils/apis/services/security.service';
import { environment } from '../../../../../environments/environment';

@Component({
  selector: 'app-catalog-detail',
  standalone: true,
  imports: [CatalogDetailForAdminComponent],
  templateUrl: './catalog-detail.component.html'
})
export class CatalogDetailComponent implements OnInit {

  catalog: any
  images: any[] = []
  activeUser: any

  constructor(private route: ActivatedRoute,
    private employeeCatalogService: EmployeeCatalogService,
    private securityService: SecurityService) {}

  ngOnInit(): void {
    this.activeUser = this.securityService.activeUser

    this.route.queryParamMap.subscribe(param => {
      let id = Number(param.get('id'))
      if(id) {
        this.employeeCatalogService.findById(id).subscribe(resp => {
          this.catalog = resp.payload.baseInfo
          this.images = resp.payload.images
        })
      }
    })
  }

}
