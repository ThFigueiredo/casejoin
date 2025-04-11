import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'produtos', pathMatch: 'full' },
  {
    path: 'produtos',
    loadChildren: () =>
      import('./features/produto/produto.module').then(m => m.ProdutoModule)
  },
  {
    path: 'categorias',
    loadChildren: () =>
      import('./features/categoria/categoria.module').then(m => m.CategoriaModule)
  }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
