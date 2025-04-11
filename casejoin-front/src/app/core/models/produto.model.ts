import { Categoria } from './categoria.model';

export interface Produto {
  id?: number;
  nome: string;
  preco: number;
  categoria: Categoria;
}
