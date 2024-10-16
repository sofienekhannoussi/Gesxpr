import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionContratRoutingModule } from './gestion-contrat-routing.module';
import { GestionContratComponent } from './gestion-contrat/gestion-contrat.component';


@NgModule({
  declarations: [
    GestionContratComponent
  ],
  imports: [
    CommonModule,
    GestionContratRoutingModule
  ]
})
export class GestionContratModule { }
