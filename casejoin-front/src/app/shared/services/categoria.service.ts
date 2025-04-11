import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CategoriaRequest } from '../../core/models/categoria-request.model';
import { CategoriaResponse } from '../../core/models/categoria-response.model';
import { environment } from 'src/app/environment/environment';
import { PagedResponse } from '../../core/models/paged-response.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private readonly API = `${environment.apiBaseUrl}/categorias`;

  constructor(private http: HttpClient) {}

  listar(page = 0, size = 10): Observable<PagedResponse<CategoriaResponse>> {
    return this.http.get<PagedResponse<CategoriaResponse>>(`${this.API}?page=${page}&size=${size}`);
  }
  
  criar(categoria: CategoriaRequest): Observable<CategoriaResponse> {
    return this.http.post<CategoriaResponse>(this.API, categoria);
  }

  buscarPorId(id: number): Observable<CategoriaResponse> {
    return this.http.get<CategoriaResponse>(`${this.API}/${id}`);
  }

  atualizar(id: number, categoria: CategoriaRequest): Observable<CategoriaResponse> {
    return this.http.put<CategoriaResponse>(`${this.API}/${id}`, categoria);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
