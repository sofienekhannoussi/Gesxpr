import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin/admin.component';
import { AdminHeaderComponent } from 'src/app/layouts/admin-header/admin-header.component';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { SharedModule } from 'src/app/shared/module/shared.module';


@NgModule({
  declarations: [
    AdminComponent,
    AdminHeaderComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
    FeatherIconModule,
    SharedModule
  ]
})
export class AdminModule { }


 