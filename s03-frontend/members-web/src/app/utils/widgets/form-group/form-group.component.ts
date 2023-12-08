import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-form-group',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './form-group.component.html'
})
export class FormGroupComponent {

  @Input()
  label!: string

  @Input()
  icon!: string

  @Input()
  margin = false

  @Input()
  valid = false

  @Input()
  dirty = false

  @Input()
  touch = false

}
