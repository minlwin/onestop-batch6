import { Component, Input } from '@angular/core';
import { Catalog } from '../../apis/model/sample-data';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-second-nav',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './second-nav.component.html'
})
export class SecondNavComponent {

  @Input()
  bgColor!: string

  @Input()
  textColor!: string

  @Input()
  cartItems: Catalog[] = []


}
