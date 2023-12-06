import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, Input } from '@angular/core';

declare const bootstrap: any

@Component({
  selector: 'app-modal-dialog',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './modal-dialog.component.html'
})
export class ModalDialogComponent implements AfterViewInit {

  dialog: any

  @Input()
  modalTitle!: string

  @Input()
  modalId!: string

  @Input()
  modalIcon!: string

  @Input()
  btnColor!: string

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
