import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfilExpertForResponsableRoutingModule } from './profil-expert-for-responsable-routing.module';
import { FeatherIconModule } from 'src/app/shared/module/feather.module';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTooltipModule } from '@angular/material/tooltip';
import { StudentHeaderComponent } from 'src/app/layouts/student-header/student-header.component';

import { SkillsDialogComponent } from '../student-profile/skills-dialog/skills-dialog.component';
import { EducationDialogComponent } from '../student-profile/education-dialog/education-dialog.component';
import { ExperienceDialogComponent } from '../student-profile/experience-dialog/experience-dialog.component';
import { EducationsPopupComponent } from '../student-profile/educations-popup/educations-popup.component';
import { SKillsDialogRespComponent } from './skills-dialog-resp/skills-dialog-resp.component';
import { EducationDialogRespComponent } from './education-dialog-resp/education-dialog-resp.component';
import { ExperienceDialogRespComponent } from './experience-dialog-resp/experience-dialog-resp.component';
import { EducationPopupRespComponent } from './education-popup-resp/education-popup-resp.component';
import { RouterModule } from '@angular/router';
import { ProfilExpertForResponsableComponent } from './profil-expert-for-responsable/profil-expert-for-responsable.component';


@NgModule({
  declarations: [ SKillsDialogRespComponent, EducationDialogRespComponent, ExperienceDialogRespComponent, EducationPopupRespComponent
    ,ProfilExpertForResponsableComponent
  ],
  imports: [
    CommonModule,
    ProfilExpertForResponsableRoutingModule,
    FeatherIconModule,
    MatIconModule,
    MatDialogModule,
    FormsModule,
    ReactiveFormsModule,
    MatTooltipModule,
    StudentHeaderComponent,
    RouterModule 
  ]
})
export class ProfilExpertForResponsableModule { }
