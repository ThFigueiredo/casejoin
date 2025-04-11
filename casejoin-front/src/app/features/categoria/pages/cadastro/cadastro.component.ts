import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoriaService } from 'src/app/shared/services/categoria.service';
import { CategoriaRequest } from 'src/app/core/models/categoria-request.model';
import { MessageHandlerService } from 'src/app/shared/services/message-handler.service';

@Component({
  selector: 'app-cadastro-categoria',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {
  nome: string = '';
  idCategoria?: number;
  isEdicao = false;

  constructor(
    private categoriaService: CategoriaService,
    private router: Router,
    private route: ActivatedRoute,
    private messageHandler: MessageHandlerService
  ) {}

  ngOnInit(): void {
    const paramId = this.route.snapshot.paramMap.get('id');
    if (paramId) {
      this.isEdicao = true;
      this.idCategoria = +paramId;
      this.categoriaService.buscarPorId(this.idCategoria).subscribe({
        next: (categoria) => this.nome = categoria.nome,
        error: () => {
          this.messageHandler.erro('Categoria não encontrada.');
          this.router.navigate(['/categorias']);
        }
      });
    }
  }

  salvar(): void {
    const categoria: CategoriaRequest = { nome: this.nome };

    if (this.isEdicao && this.idCategoria) {
      this.categoriaService.atualizar(this.idCategoria, categoria).subscribe({
        next: () => {
          this.messageHandler.sucesso('Categoria atualizada com sucesso!');
          this.router.navigate(['/categorias']);
        },
        error: (err) => {
          this.messageHandler.erro('Erro ao atualizar categoria');
          console.error(err);
        }
      });
    } else {
      this.categoriaService.criar(categoria).subscribe({
        next: () => {
          this.messageHandler.sucesso('Categoria cadastrada com sucesso!');
          this.router.navigate(['/categorias']);
        },
        error: (err) => {
          this.messageHandler.erro('Erro ao cadastrar categoria');
          console.error(err);
        }
      });
    }
  }
}
