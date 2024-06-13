import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionDesExpertsRoutingModule } from './gestion-des-experts-routing.module';
import { GestionDesExpertsComponent } from './gestion-des-experts/gestion-des-experts.component';


@NgModule({
  declarations: [
    GestionDesExpertsComponent
  ],
  imports: [
    CommonModule,
    GestionDesExpertsRoutingModule
  ]
})
export class GestionDesExpertsModule { }
