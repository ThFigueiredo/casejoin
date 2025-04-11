import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProdutoRoutingModule } from './produto-routing.module';
import { ListaComponent } from './pages/lista/lista.component';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { ProdutoCardComponent } from './components/produto-card/produto-card.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PrimengModule } from 'src/app/shared/primeng.module';

@NgModule({
  declarations: [
    ListaComponent,
    CadastroComponent,
    ProdutoCardComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    PrimengModule
  ]
})
export class ProdutoModule {}
