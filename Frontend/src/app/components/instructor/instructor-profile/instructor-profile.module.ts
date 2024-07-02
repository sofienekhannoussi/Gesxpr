import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorProfileRoutingModule } from './instructor-profile-routing.module';
import { InstructorProfileComponent } from './instructor-profile.component';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';


@NgModule({
  declarations: [
    InstructorProfileComponent
  ],
  imports: [
    CommonModule,
    InstructorProfileRoutingModule,
    FeatherIconModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatTooltipModule
  ]
})
export class InstructorProfileModule { }
