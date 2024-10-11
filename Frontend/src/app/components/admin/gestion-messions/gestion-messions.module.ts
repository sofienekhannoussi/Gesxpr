import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionMessionsRoutingModule } from './gestion-messions-routing.module';
import { GestionMessionsComponent } from './gestion-messions/gestion-messions.component';


@NgModule({
  declarations: [
    GestionMessionsComponent
  ],
  imports: [
    CommonModule,
    GestionMessionsRoutingModule
  ]
})
export class GestionMessionsModule { }
