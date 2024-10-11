import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorRoutingModule } from './instructor-routing.module';
import { InstructorComponent } from './instructor.component';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { SharedModule } from 'src/app/shared/module/shared.module';
import { HeaderResponsableComponent } from 'src/app/layouts/header-responsable/header-responsable.component';


@NgModule({
  declarations: [
    InstructorComponent,
    HeaderResponsableComponent
  ],
  imports: [
    CommonModule,
    InstructorRoutingModule,
    FeatherIconModule,
    SharedModule
  ]
})
export class InstructorModule { }
