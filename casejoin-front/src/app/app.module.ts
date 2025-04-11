import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';

import { ProdutoModule } from './features/produto/produto.module';
import { CategoriaModule } from './features/categoria/categoria.module';
import { PrimengModule } from './shared/primeng.module';
import { MessageService } from 'primeng/api';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [ AppComponent ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule, 
    ProdutoModule,
    CategoriaModule,
    PrimengModule
  ],
  providers: [ MessageService ],
  bootstrap: [AppComponent]
})
export class AppModule {}
