import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LogRoutes } from './log';

const routes: Routes = [
  {
  path: '', 
  redirectTo: 'employees',
  pathMatch: 'full' 
},
...LogRoutes
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
