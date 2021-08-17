import { Routes } from '@angular/router';
import { AdicionarComponent } from './adicionar';
import { ListarComponent } from './listar';
import { EditarComponent } from './editar';
import { UploadComponent } from './upload';
import { ExibirComponent } from './exibir';
import { RelatorioComponent } from './relatorio';
 
export const LogRoutes: Routes = [
	{ 
		path: 'list-log', 
		component: ListarComponent 
	},
	{
		path:'add-log',
		component: AdicionarComponent
	},
	{
		path:'edit-log/:id',
		component: EditarComponent
	},
	{
		path: 'details-log/:id',
		component: ExibirComponent
	},
	{
		path:'upload-log',
		component: UploadComponent  
	},
	{
		path:'relatorio-log',
		component: RelatorioComponent  
	}
];