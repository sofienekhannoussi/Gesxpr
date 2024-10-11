import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CartRoutingModule } from './cart-routing.module';
import { CartComponent } from './cart.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgxEditorModule } from 'ngx-editor';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { SharedModule } from 'src/app/shared/module/shared.module';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


@NgModule({
  declarations: [
    CartComponent
  ],
  imports: [
    CartRoutingModule,
    CommonModule,
    FeatherIconModule,
    SharedModule,
    NgxEditorModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatProgressSpinnerModule

  ]
})
export class CartModule { }
