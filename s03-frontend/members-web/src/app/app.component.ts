import { AfterViewInit, Component, ErrorHandler, Inject, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { ErrorDialogComponent } from './utils/widgets/error-dialog/error-dialog.component';
import { AppErrorHandler } from './utils/apis/model/error-handler';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ErrorDialogComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements AfterViewInit {

  @ViewChild(ErrorDialogComponent)
  errorDialog!: ErrorDialogComponent

  constructor(@Inject(ErrorHandler) private errorHandler: AppErrorHandler) {}

  ngAfterViewInit(): void {
    this.errorHandler.errorDialog = this.errorDialog
  }

}
