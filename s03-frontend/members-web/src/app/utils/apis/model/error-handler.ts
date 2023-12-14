import { ErrorHandler, Injectable, NgZone } from "@angular/core";
import { ErrorDialogComponent } from "../../widgets/error-dialog/error-dialog.component";

@Injectable()
export class AppErrorHandler implements ErrorHandler {

  errorDialog!: ErrorDialogComponent

  constructor(private zone: NgZone) {}

  handleError(error: any): void {
    this.zone.run(() => this.errorDialog.openDialog(error))
  }

}
