import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionContratComponent } from './gestion-contrat/gestion-contrat.component';

const routes: Routes = [{path:'', component:GestionContratComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionContratRoutingModule { }
