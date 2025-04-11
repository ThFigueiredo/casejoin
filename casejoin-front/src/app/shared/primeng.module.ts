import { NgModule } from '@angular/core';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { ToastModule } from 'primeng/toast';
import { MessageModule } from 'primeng/message';

@NgModule({
  exports: [
    TableModule,
    ButtonModule,
    InputTextModule,
    DropdownModule,
    ToastModule,
    MessageModule
  ]
})
export class PrimengModule {}
