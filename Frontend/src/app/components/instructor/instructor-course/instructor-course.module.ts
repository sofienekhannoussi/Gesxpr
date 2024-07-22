import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InstructorCourseRoutingModule } from './instructor-course-routing.module';
import { InstructorCourseComponent } from './instructor-course.component';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { SharedModule } from 'src/app/shared/module/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgxEditorModule } from 'ngx-editor';



@NgModule({
  declarations: [
    InstructorCourseComponent
  ],
  imports: [
    CommonModule,
    InstructorCourseRoutingModule,
    FeatherIconModule,
    SharedModule,
    NgxEditorModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule

  ],
})
export class InstructorCourseModule { }
