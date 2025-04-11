import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';

@Injectable({
  providedIn: 'root'
})
export class MessageHandlerService {

  constructor(private messageService: MessageService) {}

  sucesso(resumo: string, detalhe?: string) {
    this.messageService.add({
      severity: 'success',
      summary: resumo,
      detail: detalhe || '',
      life: 3000
    });
  }

  erro(resumo: string, detalhe?: string) {
    this.messageService.add({
      severity: 'error',
      summary: resumo,
      detail: detalhe || '',
      life: 3000
    });
  }

  info(resumo: string, detalhe?: string) {
    this.messageService.add({
      severity: 'info',
      summary: resumo,
      detail: detalhe || '',
      life: 3000
    });
  }
}
