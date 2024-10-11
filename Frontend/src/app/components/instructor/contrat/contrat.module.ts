import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContratRoutingModule } from './contrat-routing.module';
import { ContratComponent } from './contrat/contrat.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    ContratComponent
  ],
  imports: [
    CommonModule,
    ContratRoutingModule,
    FormsModule,
  ]
})
export class ContratModule { }
