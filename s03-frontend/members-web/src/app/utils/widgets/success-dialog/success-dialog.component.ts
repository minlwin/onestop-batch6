import { AfterViewInit, Component, Input } from '@angular/core';

declare const bootstrap: any

@Component({
  selector: 'app-success-dialog',
  standalone: true,
  imports: [],
  templateUrl: './success-dialog.component.html'
})
export class SuccessDialogComponent implements AfterViewInit {

  dialog: any

  @Input()
  modalId: any

  @Input()
  modalTitle: any

  @Input()
  message: any

  ngAfterViewInit() {
    this.dialog = new bootstrap.Modal(`#${this.modalId}`)
  }

  openDialog() {
    this.dialog.show()
  }

  hideDialog() {
    this.dialog.hide()
  }

}
