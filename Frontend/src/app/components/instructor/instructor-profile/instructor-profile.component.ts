import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Competence } from 'src/app/modelSTG/competence';
import { Diplome } from 'src/app/modelSTG/diplome';
import { Expertbyid } from 'src/app/modelSTG/expertbyid';
import { ProjetRealise } from 'src/app/modelSTG/projet-realise';
import { CompetenceService } from 'src/app/servicesSTG/competence.service';
import { DiplomesService } from 'src/app/servicesSTG/diplomes.service';
import { ProfilExpertService } from 'src/app/servicesSTG/profil-expert.service';
import { ProjetRealiseService } from 'src/app/servicesSTG/projet-realise.service';
import { DataService } from 'src/app/shared/service/data/data.service';
import { routes } from 'src/app/shared/service/routes/routes';
import { EducationsPopupComponent } from '../../student/student-profile/educations-popup/educations-popup.component';
import { ExperienceDialogComponent } from '../../student/student-profile/experience-dialog/experience-dialog.component';
import { SkillsDialogComponent } from '../../student/student-profile/skills-dialog/skills-dialog.component';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.scss']
})
export class InstructorProfileComponent implements OnInit {
/* public routes = routes;
[x: string]: any;
  public instructorProfile: any = [];
  public instructorProfileEducation: any = [];
  public instructorProfileExperience: any = [];
  public instructorProfileCourses: any = [];
  public instructorProfileReviews: any = [];
  public instructorProfileOverview: any = [];
  public instructorProfileContactDetails: any = [];

   constructor(private DataService: DataService) {
    this.instructorProfile = this.DataService.instructorProfile;
    this.instructorProfileEducation = this.DataService.instructorProfileEducation;
    this.instructorProfileExperience = this.DataService.instructorProfileExperience;
    this.instructorProfileCourses = this.DataService.instructorProfileCourses;
    this.instructorProfileReviews = this.DataService.instructorProfileReviews;
    this.instructorProfileOverview = this.DataService.instructorProfileOverview;
    this.instructorProfileContactDetails = this.DataService.instructorProfileContactDetails;
   }

  ngOnInit(): void {
  }
  toggleClass(data: any) {
    data.active = !data.active;
  }
}
*/

public studentProfileEducation: Diplome [] = [];
public exp:Expertbyid = new Expertbyid();
  public studentProfileExperience: ProjetRealise[] = [];
  public studentProfileCourses: any = [];
  public studentProfileReviews: any = [];
  public routes = routes;
  public studentProfileContactDetails: any = [];
  skills: Competence[] = [];
  constructor(private DataService: DataService,private  profilexpert : ProfilExpertService ,private competenceservice : CompetenceService, private diplomeservice : DiplomesService, private projetservice : ProjetRealiseService, private dialog: MatDialog) {

   // this.studentProfileEducation = this.DataService.studentProfileEducation;
   // this.studentProfileExperience = this.DataService.studentProfileExperience;
    this.studentProfileCourses = this.DataService.studentProfileCourses;
    this.studentProfileReviews = this.DataService.studentProfileReviews;
    this.studentProfileContactDetails =
      this.DataService.studentProfileContactDetails;
  }

  ngOnInit(): void {
   // const id = Number(localStorage.getItem('userId'));

this.getbyisExpert(2)
    this.getlistSkills(2)
    this.getlistDiplome(2)
    this.getlistProjet(2)

  }
  //Education




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
