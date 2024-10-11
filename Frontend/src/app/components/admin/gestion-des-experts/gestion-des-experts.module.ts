import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GestionDesExpertsRoutingModule } from './gestion-des-experts-routing.module';
import { GestionDesExpertsComponent } from './gestion-des-experts/gestion-des-experts.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    GestionDesExpertsComponent
  ],
  imports: [
    CommonModule,
    GestionDesExpertsRoutingModule,
    NgxPaginationModule,
    ReactiveFormsModule
  ]
})
export class GestionDesExpertsModule { }
