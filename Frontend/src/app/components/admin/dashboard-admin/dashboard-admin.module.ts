import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardAdminRoutingModule } from './dashboard-admin-routing.module';
import { DashboardAdminComponent } from './dashboard-admin/dashboard-admin.component';


@NgModule({
  declarations: [
    DashboardAdminComponent
  ],
  imports: [
    CommonModule,
    DashboardAdminRoutingModule
  ]
})
export class DashboardAdminModule { }
