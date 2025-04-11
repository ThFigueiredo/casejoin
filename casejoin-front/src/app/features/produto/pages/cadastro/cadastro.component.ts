import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdutoService } from 'src/app/shared/services/produto.service';
import { CategoriaService } from 'src/app/shared/services/categoria.service';
import { CategoriaResponse } from 'src/app/core/models/categoria-response.model';
import { ProdutoRequest } from 'src/app/core/models/produto-request.model';
import { MessageHandlerService } from 'src/app/shared/services/message-handler.service';

@Component({
  selector: 'app-cadastro-produto',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {
  formProduto!: FormGroup;
  categorias: CategoriaResponse[] = [];
  isEdicao = false;
  idProduto!: number;

  constructor(
    private fb: FormBuilder,
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService,
    private messageHandler: MessageHandlerService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formProduto = this.fb.group({
      nome: ['', Validators.required],
      preco: [0, Validators.required],
      categoriaId: [null, Validators.required]
    });

    this.carregarCategorias();

    this.idProduto = Number(this.route.snapshot.paramMap.get('id'));
    this.isEdicao = !!this.idProduto;

    if (this.isEdicao) {
      this.produtoService.buscarPorId(this.idProduto).subscribe({
        next: (produto) => this.formProduto.patchValue(produto),
        error: () => this.router.navigate(['/produtos'])
      });
    }
  }

  carregarCategorias(): void {
    this.categoriaService.listar().subscribe({
      next: (res) => this.categorias = res.content,
      error: (err) => console.error('Erro ao carregar categorias', err)
    });
  }

  salvar(): void {
    const produto: ProdutoRequest = this.formProduto.value;

    if (this.isEdicao) {
      this.produtoService.atualizar(this.idProduto, produto).subscribe({
        next: () => {
          this.messageHandler.sucesso('Produto atualizado com sucesso!');
          setTimeout(() => this.router.navigate(['/produtos']), 1500);
        },
        error: (err) => {
          this.messageHandler.erro('Erro ao atualizar produto');
          console.error('Erro ao atualizar produto', err);
        }
      });
    } else {
      this.produtoService.criar(produto).subscribe({
        next: () => {
          this.messageHandler.sucesso('Produto cadastrado com sucesso!');
          this.formProduto.reset();
          setTimeout(() => this.router.navigate(['/produtos']), 1500);
        },
        error: (err) => {
          this.messageHandler.erro('Erro ao cadastrar produto');
          console.error('Erro ao cadastrar produto', err);
        }
      });
    }
  }
}
