import { Component, OnInit } from '@angular/core';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { ProdutoResponse } from 'src/app/core/models/produto-response.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-produto',
  templateUrl: './lista.component.html',
  styleUrls: ['./lista.component.scss']
})
export class ListaComponent  implements OnInit {
  produtos: ProdutoResponse[] = [];
  mensagemSucesso: string | null = null;

  constructor(
    private produtoService: ProdutoService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carregarProdutos();
  }

  carregarProdutos(): void {
    this.produtoService.listar().subscribe({
      next: (res) => {
        this.produtos = res.content;
      },
      error: (err) => console.error('Erro ao carregar produtos', err)
    });
  }

  editar(id: number): void {
    this.router.navigate(['/produtos/editar', id]);
  }

  deletar(id: number): void {
    const confirmacao = confirm('Deseja realmente remover este produto?');
    if (confirmacao) {
      this.produtoService.deletar(id).subscribe({
        next: () => {
          this.mensagemSucesso = 'Produto removido com sucesso!';
          this.carregarProdutos();

          setTimeout(() => this.mensagemSucesso = null, 3000); 
        },
        error: (err) => console.error('Erro ao remover produto', err)
      });
    }
  }
}
