import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProdutoRequest } from 'src/app/core/models/produto-request.model';
import { ProdutoResponse } from 'src/app/core/models/produto-response.model';
import { PagedResponse } from '../../core/models/paged-response.model';
import { environment } from 'src/app/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  private readonly API = `${environment.apiBaseUrl}/produtos`;

  constructor(private http: HttpClient) {}

  listar(page: number = 0, size: number = 10): Observable<PagedResponse<ProdutoResponse>> {
    return this.http.get<PagedResponse<ProdutoResponse>>(`${this.API}?page=${page}&size=${size}`);
  }

  criar(produto: ProdutoRequest): Observable<ProdutoResponse> {
    return this.http.post<ProdutoResponse>(this.API, produto);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }

  atualizar(id: number, produto: ProdutoRequest): Observable<ProdutoResponse> {
    return this.http.put<ProdutoResponse>(`${this.API}/${id}`, produto);
  }

  buscarPorId(id: number): Observable<ProdutoResponse> {
    return this.http.get<ProdutoResponse>(`${this.API}/${id}`);
  }
}
