import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 

import { CategoriaRoutingModule } from './categoria-routing.module';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { ListaComponent } from '../categoria/pages/lista/lista.component';
import { PrimengModule } from 'src/app/shared/primeng.module';

@NgModule({
  declarations: [ListaComponent, CadastroComponent],
  imports: [
    CommonModule,
    FormsModule, 
    CategoriaRoutingModule,
    PrimengModule
  ]
})
export class CategoriaModule {}
