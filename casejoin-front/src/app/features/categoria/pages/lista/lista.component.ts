import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoriaService } from 'src/app/shared/services/categoria.service';
import { CategoriaResponse } from 'src/app/core/models/categoria-response.model';

@Component({
  selector: 'app-lista-categoria',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent implements OnInit {
  categorias: CategoriaResponse[] = [];
  total: number = 0;

  constructor(
    private categoriaService: CategoriaService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carregarCategorias();
  }

  carregarCategorias(): void {
    this.categoriaService.listar().subscribe({
      next: (res) => {
        this.categorias = res.content;
        this.total = res.totalElements;
      },
      error: (err) => console.error('Erro ao listar categorias', err)
    });
  }

  editar(id: number): void {
    this.router.navigate(['/categorias/editar', id]);
  }

  deletar(id: number): void {
    const confirmacao = confirm('Tem certeza que deseja remover esta categoria?');
    if (confirmacao) {
      this.categoriaService.deletar(id).subscribe({
        next: () => this.carregarCategorias(),
        error: (err) => console.error('Erro ao deletar categoria', err)
      });
    }
  }
}
