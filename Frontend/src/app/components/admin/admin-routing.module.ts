import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [{path:'',component:AdminComponent,

  children: [
    {
      path: 'gestion-des-experts',
      loadChildren: () =>
        import('./gestion-des-experts/gestion-des-experts.module').then(
          (m) => m.GestionDesExpertsModule
        ),
    },
    {
      path: 'gestion-messions',
      loadChildren: () =>
        import('./gestion-messions/gestion-messions.module').then(
          (m) => m.GestionMessionsModule        ),
    },
    {
      path: 'gestion-contrat',
      loadChildren: () =>
        import('./gestion-contrat/gestion-contrat.module').then(
          (m) => m.GestionContratModule     ),
    },
  ]



 }



];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
