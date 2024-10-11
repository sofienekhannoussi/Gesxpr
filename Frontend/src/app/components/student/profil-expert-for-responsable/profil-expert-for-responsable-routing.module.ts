import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfilExpertForResponsableComponent } from './profil-expert-for-responsable/profil-expert-for-responsable.component';

const routes: Routes = [
  {path:'', component:ProfilExpertForResponsableComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfilExpertForResponsableRoutingModule { }
