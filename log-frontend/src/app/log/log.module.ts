import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ListarComponent } from './listar';
import { AdicionarComponent } from './adicionar';
import { EditarComponent } from './editar';
import { UploadComponent } from './upload';
import { ExibirComponent } from './exibir/exibir.component';


@NgModule({
  declarations: [
    ListarComponent,
    AdicionarComponent,
    EditarComponent,
    UploadComponent,
    ExibirComponent   
  ],
  imports: [
    CommonModule,
    FormsModule,
  ]
})
export class LogModule { }
