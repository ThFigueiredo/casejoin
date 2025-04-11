import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastroComponent } from './pages/cadastro/cadastro.component';
import { ListaComponent } from './pages/lista/lista.component';

const routes: Routes = [
  { path: '', component: ListaComponent },
  { path: 'cadastro', component: CadastroComponent },
  { path: 'editar/:id', component: CadastroComponent }
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProdutoRoutingModule {}
