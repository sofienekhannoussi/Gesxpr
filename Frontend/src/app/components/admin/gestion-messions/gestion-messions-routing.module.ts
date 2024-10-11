import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GestionMessionsComponent } from './gestion-messions/gestion-messions.component';

const routes: Routes = [{path:'', component:GestionMessionsComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GestionMessionsRoutingModule { }
