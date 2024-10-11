import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Competence } from 'src/app/modelSTG/competence';
import { DataService } from 'src/app/shared/service/data/data.service';
import { Diplome } from 'src/app/modelSTG/diplome';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { ProjetRealise } from 'src/app/modelSTG/projet-realise';
import { CompetenceService } from 'src/app/servicesSTG/competence.service';
import { DiplomesService } from 'src/app/servicesSTG/diplomes.service';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { ProjetRealiseService } from 'src/app/servicesSTG/projet-realise.service';

import { EducationsPopupComponent } from '../../student-profile/educations-popup/educations-popup.component';
import { ExperienceDialogComponent } from '../../student-profile/experience-dialog/experience-dialog.component';
import { SkillsDialogComponent } from '../../student-profile/skills-dialog/skills-dialog.component';
import { EducationPopupRespComponent } from '../education-popup-resp/education-popup-resp.component';
import { ExperienceDialogRespComponent } from '../experience-dialog-resp/experience-dialog-resp.component';
import { SKillsDialogRespComponent } from '../skills-dialog-resp/skills-dialog-resp.component';
import { routes } from 'src/app/shared/service/routes/routes';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-profil-expert-for-responsable',
  templateUrl: './profil-expert-for-responsable.component.html',
  styleUrls: ['./profil-expert-for-responsable.component.scss']
})
export class ProfilExpertForResponsableComponent implements OnInit{
  public routes = routes;
  public studentProfileEducation: Diplome [] = [];
  public exp:Expertbyid = new Expertbyid();
    public studentProfileExperience: ProjetRealise[] = [];
    public studentProfileCourses: any = [];
    public studentProfileReviews: any = [];
 
    public studentProfileContactDetails: any = [];
    skills: Competence[] = [];
    constructor(private DataService: DataService,private  profilexpert : ProfilExpertService ,
      private competenceservice : CompetenceService, private diplomeservice : DiplomesService, private projetservice : ProjetRealiseService, 
      private dialog: MatDialog,private route : ActivatedRoute ) {
  
     // this.studentProfileEducation = this.DataService.studentProfileEducation;
     // this.studentProfileExperience = this.DataService.studentProfileExperience;
      this.studentProfileCourses = this.DataService.studentProfileCourses;
      this.studentProfileReviews = this.DataService.studentProfileReviews;
      this.studentProfileContactDetails =
        this.DataService.studentProfileContactDetails;
    }
  
    ngOnInit(): void {
      const id = this.route.snapshot.params['id'];
      this.getbyisExpert(id)
      this.getlistSkills(id)
      this.getlistDiplome(id)
      this.getlistProjet(id)
  
    }
    //Education
    openEducationsDialog() {
  
  
      const dialogRef = this.dialog.open(EducationPopupRespComponent, {
        width: '600px',
        data: this.studentProfileEducation,
      });
  
      dialogRef.afterClosed().subscribe((result) => {
        const id = Number(localStorage.getItem('userId'));
        this.getlistDiplome(id)
      });
    }
  
  
    getlistDiplome(id : number) {
      this.diplomeservice.getListdiplomebyExpert(id).subscribe({
        next: (data) => {
          this.studentProfileEducation=data
         // this.updateEvents()
         console.log(data);
  
        },
        error: console.log,
      });
    }
  
  
    getbyisExpert(id : number) {
      this.profilexpert.finddpetById(id).subscribe({
        next: (data) => {
          this.exp=data
         // this.updateEvents()
         console.log(data);
  
        },
        error: console.log,
      });
    }
  
    //experience
    openExperienceDialog(){
  
      const dialogRef = this.dialog.open(ExperienceDialogRespComponent, {
        width: '600px',
        data: this.studentProfileExperience,
      });
  
      dialogRef.afterClosed().subscribe((result: any) => {
        console.log('The dialog was closed');
        console.log(this.studentProfileExperience);
        // call api for update education and the arg is studentProfileExperience
      });
    }
  
  
    getlistProjet(id : number) {
      this.projetservice.getListprojetbyExpert(id).subscribe({
        next: (data) => {
          this.studentProfileExperience=data
         // this.updateEvents()
         console.log(data);
  
        },
        error: console.log,
      });
    }
    //skills
    openSkillsDialog(): void {
      console.log("sss",this.skills);
  
      const dialogRef = this.dialog.open(SKillsDialogRespComponent, {
        width: '400px',
        data: this.skills,
      });
  
      dialogRef.afterClosed().subscribe((result: any) => {
        console.log('The dialog was closed');
        console.log(this.skills);
        // call api for update skills and the arg is skills
      });
  
  
  
    }
  
  
    getlistSkills(id : number) {
      this.competenceservice.getListcompetencebyExpert(id).subscribe({
        next: (data) => {
          this.skills=data
         // this.updateEvents()
         console.log(data);
  
        },
        error: console.log,
      });
    }

}
